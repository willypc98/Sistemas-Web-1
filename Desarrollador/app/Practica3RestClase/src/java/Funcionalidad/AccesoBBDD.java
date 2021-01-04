/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidad;

import Recursos.ConjuntoRecetario;
import Recursos.Receta;
import Recursos.Recetario;
import Recursos.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author david
 */
public class AccesoBBDD {

    //refactor para q sea unica
    //import y export son a partir de leer o crear , falta el init
    private DataSource datasource;
    private Connection connection;
    private Statement createStatement;
    private InitialContext initialContext;
    private ResultSet rS;

    public void init() {
        ArrayList<String> queryBBDD = new ArrayList<>();

        conexionBBDD(queryBBDD);

    }

    public void crearRecetario(Recetario recetario, int idUser) {//añadir a conjRece
        ArrayList<String> queryBBDD = new ArrayList<>();
        int idRecetario = obtenerIdRecetarioNuevo();
        queryBBDD.add("insert into recetario value(" +idRecetario+", '"+ recetario.getNombre() + "', " + recetario.getPrecio() + ");");
        conexionBBDD(queryBBDD);
        addRelacionConjRecetarioRecetario(idUser, idRecetario);
        if (recetario.getRecetas().isEmpty() == false) {
            for (Receta rece : recetario.getRecetas()) {
                crearReceta(rece);
                addReceta(recetario.getNombre(), rece.getNombre());
            }
            
        }
    }

    public void crearReceta(Receta receta) {
        //crea una receta por cada ing
        ArrayList<String> queryBBDD = new ArrayList<>();
        int idReceta = obtenerIdRecetaNueva();
        ArrayList<Integer> idIng = new ArrayList<>();
        queryBBDD.add("insert into receta value(" + idReceta + ", '" + receta.getNombre() + "', '" + receta.getDificultad()
                + "', " + receta.getPrecio() + ");");
        conexionBBDD(queryBBDD);
        idIng = addIngrediente(receta.getIngrediente());
        addRelacionRecetaIng(idReceta, idIng);

    }

    public void addReceta(String nombreRecetario, String nombreReceta) {
        addRelacionRecetarioReceta(obtenerIdReceta(nombreReceta), nombreRecetario);

    }

    //borrar
    public void borrarRecetario(Integer idUser, String nombreRecetario) {//falta revisarlo
        ArrayList<String> queryBBDD = new ArrayList<>();
        ArrayList <String> nombreReceta= new ArrayList<>();
        nombreReceta=obtenerNombreRecetaArray(obtenerIdRecetaArray(obtenerIdRecetario(nombreRecetario)));
        rmvRelacionConjRecetario(idUser, nombreRecetario);
        if (recetarioTieneUser(nombreRecetario).isEmpty()) {

            for (String nombre : nombreReceta) {

                borrarReceta(nombre, nombreRecetario);
            }

            queryBBDD.add("delete from recetario where nombre_recetario = '" + nombreRecetario + "';");

            conexionBBDD(queryBBDD);
        }
    }

    public void borrarReceta(String recetaNombre, String recetarioNombre) {
        ArrayList<String> queryBBDD = new ArrayList<>();
        rmvRelacionRecetarioReceta(recetaNombre, recetarioNombre);
        if (recetaTieneRecetario(recetaNombre).isEmpty()) {//si esa receta no tiene ningun recetario
            ArrayList<String> idIng = obtenerIdIngrediente(obtenerIdReceta(recetaNombre));
            rmvRelacionRecetaIngrediente(recetaNombre);
            queryBBDD.add("delete from receta where nombre_receta = '" + recetaNombre + "';");
            conexionBBDD(queryBBDD);
            for (String id : idIng) {
                if (ingredienteTieneReceta(id).isEmpty()) {
                    borrarIngrediente(id);
                }
            }

        }
    }

    private void borrarIngrediente(String idIng) {//falta limpieza de ing

        {
            ArrayList<String> queryBBDD = new ArrayList<>();
            queryBBDD.add("delete from ingrediente where ingrediente_id = " + Integer.parseInt(idIng) + ";");
            conexionBBDD(queryBBDD);

        }
    }
    //leer      

