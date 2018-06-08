package com.br.library.sql;
import java.util.ArrayList;

public interface SqlMethodInterface {
    public ArrayList<Object[]> executeQuery(String query, Object[] parameters, String id);
    public int executeUpdate(String query, Object[] parameters, String id);    
}
