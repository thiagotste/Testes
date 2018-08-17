<%@page import="com.br.library.accsess.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    User u = (User) request.getSession().getAttribute("user");
    if (u == null) {
        response.sendError(403);
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
        <title>Minha Área</title>
    </head>
    <script>
        var ctx = "<%=request.getContextPath()%>";
    </script>
    <body ng-controller="userAreaController">
        <%@include file="../../WEB-INF/jspf/mainHeader.jspf"%>
        <section class="d-block">
            <div class="container">
                <div class="row mt-5 ml-5">
                    <div class="col-md-3">
                        <form name="fileForm">
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ng-show="loadingPost">
                            <img id="imageUser" src="<%= request.getContextPath()%>/ByteArrayStream?origin=userArea" ng-show="fileForm.file.$pristine || fileForm.file.$error.pattern || fileForm.file.$invalid" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ngf-src="file" ng-show="fileForm.file.$dirty && !fileForm.file.$error.pattern && fileForm.file.$valid && !loadingPost" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
                            <button type="button" class="btn btn-link" ngf-select ng-model="file" name="file" ngf-pattern="'image/*'"
                                    ngf-accept="'image/*'" ngf-max-size="4MB" ngf-model-invalid="errorFile" required>
                                Selecionar
                            </button>                            
                            <button type="button" class="btn btn-link" ng-cloak ng-click="sendFile(file)" ng-show="fileForm.file.$dirty && !fileForm.file.$error.pattern && fileForm.file.$valid">
                                Salvar
                            </button>
                            <small ng-cloak class="text-muted d-block">
                                <span ng-cloak ng-show="isImageError" style="color: red;">
                                    {{errorMessage}}
                                </span>
                            </small>                            
                            <small ng-cloak class="text-muted d-block">
                                <span ng-show="fileForm.file.$dirty && fileForm.file.$error.maxSize" style="color: red;">
                                    O arquivo é muito Grande {{errorFile.size / 1000000|number:1}}MB: máximo 2M.
                                </span>
                            </small>
                        </form>
                    </div>
                    <div class="col-md-3">
                        <p class="font-weight-normal mt-4">
                            <c:out value="${user.name}"/>
                        </p>
                    </div>
                    <div class="col-md-6">
                        <p class="font-weight-normal mt-4 text-left" ng-hide="isEmailChange">
                            <c:out value="${user.email}"/>
                            <button type="button" class="btn btn-link" ng-click="isEmailChange = true"ng-hide="isEmailChange">Trocar</button>
                        <form name="emailChangeForm" ng-cloak ng-show="isEmailChange" ng-submit="submitEmailChange(email)">
                            <div class="form-group">
                                <label for="emailChange">Email:</label>
                                <input id="emailChange" type="email" class="form-control" name="emailChange" ng-model="email" required>
                            </div>
                            <button type="submit" class="btn btn-primary" ng-disabled="emailChangeForm.$invalid">Salvar</button>
                            <button type="button" class="btn btn-secondary" ng-click="isEmailChange = false; email = '';">sair</button>
                        </form>
                        </p>
                    </div>
                </div>
                <div class="row mt-4 ml-5">
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">Data de Nscimento: <c:out value="${user.birthday}"/></p>
                    </div>
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left" ng-hide="isPhoneChange">Telefone: <c:out value="${user.phone}"/> 
                            <button type="button" class="btn btn-link" ng-click="isPhoneChange = true"ng-hide="isPhoneChange" >Trocar</button>
                        </p>                        
                        <form name="phoneChangeForm" ng-cloak ng-show="isPhoneChange" ng-submit="submitPhoneChange(phone)">
                            <div class="form-group">
                                <label for="registerPhone">Telefone:</label>
                                <input type="text" id="registerPhone" class="form-control" name="Phone" ng-model="phone" placeholder="(99)9999-9999 ou (99)99999-9999" required>
                            </div>
                            <button type="submit" class="btn btn-primary" ng-disabled="phoneChangeForm.$invalid">Salvar</button>
                            <button type="button" class="btn btn-secondary" ng-click="isPhoneChange = false; phone = '';">sair</button>
                        </form>
                    </div>
                </div>
                <div class="row mt-4  ml-5">
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">RG: <c:out value="${user.rg}"/></p>
                    </div>
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">CPF: <c:out value="${user.cpf}"/></p>
                    </div>                                        
                </div>
                <div class="row mt-4 mb-5  ml-5">
                    <div class="col-md-12">
                        <p class="font-weight-normal text-left" ng-hide="isAddressChange">Endereço: <c:out value="${user.fullAddress()}"/>
                            <button type="button" class="btn btn-link" ng-click="isAddressChange = true" ng-hide="isAddressChange">Trocar</button></p>
                        <form name="addressChangeForm" ng-cloak ng-submit="submitAddressChange(AddressChange)" ng-show="isAddressChange">
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="zcodeChange">CEP:</label>
                                    <input id="zcodeChange" type="text" class="form-control" name="zcodeChange" ng-model="AddressChange.zCode" ng-blur="" required>
                                </div>
                                <div class="form-group col-md-5">
                                    <label for="addressChange">Endereço:</label>
                                    <input id="addressChange" type="text" class="form-control" name="addressChange" ng-model="AddressChange.address" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="complementChange">Complemento:</label>
                                    <input id="complementChange" type="text" class="form-control" name="complementChange" ng-model="AddressChange.complement" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="neighborhoodChange">Bairro:</label>
                                    <input id="neighborhoodChange" type="text" class="form-control" name="neighborhoodChange" ng-model="AddressChange.neighborhood" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="neighborhoodChange">Cidade:</label>
                                    <input id="cityChange" type="text" class="form-control" name="cityChange" ng-model="AddressChange.city" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="stateChange">Estado:</label>
                                    <select id="stateChange" class="form-control" name="stateChange" ng-model="AddressChange.state" required="">
                                        <option value="" selected disabled>Escolha um estado.</option>
                                        <option value="AM">Amazonas</option>
                                        <option value="SP">São Paulo</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary" ng-disabled="addressChangeForm.$invald">Enviar</button>
                            <button type="button" class="btn btn-secondary" ng-click="isAddressChange = false; AddressChange = null;">Sair</button>
                        </form>
                    </div>                                                            
                </div>
            </div>
        </section>        
        <script src="<%= request.getContextPath()%>/res/javascript/angular/angular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/ng-file-upload-shim.min.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/ng-file-upload.min.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/userRestrict/userAngular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="../../WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>
