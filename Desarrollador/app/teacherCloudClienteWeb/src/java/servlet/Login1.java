/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import recursos.Usuario;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
public class Login1 extends HttpServlet {

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
//     ServletContext context = null ;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String respuesta = "";
        String valor;//te dice si falla o no

       Modelo modelo = new Modelo();
        Usuario user = new Usuario();

        user.setEmail(email);
        user.setPassword(password);
<<<<<<< HEAD:Desarrollador/app/teacherCloudClienteWeb/src/java/servlet/Registrar.java
        user.setModo(modo);
        user.setMonedero(30);
      
       
        
        if(password.equals(password2)==true){
          modelo.registrar(user);
          
     
            respuesta = "Usuario registrado";

        
        }else{
        respuesta = "contraseñas distintas";
        }

        

        
        if (respuesta.equals("Usuario registrado")) {
=======
        valor = modelo.validarUsuario(user);
        System.out.println(valor);

        if (valor.equals("error")) {
            respuesta = "Usuario invalido";

        } else {
//            ServletContext contexto = request.getServletContext();
//            contexto.setInitParameter("id", id.toString());
            respuesta = "Bienvenido " + email;

        }
        if (respuesta.equals("Bienvenido " + email)) {
>>>>>>> parent of ad24fb0... registrar funciona:Desarrollador/app/teacherCloudClienteWeb/src/java/servlet/Login1.java
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + respuesta + "</h1>");
                out.println(" <form action='http://localhost:8084/teacherCloudClienteWeb/login.html'>");
                out.println("<input type='submit' value='volver' />");
                out.println("</form>");
//                out.println("<a href=\"validarXSD.html\">Valida los recetarios<br/></font></a>");
//                out.println("<a href=\"menuReceta.html\">Menú Receta<br/></font></a>");
//                out.println("<a href=\"menuRecetario.html\">Menú Recetario<br/></font></a>");
//                out.println("<h1> Recetarios disponibles</h1>");
////
//                for (String name : modelo.obtenerRecetarios(id)) {
//                    out.println("<h3>" + name + "</h3>");
//
//                }
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + respuesta + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }

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