    public Recetario leerRecetario(String recetarioNombre) {
        Recetario recetario = new Recetario();
        try {
            String queryBBDD = "";

            queryBBDD = "select * from recetario where nombre_recetario = '" + recetarioNombre + "';";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);
            if (rS.next()) {
                recetario.setNombre(rS.getString("nombre_recetario"));
                recetario.setPrecio(Double.parseDouble(rS.getString("precio")));
            }
            cerrarConexion();

            ArrayList<String> idReceta = obtenerIdRecetaArray(obtenerIdRecetario(recetarioNombre));
            ArrayList<Receta> receta = new ArrayList<>();
            for (String id : idReceta) {

                queryBBDD = "select nombre_receta from receta where receta_id = " + Integer.parseInt(id) + ";";
                abrirConexion();
                rS = createStatement.executeQuery(queryBBDD);
                if (rS.next()) {
                    receta.add(leerReceta((rS.getString("nombre_receta"))));
                }
                cerrarConexion();
            }
            recetario.setRecetas(receta);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recetario;
    }

    public Receta leerReceta(String recetaNombre) {
        Receta receta = new Receta();
        try {
            String queryBBDD = "";
            abrirConexion();
            queryBBDD = "select * from receta where nombre_receta = '" + recetaNombre + "';";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);
            if (rS.next()) {
                receta.setNombre(rS.getString("nombre_receta"));
                receta.setDificultad(rS.getString("dificultad_receta"));
                receta.setPrecio(Double.parseDouble(rS.getString("precio")));
            }

            cerrarConexion();

            ArrayList<String> idIngrediente = obtenerIdIngrediente(obtenerIdReceta(recetaNombre));
            ArrayList<String> ingrediente = new ArrayList();
            for (String id : idIngrediente) {

                queryBBDD = "select * from ingrediente where ingrediente_id= '" + id + "';";
                abrirConexion();

                rS = createStatement.executeQuery(queryBBDD);
                if (rS.next()) {
                    ingrediente.add(rS.getString("nombre_ingrediente"));
                }
                cerrarConexion();
            }
            receta.setIngrediente(ingrediente);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receta;
    }

