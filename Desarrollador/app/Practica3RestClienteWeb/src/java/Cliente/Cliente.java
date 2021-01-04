/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import beans.ConjuntoRecetario;
import beans.FileUser;
import beans.Receta;
import beans.Recetario;
import beans.Usuario;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServicioRestResource
 * [servicioRest]<br>
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
    private static final String BASE_URI = "http://localhost:8080/Practica3RestClase/webresources";

    public Cliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("servicioRest");
    }

  
  //validar fichero

    public String validarFichero(Object requestEntity) throws ClientErrorException {
         WebTarget resource = webTarget;  
        resource = resource.path("validarFichero");
       return  resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), String.class);
        
    
    }

  //importar y exportar

 public byte[] exportarReceta( String nombreFichero, String nombreReceta) throws ClientErrorException {
        WebTarget resource = webTarget;  
        resource = resource.queryParam("nombreFichero", nombreFichero);
        resource = resource.queryParam("nombreReceta", nombreReceta);
        resource = resource.path("exportarReceta");
        
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(byte[].class);
    }


    public void importarReceta(byte[] bytes)  throws ClientErrorException {
        webTarget.path("importarReceta").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(bytes, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public byte[] exportarRecetario(String nombreFichero, String nombreRecetario) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("exportarRecetario");
          resource = resource.queryParam("nombreFichero", nombreFichero);
          resource = resource.queryParam("nombreRecetario", nombreRecetario);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(byte[].class);
    }



    public void importarRecetario(FileUser fU) throws ClientErrorException {
        webTarget.path("importarRecetario").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(fU, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
    
    
    //usuario login
  public Usuario validarUsuario( String usuarioNombre, String usuarioPass) throws ClientErrorException {
        WebTarget resource = webTarget;
        
            resource = resource.queryParam("usuarioNombre", usuarioNombre);
            resource = resource.queryParam("usuarioPass", usuarioPass);
        resource = resource.path("validarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Usuario.class);
    }

    public  ConjuntoRecetario obtenerRecetarios(Integer idUser) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("obtenerRecetarios");
        resource = resource.queryParam("idUser", idUser);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(ConjuntoRecetario.class);
    }
  //obtener  
public Receta  obtenerReceta(String nombreReceta) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("obtenerReceta");
        resource = resource.queryParam("nombreReceta", nombreReceta);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Receta.class);
    }

      public Recetario obtenerRecetario(String nombreRecetario) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("obtenerRecetario");
        resource = resource.queryParam("nombreRecetario", nombreRecetario);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(Recetario.class);
    }
//borrar
    public void rmvReceta(String nombreReceta, String nombreRecetario) throws ClientErrorException {
          WebTarget resource = webTarget;
        resource = resource.path("rmvReceta");
         resource = resource.queryParam("nombreReceta", nombreReceta);
         resource = resource.queryParam("nombreRecetario", nombreRecetario);
        resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).delete(String.class);
    }
     public void rmvRecetario(String idUser, String nombreRecetario) throws ClientErrorException {
          WebTarget resource = webTarget;
        resource = resource.path("rmvRecetario");
         resource = resource.queryParam("idUser", idUser);
         resource = resource.queryParam("nombreRecetario", nombreRecetario);
        resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).delete(String.class);
    }
    //crear
    public void crearRecetario(Object requestEntity) throws ClientErrorException {
        webTarget.path("crearRecetario").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void crearReceta(Object requestEntity) throws ClientErrorException {
        
        webTarget.path("crearReceta").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        
        }
    //añadir
     public void addReceta(Object requestEntity) throws ClientErrorException {
        webTarget.path("addReceta").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }
 
    
    public void close() {
        client.close();
    }
    
}
