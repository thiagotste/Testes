<!DOCTYPE html>
<html lang="br">
    <head>
        <title>Painel de Controle</title>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/empPorData.css">
        <link rel="stylesheet" href="styles/aside.css">
        <link rel="stylesheet" href="styles/sec1.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    </head>

    <jsp:include page="listaMenu.jsp"/>    
    <jsp:include page="sec1.jsp"/>
    
    <section id="sec2">
        <h1>Emprestimos po Data</h1>            
        <hr>
        <form action="ItemPainel" method="post" id="dtEmp">
            <label for="de">De: </label>
            <input type="text" name="de" id="de">
            <label for="ate">Até: </label>
            <input type="text" name="ate" id="ate">
            <input type="hidden" name="controle" value="3">
            <input type="hidden" name="cod" value="33">
            <input type="submit" value="Pesquisar">
        </form>
    </section>
    <jsp:include page="aside.jsp"/>
    <jsp:include page="footer.jsp"/>