    //para autentificacion
        public Usuario validarUSer(Usuario usuario) {
         int result=-21;
         ArrayList<Usuario> users = new ArrayList<>();
         users= leerUsuarios();
            for (Usuario user :users ) {
                //System.err.println(user.getNombre());
                if((user.getNombre().equals(usuario.getNombre()))&&(user.getPassword().equals(usuario.getPassword()))){
                  
                    result=Integer.parseInt(obtenerIdUser(user.getNombre()));
                }
            }
            usuario.setId(result);
            return usuario;
    }
    public ConjuntoRecetario ObtenerRecetarioConjRecetarios(Integer idUsuario) {
        ArrayList<String> recetarios = new ArrayList();
        ArrayList<Recetario> recetario = new ArrayList();
        ArrayList<String> idRecetario = new ArrayList();
        try {
            String queryBBDD = "";

            queryBBDD = "select recetario_id from conjunto_recetario where usuario_id = " + idUsuario + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);
            while (rS.next()) {
                idRecetario.add(rS.getString("recetario_id"));

            }
            cerrarConexion();
            for (int i = 0; i < idRecetario.size(); i++) {
                queryBBDD = "select nombre_recetario from recetario where recetario_id = '" + idRecetario.get(i) + "';";
                abrirConexion();
                rS = createStatement.executeQuery(queryBBDD);
                if (rS.next()) {
                    recetarios.add(rS.getString("nombre_recetario"));
                }
                cerrarConexion();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(recetarios.isEmpty()){
            recetarios.add("vacio");
        
        }
        for (String rece : recetarios) {
            Recetario ret = new Recetario();
            ret.setNombre(rece);
                recetario.add(ret);
            
        }
        ConjuntoRecetario conjuntoRecetario = new ConjuntoRecetario();
        conjuntoRecetario.setArrayRecetarios(recetario);
        return conjuntoRecetario;
    }

    public ArrayList<Usuario> leerUsuarios() {
        ArrayList<Usuario> usuario = new ArrayList();
//        String[] arrayNames = new String[1000];
//        String[] arrayPass = new String[1000];
        try {
            String queryBBDD = "";
            int i=0;
            queryBBDD = "select * from usuario;";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);
            
            while (rS.next()) {
                i++;
            }
            
            cerrarConexion();
            for (int j = 0; j < i; j++) {
                usuario.add(new Usuario());
            }
            i=0;
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);
            
            while (rS.next()) {
               
                usuario.get(i).setNombre(rS.getString("nombre_usuario"));
                usuario.get(i).setPassword(rS.getString("password_usuario"));
                   
        
                   i++;
            }
            
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
         
                   
        return usuario;
    }

//accesos necesarios   
    private ArrayList<String> obtenerNombreRecetaArray(ArrayList<String> idReceta) {
        ArrayList<String> nombreReceta = new ArrayList<>();
        for (int i = 0; i < idReceta.size(); i++) {
            try {
                String queryBBDD2 = "select nombre_receta from receta where receta_id = " + Integer.parseInt(idReceta.get(i)) + ";";
                abrirConexion();
                rS = createStatement.executeQuery(queryBBDD2);
                if (rS.next()) {
                    nombreReceta.add(rS.getString("nombre_receta"));
                }
                cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nombreReceta;
    }

    private ArrayList<Integer> addIngrediente(ArrayList<String> ingrediente) {

        ArrayList<Integer> idIng = new ArrayList<>();
        for (String ing : ingrediente) {
            ArrayList<String> queryBBDD = new ArrayList<>();
            int id = obtenerIdIngredienteNueva();
            queryBBDD.add("insert into ingrediente value(" + id + ", '" + ing + "');");
            idIng.add(id);
            conexionBBDD(queryBBDD);
        }

        return idIng;
    }
//addRelaciones

    private void addRelacionRecetaIng(Integer idReceta, ArrayList<Integer> idIng) {
        //id receta y id ing

        for (Integer ing : idIng) {
            ArrayList<String> queryBBDD = new ArrayList<>();
            queryBBDD.add("insert into receta_ingrediente value(" + idReceta + ", " + ing + ");");
            conexionBBDD(queryBBDD);
        }

    }

    private void addRelacionRecetarioReceta(String idReceta, String nombreRecetario) {
        ArrayList<String> queryBBDD = new ArrayList<>();

        String idRecetario = obtenerIdRecetario(nombreRecetario);

        queryBBDD.add("insert into recetario_receta value('" + idRecetario + "', '" + idReceta + "');");

        conexionBBDD(queryBBDD);
    }

    private void addRelacionConjRecetarioRecetario(int idUser, int idRecetario) {
        ArrayList<String> queryBBDD = new ArrayList<>();
      
      

        queryBBDD.add("insert into conjunto_recetario value(" + idUser + ", " + idRecetario + ");");

        conexionBBDD(queryBBDD);

    }

    //rmvRelaciones
    private void rmvRelacionConjRecetario(Integer idUser, String recetarioNombre) {
        ArrayList<String> queryBBDD = new ArrayList<>();
      
        String idRecetario = obtenerIdRecetario(recetarioNombre);
        queryBBDD.add("delete from conjunto_recetario where usuario_id=" +idUser + " and recetario_id =" + Integer.parseInt(idRecetario) + ";");
        conexionBBDD(queryBBDD);
    }

    private void rmvRelacionRecetarioReceta(String recetaNombre, String recetarioNombre) {
        ArrayList<String> queryBBDD = new ArrayList<>();
        String idReceta = obtenerIdReceta(recetaNombre);
        String idRecetario = obtenerIdRecetario(recetarioNombre);
        queryBBDD.add("delete from recetario_receta where recetario_id=" + Integer.parseInt(idRecetario) + " and receta_id =" + Integer.parseInt(idReceta) + ";");
        conexionBBDD(queryBBDD);
    }

    private void rmvRelacionRecetaIngrediente(String recetaNombre) {
        String idReceta = obtenerIdReceta(recetaNombre);

        ArrayList<String> queryBBDD = new ArrayList<>();
        queryBBDD.add("delete from receta_ingrediente where receta_id = " + Integer.parseInt(idReceta) + ";");
        conexionBBDD(queryBBDD);

       
    }
//obtener id

    private String obtenerIdReceta(String recetaNombre) {
        String respuesta = "";
        try {
            String queryBBDD = "select receta_id from receta where  nombre_receta  = '" + recetaNombre + "';";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            if (rS.next()) {
                respuesta = rS.getString("receta_id");
            }

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    private ArrayList<String> obtenerIdIngrediente(String receta_id) {

        ArrayList<String> respuesta = new ArrayList();

        try {
            String queryBBDD = "select ingrediente_id from receta_ingrediente where receta_id = " + receta_id + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(rS.getString("ingrediente_id"));
            }

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    private String obtenerIdRecetario(String nombreRecetario) {
        String respuesta = "";
        try {
            String queryBBDD = "select recetario_id from recetario where nombre_recetario = '" + nombreRecetario + "';";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            if (rS.next()) {
                respuesta = rS.getString("recetario_id");
            }

            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    private ArrayList<String> obtenerIdRecetaArray(String idRecetario) {

        ArrayList<String> respuesta = new ArrayList();

        try {
            String queryBBDD = "select receta_id from recetario_receta where recetario_id = " + idRecetario + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(rS.getString("receta_id"));
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return respuesta;
    }

    private String obtenerIdUser(String userName) {
        String respuesta = "";
        try {
            String queryBBDD = "select usuario_id from usuario where nombre_usuario = '" + userName + "';";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            if (rS.next()) {
                respuesta = rS.getString("usuario_id");
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
private int obtenerIdRecetarioNuevo() {

        ArrayList<String> respuesta = new ArrayList();
        int id = 0;
        try {
            String queryBBDD = "select recetario_id from recetario;";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(rS.getString("recetario_id"));
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = Integer.parseInt(respuesta.get(respuesta.size() - 1)) + 1;
        return id;
    }
    private int obtenerIdRecetaNueva() {

        ArrayList<String> respuesta = new ArrayList();
        int id = 0;
        try {
            String queryBBDD = "select receta_id from receta;";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(rS.getString("receta_id"));
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = Integer.parseInt(respuesta.get(respuesta.size() - 1)) + 1;
        return id;
    }

    private int obtenerIdIngredienteNueva() {

        ArrayList<String> respuesta = new ArrayList();
        int id = 0;
        try {
            String queryBBDD = "select ingrediente_id from ingrediente;";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(rS.getString("ingrediente_id"));
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        id = Integer.parseInt(respuesta.get(respuesta.size() - 1)) + 1;
        return id;
    }
// ver si tiene elementos asociados

    private ArrayList<Integer> recetarioTieneUser(String nombreRecetario) {
        ArrayList<Integer> respuesta = new ArrayList();
        try {
            String idRecetario = obtenerIdRecetario(nombreRecetario);
            String queryBBDD = "select usuario_id from conjunto_recetario where recetario_id = " + idRecetario + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(Integer.parseInt(rS.getString("usuario_id")));
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;

    }

    private ArrayList<Integer> recetaTieneRecetario(String recetaNombre) {
        ArrayList<Integer> respuesta = new ArrayList();
        try {
            String idReceta = obtenerIdReceta(recetaNombre);
            String queryBBDD = "select recetario_id from recetario_receta where receta_id = " + idReceta + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(Integer.parseInt(rS.getString("recetario_id")));
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    private ArrayList<Integer> ingredienteTieneReceta(String idIng) {
        ArrayList<Integer> respuesta = new ArrayList();
        try {

            String queryBBDD = "select receta_id from receta_ingrediente where ingrediente_id = " + Integer.parseInt(idIng) + ";";
            abrirConexion();
            rS = createStatement.executeQuery(queryBBDD);

            while (rS.next()) {
                respuesta.add(Integer.parseInt(rS.getString("receta_id")));
            }
            cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

//Gestionar conexion
    private void abrirConexion() {
        try {
            initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("jdbc/myDatasource");
            connection = datasource.getConnection();
            createStatement = connection.createStatement();
        } catch (NamingException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cerrarConexion() {
        try {
            createStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void conexionBBDD(ArrayList<String> queryBBDD) {
        try {
            abrirConexion();
            for (String query : queryBBDD) {
                createStatement.execute(query);
            }
            cerrarConexion();

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
