<%-- 
    Document   : exam
    Created on : 22-ene-2018, 17:59:48
    Author     : cdore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String examName = (String) request.getAttribute("examName");
String dni = (String) request.getAttribute("dni");
%>
<% if(examName==null || dni==null){
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

        <div class="row">
            <h1 class="text-center" id="title">CUESTIONARIO</h1>
        </div>

        <div class="container">
            <div class="col-md-2">
            </div>

            <div class="col-md-8">
                <form id="examen">
                    <div class="panel panel-primary form-group">
                        <div class="panel-heading">
                            <h4>1. Radios</h4>
                        </div>
                        <div class="panel-body">
                            <div class="form-radio">
                                <label>
                                    <input type="radio" name="radio" checked> <span class="label-text">Option 01</span>
                                </label>
                            </div>
                            <div class="form-radio">
                                <label>
                                    <input type="radio" name="radio" checked> <span class="label-text">Option 02</span>
                                </label>
                            </div>
                            <div class="form-radio">
                                <label>
                                    <input type="radio" name="radio" checked> <span class="label-text">Option 03</span>
                                </label>
                            </div>
                            <div class="form-radio">
                                <label>
                                    <input type="radio" name="radio" checked> <span class="label-text">Option 04</span>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-primary form-group">
                        <div class="panel-heading">
                            <h4>2. Check</h4>
                        </div>
                        <div class="panel-body">
                            <div class="form-check">
                                <label>
                                    <input type="checkbox" name="checkbox"> <span class="label-text">Option 01</span>
                                </label>
                            </div>
                            <div class="form-check">
                                <label>
                                    <input type="checkbox" name="checkbox"> <span class="label-text">Option 02</span>
                                </label>
                            </div>
                            <div class="form-check">
                                <label>
                                    <input type="checkbox" name="checkbox"> <span class="label-text">Option 03</span>
                                </label>
                            </div>
                            <div class="form-check">
                                <label>
                                    <input type="checkbox" name="checkbox" checked> <span class="label-text">Option 04</span>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-primary form-group">
                        <div class="panel-heading">
                            <h4>3. Select list:</h4>
                        </div>
                        <div class="panel-body">
                            <select class="form-control" id="sel1">
                                <option selected disabled>Elija una opción... </option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                            </select>
                        </div>
                    </div>

                    <div class="panel panel-primary form-group">
                        <div class="panel-heading">
                            <h4>4. Mutiple select</h4>
                        </div>
                        <div class="panel-body">
                            <select multiple class="form-control" id="sel2">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                    </div>

                    <div class="panel panel-primary form-group">
                        <div class="panel-heading">
                            <h4>5. Texto:</h4>
                        </div>
                        <div class="panel-body">
                            <input type="text" class="form-control" id="texto">
                        </div>
                    </div>


                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-md-6">
                            </div>
                            <div class="col-md-6">
                                <button type="button" class="btn btn-success btn-sm btn-block">
                                    <span class="fa fa-send"></span>Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="col-md-2">
            </div>
        </div>


    </body>
</html>
