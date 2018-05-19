<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <header>
        <img src="imagens/books.gif" alt="Logo" height="120" width="100" />
        <hgroup>
            <h1>Biblioteca Fatec Rubens Lara</h1>
            <h2>Explore o mundo do conhecimento</h2>
        </hgroup>
        <c:choose>

            <c:when test="${sessionScope.cliente != null}">
                <p id="login">Bem Vindo, <span>${sessionScope.cliente.priNome}</span><br>
                    Se não for você, <a href="ItemPainel?controle=6">clique aqui</a></p>
                </c:when>
                <c:when test="${sessionScope.fun != null}">
                <p id="login">Bem Vindo, <span>${sessionScope.fun.priNome}</span><br>
                    Se não for você, <a href="ItemPainel?controle=6">clique aqui</a></p>
                </c:when>
                <c:otherwise>
                <p id="login">Bem Vindo, <span>Visitante</span><br>
                    <a href="controCliente.jsp" >Faça login.</a></p>
                </c:otherwise>

        </c:choose>
        <nav>
            <ul id="menu">
                <li><a href="index.jsp">Home</a>                      
                </li>                    
                <li><a href="#">Fazer Emprestimo</a>                        
                </li>
                <li><a href="ListarLivros">Livros</a>
                </li>
                <li><a href="#">Autor</a>
                </li>
                <li><a href="DirecionarPainel">Meus Emprestimos</a>
                </li>
            </ul>
        </nav>
    </header>