$(document).ready(function () {
    $("#login").click(function () {
        $("#alert").modal();
    });
});


function infoExam() {

    var url = "InfoExam";
    var mensaje = "Unknown error";

    var dni = $("#dni").val();

    $.ajax({
        method: "GET",
        url: url,
        data: {dni: dni},
        success: function (u) {
            location.reload();
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                showAlert(mensaje);
            else
                showAlert(e["responseJSON"]["error"]);
        }
    });

    return false;

}