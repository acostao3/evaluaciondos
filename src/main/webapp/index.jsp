<%-- 
    Document   : index
    Created on : 02-10-2022, 13:41:33
    Author     : yorsh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evaluación dos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="jumbotron"> 
            <h1>Datos a ingresar</h1>
            <form action="nex" method="post">
                <div class ="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class ="form-control" placeholder="ingresa el nombre" required name="nombre">
                </div>
                <div class="form-group">
                    <label for="cost">Coste:</label>
                    <input type="text" inputmode="numeric" pattern="[9-0]*" class="form-control" placeholder="Ingresa el coste" required name="cost">
                </div>
                <div class="form-group">
                    <label for="origin">Origen:</label>
                    <input type="text"class="form-control" placeholder="Ingresa el origen" required name="origin">
                </div>
                <div class="form-group">
                    <label for="class1">Clase:</label>
                    <input type="text" class="form-control" placeholder="Ingresa la clase" required name="class1">
                </div>
                <div class="form-group">
                    <label for="class2">Clase:</label>
                    <input type="text" class="form-control" placeholder="Ingresa la clase secundaria, si aplica" name="class2">
                </div>
                <button type="submit" class="btn btn-primary">Ingresar</button>
            </form>
        </div>
    </body>
</html>
