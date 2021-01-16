/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicioRest;

import Funcionalidad.AccesoBBDD;
import Funcionalidad.Marsalling;
import Funcionalidad.ValidarXSD;
import Recursos.ConjuntoRecetario;
import Recursos.FileUser;

import Recursos.Receta;
import Recursos.RecetaRecetario;
import Recursos.Recetario;
import Recursos.Usuario;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author david
 */
@Singleton
@Path("servicioRest")
public class ServicioRestResource {   
private Recetario recetario = new Recetario();
    
private ArrayList<Receta> recetas = new ArrayList();
private String sCarpAct = System.getProperty("user.dir");
private File carpeta = new File(sCarpAct);
private String ruta = carpeta.getPath();
private Marsalling mrs = new Marsalling();
private ValidarXSD vXSD = new ValidarXSD();
private AccesoBBDD ABD = new AccesoBBDD();


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicioRestResource
     */
    public ServicioRestResource() {
        System.err.println(ruta);
        crearEntorno();
        ABD.init();
        
    }
   
    /**
     * Retrieves representation of an instance of ServicioRest.ServicioRestResource
     * @return an instance of java.lang.String
     */
    //obtener
    @GET
    @Path("obtenerRecetario")
    @Produces("application/xml")
    public Recetario obtenerRecetario(@QueryParam("nombreRecetario")String nombreRecetario) {
        Recetario resultado = null;
        resultado=ABD.leerRecetario(nombreRecetario);

        return resultado;
      
      
    }
     @GET
    @Path("obtenerReceta")
    @Produces("application/xml")
    public Receta obtenerReceta(@QueryParam("nombreReceta")String nombreReceta) {
        Receta resultado = null;
        resultado=ABD.leerReceta(nombreReceta);

        return resultado;

    }
//crear

    @POST
    @Path("crearRecetario")
    @Consumes("application/xml")
    public void crearRecetario(ConjuntoRecetario cnj) {
        ABD.crearRecetario(cnj.getArrayRecetarios().get(0), cnj.getIdUsuario());

    }
     @POST
    @Path("crearReceta")
    @Consumes("application/xml")
     public void crearReceta(Receta receta) {
      ABD.crearReceta(receta);
    }
      @POST
    @Path("addReceta")
    @Consumes("application/xml")
      public void addReceta(RecetaRecetario recetaRecetario) {
       ABD.addReceta(recetaRecetario.getNombreRecetario(), recetaRecetario.getNombreReceta());

    }
      //borrar
      @DELETE
    @Path("rmvRecetario")
    @Consumes("application/xml")
     public void rmvRecetario(@QueryParam("idUser")String idUser,@QueryParam("nombreRecetario")String nombreRecetario) {
      ABD.borrarRecetario(Integer.parseInt(idUser), nombreRecetario);

    }
    @DELETE
    @Path("rmvReceta")
    @Consumes("application/xml")
     public void rmvReceta(@QueryParam("nombreReceta")String nombreReceta,@QueryParam("nombreRecetario")String nombreRecetario) {
      ABD.borrarReceta(nombreReceta, nombreRecetario);

    }
     //exportar e importar
      @GET
    @Path("exportarRecetario")
    @Produces("application/xml")
     public  byte[]  exportarRecetario(@QueryParam("nombreFichero")String nombreFichero,@QueryParam("nombreRecetario")String nombreRecetario) throws IOException {
        mrs.crearXMLRecetario(nombreFichero+".xml", ABD.leerRecetario(nombreRecetario), ruta);
         File file = new File(ruta+"/files/xml/"+ nombreFichero+".xml");
         return converterByte(file);
    }
     @PUT
    @Path("importarRecetario")
    @Consumes("application/xml")
      public void importarRecetario(FileUser fU) {
       File file= new File( leerBytes(fU.getBytes()).getPath());
        recetario = mrs.importarRecetario(file);
        ABD.crearRecetario(recetario, fU.getIdUser());
        file.delete();

    }
        @GET
    @Path("exportarReceta")
    @Produces("application/xml")
       public byte[]  exportarReceta(@QueryParam("nombreFichero") String nombreFichero,
            @QueryParam("nombreReceta") String nombreReceta) throws IOException {
        mrs.crearXMLReceta(nombreFichero, obtenerReceta(nombreReceta), ruta);
         File file = new File(ruta+"/files/xml/"+ nombreFichero);
         return converterByte(file);
    }
        @PUT
    @Path("importarReceta")
    @Consumes("application/xml")
        public void importarReceta(byte[] bytes) {
         File file= new File( leerBytes(bytes).getPath());
         Receta receta=mrs.importarReceta(file);
         ABD.crearReceta(receta);
         file.delete();
        }
        
        //validar fichero
           @POST//post
    @Path("validarFichero")
    @Consumes("application/xml")
        public String validarFichero(byte[] bytes) {
         File file= new File( leerBytes(bytes).getPath());
          return "¿Es valido el xml con su xsd? " + vXSD.validarXSD(ruta + "/files/xsd/recetario.xsd",file );
        }
        
//login
  
   @GET
    @Path("validarUsuario")
    @Produces("application/xml")
    public Usuario validarUsuario(@QueryParam("usuarioNombre") String usuarioNombre, @QueryParam("usuarioPass") String usuarioPass) {
    
      
         Usuario user = new Usuario();
         user.setNombre(usuarioNombre);
         user.setPassword(usuarioPass);
         
       return  ABD.validarUSer(user);
       
    }    
     @GET
    @Path("obtenerRecetarios")
    @Produces("application/xml")
    public ConjuntoRecetario obtenerRecetarios(@QueryParam("idUser") Integer idUser) {
        
       return ABD.ObtenerRecetarioConjRecetarios(idUser);
       
    }        

