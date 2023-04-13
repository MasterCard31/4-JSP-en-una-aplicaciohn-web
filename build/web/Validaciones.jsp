<%-- 
    Document   : Validaciones
    Created on : 11 abr. 2023, 19:29:45
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            String celular = request.getParameter("celular");
            String correo = request.getParameter("correo");
            String contrasenha = request.getParameter("contrasenha");
            
            //Validar la cantidad de números que pueda tener la contraseña
            int count = 0;
            for (int i = 0; i < contrasenha.length(); i++) {
              if (Character.isDigit(contrasenha.charAt(i))) {
                count++;
              }
            }

            //Campo Nombre
            if (nombre == null || nombre.isEmpty()) {
                out.println("El campo nombre es obligatorio.");
                
            }else if(nombre.length() < 3){
                out.println("El campo nombre debe tener más de 3 caracteres.");
                
            }else if(!nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")){
                out.println("El campo nombre solo puede contener letras.");
                
            }else if(!nombre.matches("^[A-Z][a-zA-Z0-9]*$")){
                out.println("El campo nombre debe iniciar con una mayúscula.");
                
            }

            //Campo DNI
            else if (dni == null || dni.isEmpty()) {
                out.println("El campo DNI es obligatorio.");
                
            }else if(dni.length() != 8){
                out.println("El campo DNI debe tener 8 caracteres.");
                
            }else if(!dni.matches("\\d{8}")){
                out.println("El campo no tiene el formato correcto.");
            }

            //Campo Celular
            else if (celular == null || celular.isEmpty()) {
                out.println("El campo celular es obligatorio.");
                
            }else if(celular.length() != 9){
                out.println("El campo celular debe tener una longitud de 9 dígitos.");
                
            }else if(!celular.matches("\\d{9}")){
                out.println("El campo celular debe contener solo números");
                
            }

            //Campo Correo
            else if (correo == null || correo.isEmpty()) {
                out.println("El campo correo nombre es obligatorio.");
                
            }else if(correo.length() > 50){
                out.println("el campo correo excede la longitud permitida.");
                
            }else if(!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                out.println("El campo correo no tiene un formato válido de correo electrónico.");
                
            }
            
            //Campo Contraseña
            
            else if (contrasenha == null || contrasenha.isEmpty()) {
                out.println("El campo contraseña es obligatorio.");
                
            }else if(contrasenha.length() < 8 || contrasenha.length() > 50){
                out.println("El campo contraseña debe tener entre 8 y 50 caracteres.");
                
            }else if(!contrasenha.matches("^(?=.*[A-Z])[a-zA-Z0-9]*$")){
                out.println("El campo contraseña debe tener mínimo una letra mayúscula.");
                
            }
            else if(!contrasenha.matches("^(?=.*[a-z])[a-zA-Z0-9]*$")){
                out.println("El campo contraseña debe tener mínimo una letra minúscula.");
                
            }else if(count < 2) {
                out.println("El campo contraseña debe tener al menos dos números.");
                
            }else{
                response.sendRedirect("/FormularioContacto/Bienvenido.jsp");
            }
        %>
    </body>
</html>
