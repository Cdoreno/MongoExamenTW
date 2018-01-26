var respuestas = [];
var rServer;
var nota = 0;
var midni;
var examType;

$(document).ready(function () {
    $("#overlay").show();
    $("#loader").show();

    loadExam();

    $("#verNotas").click(function () {
        window.location.replace("notes.jsp");
    });
    $("#login").click(function () {
        window.location.replace("InfoExam");
    });
    $("#okModal").click(function () {
        $("#alertModal").modal('hide');
    });
    midni = ($(".getDni").attr('id'));
    examType = ($(".getExam").attr('id'));
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
                showAlert(emess);
            else {
                showAlert(e["responseJSON"]["error"]);
            }
            $("#loader").hide();
            $("#overlay").hide();
        }

    });

}

function sendNotes() {

}

function loadSelect(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group pregunta select\">\n" +
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
    $("#" + id + "").append("<option value=\"-1\" selected disabled>Elija una opción... </option>\n");
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<option value=\"" + aux + "\" >" + val + "</option>\n");
        aux++;
    });
}

function loadText(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group pregunta text\">\n" +
            "    <div class=\"panel-heading\">\n" +
            "        <h4>" + titulo + "</h4>\n" +
            "    </div>\n" +
            "    <div class=\"panel-body\">\n" +
            "        <input type=\"text\" class=\"form-control\" id=\"" + id + "\">\n" +
            "    </div>\n" +
            "</div>");
}

function loadRadio(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group pregunta\">\n"
            + "                        <div class=\"panel-heading\">\n"
            + "                            <h4>" + titulo + "</h4>\n"
            + "                        </div>\n"
            + "                        <div class=\"panel-body radio\" id =" + id + ">\n"


            + "                        </div>\n"
            + "                    </div>");
}

function loadRadioResp(id, respOpciones) {
    var aux = 0;
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<div class=\"form-radio\">\n"
                + "                                <label>\n"
                + "                                    <input type=\"radio\" name=\"" + id + "\" value=\"" + aux + "\" > <span class=\"label-text\">" + val + "</span>\n"
                + "                                </label>\n"
                + "                            </div>\n");
        aux++;
    });
}

function loadMultiple(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group pregunta multiple\">\n" +
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
    var aux = 0;
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<option value=" + aux + ">" + val + "</option>");
        aux++;
    }
    );
}

function loadCheckbox(id, titulo) {
    $("#examen").append("<div class=\"panel panel-primary form-group pregunta \">\n" +
            "                        <div class=\"panel-heading\">\n" +
            "                            <h4>" + titulo + "</h4>\n" +
            "                        </div>\n" +
            "                        <div class=\"panel-body checkbox\" id=" + id + ">\n" +
            "                        </div>\n" +
            "                    </div>");
}

function loadCheckboxResp(id, respOpciones) {
    var aux = 0;
    $.each(respOpciones, function (i, val) {
        $("#" + id + "").append("<div class=\"form-check\">\n" +
                "                                <label>\n" +
                "                                    <input type=\"checkbox\" name=\"" + id + "\" value=\"" + aux + "\"> <span class=\"label-text\">" + val + "</span>\n" +
                "                                </label>\n" +
                "                            </div>\n");
        aux++;
    });
}



function loadButtonSubmit() {
    $("#examen").append(" <div class=\"panel-footer \">\n" +
            "                        <div class=\"row\">\n" +
            "                            <div class=\"col-md-6\">\n" +
            "                            </div>\n" +
            "                            <div class=\"col-md-6\">\n" +
            "                                <button type=\"button\" class=\"btn btn-success btn-sm btn-block\" id=\"enviarNotas\">\n" +
            "                                    <span class=\"fa fa-send\"></span>Enviar resultados</button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>");

    $("#enviarNotas").click(function () {
        enviarDatos();
    });
}

function enviarDatos() {
    miCorreccion();
    /*   if (comprobarSelect()) {
     cogerSelect();
     cogerText();
     cogerRadio();
     cogerCheckbox();
     
     var url = "CorrectNotes";
     var emess = "Error interno del servidor, contacte con el administrador de la pagina.";
     
     $("#overlay").show();
     $("#loader").show();
     alert(respuestas),
     $.ajax({
     url: url,
     method: "POST",
     dataType: 'JSON',
     data: {respuestas: respuestas},
     success: function () {
     alert(respuestas);
     //var nota="8" // PONER NOTA CORRECTA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     //mostrarNota(nota);
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
     
     }*/

}

function cogerSelect() {
    $("select").each(function () {
        var idSelect = $(this).attr("id");
        var optAux = $(this).val();

        respuestas.push("{\"id\":\"" + idSelect + "\",\"respuesta\":[\"" + optAux + "\"]}");
    });
}

function cogerText() {
    $("input[type=text]").each(function () {
        var idTxt = $(this).attr("id");
        var txtAux = $(this).val();

        respuestas.push("{\"id\":\"" + idTxt + "\",\"respuesta\":[\"" + txtAux + "\"]}");
    });
}

function cogerRadio() {
    $(".radio").each(function () {
        var idRadio = $(this).attr("id");
        var radioAux = $('input[name=' + idRadio + ']:checked').val();

        respuestas.push("{\"id\":\"" + idRadio + "\",\"respuesta\":[\"" + radioAux + "\"]}");
    });
}

function cogerCheckbox() {
    $(".checkbox").each(function () {
        var idCheck = $(this).attr("id");
        var checkArray = [];
        $('input[name=' + idCheck + ']:checked').each(function () {
            var checkAux = $(this).val();
            checkArray.push(checkAux);
        });
        respuestas.push("{\"id\":\"" + idCheck + "\",\"respuesta\":[\"" + checkArray + "\"]}");
    });
}

