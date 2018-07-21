<%@page import="java.util.AbstractList"%>
<%@page import="java.util.Locale"%>
<%@page import="com.br.library.utils.DateUtils"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.br.library.sql.SqlMethodInterface"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.br.library.sql.SqlCommands" %>
<%@page import="com.br.library.utils.EfetuaMd5" %>
<%@page contentType="application/json" pageEncoding="UTF-8"%>

<%
    String action = request.getParameter("action");
    String method = request.getMethod();
    if (action == null) {
        action = "";
    }
    EfetuaMd5 md5 = new EfetuaMd5();
    String error = null;
%>
<%
    if (action.equals("tempRegister") && method.equalsIgnoreCase("post")) {
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("password") + "&bis");
        int result = 0;

        SqlCommands sql = new SqlCommands();
        ArrayList<Object[]> temp = new ArrayList<>();
        ArrayList<Object[]> user = new ArrayList<>();
        try {
            String query = "SELECT id FROM access.user_temp WHERE email = ?";
            temp = sql.executeQuery(query, new Object[]{email}, "Select tempRegister");
            if (temp.isEmpty()) {
                query = "INSERT INTO access.user_temp (email, pass) VALUES(?, ?)";
                result = sql.executeUpdate(query, new Object[]{email, password}, "Insert tempRegister");
            } else {
                query = "SELECT id FROM access.user WHERE email = ?";
                user = sql.executeQuery(query, new Object[]{email}, "Select user de tempRegister");
            }
        } catch (Exception e) {
            System.out.println("Temp Register: " + e.getMessage());
        }
        if (temp == null) {
%>
{"re":0}
<%
} else if (temp.isEmpty() && result == 0) {
%>
{"re":1}
<%
} else if (!temp.isEmpty() && user == null) {
%>
{"re":2}
<%
} else if (!temp.isEmpty() && !user.isEmpty()) {
%>
{"re":3}
<%
} else if (!temp.isEmpty() && user.isEmpty()) {
%>
{"re":4}
<%
} else {
%>
{"re":5}
<%
    }
%>
<%
    }
%>
<%
    if (action.equals("userRegister") && method.equalsIgnoreCase("post")) {
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("pass") + "&bis");

        List<Object[]> rows = null;
        SqlMethodInterface sql = new SqlCommands();
        String query = "";
        int result = 0, resultTemp = 0;
        try {
            sql.executeUpdate("BEGIN;");
            query = "SELECT id FROM access.user_temp WHERE email = ? AND pass = ?";
            rows = sql.executeQuery(query, new Object[]{email, password}, "Select user register");
            if (!rows.isEmpty()) {
                String name = request.getParameter("name");
                String birth = request.getParameter("birth");
                String rg = request.getParameter("rg");
                String cpf = request.getParameter("cpf");
                String phone = request.getParameter("phone");
                String zcode = request.getParameter("zcode");
                String address = request.getParameter("address");
                String complement = request.getParameter("complement");
                String neighbor = request.getParameter("neighbor");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                if (complement == null) {
                    complement = "";
                }
                query = "UPDATE access.user_temp SET confirmation_date = NOW() WHERE email = ?";
                resultTemp = sql.executeUpdate(query, new Object[]{email}, "Update temp user");
                if (resultTemp > 0) {
                    query = "INSERT INTO access.user (name, email, pass, rg, cpf, phone, address, birthday, cep, complement, neighbor, city, state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    result = sql.executeUpdate(query, new Object[]{name, email, password, rg, cpf, phone, address, DateUtils.convertToTimestamp(birth), zcode, complement, neighbor, city, state}, "Insert user");
                }
            }
        } catch (Exception e) {
            System.out.println("User register: " + e.getMessage());
            sql.executeUpdate("ROLLBACK;");
        }
        if (rows == null) {
%>
{"re":0}
<%
} else if (rows.isEmpty()) {
    sql.executeUpdate("ROLLBACK;");
%>
{"re":1}
<%
} else if (resultTemp == 0 || result == 0) {
    sql.executeUpdate("ROLLBACK;");
%>
{"re":2}
<%
} else {
    sql.executeUpdate("COMMIT;");
%>
{"re":3}
<%
    }
%>
<%
    }
%>
<%
    if (action.equals("forgotPassword") && method.equalsIgnoreCase("post")) {
        String email = request.getParameter("email");

        String query = "";
        SqlMethodInterface sql = new SqlCommands();
        List<Object[]> tempRow = new ArrayList<>();
        List<Object[]> row = new ArrayList<>();
        try {
            query = "SELECT id FROM access.user_temp WHERE email = ?";
            tempRow = sql.executeQuery(query, new Object[]{email}, "Select temp user from forgot password");
            if (!tempRow.isEmpty()) {
                query = "SELECT id FROM access.user WHERE email = ?";
                row = sql.executeQuery(query, new Object[]{email}, "Select user from forgot password");
            }
        } catch (Exception e) {
            System.out.println("Forgot password: " + e.getMessage());
        }
        if (tempRow == null || row == null) {
%>
{"re":0}
<%
} else if (tempRow.isEmpty()) {
%>
{"re":1}
<%
} else if (row.isEmpty()) {
%>
{"re":2}
<%
} else {
%>
{"re":3}
<%
    }
%>
<%
    }
%>
<%
    if (action.equals("redefinePass") && method.equalsIgnoreCase("post")) {
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("nPassword") + "&bis");

        SqlMethodInterface sql = new SqlCommands();
        List<Object[]> tempRow = null;
        List<Object[]> row = null;
        String query = "";
        int tempResult = 0;
        int result = 0;
        boolean isRedefine = false;
        try {
            sql.executeUpdate("BEGIN;");
            query = "SELECT id FROM access.user_temp WHERE email = ?";
            tempRow = sql.executeQuery(query, new Object[]{email}, "Select temp_user redefinePass");
            if (!tempRow.isEmpty()) {
                query = "UPDATE access.user_temp SET pass = ? WHERE email = ?";
                tempResult = sql.executeUpdate(query, new Object[]{password, email}, "Update temp_user redefinePass");
                query = "SELECT id FROM access.user WHERE email = ?";
                row = sql.executeQuery(query, new Object[]{email}, "Select user redefinePass");
                if (!row.isEmpty()) {
                    query = "UPDATE access.user SET pass = ? WHERE email = ?";
                    result = sql.executeUpdate(query, new Object[]{password, email}, "Update user redefinePass");
                }
            }
            if ((!tempRow.isEmpty() && !row.isEmpty()) && (tempResult > 0 && result > 0)) {
                sql.executeUpdate("COMMIT;");
                isRedefine = true;
            } else if ((!tempRow.isEmpty() && row.isEmpty()) && (tempResult > 0)) {
                sql.executeUpdate("COMMIT;");
                isRedefine = true;
            } else {
                sql.executeUpdate("ROLLBACK;");
                isRedefine = false;
            }
        } catch (Exception e) {
            System.out.println("redefinePass: " + e.getMessage());
            sql.executeUpdate("ROLLBACK;");
        }
        if (tempRow == null || row == null) {
%>
{"re":0}
<%
} else if (isRedefine) {
%>
{"re":1}
<%
} else {
%>
{"re":2}
<%
    }
%>
<%
    }
%>