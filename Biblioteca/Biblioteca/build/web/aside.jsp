<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside>
    <h1>Meus Serviços</h1>
    <hr>
    <nav class="anav">
        <h3><a href="DirecionarPainel">Meus Emprestimos</a></h3>
        <a href="ItemPainel?controle=0">Últimos Emprestimos</a><br>
        <a href="ItemPainel?controle=1">Emprestimos em Aberto</a><br>
        <a href="ItemPainel?controle=2">Emprestimos Finalizados</a><br>
        <a href="ItemPainel?controle=3">Emprestimos por Data</a><br>
        <a href="ItemPainel?controle=4">Todos os Emprestimos</a><br>
        <a href="ItemPainel?controle=9">Livros</a><br>
    </nav>
    <hr class="sep">
    <nav class="anav">     
        <h3><a href="DirecionarPainel">Meus Dados Cadastrais</a></h3>
        <a href="ItemPainel?controle=5">Alterar E-mail</a><br>
        <a href="ItemPainel?controle=7">Alterar Senha</a><br>
        <a href="ItemPainel?controle=8">Alterar Dados Cadastrais</a><br>
        <a href="#">Alterar Endereço</a><br>
    </nav>
    <c:if test="${sessionScope.fun != null}">
        <hr class="sep">
        <nav class="anav">     
            <h3><a href="DirecionarPainel">Área Administrativa</a></h3>
            <a href="ItemPainel?controle=9">Adicionar Classificação</a><br>
            <a href="ItemPainel?controle=10">Adicionar Escritor</a><br>
            <c:if test="${sessionScope.fun.tipo ==  'administrador'}">
                <a href="ItemPainel?controle=11">Adicionar Biblioteca</a><br>
                <a href="ItemPainel?controle=12">Adicionar Funcionario</a><br>
            </c:if>
            <a href="ItemPainel?controle=13">Adicionar Cliente</a><br>
            <a href="ItemPainel?controle=14">Adicionar Livro</a><br>            
            <a href="ItemPainel?controle=16">Realizar Entrega</a><br>
            <a href="teste.jsp">teste</a>
        </nav>
    </c:if>
</aside>