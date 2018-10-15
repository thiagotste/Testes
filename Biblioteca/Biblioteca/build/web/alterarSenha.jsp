<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Biblioteca Fatec Rubens Lara</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/alterarSenha.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>        
    </head>

    <jsp:include page="listaMenu.jsp"/>
    <jsp:include page="sec1.jsp"/>
    
    <section id="sec2">
        <h1>Alterar Senha</h1>
        <hr>
        <form action="" method="post">
            <span>*</span><label for="satual">Digite sua senha atual:</label><br>
            <input type="password" name="satual" id="satual" required><br>
            <span>*</span><label for="snova">Digite sua nova senha:</label><br>
            <input type="password" name="snova" id="snova" required><br>
            <span>*</span><label for="snovamente">Digite sua senha novamente:</label><br>
            <input type="password" name="snovamente" id="snovamente" required><br>
            <input type="submit" value="Prosseguir">
        </form>          
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>