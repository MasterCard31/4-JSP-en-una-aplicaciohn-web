<%-- 
    Document   : Formulario
    Created on : 11 abr. 2023, 19:22:15
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <form class="row g-3 m-3 " action="/FormularioContacto/Validaciones.jsp" method="post">
            <div class="col-auto">
                <label for="nombre" class="visually-hidden">Nombre:</label>
                <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre">
            </div>

            <div class="col-auto">
                <label for="dni" class="visually-hidden">DNI:</label>
                <input type="text" name="dni" class="form-control" id="dni" placeholder="DNI">
            </div>
            
            <div class="col-auto">
                <label for="celular" class="visually-hidden">Celular:</label>
                <input type="text" name="celular" class="form-control" id="celular" placeholder="Celular">
            </div>
            
            <div class="col-auto">
                <label for="correo" class="visually-hidden">Correo:</label>
                <input type="text" name="correo" class="form-control" id="correo" placeholder="Correo">
            </div>
            
            <div class="col-auto">
                <label for="contrasenha" class="visually-hidden">Contraseña</label>
                <input type="password" name="contrasenha" class="form-control" id="contrasenha" placeholder="Contraseña">
            </div>
            
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Iniciar sesión</button>
            </div>
        </form>
    </body>
</html>
