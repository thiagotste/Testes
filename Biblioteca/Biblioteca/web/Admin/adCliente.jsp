<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/cliente.css" >
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>        
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/jquery.mask.js"></script>       
        <script src="scripts/TestarCampos.js" charset="utf-8"></script>
        <script src="scripts/mascara.js"></script>
        <title>Cadastro de Cliente</title>        
    </head>  
    
    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form id="campos_form" name="cpf_form" action="AdicionarCliente" method="post">
            <p class="p1"><span class="cad">Cadastro Pessoal</span> <span 
                    class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>
            
            <input type="hidden" name="controle" id="controle" value="13">
            
            <article class="pess1">                    
                <label for="nome"><span class="lab">*</span>Nome:</label><br>
                <input type="text" name="nome" id="nome" value="${requestScope.cliente.priNome}"><br>
                <span id="snome"></span>

                <label for="sobrenome"><span class="lab">*</span>Sobrenome:</label><br>
                <input type="text" name="sobrenome" id="sobrenome" size="50" 
                       value="${requestScope.cliente.lastNome}"><br>
                <span id="ssobrenome"></span>

                <label for="cpf"><span class="lab">*</span>CPF:</label><br>
                <input type="text" name="cpf" id="cpf" value="${requestScope.cliente.cpf}">
                <span id="cpf_mensagem">Apenas números, sem pontos ou traços.</span><br>
                <span id="scpf">${requestScope.cpfMensagem}</span>

                <label for="sexo"><span class="lab">*</span>Sexo:</label><br>
                <select name="sexo" id="sexo">
                    <option value="s">Selecione</option>
                    <option value="F">Feminino</option>
                    <option value="M">Masculino</option>                        
                </select><br>
                <span id="ssexo"></span>

                <label for="dtNascimento"><span class="lab">*</span>Data de Nascimento:</label><br>
                <input type="text" name="dtNascimento" id="dtNascimento" value="${requestScope.cliente.dtNascimento}">
                <span id="dtNascimento_mensagem">(DD/MM/AAAA)</span><br>
                <span id="sdtNascimento"></span>

                <label for="telefone"><span class="lab">*</span>Telefone:</label><br>
                <input type="text" name="telefone" id="telefone" value="${requestScope.cliente.telefone1}">
                <span id="telefone_mensagem">(DDD - Telefone)</span><br>
                <span id="stelefone"></span>

                <label for="celular">Celular:</label><br>
                <input type="text" name="celular" id="celular" value="${requestScope.cliente.telefone2}" >              
                <span id="celular_mensagem">(DDD - Telefone) (Opcional)</span><br>
                <span id="scelular"></span>
            </article>
            <article class="pess2">
                <label for="email"><span class="lab">*</span>Email:</label><br>
                <input type="email" name="email" id="email" value="${sessionScope.email}"><br>
                <span id="semail">${requestScope.emailMensagem}</span>

                <label for="senha"><span class="lab">*</span>Senha:</label><br>
                <input type="password" name="senha" id="senha">
                <span id="senha_mensagem">Digite no mínimo 6 caracteres.</span><br>
                <span id="ssenha"></span>

                <label for="confsenha"><span class="lab">*</span>Confirme a senha:</label><br>
                <input type="password" name="confsenha" id="confsenha">
                <span id="conf_mensagem">Confirme a senha.</span><br>
                <span id="sconfsenha"></span>

            </article>                
            <p class="p2">Cadastro de Endereço</p>
            <article class="end1">
                <label for="cep"><span class="lab">*</span>CEP:</label><br>
                <input type="text" name="cep" id="cep" value="${requestScope.cliente.cep}"><br>
                <span id="scep"></span>

                <label for="endereco"><span class="lab">*</span>Endereço:</label><br>
                <input type="text" name="endereco" id="endereco" size="50" 
                       value="${requestScope.cliente.endereco}"><br>
                <span id="sendereco"></span>

                <label for="complemento">Complemento:</label><br>
                <input type="text" name="complemento" id="complemento" 
                       value="${requestScope.cliente.complemento}">
                <span id="complemento_mensagem">Ex. Apto 69</span><br>                  
            </article>
            <article class="end2">
                <label for="cidade"><span class="lab">*</span>Cidade:</label><br>
                <input type="text" name="cidade" id="cidade" 
                       value="${requestScope.cliente.cidade}"><br>
                <span id="scidade"></span>

                <label for="uf"><span class="lab">*</span>UF:</label><br>
                <input type="text" name="uf" id="uf" value="${requestScope.cliente.uf}"><br>
                <span id="suf"></span>
            </article>
            <p class="p3">
                <input type="submit" value="Confirmar" id="confirmar">
                <input type="reset" value="Limpar Campos">
            </p>
        </form>
    </section>        
    <jsp:include page="../footer.jsp"/>