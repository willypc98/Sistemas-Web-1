/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author darth
 */
public class ConexionBBDD {
    
    DataSource datasource;
    
    public void init() {
        try {
            InitialContext initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("jdbc/DatasourcePrueba"); //CAMBIAR Al POOL QUE LE PONGAMOS
            Connection connection = datasource.getConnection();
            Statement createStatement = connection.createStatement();
            //CREO LAS TABLAS DE LA BD
            createStatement.execute("CREATE TABLE IF NOT EXISTS RECETARIO (RECETARIO_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE_RECETARIO VARCHAR(50), PRECIO DOUBLE)");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS RECETA (RECETA_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE_RECETA VARCHAR(50), DIFICULTAD_RECETA VARCHAR(20), PRECIO DOUBLE)");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS INGREDIENTE (INGREDIENTE_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE_INGREDIENTE VARCHAR(20))");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS USUARIO(USUARIO_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE VARCHAR(30), CONTRASEÑA VARCHAR(8))");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS RECETARIO_RECETA (RECETARIO_ID INT NOT NULL, RECETARIO_ID INT NOT NULL, FOREIGN KEY (RECETARIO_ID) REFERENCES RECETARIO (RECETARIO_ID), FOREIGN KEY (RECETA_ID) REFERENCES RECETA (RECETA_ID), PRIMARY KEY (RECETARIO_ID , RECETA_ID))");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS RECETA_INGREDIENTE (RECETARIO_ID INT NOT NULL, INGREDIENTE_ID INT NOT NULL, FOREIGN KEY (RECETA_ID) REFERENCES RECETA (RECETA_ID), FOREIGN KEY (INGREDIENTE_ID) REFERENCES INGREDIENTE (INGREDIENTE_ID), PRIMARY KEY (RECETA_ID , INGREDIENTE_ID)) ");
            createStatement.close();
            createStatement.execute("CREATE TABLE IF NOT EXISTS CONJUNTO_RECETARIO (USUARIO_ID INT NOT NULL, RECETARIO_ID NOT NULL, FOREIGN KEY (USUARIO_ID) REFERENCES USUSARIO (USUARIO_ID), FOREIGN KEY (RECETARIO_ID) REFERECNCES RECETARIO (RECETARIO_ID), PRIMARY KEY (USUARIO_ID, RECETARIO_ID))");
            createStatement.close();
        
            connection.close();            
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

