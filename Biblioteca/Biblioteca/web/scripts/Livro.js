$(document).ready(function () {
    var mensagem = $(".mensagem").text().trim();
    $(".mensagem").css("display", "none");

    if (mensagem.length > 0) {
        $("#fazer").attr("disabled", "disabled");
        $(".mensagem").css("display", "block");
    }
//    else{
//        $("#fazer").removeAttr("disabled");
//        $("#mensagem").css("display", "none");
//    }
});


