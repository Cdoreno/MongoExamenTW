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
            </div>
            <div class="col-md-2">
            </div>
        </div>
    </body>
</html>
