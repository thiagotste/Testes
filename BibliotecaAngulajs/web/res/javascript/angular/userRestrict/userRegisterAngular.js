var userAngular = angular.module("userAngular", []);
userAngular.factory('context', ['$window', function ($w) {
        return {
            ctx: $w.ctx
        };
    }]);
userAngular.controller("userRegisterController", ["$scope", "$http", "postService","getService", "context", function ($scope, $http, postService, getService, context) {
        $scope.register = {};
        $scope.register.email = window.email;
        $scope.registerUser = function (register) {            
            register.action = "userRegister";
            var url = context.ctx + "/data/user.jsp";
            postService.query(register, url).then(function (response) {
                if (response.data.re === 0) {
                    $("#errorText").text("Falhao do servidor. Erro x000.");
                    $("#errorModal").modal("show");
                } else if (response.data.re === 1) {
                    $("#unsuccessfulText").text("Senha incorreta.");
                    $("#unsuccessfulModal").modal("show");
                } else if (response.data.re === 2) {
                    $("#errorText").text("Falhao do servidor. Erro x001.");
                    $("#errorModal").modal("show");
                } else {
                    $("#successText").text("Usuário inserido com sucesso.");
                    $("#successText").append("<p>Click <a href=\"" + context.ctx + "/index.jsp\">aqui</a> para voltar à página inicial.</p>");
                    $("#successModal").modal("show");
                }
                $('#successModal').on('hidden.bs.modal', function () {
                    location.href = context.ctx + "/index.jsp";
                });

            });

        };
//        $scope.nchange = function () {
//            $scope.register.address = $("#registerAddress").val();
//            $scope.register.neighbor = $("#registerNeighborhood").val();
//            $scope.register.city = $("#registerCity").val();
//        }
        $scope.zblur = function (val) {            
            function form_wiping() {
                $scope.register.address = "";
                $scope.register.neighbor = "";
                $scope.register.city = "";
                $scope.register.state = "";
            }            
            if (val) {
                var cep = val.replace(/\D/g, '');
                var validacep = /^[0-9]{8}$/;
                if (validacep.test(cep)) {
                    $scope.register.address = "...";
                    $scope.register.neighbor = "...";
                    $scope.register.city = "...";
                    $scope.register.state = "...";
                }
                getService.query("//viacep.com.br/ws/" + cep + "/json/").then(function (response) {
                    if (!("erro" in response)) {                        
                        $scope.register.address = response.data.logradouro;
                        $scope.register.neighbor = response.data.bairro;
                        $scope.register.city = response.data.localidade;
                        $scope.register.state = response.data.uf;

                    } else {
                        form_wiping();
                    }
                });
            } else {
                form_wiping();
            }
            /********************** FONTE: http://viacep.com.br/ *************************/
        }
        $scope.cpfTest = function (value) {
            if (value) {
                var cpf = value;
                var sum = 0;
                var rest = 0;

                var splitCpf = cpf.split(".");
                cpf = "";
                for (var i in splitCpf) {
                    if (i === '2') {
                        cpf += splitCpf[i].substr(0, 3) + splitCpf[i].substr(4, 2);
                    } else {
                        cpf += splitCpf[i];
                    }
                }
                if (cpf === '00000000000' || cpf === '11111111111' || cpf === '22222222222' ||
                        cpf === '33333333333' || cpf === '44444444444' || cpf === '55555555555' ||
                        cpf === '66666666666' || cpf === '77777777777' || cpf === '88888888888' ||
                        cpf === '99999999999')
                {
                    $scope.register.cpf = "";
                    $("#registerCPF").attr("placeholder", "CPF inválido.");
                }
                for (i = 0; i < 9; i++) {
                    sum += cpf.substr(i, 1) * (10 - i);
                }
                rest = (sum * 10) % 11;
                if (rest === 10 || rest === 11) {
                    rest = 0;
                }
                sum = 0;
                if (rest === parseInt(cpf.substr(9, 1))) {
                    for (i = 0; i < 10; i++) {
                        sum += cpf.substr(i, 1) * (11 - i);
                    }
                    rest = (sum * 10) % 11;
                    if (rest === 10 || rest === 11) {
                        rest = 0;
                    }
                    if (rest !== parseInt(cpf.substr(10))) {
                        $scope.register.cpf = "";
                        $("#registerCPF").attr("placeholder", "CPF inválido.");
                    }
                } else {
                    $scope.register.cpf = "";
                    $("#registerCPF").attr("placeholder", "CPF inválido.");
                }
            }
        };
    }]);
userAngular.factory('postService', ['$http', function ($http) {
        return {
            query: function (selecionado, url) {
                var serializedData = $.param(selecionado);
                return  $http({
                    method: 'POST',
                    url: url,
                    data: serializedData,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }});
            }
        };
    }]);
userAngular.factory('getService', ['$http', function ($http) {
        return {
            query: function (url) {
                return $http.get(url);
            }
        };
    }]);
userAngular.factory("getInterception", ['$q', '$log', function ($q, $rootScope, $log) {
        var xhr = 0;
        function isLoading() {
            return xhr > 0;
        }
        function updateStatus() {
            $rootScope.loadingGet = isLoading();
        }

        return {
            request: function (config) {
                xhr++;
                if (config.method === "GET") {
                    updateStatus();
                }
                return config;
            },
            requestError: function (rejection) {
                xhr--;
                updateStatus();
                $log.error('Request Error: ', rejection);
                return $q.reject(rejection);
            },
            response: function (response) {
                xhr--;
                updateStatus();
                return response;
            },
            responseError: function (rejection) {
                xhr--;
                updateStatus();
                $log.error('Response Error: ', rejection);
                return $q.reject(rejection);
            }
        };
    }]);
userAngular.factory("postInterception", ['$q', '$rootScope', '$log', function ($q, $rootScope, $log) {
        var xhr = 0;
        function isLoading() {
            return xhr > 0;
        }
        function updateStatus() {
            $rootScope.loadingPost = isLoading();
        }

        return {
            request: function (config) {
                xhr++;
                if (config.method === "POST") {
                    updateStatus();
                }
                return config;
            },
            requestError: function (rejection) {
                xhr--;
                updateStatus();
                $log.error('Request Error: ', rejection);
                return $q.reject(rejection);
            },
            response: function (response) {
                xhr--;
                updateStatus();
                return response;
            },
            responseError: function (rejection) {
                xhr--;
                updateStatus();
                $log.error('Response Error: ', rejection);
                return $q.reject(rejection);
            }
        };
    }]);
userAngular.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push("getInterception");
        $httpProvider.interceptors.push("postInterception");
    }]);
