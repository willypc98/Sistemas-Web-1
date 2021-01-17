/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Funcionalidad.BBDD;
import Recursos.Clase;
import Recursos.Peticion;
import Recursos.Usuario;
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
     public String registrar(String modo,String email,String nombre,  String pass,int monedero) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.existeUsuario(nombre);
         if(bbdd.existeUsuario(nombre).equals("no")){
         bbdd.registrar( modo, email, nombre, pass, monedero);
         return "creado";
         }else{
         return "error";
         }
    }
     
     
     
     
     //ALUMNO
     
    public Clase buscarClase(String nombreClase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         return bbdd.buscarClase(nombreClase);
    }
      public void pedirInscribirse(String incidencias_descripcion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.reportar( incidencias_descripcion);
    }
    

     public void reportar(String incidencias_descripcion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.reportar( incidencias_descripcion);
    }
      public boolean pagarClase(String nombreConsumidor, String nombreProductor) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         return bbdd.pagar(nombreConsumidor,nombreProductor);
    }
     //PROFESOR
     
     public void crearClase(Clase clase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.crearClase(clase);
    }
    
    public Clase mostrarCalificaciones(String claseCalificacion)throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException{
       return bbdd.mostrarCalificaciones(claseCalificacion);
    }
     public void aceptarPeticion(Peticion peticion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException{
        bbdd.aceptarPeticion(peticion);
    }
//    public void aceptarPeticion(Peticion peticion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
//         bbdd.crearClase(clase);
//    }
     
     public Peticion mostrarPeticion(String peticionEstado) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         return bbdd.mostrarPeticion(peticionEstado);
    } 
       public void cancelarPeticion(){
        bbdd.cancelarPeticion();
    }
    
       //extras pero necesarias
         public ArrayList<Clase> nombreClasePublicada(){
        return bbdd.nombreClasePublicada();
    }
    public Usuario mostrarModo(String usuarioEmail) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         return bbdd.mostrarModo(usuarioEmail);
 } 

    public void asociarClaseP(Clase clase) throws SQLException, ClassNotFoundException {
       bbdd.asociarClaseP(clase);
    }
}
