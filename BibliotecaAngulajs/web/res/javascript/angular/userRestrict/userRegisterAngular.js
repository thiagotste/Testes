var userAngular = angular.module("userAngular", []);
userAngular.controller("userRegisterController", ["$scope", "$http", function ($scope, $http) {
        $scope.register = {};
        $scope.registerUser = function (register) {            
            console.log(register);
        };
        $scope.nchange = function() {
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