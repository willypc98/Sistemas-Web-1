/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Recursos.Receta;
import Recursos.Recetario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author david
 */
public class Marsalling {


public void crearXMLReceta(String nombreFichero, Receta receta,String ruta) {
     if(receta!=null){
        try {
            FileWriter fichero = null;
            File file = new File(ruta+"/files/xml/"+ nombreFichero);
            comprobarFichero(file);
            try {
                fichero = new FileWriter(ruta+"/files/xml/" + nombreFichero);
                
                
                try {
                    JAXBContext context = JAXBContext.newInstance(Receta.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    m.marshal(receta, fichero);
                    
                } catch (PropertyException ex) {
                    Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JAXBException ex) {
                    Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
        }
     }else{
     
         System.out.println("No se pudo crear el xml");
     
     }
    }
public Receta importarReceta(File file){
    Receta receta= new Receta();
       try {  
   
       
        JAXBContext jaxbContext = JAXBContext.newInstance(Receta.class);  
   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        receta = (Receta) jaxbUnmarshaller.unmarshal(file);  
          
        
        
   
      } catch (JAXBException e) {  
        e.printStackTrace();  
      }  
       return receta;
    }  
public void crearXMLRecetario(String nombreFichero, Recetario recetario,String ruta) {
    
     
        try {
            FileWriter fichero = null;
            File file = new File(ruta+"/files/xml/" + nombreFichero);
            comprobarFichero(file);
            try {
                fichero = new FileWriter(ruta+"/files/xml/" + nombreFichero);
                
                
                try {
                    JAXBContext context = JAXBContext.newInstance(Recetario.class);
                    Marshaller m = context.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    m.marshal(recetario, fichero);
                    
                } catch (PropertyException ex) {
                    Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JAXBException ex) {
                    Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Marsalling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public Recetario importarRecetario(File fichero){
    Recetario recetario= new Recetario();
       try {  
   
     
        JAXBContext jaxbContext = JAXBContext.newInstance(Recetario.class);  
   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        recetario = (Recetario) jaxbUnmarshaller.unmarshal(fichero);  
          
        
        
   
      } catch (JAXBException e) {  
        e.printStackTrace();  
      }  
       return recetario;
    }  
   private void comprobarFichero(File fichero){
     if (!fichero.exists()) {
    } else {
        fichero.delete();
        System.out.println("El archivo fue sobrescrito.");
    }
} 

}
        
