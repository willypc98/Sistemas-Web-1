/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Funcionalidad.Modelo;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("Usuario")
public class GenericResource {
Modelo modelo= new Modelo();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of servlet.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
     @Path("hola")
      @Produces(MediaType.TEXT_PLAIN)
    public String getXml() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        //TODO return proper representation object
         ArrayList<String> emailUser = new ArrayList();
        emailUser= modelo.obtenerEmail();
       return emailUser.get(0);
    }
    //hace falta ponerlo en post pero ya responde
        @GET
     @Path("Login")
      @Produces(MediaType.TEXT_PLAIN)
    public String autenticar() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        //cuando este en el cliente hay que quitarlo y solo dejar en boolean
        String auxTextoRespuesta="error, email o pass erroneas";
      
        if (modelo.autenticar("a@a.com", "a")==true){
            auxTextoRespuesta="todo correcto";
        }
       return auxTextoRespuesta;
    }
    
    //falta ponerlo a put y listo
        @GET
     @Path("Registrarse")
      @Produces(MediaType.TEXT_PLAIN)
    public String registrase() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
     
       modelo.registrar("profe", "gaga", "q", "q");
        
       return "hola buenas";
    }
    
    //Alumno
    
            @GET
     @Path("Alumno/BuscarClase")
      @Produces(MediaType.TEXT_PLAIN)
    public String buscarClase() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
      ArrayList<String> clase = new ArrayList();
       clase=modelo.buscarClase("matematicas");
        
       return "la clase es "+clase.get(0)+" la descripcion es "+ clase.get(1);
    }

     //falta ponerlo a put y listo
        @GET
     @Path("Alumno/Incidencia")
      @Produces(MediaType.TEXT_PLAIN)
    public String incidencia() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
     
       modelo.reportar("devolvedme el dinero hijos de fruta");
        
       return "hola buenas, no nos importa nah";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
