var userAngular = angular.module("userAngular", ['ngFileUpload']);
userAngular.factory('context', ['$window', function ($w) {
        return {
            ctx: $w.ctx
        };
    }]);
userAngular.controller("userRegisterController", ["$scope", "postService", "context", "zcodeService", function ($scope, postService, context, zcodeService) {
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

        $scope.zblur = function (val) {
            zcodeService.code(val);
        };
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
userAngular.controller("userRedefinePassController", ["$scope", "postService", "context", function ($scope, postService, context) {
        $scope.forgotPass = function (value) {
            value.email = window.email.toString();
            value.action = "redefinePass";

            var url = context.ctx + "/data/user.jsp";
            postService.query(value, url).then(function (response) {
                switch (response.data.re) {
                    case 0:
                        $("#errorText").text("Falha do servidor. Erro x000.");
                        $("#errorModal").modal("show");
                        break;

                    case 2:
                        $("#unsuccessfulText").text("Falha ao redefinir a senha. Tente de novo mais tarde.");
                        $("#unsuccessfulModal").modal("show");
                        break;
                    default :
                        $("#successText").text("Operação realizada com sucesso.");
                        $("#successModal").modal("show");
                        break;
                }
            }).catch(function (data) {
                alert(data);
            });

        };
        $scope.testPass = function (value) {
            if ((value.nPassword !== value.nConPassword)) {
                $scope.forgotForm.$invalid = true;
                $scope.isMatch = false;
            } else {
                $scope.isMatch = true;
            }
            if (typeof value.nPassword !== "undefined") {
                if (value.nPassword.length < 6) {
                    $scope.forgotForm.$invalid = true;
                    $scope.isMinimum = false;
                } else {
                    $scope.isMinimum = true;
                }
            }
        };
        $scope.testConPass = function (value) {
            if ((value.nPassword && value.nConPassword) && (value.nPassword !== value.nConPassword)) {
                $scope.forgotForm.$invalid = true;
                $scope.isMatch = false;
            } else {
                $scope.isMatch = true;
                $scope.testPass(value);
            }
        };
    }]);
userAngular.controller('userAreaController', ['$scope', 'Upload', 'context', '$timeout', 'postService', 'getService', 'zcodeService', function ($scope, Upload, context, $timeout, postService, getService, zcodeService) {
        var val = 0;
        $scope.isImageError = false;
        $scope.isSendingImage = false;
        $scope.sendFile = function (file) {
            $scope.isSendingImage = true;
            if ($scope.fileForm.file.$valid && file) {
                Upload.upload({
                    url: context.ctx + '/data/user.jsp?action=userAngularFileUpload',
                    method: 'POST',
                    data: {file: file},
                    headers: {'content-type': undefined}
                }).then(function (resp) {
                    $scope.isSendingImage = false;
                    $("#imageUser").prop("src", context.ctx + "/ByteArrayStream?origin=userArea&val=" + val);
                    ++val;
                    if (resp.data.re === 0) {
                        $scope.isImageError = true;
                        $scope.errorMessage = "Erro ao atualizar imagem. Tente de novo mais tarde.";
                        $timeout(function () {
                            $scope.isImageError = false;
                        }, 4000);
                    } else if (resp.data.re === 1) {
                        $scope.isImageError = true;
                        $scope.errorMessage = "Erro ao enviar imagem. Tente de novo, por favor.";
                        $timeout(function () {
                            $scope.isImageError = false;
                        }, 4000);
                    }
//                    console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data.re);
                }, function (resp) {
                    $scope.isSendingImage = false;
                    alert('Error status: ' + resp.status);
                }, function (evt) {
                    $scope.isSendingImage = false;
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
                });
            }
        };
        $scope.isChangingEmail = false;
        $scope.isSendingEmail = false;
        $scope.isFailingEmailUpdate = false;
        $scope.submitEmailChange = function (value) {
            console.log(value);
            if (value) {
                $scope.isSendingEmail = true;
                var selecionado = {email: value, option: 'email', action: 'userArea'};
                var url = context.ctx + "/data/user.jsp";
                postService.query(selecionado, url).then(function (response) {
                    $scope.isSendingEmail = false;
                    if (response.data.re === 0) {
                        $scope.isFailingEmailUpdate = true;
                        $timeout(() => $scope.isFailingEmailUpdate = false, 4000);
                    } else {
                        $("#successText").text("Email modificado. Clique em fechar para entrar com novo email.")
                        $("#successModal").modal("show");
                    }
                }).catch(function (data) {
                    $scope.isSendingEmail = false;
                    alert(data);
                });
            }
            $('#successModal').on('hidden.bs.modal', function () {
                location.href = "Logout";
            });
        };
        $scope.isChangingPhone = false;
        $scope.isSendingPhone = false;
        $scope.isFailingPhoneUpdate = false;
        $scope.textPhone = window.phone;
        $scope.submitPhoneChange = function (value) {
            if (value) {
                $scope.isSendingPhone = true;
                var selecionado = {phone: value, option: 'phone', action: 'userArea'};
                var url = context.ctx + "/data/user.jsp";
                postService.query(selecionado, url).then(function (response) {
                    $scope.isSendingPhone = false;
                    if (response.data.re === 0) {
                        $scope.isFailingPhoneUpdate = true;
                        $timeout(() => $scope.isFailingPhoneUpdate = false, 4000);
                    } else {
                        $scope.textPhone = value;
                    }
                }).catch(function (data) {
                    $scope.isSendingPhone = false;
                    alert(data);
                });
            }
        };
        $scope.phoneLength = (value) => {
            if (typeof value !== "undefined") {
                var phoneLength = 0;
                for (var i = 0; i < value.length; i++) {
                    if (value.charAt(i) !== "(" && value.charAt(i) !== ")" && value.charAt(i) !== "_" && value.charAt(i) !== "-") {
                        ++phoneLength;
                    }
                }
                if (phoneLength >= 10) {
                    $scope.phoneChangeForm.$invalid = false;
                } else {
                    $scope.phoneChangeForm.$invalid = true;
                }
            }
        };
        $scope.isChangingAddress = false;
        $scope.isSendingAddress = false;
        $scope.isFailingAddressUpdate = false;
        $scope.textAddress = window.address;
        $scope.submitAddressChange = function (value) {
            console.log(value);
            if (typeof value !== "undefied") {
                $scope.isSendingAddress = true;
                var selecionado = value;
                selecionado.option = "address";
                selecionado.action = "userArea";
                var url = context.ctx + "/data/user.jsp";
                postService.query(selecionado, url).then(function (response) {

                }).catch(function (data) {
                    alert(data);
                });
            }
        }
        $scope.zblur = function (val) {
            zcodeService.code(val);
        };
    }]);
userAngular.factory('zcodeService', ['getService', function (getService) {
        return {
            code: function (val) {
                function form_wiping() {
                    val.address = "";
                    val.neighbor = "";
                    val.city = "";
                    val.state = "";
                }
                if (val.zcode) {
                    var cep = val.zcode.replace(/\D/g, '');
                    var validacep = /^[0-9]{8}$/;
                    if (validacep.test(cep)) {
                        val.address = "...";
                        val.neighbor = "...";
                        val.city = "...";
                        val.state = "...";
                    }
                    getService.query("//viacep.com.br/ws/" + cep + "/json/").then(function (response) {
                        if (!("erro" in response)) {
                            val.address = response.data.logradouro;
                            val.neighbor = response.data.bairro;
                            val.city = response.data.localidade;
                            val.state = response.data.uf;

                        } else {
                            form_wiping();
                        }
                    });
                } else {
                    form_wiping();
                }
                /********************** FONTE: http://viacep.com.br/ *************************/
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
