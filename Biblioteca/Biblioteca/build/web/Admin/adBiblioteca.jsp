<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">        
        <title>Adicionar Biblioteca</title>
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/adBiblioteca.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/TestarCampos.js" charset="utf-8"></script>
        <script src="scripts/jquery.mask.js"></script>
        <script src="scripts/mascara.js"></script>        
    </head>

    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form id="campos_form" action="AdicionarBiblioteca" method="post">
            <p class="p1"><span class="cad">Cadastro da Biblioteca</span> <span 
                    class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>
            
            <input type="hidden" name="controle" id="controle" value="11">
            
            <article id="part2">
                <label for="email"><span class="lab">*</span>Email:</label><br>
                <input type="email" name="email" id="email" maxlength="50" 
                       size="50"><br>
                <span id="semail"></span>
                
                <label for="uf"><span class="lab">*</span>UF:</label><br>
                <input type="text" name="uf" id="uf" maxlength="2" 
                       size="2"><br>
                <span id="suf"></span>
                
                <label for="telefone"><span class="lab">*</span>Telefone:</label><br>
                <input type="text" name="telefone" id="telefone"><br>
                <span id="stelefone"></span>
                                              
            </article>
            
            <article id="part1">
                <label for="nome"><span class="lab">*</span>Nome:</label><br>
                <input type="text" name="nome" id="nome" maxlength="50" 
                       size="50"><br>
                <span id="snome"></span>
                
                <label for="endereco"><span class="lab">*</span>Endereco:</label><br>
                <input type="text" name="endereco" id="endereco" maxlength="50" 
                       size="50"><br>
                <span id="sendereco"></span>
                
                <label for="cep"><span class="lab">*</span>Cep:</label><br>
                <input type="text" name="cep" id="cep"><br>
                <span id="scep"></span>
                
                <label for="cidade"><span class="lab">*</span>Cidade:</label><br>
                <input type="text" name="cidade" id="cidade" maxlength="50" 
                       size="50"><br>
                <span id="scidade"></span>
            </article>
            <p class="p3">
                <input type="submit" value="Confirmar">
                <input type="reset" value="Limpar Campos">
            </p>
        </form>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>