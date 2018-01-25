<%-- 
    Document   : notes
    Created on : 22-ene-2018, 18:00:18
    Author     : cdore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IES BORJA MOLL - Notas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/js_notas.js"></script>
    </head>
    <body>
        <div class="row">
            <h1 class="text-center" id="title">CALIFICACIONES</h1>
        </div>
        <div class="container">
            <div class="col-md-2">
            </div>

            <div class="col-md-8">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <p>Introduce las 4 últimas cifras y tu letra para buscar tus resultados</p>  
                        <input class="form-control" id="myInput" type="text" placeholder="Buscar por dni">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Tipo Examen</th>
                                    <th>Nota</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpoNotas">


                            </tbody>
                        </table>
                        <div class="text-center">
                    <button type="button" class="btn btn-primary" id="returnInicio">Volver al inicio</button>
                </div>
                    </div>
                    
                </div>
                
            </div>

            <div class="col-md-2">
            </div>
        </div>
        <div id="overlay"></div>
        <div id="loader"></div>
    </body>
</html>
