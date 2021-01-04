/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ConjuntoRecetario")
public class ConjuntoRecetario implements Serializable{
      @XmlElement
   private int idUsuario;
        @XmlElement
   private ArrayList <Recetario> arrayRecetarios= new ArrayList();

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList <Recetario> getArrayRecetarios() {
        return arrayRecetarios;
    }

    public void setArrayRecetarios(ArrayList <Recetario> arrayRecetarios) {
        this.arrayRecetarios = arrayRecetarios;
    }
    
    
}
