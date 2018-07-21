<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.br.library.sql.SqlCommands"%>
<%@page import="com.br.library.sql.SqlMethodInterface"%>
<%
    String email = request.getParameter("email");
    String query = "";
    SqlMethodInterface sql = new SqlCommands();
    List<Object[]> row = new ArrayList<>();

    try {
        query = "SELECT id FROM access.user_temp WHERE email = ?";
        row = sql.executeQuery(query, new Object[]{email}, "Forgot password.jsp");
        if(row.isEmpty()){
            response.sendError(403);
        }
    } catch (Exception e) {
        System.out.println("Forgot password.jsp: " + e.getMessage());
        response.sendError(500);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="userAngular">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/images/icons/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/main.css">
        <title>Redefinir Senha</title>
    </head>
    <script>
        var email = "${param.email}";
        var ctx = "<%= request.getContextPath() %>";
    </script>
    <body ng-controller="userRedefinePassController">
        <%@include file="../../WEB-INF/jspf/mainHeader.jspf"%>
        <main>
            <div class="container  my-5">
                <h1>Redefinir Senha</h1>
                <form name="forgotForm" ng-submit="forgotPass(forgot)" autocomplete="off">
                    <div class="form-group">
                        <label for="nPassword">Nova Senha:</label><span style="color: red;">*</span>
                        <input type="password" id="nPassword" name="npassword" ng-model="forgot.nPassword" ng-change="testPass(forgot)" class="form-control w-25" placeholder="Digite a nova senha." required>
                        <small class="text-muted">
                            <span ng-hide="isMinimum" style="color: red;">A senha deve ter no mínimo 6 caracteres.</span>
                            <span ng-show="isMinimum" ng-cloak>A senha deve ter no mínimo 6 caracteres.</span>
                        </small>
                    </div>                    
                    <div class="form-group">
                        <label for="nConPassword">Confirme a senha:</label><span style="color: red;">*</span>
                        <input type="password" id="nConPassword" name="nConPassword" ng-model="forgot.nConPassword" ng-change="testConPass(forgot)" class="form-control w-25" placeholder="Confirme a senha." required>
                        <small class="text-muted">
                            <span ng-hide="isMatch" style="color: red;">O campo deve ter o mesmo valor da senha.</span>
                            <span ng-show="isMatch">O campo deve ter o mesmo valor da senha.</span>
                        </small>
                    </div>
                    <p style="color: red;">* Campo obrigatório.</p>
                    <div ng-show="loadingPost" ng-cloak><img style="width:10%; margin-bottom: 5%;" src="<%= request.getContextPath()%>/res/images/icons/15.gif" alt=""/></div>
                    <div ng-hide="loadingPost">
                        <button type="submit" class="btn btn-primary" ng-disabled="forgotForm.$invalid">Enviar</button>
                    </div>
                </form>
            </div>
        </main>        
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/angular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/userRestrict/userAngular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="../../WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>
