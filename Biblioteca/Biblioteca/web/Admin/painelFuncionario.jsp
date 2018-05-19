<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Biblioteca Fatec Rubens Lara</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/painelCliente.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="../listaMenu.jsp"/>
    <jsp:include page="../sec1.jsp"/>

    <section id="sec2">
        <h1>Painel de Controle</h1>            
        <hr>
        <p><span class="spsec1">Meus Emprestimos</span><br>
            <span class="spsec2">Acompanhe o andamento e o hist�rico de seus emprestimos.</span>
        </p>
        <nav class="navsec">
            <a class="asec1" href="ItemPainel?controle=0">�ltimos Emprestimos</a>
            <a class="asec2" href="ItemPainel?controle=1">Emprestimos em Aberto</a>
            <a href="ItemPainel?controle=2">Emprestimos Finalizados</a><br>
            <a class="asec3" href="ItemPainel?controle=3">Emprestimos por Data</a>
            <a href="ItemPainel?controle=4">Todos os Emprestimos</a>
        </nav>
        <br>
        <p><span class="spsec1">Meus Dados Cadastrais</span><br>
            <span class="spsec2">Atualize seus dados Cadastrais.</span>
        </p>
        <nav class="navsec">
            <a class="asec4" href="ItemPainel?controle=5">Alterar E-mail</a>
            <a class="asec5" href="ItemPainel?controle=7">Alterar Senha</a>
            <a href="ItemPainel?controle=8">Alterar Dados Cadastrais</a><br>
            <a href="#">Alterar Endere�o</a>
        </nav>
        <br>
        <p><span class="spsec1">�rea Administrativa</span><br>
            <span class="spsec2">Ger�ncia o funcionamento da biblioteca.</span>
        </p>
        <nav class="navsec">
            <a class="asec6" href="ItemPainel?controle=9">Adicionar Classifica��o</a>
            <a class="asec7" href="ItemPainel?controle=10">Adicionar Escritor</a>
            <c:if test="${sessionScope.fun.tipo ==  'administrador'}">
                <a href="ItemPainel?controle=11">Adicionar Biblioteca</a><br>
                <a class="asec8" href="ItemPainel?controle=12">Adicionar Funcionario</a>
            </c:if>
            <a class="asec9" href="ItemPainel?controle=13">Adicionar Cliente</a>
            <c:choose>
                <c:when test="${sessionScope.fun.tipo ==  'administrador'}">
                    <a href="ItemPainel?controle=14">Adicionar Livro</a><br>
                </c:when>
                <c:otherwise>
                    <a class="asec10" href="ItemPainel?controle=14">Adicionar Livro</a>
                </c:otherwise>
            </c:choose>
            <a href="ItemPainel?controle=16">Realizar Entrega</a>
        </nav>
    </section>
    <jsp:include page="../aside.jsp"/>
    <jsp:include page="../footer.jsp"/>