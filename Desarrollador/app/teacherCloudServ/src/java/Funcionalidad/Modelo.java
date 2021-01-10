/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Funcionalidad.BBDD;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author david
 */
public class Modelo {
    BBDD bbdd = new BBDD();
    
    public ArrayList<String>  obtenerEmail() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
       return bbdd.obtenerEmailUsuario();
    }
    
    
    
    
    //USUARIO
    
    public boolean autenticar(String email, String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
       return bbdd.autenticar(email, pass);
    }
    //modo se refiere a si es tutor o alumno
     public void registrar(String modo,String email,String nombre,  String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.registrar( modo, email, nombre, pass);
    }
     
     
     
     
     //ALUMNO
     
    public void buscarClase(String nombreClase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.buscarClase(nombreClase);
    }
      public void pedirInscribirse(String incidencias_descripcion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.reportar( incidencias_descripcion);
    }
    
    public void mostrarChatsAlumno(String correoAlumno){
        bbdd.mostrarChatsAlumno(correoAlumno);
    }
     public void reportar(String incidencias_descripcion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.reportar( incidencias_descripcion);
    }
      public void pagarClase() throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.pagar();
    }
     //PROFESOR
     
     public void crearClase(String nombreClase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.crearClase(nombreClase);
    }
    public void mostrarChatsProfesor(String correoProfesor){
        bbdd.mostrarChatsProfesor(correoProfesor);
    }
    public void mostrarCalificaciones(){
        bbdd.mostrarCalificaciones();
    }
     public void aceptarPeticion(){
        bbdd.aceptarPeticion();
    }
     public void mostrarPeticion(){
        bbdd.mostrarPeticion();
    }
       public void cancelarPeticion(){
        bbdd.cancelarPeticion();
    }
    
       //extras pero necesarias
         public void mostarClase(String nombreClase){
        bbdd.mostarClase(nombreClase);
    }
     
}
