$(document).ready(function () {
    $("#start").click(function () {

    });
    $("#cancel").click(function () {
        $("#alert").modal("hide");
    });
});

function infoExam() {

    var url = "InfoExam";
    var emess = "Unknown error";

    if (!($("#dni").val() === "")) {
        $("#overlay").show();
        $("#loader").show();
        var dni = $("#dni").val();
        $.ajax({
            url: url,
            method: "POST",
            data: {dni: dni},
            success: function (jsn) {
                if (u["mess"] === "No tienes examenes disposibles.") {
                    $("#alert").modal("hide");
                    $("#dni").focus();
                } else {
                    $.each(jsn, function () {
                        var nameAux = this.nameExam;
                        $("#selectExam").append("<option id=" + nameAux + "> Exam " + nameAux + "</option>");
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
