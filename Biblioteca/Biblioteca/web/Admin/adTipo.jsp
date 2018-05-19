<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <title>Adicionar Tipo</title>
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/adTipo.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script>
            $(document).ready(function () {
                $("#nome").focus();
                if ($("#mensagem").text().length > 0) {
                    $("#nome").css("margin-bottom","0em");
                    $("#mensagem").css("color", "darkblue");
                }
            });
        </script>
    </head>

    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form action="AdicionarTipo" method="post">
            <p class="p1"><span class="cad">Cadastro de Tipo</span> <span 
                    class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>
            <article id="part1">
                <label for="nome"><span class="lab">*</span>Nome do Tipo:</label><br>
                <input type="text" name="nome" id="nome" value="${requestScope.nome}"><br>        
                <span id="mensagem">${requestScope.message}</span>
            </article>
            <p class="p3">
                <input type="submit" value="Confirmar">
                <input type="reset" value="Limpar Campos">
            </p>
        </form>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>