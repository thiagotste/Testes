<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta  charset="UTF-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/livro.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/Livro.js"></script>
        <title>${sessionScope.livro.nome}</title>
    </head>    

    <jsp:include page="listaMenu.jsp"/>

    <section id="sec2">
        <article id="art1">
            <h1>
                ${sessionScope.livro.nome}
            </h1>
            <ul>
                <li>
                    Formato: ${sessionScope.livro.formato}
                </li>
                <li>
                    Número de páginas: ${sessionScope.livro.numPag}
                </li>
                <li>
                    Editora: ${sessionScope.livro.editora}
                </li>
                <li>
                    Idioma: ${sessionScope.livro.idioma}
                </li>                    
            </ul>

            <form action="Livros/Amostras/${sessionScope.livro.codLivro}${sessionScope.livro.codEscritor}.pdf" 
                  method="post" target="_blank" id="amostra">
                <input type="submit" value="Amostra">
            </form>
            <form action="FazerEmprestimos" method="post" id="emprestimo">
                <c:choose>
                    <c:when test="${sessionScope.cliente != null}">
                        <input type="hidden" name="clid" value="${sessionScope.cliente.codCliente}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="funid" value="${sessionScope.fun.codFuncionario}">
                    </c:otherwise>
                </c:choose>

                <input type="hidden" name="liId" value="${sessionScope.livro.codLivro}">
                <input type="hidden" name="escId" value="${sessionScope.livro.codEscritor}">
                <input type="submit" value="Fazer Emprestimo" id="fazer">
            </form>
            <span class="mensagem">
                ${requestScope.mensagem}
            </span>
        </article>
        <figure>
            <img src="imagens/Livros/${sessionScope.livro.codLivro}${sessionScope.livro.codEscritor}.jpg" 
                 alt="${sessionScope.livro.nome}" title="${sessionScope.livro.nome}" 
                 height="350" width="220">
        </figure>
        <article id="art2">
            <h1>Descrição</h1>
            <c:forEach var="descricao" items="${sessionScope.descricao}">
                <p>
                    ${descricao}
                </p>
            </c:forEach>
        </article>
    </section>        
    <jsp:include page="footer.jsp"/>