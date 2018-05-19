
package Model.DAO;

import java.util.ArrayList;
import Model.Conexao.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Etidade.emprestimoFuncionario;

public class EmprestimoFuncionarioDAO {
    public emprestimoFuncionario selecionarEntidade(int priKey, int priKey1, int priKey2) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        
        try {
            emprestimoFuncionario ef = null;
            String query = "select * from emprestimo_funcionario "
                    + "where cod_funcionario = ? and cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, priKey1);
            ps.setInt(3, priKey2);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ef = new emprestimoFuncionario();
                ef.setCodFuncionario(rs.getInt(1));
                ef.setCodLivro(rs.getInt(rs.getInt(2)));
                ef.setCodEscritor(rs.getInt(3));
                ef.setDataEmprestimo(rs.getString(4));
                ef.setDataEntrega(rs.getString(5));
                ef.setSituacao(rs.getString(6));
            }
            
            ps.close();
            rs.close();
            
            return ef;
        } catch (SQLException e) {
            System.out.println("EmprestimoFuncionario: Erro ao buscar dados. " + e.getMessage());
        }finally{
            pool.freeConnection(conexao);        
        }
        
        return null;
    }
    
    public int adicionarEntidade(emprestimoFuncionario entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int adicionarEmprestimo = 0;
        
        try {
            String query = "insert into emprestimo_funcionario "
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, entidade.getCodFuncionario());
            ps.setInt(2, entidade.getCodLivro());
            ps.setInt(3, entidade.getCodEscritor());
            ps.setString(4, entidade.getDataEmprestimo());
            ps.setString(5, entidade.getDataEntrega()); 
            ps.setString(6, entidade.getSituacao());
            adicionarEmprestimo = ps.executeUpdate();
            
            ps.close();
            
            return adicionarEmprestimo;
        } catch (SQLException e) {
            System.out.println("EmprestimoFuncionario: Erro ao inserir dados. " + e.getMessage());
        }finally{
            pool.freeConnection(conexao);        
        }
        
        return adicionarEmprestimo;
    }
    
    public int atualizarEntidade(emprestimoFuncionario entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        int atualizarEntidade = 0;
        
        try {
            String query = "update emprestimo_funcionario "
                    + "set dt_emprestimo = ?, "
                    + "dt_entrega = ?, "
                    + "situacao = ? "
                    + "where cod_funcionario = ? and "
                    + "cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, entidade.getDataEmprestimo());
            ps.setString(2, entidade.getDataEntrega()); 
            ps.setString(3, entidade.getSituacao());
            ps.setInt(4, entidade.getCodFuncionario());
            ps.setInt(5, entidade.getCodLivro());
            ps.setInt(6, entidade.getCodEscritor());
            atualizarEntidade = ps.executeUpdate();
            
            
            ps.close();
            
            return atualizarEntidade;
        } catch (SQLException e) {
            System.out.println("EmprestimoFunionario: Erro ao atualizar dados. " + e.getMessage());
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
            String query = "update emprestimo_funcionario "
                    + "set situacao = 0 "
                    + "where cod_funcionario = ? and "
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
            String query = "delete from emprestimo_funcionario "
                    + "where cod_funcionario = ? and cod_livro = ? and cod_escritor = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, priKey);
            ps.setInt(2, priKey1);
            ps.setInt(3, priKey2);
            deletarEntidade = ps.executeUpdate();
            
            ps.close();
            
            return deletarEntidade;
        } catch (SQLException e) {
            System.out.println("EmprestimoFuncionario: Erro ao deletar dados. " + e.getMessage());
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
            String query = "delete from emprestimo_funcionario";
            PreparedStatement ps = conexao.prepareStatement(query);
            deletarEntidades = ps.executeUpdate();
            
            ps.close();
            
            return deletarEntidades;
        } catch (SQLException e) {
            System.out.println("EmprestimoFuncionario: Erro ao deletar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return deletarEntidades;
    }
    
    public ArrayList<emprestimoFuncionario> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<emprestimoFuncionario> emp = new ArrayList<>();
                
        try {
            String query = "select cod_funcionario, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega, situacao from emprestimo_funcionario";
            emprestimoFuncionario e = null;
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                e = new emprestimoFuncionario();
                e.setCodFuncionario(rs.getInt(1));
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
            System.out.println("EmprestimoFuncionario: Erro ao selecionar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<emprestimoFuncionario> selecionarTodasEntidades(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<emprestimoFuncionario> emp = new ArrayList<>();
                
        try {
            String query = "select cod_funcionario, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega, situacao from emprestimo_funcionario "
                    + "where cod_funcionario = ?";
            emprestimoFuncionario e = null;
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                e = new emprestimoFuncionario();
                e.setCodFuncionario(rs.getInt(1));
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
            System.out.println("EmprestimoFuncionario: Erro ao selecionar todos os dados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<emprestimoFuncionario> selecionarAbertos(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<emprestimoFuncionario> emp = new ArrayList<>();
        
        try {
            String query = "select cod_funcionario, e.cod_livro, e.cod_escritor, dt_emprestimo, "
                    + "dt_entrega, e.situacao from emprestimo_funcionario e "
                    + "join livro l "
                    + "on e.cod_livro = l.cod_livro and e.cod_escritor = l.cod_escritor "
                    + "where l.situacao = '1' and cod_funcionario = ? and e.situacao = '1'";
            
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                emprestimoFuncionario e = new emprestimoFuncionario();
                e.setCodFuncionario(rs.getInt(1));
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
            System.out.println("EmprestimoFuncionario: Erro ao selecionar abertos. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<emprestimoFuncionario> selecionarFechados(int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<emprestimoFuncionario> emp = new ArrayList<>();
        
        try {
            String query = "select cod_Funcionario, e.cod_livro, e.cod_escritor, dt_emprestimo, "
                    + "dt_entrega, e.situacao from emprestimo_funcionario e "
                    + "join livro l "
                    + "on e.cod_livro = l.cod_livro and e.cod_escritor = l.cod_escritor "
                    + "where e.situacao = '0' and cod_funcionario = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                emprestimoFuncionario e = new emprestimoFuncionario();
                e.setCodFuncionario(rs.getInt(1));
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
            System.out.println("EmprestimoFuncionario: Erro ao selecionar fechados. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
    
    public ArrayList<emprestimoFuncionario> pesquidarData(String d1, String d2 , int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        ArrayList<emprestimoFuncionario> emp = new ArrayList<>();
        
        try {
            String query = "select cod_funcionario, cod_livro, cod_escritor, dt_emprestimo, "
                    + "dt_entrega, situacao from emprestimo_funcionario "
                    + "where dt_emprestimo between ? and ? and ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, d1);
            ps.setString(2, d2);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                emprestimoFuncionario e = new emprestimoFuncionario();
                e.setCodFuncionario(rs.getInt(1));
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
            System.out.println("EmprestimoFuncionario: Erro ao pesquisar data. " + e.getMessage());
        }
        finally{
            pool.freeConnection(conexao);
        }
        
        return null;
    }
}
