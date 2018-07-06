var userAngular = angular.module("userAngular", []);
userAngular.factory('context', ['$window',function ($w) {
        return {
            ctx: $w.ctx
        };
}]);
userAngular.controller("userRegisterController", ["$scope", "$http", "postService", "context", function ($scope, $http, postService, context) {
        $scope.register = {};
        $scope.register.email = window.email;
        $scope.registerUser = function (register) {
            console.log(register);
            register.action = "userRegister";
            var url = context.ctx + "/data/user.jsp";
            postService.query(register, url).then(function (response) {
                console.log(response.data);
            });
            
        };
        $scope.nchange = function () {
            $scope.register.address = $("#registerAddress").val();
            $scope.register.neighbor = $("#registerNeighborhood").val();
            $scope.register.city = $("#registerCity").val();
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
userAngular.factory("getInterception", ['$q', '$log', function ($q, $log) {
        var xhr = 0;
        function isLoading() {
            return xhr > 0;
        }
        function updateStatus() {
            loadingGet = isLoading();
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
            loadingPost = isLoading();
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