function comprobarSelect() {
    var aux = true;
    $("select").each(function () {
        if ($(this).val() == "" || $(this).val() == null) {
            $(this).focus();
            showAlert("Por favor, elija una opción");
            aux = false;
            return false;
        }
    });
    if (aux) {
        return comprobarText();
    }
}



function comprobarText() {
    var aux = true;
    $("input[type=text]").each(function () {
        if ($(this).val() == "" || $(this).val() == null) {
            $(this).focus();
            showAlert("Por favor, escriba una respuesta");
            aux = false;
            return false;
        }
    });
    if (aux) {
        return comprobarRadio();
    }
}



function comprobarRadio() {
    var aux = true;
    $(".radio").each(function () {
        var radioAux = $('input[name=' + $(this).attr("id") + ']:checked').val();
        if (!(radioAux >= 0)) {
            document.getElementsByName($(this).attr("id"))[0].focus();
            showAlert("Por favor, seleccione una opción");
            aux = false;
            return false;
        }
    });
    if (aux) {
        return comprobarCheckbox();
    }
}

function comprobarCheckbox() {
    var aux = true;
    $(".checkbox").each(function () {
        var checkAux = $('input[name=' + $(this).attr("id") + ']:checked').val();
        if (!(checkAux >= 0)) {
            document.getElementsByName($(this).attr("id"))[0].focus();
            showAlert("Por favor, elija por lo menos una opción");
            aux = false;
            return false;
        }
    });
    if (aux) {
        return true;
    }
}

function mostrarNota(nota) {
    $("#parrafoNotaModal").text("Tu nota final es " + nota);
    $("#notaModal").modal({backdrop: "static", keyboard: "false"});
}

function showAlert(text) {
    $("#pModal").text(text);
    $("#alertModal").modal({backdrop: "static", keyboard: "false"});
}

function miCorreccion() {

    if (comprobarSelect()) {
        window.location.hash = '#title';
    $("#overlay").show();
    $("#loader").show();
    cogerSelect();
    cogerText();
    cogerRadio();
    cogerCheckbox();
    cogerRespServer();

    

}

}

function cogerRespServer() {
    var url = "CorrectNotes";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";
    $.ajax({
        url: url,
        dataType: 'json',
        success: function (jsn) {
            rServer = jsn;
            corregirPreguntas();
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                showAlert(emess);
            else {
                showAlert(e["responseJSON"]["error"]);
            }

        }

    });

}

function corregirPreguntas() {

    $.each(respuestas, function (index, pregunta) {
        var myObj = JSON.parse(respuestas[index]);
        var idPreg = myObj.id;
        $.each(rServer, function (indexAux, preguntaAux) {
            if (idPreg === rServer[indexAux].id) {
                switch (rServer[indexAux].tipoPregunta) {
                    case "select":
                        corregirSelect(myObj.respuesta, rServer[indexAux].respCorrecta);
                        break;
                    case "text":
                        corregirTxt(myObj.respuesta, rServer[indexAux].respCorrecta);
                        break;
                    case "radio":
                        corregirRadio(myObj.respuesta, rServer[indexAux].respCorrecta);
                        break;
                    case "multiple":
                        corregirMultiple(myObj.respuesta, rServer[indexAux].respCorrecta);
                        break;
                    case "checkbox":
                        corregirCheckbox(myObj.respuesta, rServer[indexAux].respCorrecta);
                        break;
                }
            }
        });

    });
    guardarNotaServlet();
}

function corregirSelect(txt1, txt2) {
    if (txt1.toString() === txt2.toString()) {
        nota += 1;
    }
}

function corregirTxt(txt1, txt2) {
    if (txt1.toString().toLowerCase() === txt2.toString().toLowerCase()) {
        nota += 1;
    }
}

function corregirRadio(txt1, txt2) {
    if (txt1.toString() === txt2.toString()) {
        nota += 1;
    }
}

function corregirMultiple(txt1, txt2) {
    var array = JSON.parse("[" + txt1 + "]");
    var notaAux = 1 / txt2.length;
    for (var i = 0; i < array.length; i++) {
        var heSumado = false;
        for (var j = 0; j < txt2.length; j++) {
            if (array[i].toString() === txt2[j].toString()) {
                nota += notaAux;
                heSumado = true;
            }
        }
        if (!heSumado) {
            nota -= notaAux;
        }
    }

}

function corregirCheckbox(txt1, txt2) {
    var array = JSON.parse("[" + txt1 + "]");
    var notaAux = 1 / txt2.length;
    for (var i = 0; i < array.length; i++) {
        var heSumado = false;
        for (var j = 0; j < txt2.length; j++) {
            if (array[i].toString() === txt2[j].toString()) {
                nota += notaAux;
                heSumado = true;
            }
        }
        if (!heSumado) {
            nota -= notaAux;
        }
    }
}

function guardarNotaServlet(){
    var url = "NotesServlet";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";
    alert(midni);
    alert(examType);
    alert(nota);
    $.ajax({
        url: url,
        method: "POST",

        data: {dni: midni, examName: examType, nota: nota.toFixed(2)}
        ,
        success: function () {
            var notaAux = nota.toFixed(2);
            mostrarNota(notaAux);
        }
        ,
        error: function (e) {
            if (e["responseJSON"] === undefined) {
                $("#loader").hide();
                $("#overlay").hide();
                alert(emess);
            } else
                alert(e["responseJSON"]["error"]);
        }
    }
    );
}