       //crea ficheros necesarios

    private void crearEntorno() {
        File directorio = new File(ruta + "/files/xml");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        directorio = new File(ruta + "/files/xsd");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        crearXSD();
        //crearRecetario1();
    }

    private void crearXSD() {

        File archivo = new File(ruta + "/files/xsd/recetario.xsd");
        BufferedWriter bw = null;
        if (archivo.exists() != true) {

            try {
                bw = new BufferedWriter(new FileWriter(archivo));
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw.write("<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n"
                        + "  <xs:element name=\"Recetario\">\n"
                        + "    <xs:complexType>\n"
                        + "      <xs:sequence>\n"
                        + "        <xs:element type=\"xs:string\" name=\"nombre\"/>\n"
                        + "        <xs:element name=\"recetas\">\n"
                        + "          <xs:complexType>\n"
                        + "            <xs:sequence>\n"
                        + "              <xs:element name=\"recetas\" maxOccurs=\"unbounded\" minOccurs=\"0\">\n"
                        + "                <xs:complexType>\n"
                        + "                  <xs:sequence>\n"
                        + "                    <xs:element type=\"xs:string\" name=\"nombre\"/>\n"
                        + "                    <xs:element name=\"ingrediente\">\n"
                        + "                      <xs:complexType>\n"
                        + "                        <xs:sequence>\n"
                        + "                          <xs:element type=\"xs:string\" name=\"ingrediente\" maxOccurs=\"unbounded\" minOccurs=\"0\"/>\n"
                        + "                        </xs:sequence>\n"
                        + "                      </xs:complexType>\n"
                        + "                    </xs:element>\n"
                        + "                    <xs:element type=\"xs:float\" name=\"precio\"/>\n"
                        + "                  </xs:sequence>\n"
                        + "                  <xs:attribute type=\"xs:string\" name=\"dificultad\" use=\"optional\"/>\n"
                        + "                </xs:complexType>\n"
                        + "              </xs:element>\n"
                        + "            </xs:sequence>\n"
                        + "          </xs:complexType>\n"
                        + "        </xs:element>\n"
                        + "        <xs:element type=\"xs:float\" name=\"precio\"/>\n"
                        + "      </xs:sequence>\n"
                        + "    </xs:complexType>\n"
                        + "  </xs:element>\n"
                        + "</xs:schema>\n"
                        + "");
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void crearRecetario1() {

        File archivo = new File(ruta + "/files/xml/recetario1.xml");
        BufferedWriter bw = null;
        if (archivo.exists() != true) {

            try {
                bw = new BufferedWriter(new FileWriter(archivo));
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<Recetario>\n"
                        + "    <nombre>Recetario1</nombre>\n"
                        + "    <recetas>\n"
                        + "        <recetas dificultad=\"Dificil\">\n"
                        + "            <nombre>Lasanna</nombre>\n"
                        + "            <ingrediente>\n"
                        + "                <ingrediente>Tomate</ingrediente>\n"
                        + "                <ingrediente>Carne</ingrediente>\n"
                        + "                <ingrediente>Pasta</ingrediente>\n"
                        + "                <ingrediente>Queso</ingrediente>\n"
                        + "                <ingrediente>Aceite</ingrediente>\n"
                        + "                <ingrediente>Sal</ingrediente>\n"
                        + "                <ingrediente>Bechamel</ingrediente>\n"
                        + "            </ingrediente>\n"
                        + "            <precio>12.0</precio>\n"
                        + "        </recetas>\n"
                        + "        <recetas dificultad=\"Facil\">\n"
                        + "            <nombre>Sandwich de pavo</nombre>\n"
                        + "            <ingrediente>\n"
                        + "                <ingrediente>Pavo</ingrediente>\n"
                        + "                <ingrediente>Pan</ingrediente>\n"
                        + "            </ingrediente>\n"
                        + "            <precio>1.5</precio>\n"
                        + "        </recetas>\n"
                        + "        <recetas dificultad=\"Facil\">\n"
                        + "            <nombre>Filete de ternera</nombre>\n"
                        + "            <ingrediente>\n"
                        + "                <ingrediente>Carne de ternera</ingrediente>\n"
                        + "                <ingrediente>Aceite</ingrediente>\n"
                        + "                <ingrediente>Sal</ingrediente>\n"
                        + "            </ingrediente>\n"
                        + "            <precio>1.0</precio>\n"
                        + "        </recetas>\n"
                        + "    </recetas>\n"
                        + "    <precio>20.0</precio>\n"
                        + "</Recetario>");
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //creacion de fichero a byte y al reves
      private  byte[] converterByte( File file ) throws FileNotFoundException, IOException {
       
 
        FileInputStream fiss = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream boss = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        try {
            for (int readNum; (readNum = fiss.read(buff)) != -1;) {
                boss.write(buff, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
           
        } catch (IOException ex) {
            Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        byte[] bytes = boss.toByteArray();
          boss.close();
          fiss.close();
         File fileAux=new File(file.getPath());
         System.err.println(file.getPath());
         fileAux.delete();
        return bytes;
        //below is the different part
//        File someFile = new File("java2.pdf");
//        FileOutputStream fos = new FileOutputStream(someFile);
//        fos.write(bytes);
//        fos.flush();
//        fos.close();
    }
 private File leerBytes(byte[] importarRecetario){
        FileOutputStream fos = null;
        File file = new File("./recetario.xml");
        try {
            
            fos = new FileOutputStream(file);
            fos.write(importarRecetario);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ServicioRestResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return file;
    }
}
