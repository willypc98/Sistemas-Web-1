/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
public class exportRecetario extends HttpServlet {

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
        String urlstr = null;

        String nombre = request.getParameter("nombre");
        
        Modelo mod = new Modelo();

        mod.leerBytes(mod.exportarRecetario(nombre, nombre), nombre);

        try {
            response.reset();
            urlstr = request.getParameter("nombre");
            ServletOutputStream sOutStream = response.getOutputStream();
            streamBinaryData(urlstr, sOutStream, response);
            File someFile = new File("./files/xml/" + nombre + ".xml");
            someFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Modelo modelo = new Modelo();
        
        ServletContext contexto = request.getServletContext();
        Integer id = Integer.parseInt(contexto.getInitParameter("id"));
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menú</title>");
            out.println("</head>");
            out.println("<body>");
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

    private void streamBinaryData(String file, ServletOutputStream outstr, HttpServletResponse resp) throws IOException {
        String ErrorStr = null;

        try {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            String inFile = "./files/xml/" + file + ".xml";
            int tam = (int) new File(inFile).length();
            bis = new BufferedInputStream(new FileInputStream(inFile));

            try {
                //Asignamos el tipo de fichero zip
                resp.setContentType("application/x-zip-compressed"); //Cualquier mime type
                //Seteamos el tamaño de la respuesta
                resp.setContentLength(tam);
                resp.setHeader("Content-Disposition", "attachment;filename=\"" + file + ".xml\"");

                bos = new BufferedOutputStream(outstr);

                // Bucle para leer de un fichero y escribir en el otro.
                byte[] array = new byte[1000];
                int leidos = bis.read(array);
                while (leidos > 0) {
                    bos.write(array, 0, leidos);
                    leidos = bis.read(array);
                }

            } catch (Exception e) {
                e.printStackTrace();
                ErrorStr = "Error Streaming the Data";
                outstr.print(ErrorStr);
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (outstr != null) {
                    outstr.flush();
                    outstr.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Fichero no encontrado");
            resp.sendRedirect("fileNotFound.jsp");

        }

    }
}
