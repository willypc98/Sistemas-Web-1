/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;



import Cliente.Cliente;

import recursos.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.Clase;
import recursos.IncidenciaC;


/**
 *
 * @author david
 */
public class Modelo {
    Cliente cli = new Cliente();
    private String sCarpAct = System.getProperty("user.dir");
private File carpeta = new File(sCarpAct);
private String ruta = carpeta.getPath();
    
<<<<<<< HEAD
 //USUARIO
    //login
=======

    //loguin
>>>>>>> parent of ad24fb0... registrar funciona
     public String validarUsuario(Usuario usuario){
     return cli.validarUsuario(usuario.getEmail(), usuario.getPassword());
     }
     
<<<<<<< HEAD
      public String registrar(Usuario user){
      cli.registrar(user);
      return "si";
    }
=======
>>>>>>> parent of ad24fb0... registrar funciona

      
 //ALUMNO
   public void enviarIncidencia(IncidenciaC incidencia) {
       cli.incidencia(incidencia);
    }
     public Clase buscarClase(String nombreClase){
     return cli.buscarClase(nombreClase);
     }

}
