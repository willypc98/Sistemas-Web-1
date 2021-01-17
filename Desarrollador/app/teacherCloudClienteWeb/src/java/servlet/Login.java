/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import recursos.Clase;
import recursos.ConjuntoClases;
import recursos.Usuario;

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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String respuesta = "";
        String valor= "error";//te dice si falla o no

       Modelo modelo = new Modelo();
        Usuario user = new Usuario();

        user.setEmail(email);
        user.setPassword(password);
        valor = modelo.validarUsuario(user);
        System.out.println(valor);
        ConjuntoClases cj = new ConjuntoClases();
        cj= modelo.buscarClasesPublicada();
        if (valor.equals("error")) {
            respuesta = "Usuario invalido";

        } else {
//            ServletContext contexto = request.getServletContext();
//            contexto.setInitParameter("id", id.toString());
            respuesta = "Bienvenido" + email;

        }
        if(modelo.mostrarModo(email).equals("alumno")){
        if (respuesta.equals("Bienvenido" + email)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel='stylesheet' href='css/style-botones.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='menu'>");
                out.println("<div class='menu2'>");
                out.println("<div class='buscador'>");
                out.println("<form method='post' action='/teacherCloudClienteWeb/BuscarClase' name='Incidencia'>");
                out.println("<input id='barra' type='search' name='nombreClase' placeholder='introducir la clase y dar enter' >");
                //out.println("<input id='boton' type='submit' value='Buscar'>");
                out.println("</form>");
                out.println("</div>");
                out.println("<div class='reportar'>");
                out.println("<form action='http://localhost:8084/teacherCloudClienteWeb/incidencia.html%22%3E'>");
                out.println("<input id='boton' type='submit' value='reportar'>");
                out.println("</form>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<h1>Clases disponibles</h1>");
                for(Clase clase : cj.getArrayClases()){
                
                
                     out.println("<h3>"+clase.getNombre()+"</h3>");
                    
                    
                }
                        
                out.println("</div>");
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

      }else{
         response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + " hola profe" + "</h1>");
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
