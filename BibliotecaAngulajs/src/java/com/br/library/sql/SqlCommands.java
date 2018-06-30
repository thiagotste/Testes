package com.br.library.sql;

import java.sql.*;
import com.br.library.conection.ConnectionPool;
import java.io.InputStream;
import java.util.ArrayList;

public class SqlCommands implements SqlMethodInterface {

    @Override
    public ArrayList<Object[]> executeQuery(String query, Object[] parameters, String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps;
        ArrayList<Object[]> rows = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getClass() == int.class) {
                        ps.setInt(i + 1, (int) parameters[i]);
                    }
                    if (parameters[i].getClass() == double.class) {
                        ps.setDouble(i + 1, (double) parameters[i]);
                    }
                    if (parameters[i].getClass() == String.class) {
                        ps.setString(i + 1, (String) parameters[i]);
                    }
                    if (parameters[i].getClass() == Boolean.class) {
                        ps.setBoolean(i + 1, (boolean) parameters[i]);
                    }
                    if (parameters[i].getClass() == Timestamp.class) {
                        ps.setTimestamp(i + 1, (Timestamp) parameters[i]);
                    }
                    if (parameters[i].getClass() == InputStream.class) {
                        ps.setBinaryStream(i + 1, (InputStream) parameters[i]);
                    }
                }
            }
            ResultSet rs = ps.executeQuery();
            Object[] row = new Object[rs.getMetaData().getColumnCount()];
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    if (rs.getMetaData().getColumnClassName(i + 1).equals("java.sql.Timestamp")) {
                        row[i] = rs.getTimestamp(i + 1);
                    } else {
                        row[i] = rs.getObject(i + 1);
                    }
                }
                rows.add(row);
            }
            rs.close();
            ps.close();
            return rows;
        } catch (SQLException e) {
            System.out.println(id + ": " + e.getMessage());
            return null;
        } finally {
            pool.freeConnection(conn);
        }
    }

    @Override
    public int executeUpdate(String query, Object[] parameters, String id) {
        int result = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(query);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i].getClass() == int.class) {
                        ps.setInt(i + 1, (int) parameters[i]);
                    }
                    if (parameters[i].getClass() == double.class) {
                        ps.setDouble(i + 1, (double) parameters[i]);
                    }
                    if (parameters[i].getClass() == String.class) {
                        ps.setString(i + 1, (String) parameters[i]);
                    }
                    if (parameters[i].getClass() == Boolean.class) {
                        ps.setBoolean(i + 1, (boolean) parameters[i]);
                    }
                    if (parameters[i].getClass() == Timestamp.class) {
                        ps.setTimestamp(i + 1, (Timestamp) parameters[i]);
                    }
                    if (parameters[i].getClass() == InputStream.class) {
                        ps.setBinaryStream(i + 1, (InputStream) parameters[i]);
                    }
                }
            }
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(id + ": " + e.getMessage());
        } finally {
            pool.freeConnection(conn);
        }
        return result;
    }
}
