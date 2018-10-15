package Model.DAO;

import Model.Etidade.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import Model.Conexao.ConnectionPool;
import java.sql.Connection;

public class LivroDAO extends GenericDAO<Livro> {

    public Livro selecionarEntidade(int priKey, int key) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            Livro l = new Livro();
            String query = "select * from livro "
                    + "where cod_livro = ? and "
                    + "cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l.setCodLivro(rs.getInt(1));
                l.setCodEscritor(rs.getInt(2));
                l.setCodTipo(rs.getInt(3));
                l.setCodBiblioteca(rs.getInt(4));
                l.setNome(rs.getString(5));
                l.setEditora(rs.getString(6));
                l.setVolume(rs.getString(7));
                l.setEdicao(rs.getString(8));
                l.setIdioma(rs.getString(9));
                l.setNumPag(rs.getString(10));
                l.setAnoLancamento(rs.getString(11));
                l.setDtAquisicao(rs.getString(12));
                l.setDescricao(rs.getString(13));
                l.setFormato(rs.getString(14));
                l.setSituacao(rs.getString(15));
            }

            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return l;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao buscar dado. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int adicionarEntidade(Livro entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        try {
            int linhasAdicionadas = 0;
            String query = "insert into livro "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, entidade.getCodLivro());
            ps.setInt(2, entidade.getCodEscritor());
            ps.setInt(3, entidade.getCodTipo());
            ps.setInt(4, entidade.getCodBiblioteca());
            ps.setString(5, entidade.getNome());
            ps.setString(6, entidade.getEditora());
            ps.setString(7, entidade.getVolume());
            ps.setString(8, entidade.getEdicao());
            ps.setString(9, entidade.getIdioma());
            ps.setString(10, entidade.getNumPag());
            ps.setString(11, entidade.getAnoLancamento());
            ps.setString(12, entidade.getDtAquisicao());
            ps.setString(13, entidade.getDescricao());
            ps.setString(14, entidade.getFormato());
            ps.setString(15, entidade.getSituacao());

            linhasAdicionadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasAdicionadas;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao inserir dado. " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int atualizarEntidade(Livro entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        try {
            int linhasAtualizadas = 0;
            String query = "update livro "
                    + "set cod_tipo = ?,\n"
                    + "cod_biblioteca = ?,\n"
                    + "nome = ?,\n"
                    + "editora = ?,\n"
                    + "vol = ?,\n"
                    + "edicao = ?,\n"
                    + "idioma = ?,\n"
                    + "num_pag = ?,\n"
                    + "ano_lancamento = ?,\n"
                    + "dt_aquisicao = ?,\n"
                    + "descricao = ?,\n"
                    + "formato = ?,\n"
                    + "situacao = ?\n"
                    + "where Cod_livro = ? and \n"
                    + "cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, entidade.getCodTipo());
            ps.setInt(2, entidade.getCodBiblioteca());
            ps.setString(3, entidade.getNome());
            ps.setString(4, entidade.getEditora());
            ps.setString(5, entidade.getVolume());
            ps.setString(6, entidade.getEdicao());
            ps.setString(7, entidade.getIdioma());
            ps.setString(8, entidade.getNumPag());
            ps.setString(9, entidade.getAnoLancamento());
            ps.setString(10, entidade.getDtAquisicao());
            ps.setString(11, entidade.getDescricao());
            ps.setString(12, entidade.getFormato());
            ps.setString(13, entidade.getSituacao());
            ps.setInt(14, entidade.getCodLivro());
            ps.setInt(15, entidade.getCodEscritor());
            

            linhasAtualizadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasAtualizadas;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao atualizar dado. " + e.getMessage());
        }

        return 0;
    }
    
    public int atualizarSituacao(String situ, int codLi, int codEsc){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int linhaAtualizada = 0;
        
        try {
            String query = "update livro "
                    + "set situacao = ? "
                    + "where cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, situ);
            ps.setInt(2, codLi);
            ps.setInt(3, codEsc);
            
            linhaAtualizada = ps.executeUpdate();
            
            pool.freeConnection(conexao);
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao atualizar situacao. " + e.getMessage());
        }
        
        return linhaAtualizada;
    }

    public int deletarEntidade(int priKey, int key) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            int linhaDeletada = 0;
            String query = "delete from livro "
                    + "where cod_livro = ? and "
                    + "cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, key);

            linhaDeletada = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhaDeletada;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao deletar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        try {
            int linhasDeletadas = 0;
            String query = "delete from livro";
            PreparedStatement ps = conexao.prepareStatement(query);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(conexao);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao deletar dados. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Livro> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();

        try {
            ArrayList<Livro> livros = new ArrayList<>();
            Livro l;
            String query = "select * from livro l "
                    + "join escritor e "
                    + "on l.Cod_Escritor = e.Cod_Escritor "
                    + "order by Ult_Nome, nome";
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                l = new Livro();
                l.setCodLivro(rs.getInt(1));
                l.setCodEscritor(rs.getInt(2));
                l.setCodTipo(rs.getInt(3));
                l.setCodBiblioteca(rs.getInt(4));
                l.setNome(rs.getString(5));
                l.setEditora(rs.getString(6));
                l.setVolume(rs.getString(7));
                l.setEdicao(rs.getString(8));
                l.setIdioma(rs.getString(9));
                l.setNumPag(rs.getString(10));
                l.setAnoLancamento(rs.getString(11));
                l.setDtAquisicao(rs.getString(12));
                l.setDescricao(rs.getString(13));
                l.setFormato(rs.getString(14));
                l.setSituacao(rs.getString(15));

                livros.add(l);
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();

            return livros;
        } catch (SQLException e) {
            System.out.println("Livro: Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }

    public boolean isPesquisarCodigo(int codLi, int codEsc) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean codigos = false;

        try {
            String query = "select Cod_Livro, Cod_Escritor \n"
                    + "from livro\n"
                    + "where Cod_Livro = ? and Cod_Escritor = ?";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, codLi);
            ps.setInt(2, codEsc);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                codigos = true;
            }
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return codigos;
        } catch (SQLException sqle) {
            System.out.println("Livro: Erro ao pesquisar código. " + sqle.getMessage());
        }

        return codigos;
    }

    @Override
    public Livro selecionarEntidade(int priKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletarEntidade(int priKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
