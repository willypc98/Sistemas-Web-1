/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

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
@XmlRootElement(name = "ConjuntoClases")
public class ConjuntoClases {
    @XmlElement()
     private ArrayList <Clase> arrayRecetarios= new ArrayList();

    public ArrayList <Clase> getArrayRecetarios() {
        return arrayRecetarios;
    }

    public void setArrayRecetarios(ArrayList <Clase> arrayRecetarios) {
        this.arrayRecetarios = arrayRecetarios;
    }
}
