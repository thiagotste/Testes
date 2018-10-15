<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Biblioteca Fatec Rubens Lara</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/alterarCadastro.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    </head>

    <jsp:include page="listaMenu.jsp"/>

    <jsp:include page="sec1.jsp"/>
    <section id="sec2">
        <h1>Alterar Dados Cadastrais</h1>
        <hr>
        <form action="" method="post">
            <span>*</span><label for="nome">Nome:</label><br>
            <input type="text" name="nome" id="nome" required
                   value="${sessionScope.cliente.priNome}"><br>
            <label for="cpf">CPF:</label><br>
            <p>${sessionScope.cliente.cpf}</p>
            <span>*</span><label for="telefone">Telefone:</label><br>
            <input type="text" name="telefone" id="telefone" required
                   value="${sessionScope.cliente.telefone1}"><br>
            <span>*</span><label for="celular">Celular:</label><br>
            <input type="text" name="celular" id="celular" required
                   value="${sessionScope.cliente.telefone2}"><br>
            <input type="submit" value="Prosseguir" id="prosseguir">
        </form>
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>