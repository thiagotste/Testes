package Model.DAO;

import Model.Etidade.Escritor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import Model.Conexao.ConnectionPool;
import java.sql.Connection;

public class EscritorDAO extends GenericDAO<Escritor> {

    @Override
    public Escritor selecionarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            Escritor e = new Escritor();
            String query = "select * from escritor "
                    + "where cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e.setCodEscritor(rs.getInt(1));
                e.setPriNome(rs.getString(2));
                e.setLastNome(rs.getString(3));
            }

            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return e;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dado. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int adicionarEntidade(Escritor entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasAdicionadas = 0;
            String query = "insert into escritor (Pri_Nome, Ult_Nome) "
                    + "values(?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getPriNome());
            ps.setString(2, entidade.getLastNome());

            linhasAdicionadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasAdicionadas;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dado. " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int atualizarEntidade(Escritor entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasAtualizadas = 0;
            String query = "update escritor "
                    + "set Pri_nome = ?,\n"
                    + "Ult_nome = ?\n"
                    + "where Cod_Escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getPriNome());
            ps.setString(2, entidade.getLastNome());
            ps.setInt(3, entidade.getCodEscritor());

            linhasAtualizadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasAtualizadas;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhaDeletada = 0;
            String query = "delete from escritor "
                    + "where cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);

            linhaDeletada = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhaDeletada;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasDeletadas = 0;
            String query = "delete from escritor";
            PreparedStatement ps = conexao.prepareStatement(query);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar dados. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Escritor> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            ArrayList<Escritor> Escritores = new ArrayList<>();
            Escritor e;
            String query = "select * from escritor";
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e = new Escritor();
                e.setCodEscritor(rs.getInt(1));
                e.setPriNome(rs.getString(2));
                e.setLastNome(rs.getString(3));

                Escritores.add(e);
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return Escritores;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }

}
