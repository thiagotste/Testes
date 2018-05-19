package Model.DAO;

import Model.Conexao.ConnectionPool;
import Model.Etidade.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;



public class FuncionarioDAO extends GenericDAO<Funcionario>{

    public Funcionario selecionarEntidade(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        try {
            Funcionario f= new Funcionario();
            
            String query = "select * from funcionario "
                    + "where email = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f.setCodFuncionario(rs.getInt(1));
                f.setCodBiblioteca(rs.getInt(2));
                f.setPriNome(rs.getString(3));
                f.setLastNome(rs.getString(4));
                f.setEmail(rs.getString(5));
                f.setSenha(rs.getString(6));
                f.setCpf(rs.getString(7));
                f.setSexo(rs.getString(8));
                f.setDtNascimento(rs.getString(9));
                f.setEndereco(rs.getString(10));
                f.setComplemento(rs.getString(11));
                f.setCep(rs.getString(12));
                f.setCidade(rs.getString(13));
                f.setUf(rs.getString(14));
                f.setTelefone1(rs.getString(15));
                f.setTelefone2(rs.getString(16));
                f.setTipo(rs.getString(17));
            }

            ps.close();
            rs.close();
            pool.freeConnection(connection);
            return f;
        } catch (SQLException e) {
            System.out.println("Funcionário: Erro ao buscar dado. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int adicionarEntidade(Funcionario entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into funcionario (cod_Biblioteca, "
                + "Pri_Nome, Ult_Nome, email, CPF, "
                + "Sexo, Dt_nascimento, Endereco, complemento, CEP, Cidade, UF, "
                + "Telefone1, Telefone2, Senha, tipo)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, entidade.getCodBiblioteca());
            ps.setString(2, entidade.getPriNome());
            ps.setString(3, entidade.getLastNome());
            ps.setString(4, entidade.getEmail());
            ps.setString(5, entidade.getCpf());
            ps.setString(6, entidade.getSexo());
            ps.setString(7, entidade.getDtNascimento());
            ps.setString(8, entidade.getEndereco());
            ps.setString(9, entidade.getComplemento());
            ps.setString(10, entidade.getCep());
            ps.setString(11, entidade.getCidade());
            ps.setString(12, entidade.getUf());
            ps.setString(13, entidade.getTelefone1());
            ps.setString(14, entidade.getTelefone2());
            ps.setString(15, entidade.getSenha());
            ps.setString(16, entidade.getTipo());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Funcionário: Erro ao inserir dado. " + e.getMessage());
            return 0;
        } finally {
            pool.freeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public int atualizarEntidade(Funcionario entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        try {
            int linhasAtualizadas = 0;
            String query = "update funcionario "
                    + "set cod_biblioteca = ?,\n"
                    + "Pri_nome = ?,\n"
                    + "Ult_nome = ?,\n"
                    + "email = ?\n"
                    + "cpf = ?,\n"
                    + "sexo = ?,\n"
                    + "dt_nascimento = ?\n"
                    + "Endereco = ?,\n"
                    + "complemento = ?,\n"
                    + "CEP = ?,\n"
                    + "Cidade = ?,\n"
                    + "uf = ?,\n"
                    + "Telefone1 = ?,\n"
                    + "Telefone2 = ?,\n"
                    + "Senha = ?,\n"
                    + "Tipo = ?\n"
                    + "where Cod_funcionario = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, entidade.getCodBiblioteca());
            ps.setString(2, entidade.getPriNome());
            ps.setString(3, entidade.getLastNome());
            ps.setString(4, entidade.getEmail());
            ps.setString(5, entidade.getCpf());
            ps.setString(6, entidade.getSexo());
            ps.setString(7, entidade.getDtNascimento());
            ps.setString(8, entidade.getEndereco());
            ps.setString(9, entidade.getComplemento());
            ps.setString(10, entidade.getCep());
            ps.setString(11, entidade.getCidade());
            ps.setString(12, entidade.getUf());
            ps.setString(13, entidade.getTelefone1());
            ps.setString(14, entidade.getTelefone2());
            ps.setString(15, entidade.getSenha());
            ps.setString(16, entidade.getTipo());
            ps.setInt(17, entidade.getCodFuncionario());

            linhasAtualizadas = ps.executeUpdate();

            pool.freeConnection(connection);
            ps.close();

            return linhasAtualizadas;
        } catch (SQLException e) {
            System.out.println("Funcionário: Erro ao atualizar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarEntidade(int priKey) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            int linhaDeletada = 0;
            String query = "delete from funcionario "
                    + "where cod_funcionario = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, priKey);

            linhaDeletada = ps.executeUpdate();

            pool.freeConnection(connection);
            ps.close();

            return linhaDeletada;
        } catch (SQLException e) {
            System.out.println("Funcionário: Erro ao deletar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            int linhasDeletadas = 0;
            String query = "delete from funcionario";
            PreparedStatement ps = connection.prepareStatement(query);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(connection);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Funcionário: Erro ao deletar dados. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Funcionario> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            Funcionario f;
            String query = "select * from funcionario";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                f = new Funcionario();
                f.setCodFuncionario(rs.getInt(1));
                f.setCodBiblioteca(rs.getInt(2));
                f.setPriNome(rs.getString(3));
                f.setLastNome(rs.getString(4));
                f.setEmail(rs.getString(5));
                f.setSenha(rs.getString(6));
                f.setCpf(rs.getString(7));
                f.setSexo(rs.getString(8));
                f.setDtNascimento(rs.getString(9));
                f.setEndereco(rs.getString(10));
                f.setComplemento(rs.getString(11));
                f.setCep(rs.getString(12));
                f.setCidade(rs.getString(13));
                f.setUf(rs.getString(14));
                f.setTelefone1(rs.getString(15));
                f.setTelefone2(rs.getString(16));
                f.setTipo(rs.getString(17));

                funcionarios.add(f);
            }
            pool.freeConnection(connection);
            ps.close();
            rs.close();

            return funcionarios;
        } catch (SQLException e) {
            System.out.println("Funcioinário: Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }
    
    public boolean isPesquisarEmail(String e) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        boolean email = false;
        
        try {            
            String query = "select email from funcionario "
                    + "where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                email = true;
            }
            pool.freeConnection(connection);
            ps.close();
            rs.close();
            
            return email;
        } catch (SQLException sqle) {
            System.out.println("Funcionário: Error ao pesquisar email. " + sqle.getMessage());
        }
        return email;
    }
    
    public boolean isPequisarSenha(String s){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean senha = false;
        
        try {
            String query = "select senha from funcionario "
                    + "where senha = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                senha = true;
            }
            
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return senha;
        } catch (SQLException sqle) {
            System.out.println("Funcionario: Error ao pesquisar senha. " + sqle.getMessage());
        }
        return senha;
    }
    public boolean isPequisarCPF(String c){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean cpf = false;
        
        try {
            String query = "select cpf from funcionario "
                    + "where cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, c);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                cpf = true;
            }
            
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return cpf;
        } catch (SQLException sqle) {
            System.out.println("Funcionario: Error ao pesquisar senha. " + sqle.getMessage());
        }
        return cpf;
    }
    
    public String pesquisarTipo(String e){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        String tipoFun = "";
        
        try {
            String quuery = "select tipo from funcionario "
                    + "where email = ?";
            PreparedStatement ps = conexao.prepareStatement(quuery);
            ps.setString(1, e);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tipoFun = rs.getString(1);
            }
            
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return tipoFun;
        } catch (SQLException sqle) {
            System.out.println("Funcionário: Error ao pesquisar código. " + sqle.getMessage());
        }
        
        return tipoFun;
    }

    @Override
    public Funcionario selecionarEntidade(int priKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
