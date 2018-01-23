$(document).ready(function () {
    $("#start").click(function () {
        typeExam();
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
                if (jsn["mess"] === "No tienes examenes disponibles.") {
                    $("#loader").hide();
                    $("#overlay").hide();
                    $("#dni").focus();
                    alert(jsn["mess"]);
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

function typeExam() {

    var url = "QuestionLoader";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";

    var dni = $("#dni").val();
    var examType = $("#selectExam option:selected").attr("id")

    if (dni !== null && examType !== "-1") {
        $("#overlay").show();
        $("#loader").show();
        $("#alert").modal("hide");

        $.ajax({
            url: url,
            method: "POST",
            data: {dni: dni, nameExam: examType},
            success: function () {

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


    } else {
        alert("No tiene seleccionado ningún examen.");
    }
    return false;
}
