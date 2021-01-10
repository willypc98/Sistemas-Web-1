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
    public boolean autenticar(String email, String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
       return bbdd.autenticar(email, pass);
    }
    //modo se refiere a si es tutor o alumno
     public void registrar(String modo,String email,String nombre,  String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.registrar( modo, email, nombre, pass);
    }
     
      public void buscarClase(String nombreClase) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.buscarClase(nombreClase);
    }
    public void mostrarChatsAlumno(String correoAlumno){
        bbdd.mostrarChatsAlumno(correoAlumno);
    }
     public void reportar(String incidencias_descripcion) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
         bbdd.reportar( incidencias_descripcion);
    }
}
