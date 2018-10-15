<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta  charset="UTF-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/listarLivros.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/processarImagens.js"></script>
        <title>Listar Livros</title>
    </head>
    
    <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <jsp:include page="listaMenu.jsp"/>

    <section id="sec2">
        <ul class="livro">
            <c:forEach var="livro" items="${sessionScope.livros}">                    
                <li class="${livro.codLivro}">
                    <a href="livro?codLivro=${livro.codLivro}&codEscritor=${livro.codEscritor}">
                        <img src="imagens/Livros/${livro.codLivro}${livro.codEscritor}.jpg" 
                             alt="${livro.nome}" title="${livro.nome}" height="300" width="200">
                    </a>      
                </li>
            </c:forEach>
        </ul>
    </section>        
    <jsp:include page="footer.jsp"/>
</body>
</html>
