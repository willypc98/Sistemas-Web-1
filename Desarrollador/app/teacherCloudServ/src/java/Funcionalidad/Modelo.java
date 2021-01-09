/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Funcionalidad.BBDD;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author david
 */
public class Modelo {
    BBDD bbdd = new BBDD();
    
    public boolean autenticar(String email, String pass) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
       return bbdd.autenticar(email, pass);
    }
    //modo se refiere a si es tutor o alumno
     public void registrar(String nombre, String email, String pass, String modo) throws SQLException, ClassNotFoundException, NamingException, NoSuchAlgorithmException {
        bbdd.registrar(nombre, email, pass, modo);
    }
    
}
