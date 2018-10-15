<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset = "UTF-8">
        <link rel="shotcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/adLivro.css">
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/jquery.mask.js"></script>
        <script src="scripts/TestarCampos.js" charset="utf-8"></script>
        <script src="scripts/mascara.js"></script>
        <script>
            $(document).ready(function () {
                var codLivro = $("#codLivro").val();
                var codEscritor = $("#codEscritor").val();
                var codTipo = $("#codTipo").val();
                var codBiblioteca = $("#codBiblioteca").val();

                if (codLivro == 0 && codEscritor == 0 && codTipo == 0
                        && codBiblioteca == 0) {
                    $("#codLivro").val("");
                    $("#codEscritor").val("");
                    $("#codTipo").val("");
                    $("#codBiblioteca").val("");
                }
            });
        </script>
        <title>Adicionar Livro</title>
    </head>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form id="campos_form" action="AdicionarLivro" method="post" enctype="multipart/form-data">
            <p class="p1"><span class="cad">Cadastro de Livro</span> 
                <span class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>

            <input type="hidden" name="controle" id="controle" value="14">

            <article id="part2">
                <label for="vol"><span class="lab">*</span>Volume:</label><br>
                <input type="number" name="vol" id="vol" min="1"
                       value="${requestScope.livro.volume}"><br>
                <span id="svol"></span>

                <label for="idioma"><span class="lab">*</span>Idioma:</label><br>
                <input type="text" name="idioma" id="idioma"
                       value="${requestScope.livro.idioma}"><br>
                <span id="sidioma"></span>

                <label for="numPagina"><span class="lab">*</span>Número de Página:</label><br>
                <input type="text" name="numPagina" id="numPagina"
                       value="${requestScope.livro.numPag}"><br>
                <span id="snumPagina"></span>

                <label for="edicao"><span class="lab">*</span>Edição:</label><br>
                <input type="number" name="edicao" id="edicao" min="1"
                       value="${requestScope.livro.edicao}"><br>
                <span id="sedicao"></span>

                <label for="anoLancamento"><span class="lab">*</span>Ano de lancamento:</label><br>
                <input type="text" name="anoLancamento" id="anoLancamento" maxlength="4"
                       value="${requestScope.livro.anoLancamento}" 
                       pattern="^[12]\d{3}" size="3"><br>
                <span id="sanoLancamento"></span>

                <label for="dataCompra"><span class="lab">*</span>Data de compra:</label><br>
                <input type="text" name="dataCompra" id="dataCompra"
                       value="${requestScope.livro.dtAquisicao}"><br>
                <span id="sdataCompra"></span>

                <label for="formato"><span class="lab">*</span>Formato</label><br>
                <input type="text" name="formato" id="formato"
                       value="${requestScope.livro.formato}"><br>
                <span id="sformato"></span>
                
                <label for="imagem">Adicionar Imagem:</label>
                <input type="file" name="imagem" accept="image/jpeg, image/gif">

            </article>

            <article id="part1">
                <label for="codLivro"><span class="lab">*</span>Cod do livro:</label><br>
                <input type="text" name="codLivro" id="codLivro" size="11" 
                       value="${requestScope.livro.codLivro}" autofocus><br>
                <span id="scodLivro"></span>

                <label for="codEscritor"><span class="lab">*</span>Code do escritor:</label><br>
                <input type="text" name="codEscritor" id="codEscritor" size="11" 
                       value="${requestScope.livro.codEscritor}"list="escritores"><br>
                <span id="scodEscritor"></span>
                <datalist id="escritores">
                    <c:forEach var="escritor" items="${sessionScope.escritores}">
                        <option value="${escritor.codEscritor}" 
                                label="${escritor.priNome} ${escritor.lastNome}">
                        </c:forEach>
                </datalist>

                <label for="codTipo"><span class="lab">*</span>Codigo do tipo:</label><br>
                <input type="text" name="codTipo" id="codTipo" size="11"
                       value="${requestScope.livro.codTipo}" list="tipos"><br>
                <span id="scodTipo"></span>
                <datalist id="tipos">
                    <c:forEach var="tipo" items="${sessionScope.tipos}">
                        <option value="${tipo.codTipo}" label="${tipo.nome}">
                        </c:forEach>
                </datalist>

                <label for="codBiblioteca"><span class="lab">*</span>Codigo da biblioteca:</label><br>
                <input type="text" name="codBiblioteca" id="codBiblioteca" size="11"
                       value="${requestScope.livro.codBiblioteca}" list="bibliotecas"><br>
                <span id="scodBiblioteca"></span>
                <datalist id="bibliotecas">
                    <c:forEach var="biblioteca" items="${sessionScope.bibliotecas}">
                        <option value="${biblioteca.codBiblioteca}" label="${biblioteca.nome}">
                        </c:forEach>                    
                </datalist>

                <label for="nome"><span class="lab">*</span>Nome do livro:</label><br>
                <input type="text" name="nome" id="nome" size="50"
                       value="${requestScope.livro.nome}"><br>                
                <span id="snome"></span>

                <label for="editora"><span class="lab">*</span>Editora:</label><br>
                <input type="text" name="editora" id="editora" size="50"
                       value="${requestScope.livro.editora}"><br>
                <span id="seditora"></span>               

                <label for="descricao"><span class="lab">*</span>Descrição:</label><br>
                <textarea name="descricao" id="descricao" cols="55">
                    <c:forEach var="descricao" items="${requestScope.descricao}">                
                        ${descricao.trim()}                
                    </c:forEach>
                </textarea><br>
                <span id="sdescricao"></span>
            </article>
            <p class="p3">
                <input type="submit" value="Confirmar" id="confirmar">
                <input type="reset" value="Limpar Campos">
            </p>
        </form>
    </section>
    <jsp:include page="../footer.jsp"/>
