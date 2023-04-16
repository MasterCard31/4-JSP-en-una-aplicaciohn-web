/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tecsup.formulario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author carlo
 */
@WebServlet(name = "AlmacenarDatosServlet", urlPatterns = {"/AlmacenarDatosServlet"})
public class AlmacenarDatosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        //Establecer la conexión a la base de datos
        String jdbcUrl = "jdbc:mysql://localhost:3306/formulariocontacto";
        String usuario = "root";
        String clave = "tecsup";
        Connection conexion = null;

        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(jdbcUrl, usuario, clave);

        //Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        request.setAttribute("nombreValue", nombre);
        String dni = request.getParameter("dni");
        request.setAttribute("dniValue", dni);
        String celular = request.getParameter("celular");
        request.setAttribute("celularValue", celular);
        String correo = request.getParameter("correo");
        request.setAttribute("correoValue", correo);
        String contrasenha = request.getParameter("contrasenha");

        // Variables para guardar los mensajes de error
        String errorNombre = "";
        String errorDni = "";
        String errorCelular = "";
        String errorCorreo = "";
        String errorContrasenha = "";

        // Construir la consulta SQL
        String sql = "SELECT COUNT(*) AS count FROM users WHERE correo = ?";
        // Preparar la consulta
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setString(1, correo);

        // Ejecutar la consulta
        ResultSet rs = stmt.executeQuery();

        //Validar la cantidad de números que pueda tener la contraseña
        int count = 0;
        for (int i = 0; i < contrasenha.length(); i++) {
            if (Character.isDigit(contrasenha.charAt(i))) {
                count++;
            }
        }

        // Validar el nombre
        if (nombre == null || nombre.isEmpty()) {
            errorNombre = "El nombre es obligatorio";
        } else if (nombre.length() < 3) {
            errorNombre = "El campo nombre debe tener más de 3 caracteres.";
        } else if (!nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
            errorNombre = "El campo nombre solo puede contener letras.";
        } else if (!nombre.matches("^[A-Z][a-zA-Z0-9]*$")) {
            errorNombre = "El campo nombre debe iniciar con una mayúscula.";
            
        } //Campo DNI
        else if (dni == null || dni.isEmpty()) {
            errorDni = "El campo DNI es obligatorio.";
        } else if (dni.length() != 8) {
            errorDni = "El campo DNI debe tener 8 caracteres.";
        } else if (!dni.matches("\\d{8}")) {
            errorDni = "El campo DNI no tiene el formato correcto.";
            
        } //Campo Celular
        else if (celular == null || celular.isEmpty()) {
            errorCelular = "El campo celular es obligatorio.";
        } else if (celular.length() != 9) {
            errorCelular = "El campo celular debe tener una longitud de 9 dígitos.";
        } else if (!celular.matches("\\d{9}")) {
            errorCelular = "El campo celular debe contener solo números.";

        } //Campo Correo
        else if (correo == null || correo.isEmpty()) {
            errorCorreo = "El campo correo es obligatorio.";
        } else if (correo.length() > 50) {
            errorCorreo = "El campo correo excede la longitud permitida.";
        } else if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errorCorreo = "El campo correo no tiene un formato válido de correo electrónico.";
        } else if (rs.next()) {
            int veces = rs.getInt("count");
            if (veces > 0) {
                errorCorreo = "Este correo electrónico ya se encuentra registrado."; 
            }
            
        } //Campo Contraseña
        else if (contrasenha == null || contrasenha.isEmpty()) {
            errorContrasenha = "El campo contraseña es obligatorio.";
        } else if (contrasenha.length() < 8 || contrasenha.length() > 50) {
            errorContrasenha = "El campo contraseña debe tener entre 8 y 50 caracteres.";
        } else if (!contrasenha.matches("^(?=.*[A-Z])[a-zA-Z0-9]*$")) {
            errorContrasenha = "El campo contraseña debe tener mínimo una letra mayúscula.";
        } else if (!contrasenha.matches("^(?=.*[a-z])[a-zA-Z0-9]*$")) {
            errorContrasenha = "El campo contraseña debe tener mínimo una letra minúscula.";
        } else if (count < 2) {
            errorContrasenha = "El campo contraseña debe tener al menos dos números.";
        }

        if (!errorNombre.isEmpty() || !errorDni.isEmpty() || !errorCelular.isEmpty() || !errorCorreo.isEmpty() || !errorContrasenha.isEmpty()) {
            request.setAttribute("errorNombre", errorNombre);
            request.setAttribute("errorDni", errorDni);
            request.setAttribute("errorCelular", errorCelular);
            request.setAttribute("errorCorreo", errorCorreo);
            request.setAttribute("errorContrasenha", errorContrasenha);
            RequestDispatcher rd = request.getRequestDispatcher("Formulario.jsp");
            rd.forward(request, response);
        } else {
            // Si no hay errores, guardar los datos en la base de datos

            try {
                //Crear la consulta SQL y ejecutarla
                String consulta = "INSERT INTO users (nombre, dni, celular, correo, contrasenha) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
                    ps.setString(1, nombre);
                    ps.setString(2, dni);
                    ps.setString(3, celular);
                    ps.setString(4, correo);
                    ps.setString(5, contrasenha);

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
            } finally {
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException e) {
                }
            }
            response.sendRedirect("/FormularioContacto/Bienvenido.jsp");
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlmacenarDatosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlmacenarDatosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
