<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="userAngular">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/images/icons/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/main.css">
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
                            <img id="imageUser" src="<%= request.getContextPath()%>/ByteArrayStream?origin=userArea" ng-show="fileForm.file.$pristine || fileForm.file.$error.pattern || fileForm.file.$invalid" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
                            <img ng-cloak src="<%= request.getContextPath()%>/res/images/icons/15.gif" ngf-src="file" ng-show="fileForm.file.$dirty && !fileForm.file.$error.pattern && fileForm.file.$valid" class="img-thumbnail border-0 rounded-circle ml-5" width="100" height="100" alt="Foto do usuário" title="Foto do usuário">
                            <button type="button" class="btn btn-link" ngf-select ng-model="file" name="file" ngf-pattern="'image/*'"
                                    ngf-accept="'image/*'" ngf-max-size="4MB" ngf-model-invalid="errorFile" required>
                                Selecionar
                            </button>                            
                            <button type="button" class="btn btn-link" ng-cloak ng-click="sendFile(file)" ng-show="fileForm.file.$dirty && !fileForm.file.$error.pattern && fileForm.file.$valid">
                                Salvar
                            </button>
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
                        <p class="font-weight-normal mt-4 text-left">
                            <c:out value="${user.email}"/>
                        </p>
                    </div>
                </div>
                <div class="row mt-4 ml-5">
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">Data de Nscimento: <c:out value="${user.birthday}"/></p>
                    </div>
                    <div class="col-md-6">
                        <p class="font-weight-normal text-left">Telefone: <c:out value="${user.phone}"/></p>
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
                        <p class="font-weight-normal text-left">Endereço: <c:out value="${user.fullAddress()}"/></p>
                    </div>                                                            
                </div>
            </div>
        </section>        
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/angular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/ng-file-upload-shim.min.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/ng-file-upload.min.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/userRestrict/userAngular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <%@include  file="../../WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>
