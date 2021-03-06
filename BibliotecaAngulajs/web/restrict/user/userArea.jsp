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
        var phone = "<c:out value="${user.phone}"/>";
        var address = "<c:out value="${user.getFullAddress()}"/>";
    </script>
    <body ng-controller="userAreaController">
        <%@include file="../../WEB-INF/jspf/mainHeader.jspf"%>        
        <section class="d-block">
            <div class="container">
                <div class="row mt-5 ml-5">
                    <div class="col-md-3">
                        <form name="fileForm">
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ng-show="isSendingImage">
                            <img id="imageUser" src="<%= request.getContextPath()%>/ByteArrayStream?origin=userArea" ng-show="fileForm.file.$pristine || fileForm.file.$error.pattern || fileForm.file.$invalid" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ngf-src="file" ng-show="fileForm.file.$dirty && !fileForm.file.$error.pattern && fileForm.file.$valid && !isSendingImage" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
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
                    <div class="col-md-4">
                        <p class="font-weight-normal mt-4 text-left" ng-cloak ng-hide="isChangingEmail">
                            <c:out value="${user.email}"/>
                            <button type="button" class="btn btn-link" ng-click="isChangingEmail = true" ng-hide="isChangingEmail">Trocar</button>
                        </p>
                        <form name="emailChangeForm" ng-cloak ng-show="isChangingEmail" ng-submit="submitEmailChange(email)">
                            <div class="form-group">
                                <label for="emailChange">Email:</label>
                                <input id="emailChange" type="email" class="form-control" name="emailChange" ng-model="email" required>
                                <small class="form-text text-muted" ng-show="isFailingEmailUpdate">
                                    <span style="color: red;">Falha ao modificar o email. Tente de novo mais tarde.</span>
                                </small>
                            </div>
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ng-show="isSendingEmail">
                            <div ng-hide="isSendingEmail">
                                <button type="submit" class="btn btn-primary" ng-disabled="emailChangeForm.$invalid">Salvar</button>
                                <button type="button" class="btn btn-secondary" ng-click="isChanging('email')">sair</button>
                            </div>
                        </form>
                        </p>
                    </div>
                    <div class="col-md-2">
                        <a href="" class="btn btn-outline-dark mt-4" data-toggle="modal" data-target="#changePasswordModal">Trocar Senha</a>
                        <%@include file="../../WEB-INF/jspf/user/changePassword.jspf"%>
                    </div>
                </div>
                <div class="row mt-4 ml-5">
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">Data de Nscimento: <c:out value="${user.birthday}"/></p>
                    </div>
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left" ng-cloak ng-hide="isChangingPhone">Telefone: {{textPhone}} 
                            <button type="button" class="btn btn-link" ng-click="isChangingPhone = true"ng-hide="isPhoneChange" >Trocar</button>
                        </p>                        
                        <form name="phoneChangeForm" ng-cloak ng-show="isChangingPhone" ng-submit="submitPhoneChange(phone)">
                            <div class="form-group">
                                <label for="registerPhone">Telefone:</label>
                                <input type="text" id="registerPhone" class="form-control" name="phone" ng-model="phone" ng-change="phoneLength(phone)" placeholder="(99)9999-9999 ou (99)99999-9999" required>
                                <small class="form-text text-muted" ng-show="isFailingPhoneUpdate">
                                    <span style="color: red;">Falha ao modificar o telefone. Tente de novo mais tarde.</span>
                                </small>
                            </div>
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ng-show="isSendingPhone">
                            <div ng-hide="isSendingPhone">
                                <button type="submit" class="btn btn-primary" ng-disabled="phoneChangeForm.$invalid">Salvar</button>
                                <button type="button" class="btn btn-secondary" ng-click="isChanging('phone')">sair</button>
                            </div>                            
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
                        <p class="font-weight-normal text-left" ng-cloak ng-hide="isChangingAddress">Endereço: {{textAddress}}
                            <button type="button" class="btn btn-link" ng-click="isChangingAddress = true" ng-hide="isChangingAddress">Trocar</button></p>
                        <form name="addressChangeForm" ng-cloak ng-submit="submitAddressChange(AddressChange)" ng-show="isChangingAddress">
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="zcodeChange">CEP:</label><span style="color: red;">*</span>
                                    <input id="zcodeChange" type="text" class="form-control" name="zcodeChange" maxlength="20" ng-model="AddressChange.zcode" ng-blur="zblur(AddressChange)" required>
                                </div>
                                <div class="form-group col-md-5">
                                    <label for="addressChange">Endereço:</label><span style="color: red;">*</span>
                                    <input id="addressChange" type="text" class="form-control" name="addressChange" maxlength="250" ng-model="AddressChange.address" required>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="complementChange">Complemento:</label>
                                    <input id="complementChange" type="text" class="form-control" name="complementChange" maxlength="20" ng-model="AddressChange.complement">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="neighborhoodChange">Bairro:</label><span style="color: red;">*</span>
                                    <input id="neighborhoodChange" type="text" class="form-control" name="neighborhoodChange" maxlength="30" ng-model="AddressChange.neighbor" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="neighborhoodChange">Cidade:</label><span style="color: red;">*</span>
                                    <input id="cityChange" type="text" class="form-control" name="cityChange" maxlength="30" ng-model="AddressChange.city" required>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="stateChange">Estado:</label><span style="color: red;">*</span>
                                    <select id="stateChange" class="form-control" name="stateChange" ng-model="AddressChange.state" required>
                                        <option value="" selected disabled>Escolha um estado.</option>
                                        <option value="AM">Amazonas</option>
                                        <option value="SP">São Paulo</option>
                                    </select>
                                </div>
                            </div>
                            <small class="form-text text-muted" ng-show="isFailingAddressUpdate">
                                <span style="color: red;">Falha ao modificar o endereço. Tente de novo mais tarde.</span>
                            </small>
                            <p style="color: red;">* Campo obrigatório.</p>
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ng-show="isSendingAddress">
                            <div ng-hide="isSendingAddress">
                                <button type="submit" class="btn btn-primary" ng-disabled="addressChangeForm.$invalid">Enviar</button>
                                <button type="button" class="btn btn-secondary" ng-click="isChanging('address')">Sair</button>
                            </div>
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
