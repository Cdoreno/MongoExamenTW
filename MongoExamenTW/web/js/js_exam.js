$(document).ready(function () {
    $("#overlay").show();
    $("#loader").show();
    loadExam();
});

function loadExam() {
    var url = "QuestionLoader";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";
    $.ajax({
        url: url,
        dataType: 'json',
        success: function (jsn) {
            $.each(jsn, function (index, pregunta) {
                switch (pregunta.tipoPregunta) {
                    case "select":
                        loadSelect(pregunta.id, pregunta.preguntaTexto);
                        loadSelectResp(pregunta.id, pregunta.respOpciones);
                        break;
                    case "text":
                        loadText(pregunta.id, pregunta.preguntaTexto);
                        break;
                    case "radio":
                        loadRadio(pregunta.id, pregunta.preguntaTexto);
                        loadRadioResp(pregunta.id, pregunta.respOpciones);
                        break;
                    case "multiple":
                        loadMultiple(pregunta.id, pregunta.preguntaTexto);
                        loadMultipleResp(pregunta.id, pregunta.respOpciones);
                        break;
                    case "checkbox":
                        loadCheckbox(pregunta.id, pregunta.preguntaTexto);
                        loadCheckboxResp(pregunta.id, pregunta.respOpciones);
                        break;
                }
            }
            );
            $("#loader").hide();
            $("#overlay").hide();
            loadButtonSubmit();
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                alert(emess);
            else {
                alert(e["responseJSON"]["error"]);
            }
            $("#loader").hide();
            $("#overlay").hide();
        }

    });

}

//function sendNotes() {
//    var url = "NotesServlet";
//    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";
//
//    var respuestas = [];
//
//    $.each()
//    )
//
//
//    $.ajax({
//    url: url,
//            data: {respuestas:respuestas},
//    }


    function loadSelect(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group select\">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <h4>" + titulo + "</h4>\n" +
            "                        </div>\n" +
            "                        <div class=\"panel-body\">\n" +
            "                            <select class=\"form-control\" id=\"" + id + "\">\n" +
            "                            </select>\n" +
            "                        </div>\n" +
            "                    </div>");
}

function loadSelectResp(id, respOpciones) {
    var aux = 0;
    $("#" + id + "").append("<option id=\"-1\" selected disabled>Elija una opción... </option>\n");
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<option id=\"" + aux + "\" >" + val + "</option>\n");
        aux++;
    });
}

function loadText(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group text\">\n" +
            "    <div class=\"panel-heading\">\n" +
            "        <h4>" + titulo + "</h4>\n" +
            "    </div>\n" +
            "    <div class=\"panel-body\">\n" +
            "        <input type=\"text\" class=\"form-control\" id=\"" + id + "\">\n" +
            "    </div>\n" +
            "</div>");
}

function loadRadio(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group radio\">\n"
            + "                        <div class=\"panel-heading\">\n"
            + "                            <h4>" + titulo + "</h4>\n"
            + "                        </div>\n"
            + "                        <div class=\"panel-body\" id =" + id + ">\n"
            + "                        </div>\n"
            + "                    </div>");
}

function loadRadioResp(id, respOpciones) {
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<div class=\"form-radio\">\n"
                + "                                <label>\n"
                + "                                    <input type=\"radio\" name=\"" + id + "\"> <span class=\"label-text\">" + val + "</span>\n"
                + "                                </label>\n"
                + "                            </div>\n");
    });
}

function loadMultiple(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group multiple\">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <h4>" + titulo + "</h4>\n" +
            "                        </div>\n" +
            "                        <div class=\"panel-body\">\n" +
            "                            <select multiple class=\"form-control\" id=\"" + id + "\">\n" +
            "                            </select>\n" +
            "                        </div>\n" +
            "                    </div>");
}

function loadMultipleResp(id, respOpciones) {
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<option>" + val + "</option>");
    }
    );
}

function loadCheckbox(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group checkbox\">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <h4>" + titulo + "</h4>\n" +
            "                        </div>\n" +
            "                        <div class=\"panel-body\" id=" + id + ">\n" +
            "                        </div>\n" +
            "                    </div>");
}

function loadCheckboxResp(id, respOpciones) {
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<div class=\"form-check\">\n" +
                "                                <label>\n" +
                "                                    <input type=\"checkbox\" name=\"" + id + "\"> <span class=\"label-text\">" + val + "</span>\n" +
                "                                </label>\n" +
                "                            </div>\n");
    });
}



function loadButtonSubmit() {
    $("#examen").append(" <div class=\"panel-footer\">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-md-6\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-success btn-sm btn-block\">\n" +
            "                                    <span class=\"fa fa-send\"></span>Enviar resultados</button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");
}
