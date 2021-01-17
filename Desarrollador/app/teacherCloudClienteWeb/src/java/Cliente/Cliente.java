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
        
        String ho;
            resource = resource.queryParam("usuarioEmail", usuarioNombre);
            resource = resource.queryParam("usuarioPass", usuarioPass);
        resource = resource.path("Login");
         return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    
}
