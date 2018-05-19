
$(document).ready(function () {
    var isValidArray = [];
    var isValid = true;
    var i = 0;

    if ($("#scpf") !== "") {
        $("#cpf").css("margin-bottom", "0em");
        $("#scpf").css("display", "block");
        $("#scpf").css("margin-bottom", "0.75em");
    }

    $("#campos_form").submit(function (event) {
        i = 0;
        var controle = $("#controle").val();

        switch (controle) {
            case "10":
                var nome = $("#nome").val();
                var sobrenome = $("#sobrenome").val();

                var listaCampos = [nome, sobrenome];

                var listaNome = ["nome", "sobrenome"];

                for (i = 0; i < listaCampos.length; i++) {
                    verificar(listaCampos[i], listaNome[i]);
                }

                if (isValidArray[0] === false || isValidArray[1] === false) {
                    event.preventDefault();
                }
                break;

            case "11":
                var nome = $("#nome").val();
                var telefone = $("#telefone").val();
                var email = $("#email").val();
                var cep = $("#cep").val();
                var endereco = $("#endereco").val();
                var cidade = $("#cidade").val();
                var uf = $("#uf").val();

                var listaCampos = [nome, telefone, email, cep, endereco, cidade,
                    uf];

                var listaNome = ["nome", "telefone", "email", "cep", "endereco",
                    "cidade", "uf"];

                for (i = 0; i < listaCampos.length; i++) {
                    verificar(listaCampos[i], listaNome[i]);
                }

                if (isValidArray[0] === false || isValidArray[1] === false || isValidArray[2] === false
                        || isValidArray[3] === false || isValidArray[4] === false || isValidArray[5] === false
                        || isValidArray[6] === false) {
                    event.preventDefault();
                }
                break;

            case "12":
                var cpf = $("#cpf").val();
                var nome = $("#nome").val();
                var sobrenome = $("#sobrenome").val();
                var sexo = $("#sexo").val();
                var dtNascimento = $("#dtNascimento").val();
                var telefone = $("#telefone").val();
                var celular = $("#celular").val();
                var email = $("#email").val();
                var codBiblioteca = $("#codbiblioteca").val();
                var senha = $("#senha").val();
                var confSenha = $("#confsenha").val();
                var cep = $("#cep").val();
                var endereco = $("#endereco").val();
                var cidade = $("#cidade").val();
                var uf = $("#uf").val();

                var listaCampos = [cpf, nome, sobrenome, sexo, dtNascimento,
                    telefone, celular, email, codBiblioteca, senha, confSenha,
                    cep, endereco, cidade, uf];

                var listaNome = ["cpf", "nome", "sobrenome", "sexo", "dtNascimento",
                    "telefone", "celular", "email", "codbiblioteca", "senha",
                    "confsenha", "cep", "endereco",
                    "cidade", "uf"];

                for (i = 0; i < listaCampos.length; i++) {
                    if (listaNome[i] === "senha") {
                        verificarSenha(listaCampos[i], listaNome[i]);
                    } else if (listaNome[i] === "confsenha") {
                        verificarConfSenha(listaCampos[i], senha, listaNome[i]);
                    } else {
                        verificar(listaCampos[i], listaNome[i]);
                    }
                }

                if (cpf !== "")
                    testarCpf(cpf);

                if (isValid === false || isValidArray[0] === false || isValidArray[1] === false
                        || isValidArray[2] === false || isValidArray[3] === false
                        || isValidArray[4] === false || isValidArray[5] === false
                        || isValidArray[6] === false || isValidArray[7] === false
                        || isValidArray[8] === false || isValidArray[9] === false
                        || isValidArray[10] === false || isValidArray[11] === false
                        || isValidArray[12] === false || isValidArray[13] === false
                        || isValidArray[14] === false || isValidArray[15] === false) {

                    event.preventDefault();
                }
                break;

            case "13":
                var cpf = $("#cpf").val();
                var nome = $("#nome").val();
                var sobrenome = $("#sobrenome").val();
                var sexo = $("#sexo").val();
                var dtNascimento = $("#dtNascimento").val();
                var telefone = $("#telefone").val();
                var celular = $("#celular").val();
                var email = $("#email").val();
                var senha = $("#senha").val();
                var confSenha = $("#confsenha").val();
                var cep = $("#cep").val();
                var endereco = $("#endereco").val();
                var cidade = $("#cidade").val();
                var uf = $("#uf").val();

                var listaCampos = [cpf, nome, sobrenome, sexo, dtNascimento,
                    telefone, celular, email, senha, confSenha, cep, endereco,
                    cidade, uf];

                var listaNome = ["cpf", "nome", "sobrenome", "sexo", "dtNascimento",
                    "telefone", "celular", "email", "senha", "confsenha", "cep", "endereco",
                    "cidade", "uf"];

                for (i = 0; i < listaCampos.length; i++) {
                    if (listaNome[i] === "senha") {
                        verificarSenha(listaCampos[i], listaNome[i]);
                    } else if (listaNome[i] === "confsenha") {
                        verificarConfSenha(listaCampos[i], senha, listaNome[i]);
                    } else {
                        verificar(listaCampos[i], listaNome[i]);
                    }
                }

                if (cpf !== "")
                    testarCpf(cpf);

                if (isValid === false || isValidArray[0] === false || isValidArray[1] === false || isValidArray[2] === false
                        || isValidArray[3] === false || isValidArray[4] === false || isValidArray[5] === false
                        || isValidArray[6] === false || isValidArray[7] === false || isValidArray[8] === false
                        || isValidArray[9] === false || isValidArray[10] === false || isValidArray[11] === false
                        || isValidArray[12] === false || isValidArray[13] === false || isValidArray[14] === false) {
                    event.preventDefault();
                }
                break;

            case "14":
                var codLivro = $("#codLivro").val();
                var codEscritor = $("#codEscritor").val();
                var codTipo = $("#codTipo").val();
                var codBiblioteca = $("#codBiblioteca").val();
                var nome = $("#nome").val();
                var editora = $("#editora").val();
                var volume = $("#vol").val();
                var edicao = $("#edicao").val();
                var idioma = $("#idioma").val();
                var numPagina = $("#numPagina").val();
                var anoLancamento = $("#anoLancamento").val();
                var dataCompra = $("#dataCompra").val();
                var descricao = $("#descricao").val().trim();
                var formato = $("#formato").val();

                var listaCampos = [codLivro, codEscritor, codTipo, codBiblioteca,
                    nome, editora, volume, edicao, idioma, numPagina, anoLancamento, 
                    dataCompra, descricao, formato];

                var listaNome = ["codLivro", "codEscritor", "codTipo", "codBiblioteca",
                    "nome", "editora", "vol", "edicao", "idioma", "numPagina",
                    "anoLancamento", "dataCompra", "descricao", "formato"];

                for (i = 0; i < listaCampos.length; i++) {
                    verificar(listaCampos[i], listaNome[i]);
                }

                if (isValidArray[0] === false || isValidArray[1] === false 
                        || isValidArray[2] === false || isValidArray[3] === false 
                        || isValidArray[4] === false || isValidArray[5] === false
                        || isValidArray[6] === false || isValidArray[7] === false 
                        || isValidArray[8] === false || isValidArray[9] === false
                        || isValidArray[10] === false || isValidArray[11] === false
                        || isValidArray[12] === false || isValidArray[13] === false) {
                    
                    event.preventDefault();
                }
                break;
            case "16":
                var pesEmpes = $("#pesEmpes").val();
                
                var listaCampos = [pesEmpes];

                var listaNome = ["pesEmpes"];
                
                verificar(listaCampos[i], listaNome[i]);
                
                if(isValidArray[0] === false){
                    event.preventDefault();
                }
                break;

            default :
                alert("Ainda não foi padronizado.");
                event.preventDefault();
                break;
        }
    });

    function verificar(campo, scampo) {
        if (campo.length === 0 || campo === "s") {
            $("#" + scampo).css("margin-bottom", "0em");
            $("#s" + scampo).css("display", "block");
            $("#s" + scampo).css("margin-bottom", "0.75em");
            if (scampo === "dtNascimento") {
                $("#s" + scampo).text("Data incorreta.");
            } else
                $("#s" + scampo).text("Este campo é de preenchimento obrigatório.");
            isValidArray[i] = false;
        } else {
            $("#s" + scampo).css("display", "none");
            $("#" + scampo).css("margin-bottom", "0.75em");
            isValidArray [i] = true;
        }
    }

    function verificarSenha(campo, scampo) {
        if (campo.length === 0) {
            $("#" + scampo).css("margin-bottom", "0em");
            $("#s" + scampo).css("display", "block");
            $("#s" + scampo).css("margin-bottom", "0.75em");
            $("#s" + scampo).text("Este campo é de preenchimento obrigatório.");
            isValidArray[i] = false;
        } else if (campo.length < 6) {
            $("#" + scampo).css("margin-bottom", "0em");
            $("#s" + scampo).css("display", "block");
            $("#s" + scampo).css("margin-bottom", "0.75em");
            $("#s" + scampo).text("Senha precisa ter no mínimo 6 caracteres.");
            isValidArray[i] = false;
        } else {
            $("#s" + scampo).css("display", "none");
            $("#" + scampo).css("margin-bottom", "0.75em");
            isValidArray[i] = true;
        }
    }

    function verificarConfSenha(campo, campo2, scampo) {
        if (campo.length === 0) {
            $("#" + scampo).css("margin-bottom", "0em");
            $("#s" + scampo).css("display", "block");
            $("#s" + scampo).css("margin-bottom", "0.75em");
            $("#s" + scampo).text("Este campo é de preenchimento obrigatório.");
            isValidArray[i] = false;
        } else if (campo !== campo2) {
            $("#" + scampo).css("margin-bottom", "0em");
            $("#s" + scampo).css("display", "block");
            $("#s" + scampo).css("margin-bottom", "0.75em");
            $("#s" + scampo).text("O valor precisa ser igual ao campo senha.");
            isValidArray[i] = false;
        } else {
            $("#s" + scampo).css("display", "none");
            $("#" + scampo).css("margin-bottom", "0.75em");
            isValidArray[i] = true;
        }
    }

    function testarCpf(cpf) {

        var cpfValid = true;
        var soma = 0;
        var resto = 0;
        var isValid = true;

        var splitCpf = cpf.split(".");
        cpf = "";
        for (var i in splitCpf) {
            if (i == 2) {
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
            $("#cpf").css("margin-bottom", "0em");
            $("#scpf").css("display", "block");
            $("#scpf").css("margin-bottom", "0.75em");
            $("#scpf").text("Cpf inválido.");

            isValid = false;
            cpfValid = false;
        }

        for (i = 0; i < 9; i++) {
            soma += cpf.substr(i, 1) * (10 - i);
        }
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) {
            resto = 0;
        }
        soma = 0;
        if (resto === parseInt(cpf.substr(9, 1))) {
            for (i = 0; i < 10; i++) {
                soma += cpf.substr(i, 1) * (11 - i);
            }
            resto = (soma * 10) % 11;
            if (resto === 10 || resto === 11) {
                resto = 0;
            }
            if (resto !== parseInt(cpf.substr(10))) {
                $("#cpf").css("margin-bottom", "0em");
                $("#scpf").css("display", "block");
                $("#scpf").css("margin-bottom", "0.75em");
                $("#scpf").text("CPF inválido.");

                isValid = false;
                cpfValid = false;
            }
        } else {
            $("#cpf").css("margin-bottom", "0em");
            $("#scpf").css("display", "block");
            $("#scpf").css("margin-bottom", "0.75em");
            $("#scpf").text("CPF inválido.");

            isValid = false;
            cpfValid = false;
        }
        if (cpfValid) {
            $("#cpf").css("margin-bottom", "0em");
            $("#scpf").css("display", "block");
            $("#scpf").css("margin-bottom", "0.75em");
            $("#scpf").text("Cpf válido.");
        }
    }
});
