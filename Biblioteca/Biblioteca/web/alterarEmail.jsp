<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset=UTF-8>
        <title>Painel de Controle</title>
        <link rel="stylesheet" href="styles/alterarEmail.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    </head>

    <jsp:include page="listaMenu.jsp"/>
    <jsp:include page="sec1.jsp"/>
    
    <section id="sec2">
        <h1>Aterar E-mail</h1>
        <hr>
        <form action="" method="post">
            <span>*</span><label for="eantigo">Seu e-mail antigo:</label><br>
            <input type="email" name="eantigo" id="eantigo" size="50" required><br>
            <span>*</span><label for="enovo">Digite seu e-mail novo:</label><br>
            <input type="email" name="enovo" id="coneantigo" size="50" required><br>
            <span>*</span><label for="conenovo">Digite novamente seu e-mail novo:</label><br>
            <input type="email" name="conenovo" id="coneantigo" size="50" required><br>                
            <span>*</span><label for="senha">Digite sua senha:</label><br>
            <input type="password" name="senha" id="coneantigo" required><br>

            <input type="submit" value="Prosseguir" id="prosseguir">
        </form>
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>
