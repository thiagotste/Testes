<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/images/icons/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/main.css">        
        <title>Erro 500</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/mainHeader.jspf"%>
        <main style="margin: 4.6em 0;">
            <span class="oi-info"></span>
            <div class="container">
                <h1 class="display-1 text-muted mb-5 text-center">Erro 500</h1>
                <h4>Ops! Erro interno.</h4>
                <h4>Aguarde alguns instantes e tente novamente.</h4>
            </div>            
        </main>
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>