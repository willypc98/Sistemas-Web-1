/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RecetaRecetario")
public class RecetaRecetario {
     @XmlElement
   private String nombreRecetario;
      @XmlElement
    private String nombreReceta;

    public String getNombreRecetario() {
        return nombreRecetario;
    }

    public void setNombreRecetario(String nombreRecetario) {
        this.nombreRecetario = nombreRecetario;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }
    
}
