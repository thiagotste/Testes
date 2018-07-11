package com.br.library.sql;
import java.util.ArrayList;

public interface SqlMethodInterface {
    ArrayList<Object[]> executeQuery(String query, Object[] parameters, String id);
    int executeUpdate(String query, Object[] parameters, String id);
    int executeUpdate(String query);
}
