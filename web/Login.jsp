<%-- 
    Document   : Login
    Created on : 14 abr. 2023, 10:56:29
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <form class="row g-3 m-3 " action="/FormularioContacto/ValidarServlet" method="POST">
            <h1 class="h1 mb-3"> Iniciar Sesión </h1>
            <div class="col-auto">
                <label for="correo" class="visually-hidden">Correo electrónico:</label>
                <input type="text" name="correo" class="form-control" id="correo" placeholder="Correo Electrónico">
            </div>
            <div class="col-auto">
                <label for="contrasenha" class="visually-hidden">Contraseña</label>
                <input type="password" name="contrasenha" class="form-control" id="contrasenha" placeholder="Contraseña">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3">Iniciar sesión</button>
                <a href="Formulario.jsp" class="btn btn-danger mb-3">Registrarse</a>
            </div>
        </form>
</body>
</html>
