<%@page import="java.util.ArrayList"%>
<%@page import="com.br.library.sql.SqlCommands" %>
<%@page import="com.br.library.utils.EfetuaMd5" %>
<%@page contentType="application/json" pageEncoding="UTF-8"%>

<%
    String action = request.getParameter("action");
    if (action == null) {
        action = "";
    }
    EfetuaMd5 md5 = new EfetuaMd5();
    String error = null;
%>
<%
    if (action.equals("tempRegister")) {
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("password") + "bis");
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