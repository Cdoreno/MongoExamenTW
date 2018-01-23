function loadExam() {
    var url = "QuestionLoader";
    var emess = "Error interno del servidor, contacte con el administrador de la pagina.";

    $.ajax({
        url: url,
        dataType: 'json',
        success: function (jsn) {
            $.each(jsn, function () {

                var nameAux = this.configureName;
                var difiAux = this.diffId;
                var shAux = this.spaceshipId;
                var lanAux = this.planetId;
                arrayConfig.push([nameAux, difiAux, shAux, lanAux]);

                var anotherDif = (difiAux === 0) ? "Easy" : (difiAux === 1) ? "Medium" : "Hard";
                var anotherShip = (shAux === 0) ? "Spaceship" : "UFO";
                var anotherLand = (lanAux === 0) ? "Moon" : "Mars";
//
//                $("#examen").append("<div class=\"panel panel-primary form-group\">
//                        <div class="panel-heading">
//////////////                            <h4>1. Radios</h4>
//////////////                        </div>//
//////////////                        <div class="panel-body">//
//////////////                            <div class="form-radio">
////////////                                <label>
//////////                                    <input type="radio" name="radio" checked> <span class="label-text">Option 01</span>
////////                                </label>//
////////                            </div>//
////////                            <div class="form-radio">//
////////                                <label>//
////////                                    <input type="radio" name="radio" checked> <span class="label-text">Option 02</span>
//////                                </label>//
//////                            </div>//
//////                            <div class="form-radio">//
//////                                <label>//
//////                                    <input type="radio" name="radio" checked> <span class="label-text">Option 03</span>
////                                </label>//
////                            </div>//
////                            <div class="form-radio">//
////                                <label>//
////                                    <input type="radio" name="radio" checked> <span class="label-text">Option 04</span>
//                                </label>//
//                            </div>//
//                        </div>//
//                    </div>//");

                indexConfig++;

            });
        },
        error: function (e) {
            if (e["responseJSON"] === undefined)
                showAlert(emess);
            else
                showAlert(e["responseJSON"]["error"]);
        }
    });
}