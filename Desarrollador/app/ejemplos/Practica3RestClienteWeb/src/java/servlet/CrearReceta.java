/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Receta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darth
 */
public class CrearReceta extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String dificultad = request.getParameter("dificultad");
        String ingrediente = request.getParameter("ingrediente");
        Double precio = Double.parseDouble(request.getParameter("precio"));

        Modelo modelo = new Modelo();
        Receta receta = new Receta();

        ServletContext contexto = request.getServletContext();
        Integer id = Integer.parseInt(contexto.getInitParameter("id"));

        String delimitador = ",";
        String[] arrayIngrediente = ingrediente.split(delimitador);
        ArrayList<String> arrayListIngrediente = new ArrayList<>();

        for (String elemento : arrayIngrediente) {
            arrayListIngrediente.add(elemento);
            System.out.println("elemento");
        }

        receta.setNombre(nombre);
        receta.setDificultad(dificultad);
        receta.setIngrediente(arrayListIngrediente);

        receta.setPrecio(precio);

        modelo.crearReceta(receta);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Crear Receta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>La receta  " + receta.getNombre() + " fue creada </h3>");
            out.println("<h1>Menú</h1>");
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
