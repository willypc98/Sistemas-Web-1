/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

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
    private static final String pass="1234a";
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

    protected void registrar(String modo,String email,String nombre,  String pass ) throws SQLException, ClassNotFoundException {
            if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCusuario (usuario_modo, usuario_email, usuario_nombre, usuario_pass) VALUES ('" + modo + "', '" + email + "', '" + nombre + "', '" + pass + "')");
           con.close();
           }
          }

    protected void buscarClase(String nombreClase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     protected void reportar(String incidencias_descripcion  ) throws SQLException, ClassNotFoundException {
         if(conector()==true){
                
            createStatement.executeUpdate("INSERT INTO TCincidencia (incidencias_descripcion) VALUES ('" + incidencias_descripcion  + "');");
           con.close();
           }
    }
    
      protected void mostrarChatsAlumno(String correoAlumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mostrarChatsProfesor(String correoProfesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void crearClase(String nombreClase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mostrarCalificaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void aceptarPeticion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mostrarPeticion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void cancelarPeticion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void pagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void mostarClase(String nombreClase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
