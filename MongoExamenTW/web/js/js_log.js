$(document).ready(function () {
    $("#start").click(function () {

    });
    $("#cancel").click(function () {
        $("#alert").modal("hide");
    });
});

function infoExam() {
    $("#selectExam").text("");
    $("#selectExam").append("<option id=\"-1\" selected disabled>Seleccione un examen...</option>");
    var url = "InfoExam";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";

    if (!($("#dni").val() === "")) {
        $("#overlay").show();
        $("#loader").show();
        var dni = $("#dni").val();
        $.ajax({
            url: url,
            method: "POST",
            data: {dni: dni},
            success: function (jsn) {
                if (jsn["mess"] === "No tienes examenes disposibles.") {
                    $("#alert").modal("hide");
                    $("#dni").focus();
                } else {
                    $.each(jsn, function () {
                        var nameAux = this.nameExam;
                        $("#selectExam").append("<option id=" + nameAux + "> Examen " + nameAux + "</option>");
                    });
                    $("#alert").modal({backdrop: "static", keyboard: "false"});
                    $("#loader").hide();
                    $("#overlay").hide();
                }
            },
            error: function (e) {
                if (e["responseJSON"] === undefined) {
                    $("#loader").hide();
                    $("#overlay").hide();
                    alert(emess);
                } else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
    return false;
}
