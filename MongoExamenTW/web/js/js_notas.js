$(document).ready(function () {
    $("#overlay").show();
    $("#loader").show();

    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#cuerpoNotas tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
    $("#returnInicio").click(function () {
        window.location.replace("InfoExam");
    });
    loadNotas();
});

function loadNotas() {
    var url = "NotesServlet";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";

    $.ajax({
        url: url,
        dataType: 'json',
        success: function (jsn) {


            $("#cuerpoNotas").text("");

            $.each(jsn, function () {
                var dniAux = this.dni;
                var examAux = this.nameExam;
                var notaAux = this.nota;
                $("#cuerpoNotas").append("<tr><td>" + dniAux + "</td><td>" + examAux + "</td><td>" + notaAux + "</td></tr>");
            });
            $("#loader").hide();
            $("#overlay").hide();
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else
                alert(e["responseJSON"]["error"]);
            $("#loader").hide();
            $("#overlay").hide();
        }
    });


}