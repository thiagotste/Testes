<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" sizes="96x96" href="<%= request.getContextPath()%>/res/images/icons/favicon-96x96.png">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/bootstrap.css">        
        <link rel="stylesheet" href="<%= request.getContextPath()%>/res/css/main.css">
        <title>Área Administrativa</title>
    </head>
    <body ng-app="adminArea">
        <%@include file="../../WEB-INF/jspf/mainHeader.jspf"%>
        <section class="d-block">
            <div class="container">
                <h2 class="text-secondary text-center my-5">Área Administrativa</h2>
                <button class="btn btn-primary mb-4" id="colButton" type="button" data-toggle="collapse" data-target="#adminTabs" aria-expanded="true" aria-controls="adminTabs">
                    Esconder Abas
                </button>
            </div>
        </section>
        <section class="d-block">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <div class="nav flex-column nav-pills collapse show" id="adminTabs" role="tablist" aria-orientation="vertical">
                            <a class="nav-link active" id="userAdminTab" data-toggle="pill" href="#userAdmin" role="tab" aria-controls="userAdmin" aria-selected="true">Usuários</a>
                            <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">Profile</a>
                            <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab" aria-controls="v-pills-messages" aria-selected="false">Messages</a>
                            <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</a>
                        </div>
                    </div>
                    <div id="colItem" class="col-md-9">
                        <h3 class="text-secondary text-center mb-3">Usuários</h3>
                        <div class="tab-content" id="v-pills-tabContent">
                            <div class="tab-pane fade show active" id="userAdmin" role="tabpanel" aria-labelledby="userAdminTab" ng-controller="AdminAreaUserController">
                                <table class="table table-striped table-bordered table-hover">
                                    <caption>Lista de Usuários</caption>
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">First</th>
                                            <th scope="col">Last</th>
                                            <th scope="col">Handle</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>Mark</td>
                                            <td>Otto</td>
                                            <td>@mdo</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>Jacob</td>
                                            <td>Thornton</td>
                                            <td>@fat</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>Larry</td>
                                            <td>the Bird</td>
                                            <td>@twitter</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                                Culpa dolor voluptate do laboris laboris irure reprehenderit id incididunt duis pariatur mollit aute magna pariatur consectetur. Eu veniam duis non ut dolor deserunt commodo et minim in quis laboris ipsum velit id veniam. Quis ut consectetur adipisicing officia excepteur non sit. Ut et elit aliquip labore Lorem enim eu. Ullamco mollit occaecat dolore ipsum id officia mollit qui esse anim eiusmod do sint minim consectetur qui.
                            </div>
                            <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                                Fugiat id quis dolor culpa eiusmod anim velit excepteur proident dolor aute qui magna. Ad proident laboris ullamco esse anim Lorem Lorem veniam quis Lorem irure occaecat velit nostrud magna nulla. Velit et et proident Lorem do ea tempor officia dolor. Reprehenderit Lorem aliquip labore est magna commodo est ea veniam consectetur.
                            </div>
                            <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                                Eu dolore ea ullamco dolore Lorem id cupidatat excepteur reprehenderit consectetur elit id dolor proident in cupidatat officia. Voluptate excepteur commodo labore nisi cillum duis aliqua do. Aliqua amet qui mollit consectetur nulla mollit velit aliqua veniam nisi id do Lorem deserunt amet. Culpa ullamco sit adipisicing labore officia magna elit nisi in aute tempor commodo eiusmod.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>        
        <script src="<%= request.getContextPath()%>/res/javascript/jquery-3.3.1.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/angular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/angular/adminRestrict/adminAngular.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/popper-1.14.3.js"></script>
        <script src="<%= request.getContextPath()%>/res/javascript/bootstrap.js"></script>
        <script>
            $(function () {
                $("#colButton").click(function () {
                    if ($("#colItem").hasClass("col-md-9")) {
                        $("#colButton").text("Mostrar Abas");
                        setTimeout(function () {
                            $("#colItem").removeClass("col-md-9");
                            $("#colItem").addClass("col-md-12");
                        }, 200);
                    } else {
                        $("#colItem").removeClass("col-md-12");
                        $("#colItem").addClass("col-md-9");                        
                        $("#colButton").text("Esconder Abas");
                    }
                });
            });
        </script>
        <%@include  file="../../WEB-INF/jspf/mainFooter.jspf" %>
    </body>
</html>
