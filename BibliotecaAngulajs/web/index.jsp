<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/imagens/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/res/css/main.css">
        <title>Biblioteca</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/mainHeader.jspf"%>
        <section class="d-block">
            <div class="container">
                <h1>Hello World!</h1>
            </div>
        </section>        
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>
