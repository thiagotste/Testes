$(document).ready(function () {
//    if ($("#email").val() != "") {
//        $("#nao_tenho_form span").text("Este email ja esta cadastrado.");
//        $("#nao_tenho_form span").show();
//        $(".criar").css("margin-top", "1em");
//    } else {
//        $("#nao_tenho_form span").hide();
//        $(".criar").css("margin-top", "0");
//    }
//    $("#nao_tenho_form").submit(function(event) {
//        var email = $("#email").val();
//        var isValid = true;
//
//        if (email.length === 0) {
//            $("#nao_tenho_form span").text("Este campo e obrigatorio.");
//            $("#nao_tenho_form span").show();
//            $(".criar").css("margin-top", "1em");
//            isValid = false;
//        }
//
//        if (isValid === false)
//            event.preventDefault();
//    });

    
    if ($(".er_ms").text().length !== 0) {
        $("#tenho h1").css("padding-bottom", "0");
        $(".er_ms").css("margin-bottom", "0.9em")
    }

    $("#tem_form").submit(function (event) {
        var email_cpf = $("#email_cpf").val();
        var senha = $("#senha_cpf").val();
        var isValid = true;
        //$(".e_message").hide();

        if (email_cpf.length === 0) {
            $("#email_cpf").css("margin-bottom", "0");
            $(".e_message").text("O preenchimento deste campo e obrigatorio.");
            $(".e_message").show();

            isValid = false;
        }
        if (senha.length === 0) {
            isValid = false;
        }
        
        if (isValid === false)
            event.preventDefault();
    });
});
