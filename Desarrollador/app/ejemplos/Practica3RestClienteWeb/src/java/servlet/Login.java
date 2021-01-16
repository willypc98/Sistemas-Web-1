/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Usuario;
import static com.sun.faces.facelets.util.Path.context;

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
public class Login extends HttpServlet {

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
//        ServletContext context = null ;
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String respuesta = "";
        Integer id;

        Modelo modelo = new Modelo();
        Usuario user = new Usuario();

        user.setNombre(nombre);
        user.setPassword(password);
        id = modelo.validarUsuario(user);

        if (id < 0) {
            respuesta = "Usuario invalido";

        } else {
            ServletContext contexto = request.getServletContext();
            contexto.setInitParameter("id", id.toString());
            respuesta = "Bienvenido " + nombre;

        }
        if (respuesta.equals("Bienvenido " + nombre)) {
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
                out.println("<a href=\"validarXSD.html\">Valida los recetarios<br/></font></a>");
                out.println("<a href=\"menuReceta.html\">Menú Receta<br/></font></a>");
                out.println("<a href=\"menuRecetario.html\">Menú Recetario<br/></font></a>");
                out.println("<h1> Recetarios disponibles</h1>");

                for (String name : modelo.obtenerRecetarios(id)) {
                    out.println("<h3>" + name + "</h3>");

                }
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
