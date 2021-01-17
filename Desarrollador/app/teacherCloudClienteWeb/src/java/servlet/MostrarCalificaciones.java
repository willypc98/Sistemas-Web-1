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

/**
 *
 * @author willp
 */
public class MostrarCalificaciones extends HttpServlet {

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
          
               Modelo modelo = new Modelo();
               ConjuntoClases cj = new ConjuntoClases();
        response.setContentType("text/html;charset=UTF-8");
          String emailProfe = request.getParameter("emailProfe");
        String respuesta = "";
        String valor= "alloo";//te dice si falla o no
        cj = modelo.mostrarClasesAsociadas(emailProfe);

   
      

      

     
//            ServletContext contexto = request.getServletContext();
//            contexto.setInitParameter("id", id.toString());
           

        
       
  
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");
                out.println("</head>");
                out.println("<body>");
                    for(Clase clase : cj.getArrayClases()){
                
                
                     out.println("<h3>"+clase.getNombre()+"</h3>");
                     out.println("<h3>"+clase.getCalificacion()+"</h3>");
                    
                }
            
                out.println("</body>");
                out.println("</html>");
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
