<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="imagens/favicon.ico">
        <link rel="stylesheet" href="styles/realizarEntrega.css">
        <link rel="stylesheet" href="styles/footer.css">
        <link rel="stylesheet" href="styles/listaMenu.css"> 
        <script src="scripts/jquery-2.1.1.js"></script>
        <script src="scripts/TestarCampos.js" charset="utf-8"></script>
        <title>Realizar Entrega</title>
    </head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="../listaMenu.jsp"/>

    <section>
        <form id="campos_form" action="RealizarEntrega" method="get">
            <p class="p1"><span class="cad">Realizar Entrega</span> <span 
                    class="ast">*</span><span id="cpo">Campos de preenchimento obrigatórios.</span>
            </p>

            <input type="hidden" name="controle" id="controle" value="16">

            <article id="part1">
                <label for="pesEmpes"><span class="lab">*</span>Digite o código do cliente:</label><br>
                <input type="number" name="pesEmpes" id="pesEmpes"><br>
                <input type="hidden" name="situacao" value="0">
                <span id="spesEmpes"></span>

                <select name="tipo" id="tipo">
                    <option value="cliente">Cliente</option>
                    <option value="funcionario">Funcionário</option>
                </select><br>

                <input type="submit" value="Buscar">
            </article>           
        </form>

        <article id="part2">
            <table>
                <thead>
                    <tr>
                        <th>
                            Emprestimo
                        </th>
                        <th>
                            Data de Emprestimo
                        </th>
                        <th>
                            Data de Entrega
                        </th>
                        <th>
                            Concluir Entrega
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ef" items="${sessionScope.arrayEmprestimoFun}">
                        <c:choose>
                            <c:when test="${sessionScope.t == 'cliente'}">
                                <tr>
                                    <td>
                                        ${ef.codCliente}-${ef.codLivro}-${ef.codEscritor}
                                    </td>
                                    <td>
                                        ${ef.dataEmprestimo}
                                    </td>
                                    <td>
                                        ${ef.dataEntrega}
                                    </td>
                                    <td>
                                        <a href="RealizarEntrega?situacao=1&cod=${ef.codCliente}&codL=${ef.codLivro}&codE=${ef.codEscritor}">
                                            Concluir Entrega</a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>
                                        ${ef.codFuncionario}-${ef.codLivro}-${ef.codEscritor}
                                    </td>
                                    <td>
                                        ${ef.dataEmprestimo}
                                    </td>
                                    <td>
                                        ${ef.dataEntrega}
                                    </td>
                                    <td>
                                        <a href="RealizarEntrega?situacao=1&cod=${ef.codFuncionario}&codL=${ef.codLivro}&codE=${ef.codEscritor}">
                                            Concluir Entrega</a>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </tbody>
            </table>            
        </article>

    </section>        

    <jsp:include page="../footer.jsp"/>