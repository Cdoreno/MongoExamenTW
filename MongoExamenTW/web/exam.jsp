<%-- 
    Document   : exam
    Created on : 22-ene-2018, 17:59:48
    Author     : cdore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String examName = (String) request.getAttribute("examName");
    String dni = (String) request.getAttribute("dni");
%>
<% if (examName == null || dni == null) {
        response.sendRedirect("index.html");
    }%>
<html lang="es">
    <head>
        <title>IES BORJA MOLL - Examen</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="js/js_exam.js"></script>
    </head>
    <body>
        <div id="overlay"></div>
        <div id="loader"></div>
        <div class="row">
            <h1 class="text-center" id="title">CUESTIONARIO</h1>
        </div>
        <div class="container">
            <div class="col-md-2">
            </div>
            <div class="col-md-8">
                <form id="examen">
                </form>

                <div class="modal fade" id="notaModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">     
                            <div class="modal-body text-center">
                                <div>
                                    <p id="parrafoNotaModal"></p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="btn-group btn-group-justified">
                                    <div class="btn-group">
                                        <button class="btn btn-secondary" type="button" id="verNotas">Ver notas</button>
                                    </div>
                                    <div class="btn-group">
                                        <button class="btn btn-primary" id="login" type="button">Inicio</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="alertModal" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">     
                            <div class="modal-body text-center">
                                <div id="parrafosAbout">
                                    <p id="pModal"></p>
                                </div>
                            </div>
                            <div class="modal-footer text-center">
                                <button type="button" class="btn btn-primary" id="okModal">OK</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-md-2">
            </div>
        </div>
        <div class="getDni" id="<%= request.getAttribute("dni") %>"></div>
        <div class="getExam" id="<%= request.getAttribute("examName") %>"></div>
        
    </body>
</html>
