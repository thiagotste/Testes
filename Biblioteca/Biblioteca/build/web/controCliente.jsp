<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/cadastro.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/temNaoTemCadast.js"></script>
        <title>Cadastrar Cliente</title>
    </head>

    <jsp:include page="listaMenu.jsp"/>

    <section>
        <%--
        <article id="nao_tenho">
            <form action="ControlarCliente" method="post" id="nao_tenho_form">
                <h1>Não tenho cadastro</h1>
                <label for="email">Email</label><br>
                <input type="email" name="email" id="email" value="${requestScope.email}"><br>
                <input type="hidden" name="controle" value="1">
                <span></span><br>
                <input type="submit" value="Criar Cadastro" class="criar">
            </form>
        </article>
        --%>
        <article id="tenho">
            <form id="tem_form" action="ControlarCliente" method="post">
                <h1>Já tenho Cadastro</h1>
                <p class="er_ms">${requestScope.mensagem}</p>
                <label for="email_cpf">Email:</label><br>
                <input type="text" id="email_cpf" name="email_cpf"><br>
                <p class="e_message"></p><br>
                <label for="senha_cpf">Senha:</label><br>
                <input type="password" id="senha_cpf" name="senha_cpf"><br>
                <input type="hidden" name="controle" value="0">
                <a href="#">Esqueci minha senha.</a><br>
                <input type="submit" value="Prosseguir" class="prosseguir">
            </form>
        </article>            
    </section>
    <jsp:include page="footer.jsp"/>
