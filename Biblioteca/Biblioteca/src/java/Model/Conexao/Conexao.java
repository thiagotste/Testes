package Model.Conexao;

import com.mysql.jdbc.Connection;
import java.sql.*;

public class Conexao {

    private static Connection conexao;
    private static Conexao con;

    public Conexao() {

    }

    public static Conexao getInstance() {
        if (con == null) {
            con = new Conexao();
        }
        return con;
    }

    public Connection getConexao() {
        if (conexao == null) {
            try {
                String dbURL = "jdbc:mysql://localhost:3306/db_biblioteca";
                String username = "root";
                String password = "thiago";   
                conexao = (Connection) DriverManager.
                        getConnection(dbURL, username, password);
            } catch (SQLException e) {
                for (Throwable t : e) {
                    t.printStackTrace();
                }
                System.out.println(e.getMessage());
            } catch (NullPointerException n) {
                System.out.println(n.getMessage());
            }
        }
        return conexao;
    }

}
