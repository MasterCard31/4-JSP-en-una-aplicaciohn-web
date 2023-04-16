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
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body class="container">
        <form class="row g-3 m-3 w-50"  action="/FormularioContacto/AlmacenarDatosServlet" method="post">
            <h1 class="h1 mb-3"> Registrarse </h1>

            <div class="">
                <label for="nombre" class="visually-hidden">Nombre:</label>
                <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" value="${nombreValue}">

                <c:if test="${not empty requestScope.errorNombre}">
                    <small class="error text-muted">${requestScope.errorNombre}</small>
                </c:if>
            </div>

            <div class="d-flex p-2 justify-content-between">            
                <div class="col-auto">
                    <label for="dni" class="visually-hidden">DNI:</label>
                    <input type="text" name="dni" class="form-control" id="dni" placeholder="DNI" value="${dniValue}">
                    <c:if test="${not empty requestScope.errorDni}">
                        <small class="error text-muted">${requestScope.errorDni}</small>
                    </c:if>
                </div>

                <div class="col-auto">
                    <label for="celular" class="visually-hidden">Celular:</label>
                    <input type="text" name="celular" class="form-control" id="celular" placeholder="Celular" value="${celularValue}">
                    <c:if test="${not empty requestScope.errorCelular}">
                        <small class="error text-muted">${requestScope.errorCelular}</small>
                    </c:if>
                </div>
            </div>

            <div class="">
                <label for="correo" class="visually-hidden">Correo:</label>
                <input type="text" name="correo" class="form-control" id="correo" placeholder="Correo" value="${correoValue}">
                <c:if test="${not empty requestScope.errorCorreo}">
                    <small class="error text-muted">${requestScope.errorCorreo}</small>
                </c:if>
            </div>

            <div class="">
                <label for="contrasenha" class="visually-hidden">Contraseña</label>
                <input type="password" name="contrasenha" class="form-control" id="contrasenha" placeholder="Contraseña">
                <c:if test="${not empty requestScope.errorContrasenha}">
                    <p class="error text-muted">${requestScope.errorContrasenha}</p>
                </c:if>
            </div>

            <div class="">
                <button type="submit" class="btn btn-danger mb-3">Registrar</button>
                <button class="btn btn-primary mb-3" onclick="limpiarCampos()">Actualizar</button>
            </div>
            <small class="text-end"><a class="" href="Login.jsp">Iniciar Sesión</a></small>
        </form>
    </body>

    <script>
        function limpiarCampos() {
            document.getElementById("nombre").value = "";
            document.getElementById("dni").value = "";
            document.getElementById("celular").value = "";
            document.getElementById("correo").value = "";
            document.getElementById("contrasenha").value = "";
        }
    </script>

</html>

