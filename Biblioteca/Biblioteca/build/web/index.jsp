<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Biblioteca Fatec Rubens Lara</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    </head>

    <jsp:include page="listaMenu.jsp"/>

    <section>
        <h1>Section</h1>
        <form action="AdicionarTipo" method="get">
            <input type="submit" value="submit">
        </form>
    </section>
    <aside>
        <h1>Aside</h1>
    </aside>
    <jsp:include page="footer.jsp"/>