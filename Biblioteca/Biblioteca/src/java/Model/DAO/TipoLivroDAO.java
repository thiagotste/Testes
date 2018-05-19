package Model.DAO;

import Model.Etidade.TipoLivro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import Model.Conexao.ConnectionPool;
import java.sql.Connection;

public class TipoLivroDAO extends GenericDAO<TipoLivro> {

    @Override
    public TipoLivro selecionarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            TipoLivro t = new TipoLivro();
            String query = "select * from tipo_livro "
                    + "where cod_tipo = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t.setCodTipo(rs.getInt(1));
                t.setNome(rs.getString(2));
            }

            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return t;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dado. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int adicionarEntidade(TipoLivro entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            int linhasAdicionadas = 0;
            String query = "insert into tipo_livro (nome) "
                    + "values(?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getNome());
            
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
    public int atualizarEntidade(TipoLivro entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            int linhasAtualizadas = 0;
            String query = "update tipo_livro "
                    + "set nome = ?,\n"
                    + "where Cod_tipo = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getCodTipo());

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
            String query = "delete from tipo_livro "
                    + "where cod_tipo = ?";
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
            String query = "delete from tipo_livro";
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
    public ArrayList<TipoLivro> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            ArrayList<TipoLivro> Tipos = new ArrayList<>();
            TipoLivro t;
            String query = "select * from tipo_livro";
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t = new TipoLivro();
                t.setCodTipo(rs.getInt(1));
                t.setNome(rs.getString(2));
                
                Tipos.add(t);
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return Tipos;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }
    public boolean isPesquisarNome(String n){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean aux = false;        
        try {
            
            String query = "select nome from tipo_livro "
                    + "where nome = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, n);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                aux = true;
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return aux;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar os dados. " + e.getMessage());
        }
        return aux;
    }
}
