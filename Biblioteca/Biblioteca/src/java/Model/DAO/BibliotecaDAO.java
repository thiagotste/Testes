package Model.DAO;

import Model.Etidade.Biblioteca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Conexao.ConnectionPool;
import java.sql.Connection;

public class BibliotecaDAO extends GenericDAO<Biblioteca> {

    @Override
    public Biblioteca selecionarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            String query = "select * from biblioteca where cod_biblioteca = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ResultSet rs = ps.executeQuery();

            Biblioteca b = new Biblioteca();
            if (rs.next()) {
                b.setCodBiblioteca(rs.getInt(1));
                b.setNome(rs.getString(2));
                b.setEndereco(rs.getString(3));
                b.setCep(rs.getString(4));
                b.setCidade(rs.getString(5));
                b.setUf(rs.getString(6));
                b.setTelefone1(rs.getString(7));
                b.setEmail(rs.getString(8));
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return b;
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao buscar dado. " + e.getMessage());
        }
        return null;
    }

    @Override
    public int adicionarEntidade(Biblioteca entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            int linhasInseridas = 0;
            String query = "insert into biblioteca "
                    + "(nome, Endereco, cep, Cidade, UF, Telefone1, email)\n"
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEndereco());
            ps.setString(3, entidade.getCep());
            ps.setString(4, entidade.getCidade());
            ps.setString(5, entidade.getUf());
            ps.setString(6, entidade.getTelefone1());
            ps.setString(7, entidade.getEmail());

            linhasInseridas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao inserir dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int atualizarEntidade(Biblioteca entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasModificadas = 0;
            String query = "update biblioteca\n"
                    + "set nome = ?,\n"
                    + "Endereco = ?,\n"
                    + "CEP = ?,\n"
                    + "Cidade = ?,\n"
                    + "uf = ?,\n"
                    + "Telefone1 = ?,\n"
                    + "email = ?\n"
                    + "where Cod_Bibioteca = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEndereco());
            ps.setString(3, entidade.getCep());
            ps.setString(4, entidade.getCidade());
            ps.setString(5, entidade.getUf());
            ps.setString(6, entidade.getTelefone1());
            ps.setString(7, entidade.getEmail());
            ps.setInt(8, entidade.getCodBiblioteca());
            linhasModificadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasModificadas;
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao atualizar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasDeletadas = 0;
            String query = "delete from biblioteca\n"
                    + "where Cod_Bibioteca = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao deletar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhasDeletadas = 0;
            String query = "delete from biblioteca";
            PreparedStatement ps = conexao.prepareStatement(query);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao deletar dados. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Biblioteca> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            ArrayList<Biblioteca> bibliotecas
                    = new ArrayList<>();
            Biblioteca b;
            String query = "select * from biblioteca";

            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                b = new Biblioteca();
                b.setCodBiblioteca(rs.getInt(1));
                b.setNome(rs.getString(2));
                b.setEndereco(rs.getString(3));
                b.setCep(rs.getString(4));
                b.setCidade(rs.getString(5));
                b.setUf(rs.getString(6));
                b.setTelefone1(rs.getString(7));
                b.setEmail(rs.getString(8));

                bibliotecas.add(b);
            }

            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return bibliotecas;
        } catch (SQLException e) {
            System.out.println("Biblioteca: Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }
    
    public boolean isPequisarCodBiblioteca(int c){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean codBiblioteca = false;
        
        try {
            String query = "select cod_biblioteca from biblioteca "
                    + "where cod_biblioteca = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, c);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                codBiblioteca = true;
            }
            
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return codBiblioteca;
        } catch (SQLException sqle) {
            System.out.println("Funcionario: Error ao pesquisar senha. " + sqle.getMessage());
        }
        return codBiblioteca;
    }

}
