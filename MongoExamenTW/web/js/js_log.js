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

        $.ajax({
            url: url,
            dataType: 'json',
            success: function (jsn) {
                $.each(jsn, function () {
                    var nameAux = this.nameExam;
                    $("#selectExam").append("<option id=" + nameAux + "> Exam " + nameAux + "</option>");
                });

                $("#loader").hide();
                $("#alert").modal({backdrop: "static", keyboard: "false"});
                $("#overlay").hide();
            },

            error: function (e) {
                if (e["responseJSON"] === undefined) {
                    alert(emess);
                    $("#loader").hide();
                    $("#overlay").hide();
                } else
                    alert(e["responseJSON"]["error"]);
            }
        });
    }
    return false;
}
