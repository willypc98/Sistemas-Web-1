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


/**
 *
 * @author david
 */
public class Modelo {
    Cliente cli = new Cliente();
    private String sCarpAct = System.getProperty("user.dir");
private File carpeta = new File(sCarpAct);
private String ruta = carpeta.getPath();
    

    //login
     public String validarUsuario(Usuario usuario){
     return cli.validarUsuario(usuario.getEmail(), usuario.getPassword());
     }
     
      public String registrar(Usuario user){
        System.out.println(cli.registrar(user).getEntity());
      return "si";
    }


}