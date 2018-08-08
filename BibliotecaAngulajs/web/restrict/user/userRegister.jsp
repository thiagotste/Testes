<%@page import="java.util.List"%>
<%@page import="com.br.library.sql.SqlCommands"%>
<%@page import="com.br.library.sql.SqlMethodInterface"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SqlMethodInterface sql = new SqlCommands();
    String email = request.getParameter("email");
    String query = "";
    List<Object[]> rows = null;
    try {
        query = "SELECT id FROM access.user_temp WHERE email = ?";
        rows = sql.executeQuery(query, new Object[]{email}, "Select temp user register");
        if (!rows.isEmpty()) {
            query = "SELECT id FROM access.user WHERE email = ?";
            rows = sql.executeQuery(query, new Object[]{email}, "Select user register");
            if (!rows.isEmpty()) {
                response.sendError(403, "Email já cadastrado.");
            }
        } else {
            response.sendError(403, "Precisa fazer o pré-cadastro.");
        }
    } catch (Exception e) {
        response.sendError(500);
    }
%>
<!DOCTYPE html>
<html ng-app="userAngular">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/images/icons/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/main.css">
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/jquery.inputmask.bundle.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/phone-codes/phone.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/phone-codes/phone-be.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/phone-codes/phone-ru.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/utilities.js"></script>
        <title>Cadastrar Usuário</title>
    </head>
    <script>
        var email = "${param.email}";
        var ctx = "<%= request.getContextPath()%>";
    </script>
    <body ng-controller="userRegisterController">
        <%@include file="../../WEB-INF/jspf/mainHeader.jspf"%>        
        <main class="container my-5">
            <h1>Registro do Usuário</h1>
            <form novalidate name="registerForm" ng-submit="registerUser(register)" autocomplete="off">
                <div class="form-row">
                    <div class="form-group col-9">
                        <label for="registerName">Nome Completo:</label><span style="color: red;">*</span>
                        <input type="text" id="registerName" class="form-control" name="name" ng-model="register.name" placeholder="Digite o nome." required>
                    </div>
                    <div class="form-group col-3">
                        <label for="registerBirth">Data de Nascimento:</label><span style="color: red;">*</span>
                        <input type="date" id="registerBirth" class="form-control" name="birth" ng-model="register.birth" placeholder="dd/mm/aaaa" required>
                        <%-- <div ng-show="registerForm.$submitted || registerForm.birth.$touched">
                            <span ng-show="registerForm.birth.$error.required">Tell us your email.</span>
                            <span ng-show="registerForm.birth.$error.date">This is not a valid date.</span>
                        </div> --%>                      
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col">
                        <label for="registerRG">RG:</label><span style="color: red;">*</span>
                        <input type="text" id="registerRG" class="form-control" name="rg" ng-model="register.rg" placeholder="Digite o RG." required>
                    </div>
                    <div class="form-group col">
                        <label for="registerCPF">CPF:</label><span style="color: red;">*</span>
                        <input type="text" id="registerCPF" class="form-control" name="cpf" ng-model="register.cpf" ng-blur="cpfTest(register.cpf)" placeholder="Digite o CPF." required>
                    </div>
                    <div class="form-group col">
                        <label for="registerPhone">Telefone:</label><span style="color: red;">*</span>
                        <input type="text" id="registerPhone" class="form-control" name="Phone" ng-model="register.phone" placeholder="(99)9999-9999 ou (99)99999-9999" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-2">
                        <label for="registerZCode">CEP:</label><span style="color: red;">*</span>
                        <input type="text" id="registerZCode" class="form-control" name="zcode" ng-model="register.zcode" ng-blur="zblur(register.zcode)" placeholder="Digite o CEP." required>
                    </div>
                    <div class="form-group col-6">
                        <label for="registerAddress">Endereço:</label><span style="color: red;">*</span>
                        <input type="text" id="registerAddress" class="form-control" name="address" ng-model="register.address" placeholder="Digite o endereço." required>                    
                    </div>
                    <div class="form-group col-2">
                        <label for="registerComplement">Complemento:</label>
                        <input type="text" id="registerComplement" class="form-control" name="complement" ng-model="register.complement" placeholder="Ex. Apto...">
                    </div>
                    <div class="form-group col-2">
                        <label for="registerNeighborhood">Bairro:</label><span style="color: red;">*</span>
                        <input type="text" id="registerNeighborhood" class="form-control" name="neighborhood" ng-model="register.neighbor" placeholder="Digite o bairro.">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col">
                        <label for="registerCity">Cidade:</label><span style="color: red;">*</span>
                        <input type="text" id="registerCity" class="form-control" name="city" ng-model="register.city" placeholder="Digite a Cidade." required>
                    </div>
                    <div class="form-group col">
                        <label for="registerState">Estado:</label><span style="color: red;">*</span>
                        <select id="registerState" class="form-control" required ng-model="register.state">
                            <option value="" disabled selected>Selecione o estado.</option>
                            <option value="AM">Amazonas</option>
                            <option value="SP">São Paulo</option>
                        </select>                        
                    </div>                    
                </div>                
                <div class="form-row">
                    <div class="form-group col-6">
                        <label for="registerEmail">Email:</label><span style="color: red;">*</span>
                        <input type="email" id="registerEmail" class="form-control" name="email" ng-model="register.email" placeholder="" readonly required>
                    </div>
                    <div class="form-group col-6">
                        <label for="registerPass">Senha:</label><span style="color: red;">*</span>
                        <input type="password" id="registerPass" class="form-control" name="password" ng-model="register.pass" placeholder="Digite a senha" required>
                    </div>                    
                </div>
                <p style="color: red;">* Campo obrigatório.</p>
                <div ng-show="loadingPost" ng-cloak><img style="width:15%;margin-left: 42%; margin-bottom: 5%;" src="<%= request.getContextPath()%>/res/images/icons/15.gif" alt=""/></div>
                <div class="mx-auto text-center" ng-hide="loadingPost">
                    <button type="submit" class="btn btn-primary btn-lg" ng-disabled="registerForm.$invalid">Registrar</button> 
                </div>
            </form>
        </main>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/angular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/userRestrict/userAngular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="../../WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>