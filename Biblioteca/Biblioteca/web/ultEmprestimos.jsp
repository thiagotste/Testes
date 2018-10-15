<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Painel de Controle</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/ultEmprestimos.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>        
    </head>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <jsp:include page="listaMenu.jsp"/>
    <jsp:include page="sec1.jsp"/>
    
    <section id="sec2">
        <c:choose>
            <c:when test="${sessionScope.fudeu  == 0}">
                <h1>Últimos Emprestimos</h1>
            </c:when>
            <c:when test="${sessionScope.fudeu  == 1}">
                <h1>Emprestimos Abertos</h1>
            </c:when>
            <c:when test="${sessionScope.fudeu  == 2}">
                <h1>Emprestimos Finalizados</h1>
            </c:when>
            <c:when test="${sessionScope.fudeu == 3}">
                <h1>Emprestimos por Data</h1>
            </c:when>
            <c:when test="${sessionScope.fudeu  == 4}">
                <h1>Todos Emprestimos</h1>
            </c:when>
        </c:choose>
        <hr>
        <table>
            <thead>
                <tr>
                    <th>
                        Emprestimo
                    </th>
                    <th>
                        Data de Emprestimo
                    </th>
                    <th>
                        Data de Entrega
                    </th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="e" items="${sessionScope.arrayEmprestimo}">
                <tr>
                    <td>
                        ${e.codCliente}-${e.codLivro}
                    </td>
                    <td>
                        ${e.dataEmprestimo}
                    </td>
                    <td>
                        ${e.dataEntrega}
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>            
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>