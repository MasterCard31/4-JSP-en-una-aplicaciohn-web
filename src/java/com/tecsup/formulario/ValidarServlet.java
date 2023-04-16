/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tecsup.formulario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author carlo
 */
@WebServlet(name = "ValidarServlet", urlPatterns = {"/ValidarServlet"})
public class ValidarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los valores de correo y contraseña desde el formulario de inicio de sesión
        String correo = request.getParameter("correo");
        String contrasenha = request.getParameter("contrasenha");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
                try {
            // Conectar a la base de datos MySQL
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/formulariocontacto", "root", "tecsup");

            // Verificar el nombre de usuario y la contraseña en la tabla de usuarios
            String sql = "SELECT * FROM users WHERE correo=? AND contrasenha=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contrasenha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Si los detalles son correctos, crear una sesión de usuario y redirigir a la página de inicio de sesión exitoso
                HttpSession session = request.getSession();
                session.setAttribute("correo", correo);
                response.sendRedirect("Bienvenido.jsp");
            } else {
                // Si los detalles son incorrectos, enviar un mensaje de error y redirigir al usuario a la página de inicio de sesión
                request.setAttribute("error", "Usuario o contraseña inválidos");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
