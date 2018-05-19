package Model.DAO;

import Model.Etidade.Emprestimo;
import java.util.ArrayList;
import Model.Conexao.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmprestimoDAO{

    public Emprestimo selecionarEntidade(int priKey, int priKey1, int priKey2) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            Emprestimo e = null;
            String query = "select * from emprestimo "
                    + "where cod_cliente = ? and cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, priKey1);
            ps.setInt(3, priKey2);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));                
                e.setCodLivro(rs.getInt(rs.getInt(2)));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
            }
            
            ps.close();
            rs.close();
            
            return e;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao buscar dados. " + e.getMessage());
        }finally{
            pool.freeConnection(conexao);        
        }
        
        return null;
    }
    
    public int adicionarEntidade(Emprestimo entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int adicionarEmprestimo = 0;
        
        try {
            String query = "insert into emprestimo "
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, entidade.getCodCliente());
            ps.setInt(2, entidade.getCodLivro());
            ps.setInt(3, entidade.getCodEscritor());
            ps.setString(4, entidade.getDataEmprestimo());
            ps.setString(5, entidade.getDataEntrega());
            ps.setString(6, entidade.getSituacao());
            adicionarEmprestimo = ps.executeUpdate();
            
            ps.close();
            
            return adicionarEmprestimo;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao inserir dados. " + e.getMessage());
        }finally{
            pool.freeConnection(conexao);        
        }
        
        return adicionarEmprestimo;
    }

    public int atualizarEntidade(Emprestimo entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int atualizarEntidade = 0;
        
        try {
            String query = "update emprestimo "
                    + "set dt_emprestimo = ?, "
                    + "dt_entrega = ?, "
                    + "situacao = ? "
                    + "where cod_cliente = ? and "
                    + "cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getDataEmprestimo());
            ps.setString(2, entidade.getDataEntrega());
            ps.setString(3, entidade.getSituacao());
            ps.setInt(4, entidade.getCodCliente());
            ps.setInt(5, entidade.getCodLivro());
            ps.setInt(6, entidade.getCodEscritor());
            atualizarEntidade = ps.executeUpdate();
            
            
            ps.close();
            
            return atualizarEntidade;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao atualizar dados. " + e.getMessage());
        }finally{
            pool.freeConnection(conexao);        
        }
        
        return atualizarEntidade;
    }
    
    public int atualizarSituacao(int codF, int codL, int codE){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int atualizarSituacao = 0;
        
        try {
            String query = "update emprestimo "
                    + "set situacao = 0 "
                    + "where cod_cliente = ? and "
                    + "cod_livro = ? and cod_escritor = ?";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, codF);
            ps.setInt(2, codL);
            ps.setInt(3, codE);
            atualizarSituacao = ps.executeUpdate();
            
            ps.close();
        } catch (SQLException e) {
            System.out.println("EmprestimoFunionario: Erro ao atualizar situação. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        return atualizarSituacao;
    }

    public int deletarEntidade(int priKey, int priKey1, int priKey2) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int deletarEntidade = 0;
        
        try {
            String query = "delete from emprestimo "
                    + "where cod_cliente = ? and cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, priKey1);
            ps.setInt(3, priKey2);
            deletarEntidade = ps.executeUpdate();
            
            ps.close();
            
            return deletarEntidade;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao deletar dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return deletarEntidade;
    }

    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int deletarEntidades = 0;
        
        try {
            String query = "delete from emprestimo";
            PreparedStatement ps = conexao.prepareStatement(query);
            deletarEntidades = ps.executeUpdate();
            
            ps.close();
            
            return deletarEntidades;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao deletar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return deletarEntidades;
    }

    public ArrayList<Emprestimo> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<Emprestimo> emp = new ArrayList<>();
                
        try {
            String query = "select cod_cliente, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega, situacao from emprestimo";
            Emprestimo e = null;
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));
                e.setCodLivro(rs.getInt(2));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
                
                emp.add(e);
            }
            
            ps.close();
            rs.close();
            
            return emp;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao selecionar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<Emprestimo> selecionarTodasEntidades(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<Emprestimo> emp = new ArrayList<>();
                
        try {
            String query = "select cod_cliente, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega from emprestimo, situacao "
                    + "where cod_cliente = ?";
            Emprestimo e = null;
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));
                e.setCodLivro(rs.getInt(2));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
                
                emp.add(e);
            }
            
            ps.close();
            rs.close();
            
            return emp;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao selecionar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<Emprestimo> selecionarAbertos(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<Emprestimo> emp = new ArrayList<>();
        
        try {
            String query = "select cod_cliente, e.cod_livro, e.cod_escritor, dt_emprestimo, "
                    + "dt_entrega, e.situacao from emprestimo e "
                    + "join livro l "
                    + "on e.cod_livro = l.cod_livro and e.cod_escritor = l.cod_escritor "
                    + "where l.situacao = '1' and e.cod_cliente = ? and e.situacao = '1'";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Emprestimo e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));
                e.setCodLivro(rs.getInt(2));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
                
                emp.add(e);
            }
            
            ps.close();
            rs.close();
            
            return emp;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao selecionar abertos. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<Emprestimo> selecionarFechados(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<Emprestimo> emp = new ArrayList<>();
        
        try {
            String query = "select cod_cliente, e.cod_livro, e.cod_escritor, dt_emprestimo, "
                    + "dt_entrega, situacao from emprestimo e "
                    + "join livro l "
                    + "on e.cod_livro = l.cod_livro  and e.cod_escritor = l.cod_escritor "
                    + "where e.situacao = '0' and cod_cliente = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Emprestimo e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));
                e.setCodLivro(rs.getInt(2));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
                
                emp.add(e);
            }
            
            ps.close();
            rs.close();
            
            return emp;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao selecionar fechados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<Emprestimo> pesquidarData(String d1, String d2 , int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<Emprestimo> emp = new ArrayList<>();
        
        try {
            String query = "select cod_cliente, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega from emprestimo,situacao "
                    + "where dt_emprestimo between ? and ? and ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, d1);
            ps.setString(2, d2);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Emprestimo e = new Emprestimo();
                e.setCodCliente(rs.getInt(1));
                e.setCodLivro(rs.getInt(2));
                e.setCodEscritor(rs.getInt(3));
                e.setDataEmprestimo(rs.getString(4));
                e.setDataEntrega(rs.getString(5));
                e.setSituacao(rs.getString(6));
                
                emp.add(e);
            }
            
            ps.close();
            rs.close();
            
            return emp;
        } catch (SQLException e) {
            System.out.println("Emprestimo: Erro ao pesquisar data. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
}
