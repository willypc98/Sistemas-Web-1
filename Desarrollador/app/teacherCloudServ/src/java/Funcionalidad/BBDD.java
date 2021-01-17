/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Recursos.Clase;
import Recursos.Peticion;
import Recursos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class BBDD {
    
    
private static Connection con;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="root";
    private static final String url="jdbc:mysql://localhost:3306/teacherCloud";
  

    
    
 
    private Statement createStatement;

    private ResultSet rS; 
    
    
     protected boolean conector() throws SQLException, ClassNotFoundException {
        // Reseteamos a null la conexion a la bd
        con=null;
        boolean valor=false;
        try{
            
           
            int i=0;
          //  String valor= "fallo";
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
                
                  
                createStatement = con.createStatement();
                valor= true;
            }
        }
         //Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
           
            System.out.println( e);
        }

        return  valor;
         
     }
       protected ArrayList<String> obtenerEmailUsuario(){
     ArrayList<String> emailUser = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from TCusuario";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {
//              contenido.add(rS.getString("nombre_usuario"));
//              contenido.add(rS.getString("password_usuario"));
                        
                        emailUser.add(rS.getString("email"));
                        
                        i++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return emailUser;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emailUser;
     }
   protected boolean autenticar(String email, String pass) {
        //esta variable es la encargada de almacenar temporalmente la pass para poder compararla
        String passAux="";
        
        //var auxiliar que te devuelve true si se encuentra la coincidencia de pas intro con la encontrada tras la sentencia select
        boolean valor= false;
          try {
            if(conector()==true){
                String queryBBDD = "select usuario_pass from TCusuario where usuario_email like '"+email+"'";
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {                     
                       passAux=rS.getString("usuario_pass");                        
                    }
                    
                    
                    if(passAux.equals(pass)){
                        valor=true;
                    }
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                   
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    protected void registrar(String modo,String email,String nombre,  String pass, int monedero ) throws SQLException, ClassNotFoundException {
            if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCusuario (usuario_modo, usuario_email, usuario_nombre, usuario_pass, usuario_monedero) VALUES ('" + modo + "', '" + email + "', '" + nombre + "', '" + pass +"', '"+monedero + "')");
           con.close();
           }
          }
    
    // busca la clase por el nombre proporcionado y devuelve todo la informacion de esta en el arrayList clase
    protected Clase buscarClase(String nombreClase) {
        Clase clase= new Clase();
      
        try {
            if(conector()==true){
                String queryBBDD = "select * from TCclase where clase_nombre like '"+nombreClase+"'";
              
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {   
                        
                        clase.setNombre(rS.getString("clase_nombre"));
                        clase.setDescripcion(rS.getString("clase_descripcion"));
                        clase.setCalificacion(Integer.parseInt(rS.getString("clase_calificacion")));
                        clase.setIdentificador(Integer.parseInt(rS.getString("clase_identificador")));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return clase;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clase;
    }
    
     protected void reportar(String incidencias_descripcion  ) throws SQLException, ClassNotFoundException {
         if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCincidencia (incidencias_descripcion) VALUES ('" + incidencias_descripcion  + "');");
           con.close();
           }
    }
    


    void crearClase(Clase clase) throws SQLException, ClassNotFoundException {
        
        
                    if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCclase (clase_nombre,clase_descripcion,clase_calificacion) VALUES ('" + clase.getNombre()  + "','"+clase.getDescripcion()+"',"
                                          +clase.getCalificacion()+");");
           con.close();
           }
        
    }
/**Busca todas  las calificaciones  **/
    protected Clase mostrarCalificaciones(String nombreClaseCalificacion) {
        Clase clase= new Clase();
      
        try {
            if(conector()==true){
                String queryBBDD = "select * from TCclase where clase_nombre like '"+nombreClaseCalificacion+"';";
              
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {   
                        clase.setCalificacion(Integer.parseInt(rS.getString("clase_calificacion")));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return clase;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clase;
    }

    protected void aceptarPeticion(Peticion peticion) throws SQLException, ClassNotFoundException {
       
     if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCpeticion (peticion_estado,usuario_email,clase_identificador) VALUES ('" + peticion.getEstado()  + "','"+peticion.getUsuarioEmail()+"',"
                                          +peticion.getClaseIdentificador()+");");
           con.close();
           }
        
    }
    

protected Peticion mostrarPeticion(String peticionEstado) {
        Peticion peticion = new Peticion();
      System.err.println("hola");
        try {
            
            if(conector()==true){
                String queryBBDD = "select * from TCpeticion where peticion_estado like '"+peticionEstado+"';";
              System.err.println("hola2");
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {  
                        peticion.setUsuarioEmail(rS.getString("usuario_email"));
                        peticion.setClaseIdentificador(rS.getString("clase_identificador"));
                         
                        
                    }
                    System.err.println(peticion.getUsuarioEmail());
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
           
            else{
                return peticion;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return peticion;
    }
    
    void cancelarPeticion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected boolean pagar(String nombreConsumidor, String nombreProductor) {
        
        //variable de control para saber si se ha relizado el pago
        boolean valor=false;
        System.out.println(verDinero(nombreConsumidor));
        System.out.println(verDinero(nombreProductor));
       if(Integer.parseInt(verDinero(nombreConsumidor))>=1) {
           valor=true;
           modificarDinero(nombreConsumidor,verDinero(nombreConsumidor),"resta");
           modificarDinero(nombreProductor,verDinero(nombreProductor),"suma");
          
           
       }
           
       
      return valor;
    }


    
    
    
    //extras
    
    
    //te ve el dinero del usuario con un nombre determinado
    protected String verDinero(String nombre) {
        //esta variable es la encargada de almacenar temporalmente el dinero
        String dinero="-1000";
        
       
          try {
            if(conector()==true){
                String queryBBDD = "select usuario_monedero  from TCusuario where usuario_nombre like '"+nombre+"'";
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {                     
                       dinero=rS.getString("usuario_monedero");                        
                    }
                       
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                   
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return dinero;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dinero;
    }
    
     protected String modificarDinero(String nombre, String dinero, String tipo) {
        //esta variable es la encargada de almacenar temporalmente el dinero
        
        int dineroAux=0;
        
        if(tipo.equals("suma")){
        dineroAux =(Integer.parseInt(dinero)+1);
        }else{
         dineroAux =(Integer.parseInt(dinero)-1);
        }
              
       
          try {
            if(conector()==true){
                String queryBBDD = "update TCusuario set usuario_monedero="+dineroAux+"  where usuario_nombre like '"+nombre+"';";
                
                try {
                   createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                   
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return dinero;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dinero;
    }
    
     
     protected ArrayList<Clase> nombreClasePublicada() {
      ArrayList<Clase> claseArray = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from TCclase;";
              
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) { 
                        Clase clase = new Clase();
                        clase.setNombre(rS.getString("clase_nombre"));
                         clase.setDescripcion(rS.getString("clase_descripcion"));
                          clase.setCalificacion(Integer.parseInt(rS.getString("clase_calificacion")));
                        
                        claseArray.add(clase);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return claseArray;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claseArray;
    }

    protected String existeUsuario(String nombre) {
        String respuesta= "no";
        try {
            if(conector()==true){
                String queryBBDD = "select usuario_nombre from TCusuario ;";
              
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    while (rS.next()) {                     
                        if(rS.getString("usuario_nombre").equals(nombre)){
                         respuesta="si";
                        }
                        

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            else{
                return respuesta;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    protected Usuario mostrarModo(String usuarioEmail) {
        Usuario usuario= new Usuario();
        try {
            if(conector()==true){
                String queryBBDD = "select * from TCusuario where usuario_email like '"+usuarioEmail+"';";

                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    while (rS.next()) {
                     usuario.setModo(rS.getString("usuario_modo"));

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
     protected void asociarClaseP(Clase clase) throws SQLException, ClassNotFoundException {
        
        
                    if(conector()==true){
            
            createStatement.executeUpdate("INSERT INTO TCclaseUsuario (usuario_modo,usuario_email,clase_identificador) VALUES  ('profesor', '"+clase.getNombreTutor()+"' ,'"+clase.getIdentificador()+"');");
           con.close();
           }
        
    }
      protected void asociarClaseA(Clase clase) throws SQLException, ClassNotFoundException {
        
        
                    if(conector()==true){
            
            createStatement.executeUpdate("INSERT INTO TCclaseUsuario (usuario_modo,usuario_email,clase_identificador) VALUES  ('profesor', '"+clase.getNombreTutor()+"' ,'"+clase.getIdentificador()+"');");
           con.close();
           }
        
    }
}
