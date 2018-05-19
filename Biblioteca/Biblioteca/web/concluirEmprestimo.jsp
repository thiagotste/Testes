<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset=UTF-8>
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/concluirEmprestimo.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <title>Concluir Emprestimo</title>
    </head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="listaMenu.jsp"/>

    <section>
        <p>
            <c:if test="${sessionScope.cliente != null}">
                Parabéns, ${sessionScope.cliente.priNome}, por concluir com sucesso o empréstimo.<br>
            </c:if>
            <c:if test="${sessionScope.fun != null}">
                Parabéns, ${sessionScope.fun.priNome}, por concluir com sucesso o empréstimo.<br>
            </c:if>            
            A data de entrega do livro é dia ${sessionScope.sEntrega}.<br>
            Clique <a href="Download">aqui</a> para fazer download da versão digital.
        </p>
    </section>
    <jsp:include page="footer.jsp"/>
