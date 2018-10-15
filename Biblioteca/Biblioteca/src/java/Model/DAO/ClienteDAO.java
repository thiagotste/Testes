package Model.DAO;

import Model.Conexao.ConnectionPool;
import Model.Etidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ClienteDAO extends GenericDAO<Cliente> {
    
    public Cliente selecionarEntidade(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        try {
            Cliente c = new Cliente();
            String query = "select * from cliente "
                    + "where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setCodCliente(rs.getInt(1));
                c.setPriNome(rs.getString(2));
                c.setLastNome(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setSenha(rs.getString(5));
                c.setCpf(rs.getString(6));
                c.setSexo(rs.getString(7));
                c.setDtNascimento(rs.getString(8));
                c.setEndereco(rs.getString(9));
                c.setComplemento(rs.getString(10));
                c.setCep(rs.getString(11));
                c.setCidade(rs.getString(12));
                c.setUf(rs.getString(13));
                c.setTelefone1(rs.getString(14));
                c.setTelefone2(rs.getString(15));                
            }

            ps.close();
            rs.close();
            pool.freeConnection(connection);
            return c;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dado. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int adicionarEntidade(Cliente entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into cliente (Pri_Nome, Ult_Nome, email, CPF, "
                + "Sexo, Dt_nascimento, Endereco, complemento, CEP, Cidade, UF, "
                + "Telefone1, Telefone2, Senha)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, entidade.getPriNome());
            ps.setString(2, entidade.getLastNome());
            ps.setString(3, entidade.getEmail());
            ps.setString(4, entidade.getCpf());
            ps.setString(5, entidade.getSexo());
            ps.setString(6, entidade.getDtNascimento());
            ps.setString(7, entidade.getEndereco());
            ps.setString(8, entidade.getComplemento());
            ps.setString(9, entidade.getCep());
            ps.setString(10, entidade.getCidade());
            ps.setString(11, entidade.getUf());
            ps.setString(12, entidade.getTelefone1());
            ps.setString(13, entidade.getTelefone2());
            ps.setString(14, entidade.getSenha());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dado. " + e.getMessage());
            return 0;
        } finally {
            pool.freeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }

    }

    @Override
    public int atualizarEntidade(Cliente entidade) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        try {
            int linhasAtualizadas = 0;
            String query = "update cliente "
                    + "set Pri_nome = ?,\n"
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
                    + "Senha = ?\n"
                    + "where Cod_Cliente = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entidade.getPriNome());
            ps.setString(2, entidade.getLastNome());
            ps.setString(3, entidade.getEmail());
            ps.setString(4, entidade.getCpf());
            ps.setString(5, entidade.getSexo());
            ps.setString(6, entidade.getDtNascimento());
            ps.setString(7, entidade.getEndereco());
            ps.setString(8, entidade.getComplemento());
            ps.setString(9, entidade.getCep());
            ps.setString(10, entidade.getCidade());
            ps.setString(11, entidade.getUf());
            ps.setString(12, entidade.getTelefone1());
            ps.setString(13, entidade.getTelefone2());
            ps.setString(14, entidade.getSenha());
            ps.setInt(15, entidade.getCodCliente());

            linhasAtualizadas = ps.executeUpdate();

            pool.freeConnection(connection);
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
        Connection connection = pool.getConnection();
        try {
            int linhaDeletada = 0;
            String query = "delete from cliente "
                    + "where cod_cliente = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, priKey);

            linhaDeletada = ps.executeUpdate();

            pool.freeConnection(connection);
            ps.close();

            return linhaDeletada;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar dado. " + e.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Cliente> selecionarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();
            Cliente c;
            String query = "select * from cliente";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new Cliente();
                c.setCodCliente(rs.getInt(1));
                c.setPriNome(rs.getString(2));
                c.setLastNome(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setCpf(rs.getString(5));
                c.setSexo(rs.getString(6));
                c.setDtNascimento(rs.getString(7));
                c.setEndereco(rs.getString(8));
                c.setComplemento(rs.getString(9));
                c.setCep(rs.getString(10));
                c.setCidade(rs.getString(11));
                c.setUf(rs.getString(12));
                c.setTelefone1(rs.getString(13));
                c.setTelefone2(rs.getString(14));
                c.setSenha(rs.getString(15));

                clientes.add(c);
            }
            pool.freeConnection(connection);
            ps.close();
            rs.close();

            return clientes;
        } catch (SQLException e) {
            System.out.println("Erro ao selecionar todos os dados. " + e.getMessage());
        }

        return null;
    }

    @Override
    public int deletarTodasEntidades() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try {
            int linhasDeletadas = 0;
            String query = "delete from cliente";
            PreparedStatement ps = connection.prepareStatement(query);
            linhasDeletadas = ps.executeUpdate();

            pool.freeConnection(connection);
            ps.close();

            return linhasDeletadas;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar dados. " + e.getMessage());
        }

        return 0;
    }

    public String pesquisarEmail(String e) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection(); 
        
        try {
            String email = "";
            
            String query = "select email from cliente\n"
                    + "where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                email = rs.getString(1);
            }
            pool.freeConnection(connection);
            ps.close();
            rs.close();
            
            return email;
        } catch (SQLException sqle) {
            System.out.println("Error ao pesquisar email. " + sqle.getMessage());
        }
        return null;
    }
    
    public boolean isPequisarSenha(String s){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();
        boolean senha = false;
        
        try {
            String query = "select senha from cliente "
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
            System.out.println("Error ao pesquisar senha. " + sqle.getMessage());
        }
        return senha;
    }
    
    public boolean isPesquisarCpf(String cpf){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conexao = pool.getConnection();        
        boolean reCpf = false;
        
        try {
            String query = "select cpf from cliente " 
                    + "where cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                 reCpf = true;
            }
            
            pool.freeConnection(conexao);
            ps.close();
            rs.close();
            
            return reCpf;
        } catch (SQLException sqle) {
            System.out.println("Error ao pesquisar cpf. " + sqle.getMessage());
        }
        
        return reCpf;
    }

    @Override
    public Cliente selecionarEntidade(int priKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
