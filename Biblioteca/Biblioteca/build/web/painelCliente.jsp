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

    <jsp:include page="listaMenu.jsp"/>      
    <jsp:include page="sec1.jsp"/>
    
    <section id="sec2">
        <h1>Painel de Controle</h1>            
        <hr>
        <p><span class="spsec1">Meus Emprestimos</span><br>
            <span class="spsec2">Acompanhe o andamento e o histórico de seus emprestimos.</span>
        </p>
        <nav class="navsec">
            <a class="asec1" href="ItemPainel?controle=0">Últimos Emprestimos</a>
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
            <a href="#">Alterar Endereço</a>
        </nav>
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>