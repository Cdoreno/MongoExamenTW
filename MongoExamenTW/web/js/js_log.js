var myVar;

$(document).ready(function () {
    $("#login").click(function () {
        document.getElementById("loader").style.display = "block";
        myVar = setTimeout(infoExam, 300);
        infoExam();

    });
});

function infoExam() {

    var url = "InfoExam";
    var emess = "Unknown error";


    $.ajax({
        url: url,
        dataType: 'json',
        success: function (jsn) {
            alert(jsn);
            $.each(jsn, function () {
                var nameAux = this.nameExam;
                alert(nameAux);
                $("#selectExam").append("<option id=" + nameAux + "> Exam " + nameAux + "</option>");
            });
            document.getElementById("loader").style.display = "none";
            $("#alert").modal();
        },

        error: function (e) {
            if (e["responseJSON"] === undefined){
                document.getElementById("loader").style.display = "none";
                alert(emess);
            }else
                alert(e["responseJSON"]["error"]);
        }
    });
}