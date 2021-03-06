/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import recursos.Clase;
import recursos.ConjuntoClases;
import recursos.Usuario;

/**
 * Jersey REST client generated for REST resource:GenericResource [Usuario]<br>
 * USAGE:
 * <pre>
 *        Cliente client = new Cliente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author david
 */
public class Cliente {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/teacherCloudServ/webresources";

    public Cliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Usuario");
    }
  public String validarUsuario( String usuarioNombre, String usuarioPass) throws ClientErrorException {
        WebTarget resource = webTarget;
        
  
            resource = resource.queryParam("usuarioEmail", usuarioNombre);
            resource = resource.queryParam("usuarioPass", usuarioPass);
        resource = resource.path("Login");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }
   public void registrar(Object requestEntity) throws ClientErrorException {
       
        webTarget.path("Registrarse").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        
        }
   
       public Clase buscarClase( String nombreClase) throws ClientErrorException {
        WebTarget resource = webTarget;
        
            resource = resource.queryParam("nombreClase", nombreClase);
        resource = resource.path("Alumno/BuscarClase");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Clase.class);
    }
    
        public ConjuntoClases buscarClasesPublicada() throws ClientErrorException {
        WebTarget resource = webTarget;   
        resource = resource.path("Alumno/BuscarClasePublicada");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(ConjuntoClases.class);
    }
        
    public void incidencia(Object requestEntity) {
        webTarget.path("Alumno/Incidencia").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    
    
    
    //Profesor
    public Usuario mostrarModo(String usuarioEmail)throws ClientErrorException{
        WebTarget resource = webTarget;   
         resource = resource.queryParam("usuarioEmail", usuarioEmail);
            
        resource = resource.path("MostrarModo");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Usuario.class);
       
        
        
        
   
    }
     public ConjuntoClases mostrarClasesAsociadas(String emailProfe) throws ClientErrorException {
        WebTarget resource = webTarget;   
        resource = resource.queryParam("usuarioEmail", emailProfe);
        resource = resource.path("Alumno/BuscarClasePublicada");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(ConjuntoClases.class);
    }
        public Clase mostrarCalificaciones(String nombreClase)throws ClientErrorException{
        WebTarget resource = webTarget;   
         resource = resource.queryParam("claseCalificacion", nombreClase);
            
        resource = resource.path("Profesor/BuscarCalificaciones");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Clase.class);
       
        
        
        
   
    }
    
    public void ofertarClase(Object requestEntity)throws ClientErrorException{
          webTarget.path("Profesor/CrearClase").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    
   
    }
     public void asociarClaseP(Object requestEntity)throws ClientErrorException{
          webTarget.path("Profesor/AsociarClase").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    
   
    }
          public void asociarClaseA(Object requestEntity)throws ClientErrorException{
          webTarget.path("Alumno/AsociarClase").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    
   
    }
}
