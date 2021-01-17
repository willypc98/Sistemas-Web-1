/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Funcionalidad.Modelo;
import Recursos.Clase;
import Recursos.Usuario;
import Recursos.Peticion;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Singleton;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author david
 */
@Singleton
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


    //hace falta ponerlo en post pero ya responde
     @GET
     @Path("Login")
    @Produces("application/xml")
    public String autenticar(@QueryParam("usuarioEmail") String usuarioEmail,@QueryParam("usuarioPass") String usuarioPass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        //cuando este en el cliente hay que quitarlo y solo dejar en boolean
        //@QueryParam("usuarioEmail") String usuarioEmail,@QueryParam("usuarioPass") String usuarioPass
        String auxTextoRespuesta="error";
        
        if (modelo.autenticar(usuarioEmail , usuarioPass)==true){
            auxTextoRespuesta="todo correcto";
        }
       return auxTextoRespuesta;
    }


    @POST
    @Path("Registrarse")
    @Consumes("application/xml")
    public String registrase(Usuario user)throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
       
        //(@FormParam("usuarioModo") String usuarioModo,@FormParam("usuarioEmail") String usuarioEmail,@FormParam("usuarioNombre") String usuarioNombre,@FormParam("usuarioPass") String usuarioPass,@FormParam("usuarioMonedero") String usuarioMonedero)
        //modelo.registrar(usuarioModo, usuarioEmail, usuarioNombre, usuarioPass,Integer.getInteger(usuarioMonedero));
        return modelo.registrar(user.getModo(), user.getEmail(), user.getNombre(),user.getPassword(), user.getMonedero());
       
    }

    //Alumno
    @POST
    @Path("Alumno/BuscarClase")
    public Clase buscarClase(@FormParam("nombreClase") String nombreClase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        Clase clase = new Clase();
        clase = modelo.buscarClase(nombreClase);

        return clase;
    }

   
    @PUT
    @Path("Alumno/Incidencia")
    public void incidencia(@FormParam("incidencia") String incidencia) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {

        modelo.reportar(incidencia);

    }

    @POST
    @Path("Alumno/Pago")
    @Produces(MediaType.TEXT_PLAIN)
    public String pago(@FormParam("nombreAlumno") String nombreConsumidor, @FormParam("nombreProfesor") String nombreProductor) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {

        boolean valor = modelo.pagarClase(nombreConsumidor, nombreProductor);

        if (valor == false) {
            return "no tiene dinero";
        } else {
            return "ha sido timado";
        }

    }

    @GET
    @Path("Alumno/BuscarClasePublicada")
    @Produces(MediaType.TEXT_PLAIN)
    public String nombreClasePublicada() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        ArrayList<String> clase = new ArrayList();
        clase = modelo.nombreClasePublicada();

        return "la clase es " + clase.get(1);
    }
    //Profesor
    
    @PUT
    @Path("Profesor/crearClase")
    public void crearClase(@FormParam("clase_nombre") String clase_nombre,@FormParam("clase_descripcion") String clase_descripcion,@FormParam("clase_calificacion") String clase_calificacion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        Clase clase = new Clase();
        clase.setNombre(clase_nombre);
        clase.setDescripcion(clase_descripcion);
        clase.setCalificacion(Integer.parseInt(clase_calificacion));
        modelo.crearClase(clase);
        

    }
        @POST
    @Path("Profesor/BuscarCalificaciones")
    public Clase buscarCalificaciones(@FormParam("claseCalificacion") String claseCalificacion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        Clase clase = new Clase();
        clase = modelo.mostrarCalificaciones(claseCalificacion);

        return clase;
    }
        @PUT
    @Path("AceptarPeticion")
    public void AceptarPeticion(@FormParam("peticion_estado") String peticion_estado,@FormParam("usuario_nombre") String usuario_nombre,@FormParam("clase_identificador") String clase_identificador) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        Peticion peticion = new Peticion();
        
        peticion.setEstado(peticion_estado);
        peticion.setUsuarioEmail(usuario_nombre);
        peticion.setClaseIdentificador(clase_identificador);
        
        modelo.aceptarPeticion(peticion);
        

    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
//    @DELETE
//    @Path("rmvReceta")
//     public void rmvReceta(@FormParam("nombreReceta")String nombreReceta,@FormParam("nombreRecetario")String nombreRecetario) {
//      ABD.borrarReceta(nombreReceta, nombreRecetario);
//
//    }
}
