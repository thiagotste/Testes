<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <title>Adicionar Escritor</title>
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/adEscritor.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/TestarCampos.js" charset="utf-8"></script>
    </head>

    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form id="campos_form" action="AdicionarEscritor" method="post">
            <p class="p1"><span class="cad">Cadastro Pessoal</span> <span 
                    class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>
            
            <input type="hidden" name="controle" id="controle" value="10">
            
            <article id="part1">
                <label for="nome"><span class="lab">*</span>Nome:</label><br>
                <input type="text" name="nome" id="nome"><br>
                <span id="snome"></span>
                
                <label for="sobrenome"><span class="lab">*</span>Sobrenome:</label><br>
                <input type="text" name="sobrenome" id="sobrenome"><br>
                <span id="ssobrenome"></span>
            </article>
            <p class="p3">
                <input type="submit" value="Confirmar">
                <input type="reset" value="Limpar Campos">
            </p>
        </form>
    </section>
    <jsp:include page="../footer.jsp"/>