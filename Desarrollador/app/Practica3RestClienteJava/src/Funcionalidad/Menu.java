package Funcionalidad;

import Recursos.Receta;
import Recursos.Recetario;
import Recursos.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private Integer opcion = -1;
    private Modelo modelo = new Modelo();
    private String respuesta, respuesta2, respuesta3, respuesta4;
    private String sCarpAct = System.getProperty("user.dir");
    private File carpeta = new File(sCarpAct);
    private String ruta = carpeta.getPath();
    private int idUser=2;//-1 si no tiene y -21 si no existe
    
    
    public void menu(){
      System.out.println("---------------------------Login------------------------------------");
           
            System.out.println("Usuario:");
            respuesta = scanner.nextLine();
            System.out.println("Contraseña:");
            respuesta2 = scanner.nextLine();
            Usuario user = new Usuario();
            user.setNombre(respuesta);
            user.setPassword(respuesta2);
            idUser=modelo.validarUsuario(user);
            if(idUser<0){
                System.err.println("Usuario invalido");
            
            }else{
 
            menuInterno();
               
            }
//
//    menuInterno();
//   
    }
    
    private void menuInterno() {

        while (opcion != 0) {

            System.out.println("---------------------------Menú------------------------------------");
            System.out.println("1-Mostrar Recetarios disponibles ");
            System.out.println("2-Menú Recetario ");
            System.out.println("3-Menú Receta ");
            System.out.println("4-Validación con XSD");
            System.out.println("0-Salir");
            System.out.println();
            System.out.print("Introducca una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    System.out.println("Estos son los recetarios disponibles");
                    ArrayList <String> nombres=modelo.obtenerRecetarios(idUser);
                    if(nombres.get(0).equals("vacio")){
                        System.out.println("Este usuario no tiene recetarios");
                    }else{
                    for (String recetarios : nombres) {
                        System.out.println(recetarios);
                    }
                    }
                    break;
                case 2:
                    limpiarTerminal(20);
                    while (opcion != 0) {

                        try {
                            System.out.println("-----------------------Menú Recetario-------------------------------");
                            System.out.println("1-Crear");
                            System.out.println("2-Leer");
                            System.out.println("3-Añadir Recetas");
                            System.out.println("4-Borrar");
                            System.out.println("5-Importar");
                            System.out.println("6-Exportar");
                            System.out.println("0-Volver al Menu principal");
                            System.out.println();
                            System.out.print("Introducca una opción: ");
                            opcion = Integer.parseInt(scanner.nextLine());
                            switch (opcion) {
                                case 1:
                                    //crear recetario
                                    System.out.println("En esta opcion creará el recetario con las recetas introucidas o importadas");
                                    System.out.println("Introduce el nombre del recetario");
                                    respuesta = scanner.nextLine();
                                    System.out.println("Introduce el precio del recetario");
                                    respuesta2 = scanner.nextLine();
                                    modelo.crearRecetario(modelo.crearRecetarioEsructura(respuesta, Double.parseDouble(respuesta2)),idUser);

                                    break;

                                case 2:
                                    //leer recetario                          
                                    System.out.println("Introduce el nombre del recetario");
                                    respuesta = scanner.nextLine();
                                    listarRecetario(modelo.obtenerRecetario(respuesta));
                                    break;
                                 case 3:
                                    // Añade la receta escogida
                                    System.out.println("Introduce el nombre del recetario");
                                    respuesta = scanner.nextLine();
                                    System.out.println("Introduce el nombre de la receta");
                                    respuesta2 = scanner.nextLine();
                                    modelo.addReceta(respuesta,respuesta2);
                                    limpiarTerminal(10);
                                    break;
                                 case 4:
                                    // Borrar recetario
                                    System.out.println("Introduce el nombre del recetario");
                                    respuesta = scanner.nextLine();                                   
                                    modelo.rmvRecetario(idUser, respuesta);
                                    limpiarTerminal(10);
                                    break;
                                case 5:
                                    // Importar recetario
                                    System.out.println("Introduce el nombre del fichero sin la extensión del recetario");
                                    respuesta = scanner.nextLine();
                                    modelo.importarRecetario(new File(ruta + "/files/xml/" + respuesta + ".xml"),idUser);
                                    break;
                                case 6:
                                    //Exportar recetario
                                     System.out.println("Introduce el nombre del recetario");
                                    respuesta2 = scanner.nextLine();
                                    System.out.println("Introduce el nombre del fichero sin la extensión del recetario");
                                    respuesta = scanner.nextLine();
                                    modelo.leerBytes(modelo.exportarRecetario(respuesta, respuesta2), respuesta);
                                    break;

                                default:
                                    System.out.println("Error, introduzca un numero del 0 al 4");

                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    opcion = -1;
                    limpiarTerminal(20);
                    break;
                case 3:
                    limpiarTerminal(10);
                    while (opcion != 0) {

                        try {
                            System.out.println("-----------------------Menú Receta-------------------------------");        
                            System.out.println("1-Crear");
                            System.out.println("2-Leer");
                            System.out.println("3-Borrar");
                            System.out.println("4-Importar");
                            System.out.println("5-Exportar");
                            System.out.println("0-Volver al Menu principal");
                            System.out.println();
                            System.out.print("Introducca una opción: ");
                            opcion = Integer.parseInt(scanner.nextLine());
                            switch (opcion) {
                                case 1:
                                    //crear recetas
//                                    System.out.println("Introduce el nombre del recetario donde estará la receta");
//                                    respuesta3 = scanner.nextLine();
                                    System.out.println("Introduce el nombre de la receta");
                                    respuesta = scanner.nextLine();
                                    System.out.println("Introduce la dificultad de la receta");
                                    respuesta2 = scanner.nextLine();
                                    System.out.println("Introduce el precio de la receta");
                                    respuesta4 = scanner.nextLine();
                                    modelo.crearReceta(modelo.crearRecetaEsructura(respuesta, respuesta2, Double.parseDouble(respuesta4), pedirIngredientes()));
                                    limpiarTerminal(10);
                                    break;

                                case 2:
                                    // Lee la receta escogida
                                    System.out.println("Introduce el nombre de la receta");
                                    respuesta = scanner.nextLine();
                                    listarReceta(modelo.obtenerReceta(respuesta));
                                    break;
                                case 3:
                                    // Borra la receta escogida
                                    System.out.println("Introduce el nombre de la receta");
                                    respuesta = scanner.nextLine();
                                    System.out.println("Introduce el nombre del recetario");
                                    respuesta2 = scanner.nextLine();
                                    modelo.rmvReceta(respuesta,respuesta2);
                                    limpiarTerminal(10);
                                    break;
                                case 4:
                                    //importar receta
                                    System.out.println("Introduce el nombre del fichero sin la extensión de la receta");
                                    respuesta = scanner.nextLine();
                                    modelo.importarReceta(new File(ruta + "/files/xml/" + respuesta + ".xml"));
                                    limpiarTerminal(10);
                                    break;
                                case 5:
                                    //exportar receta
                                    System.out.println("En esta opcion creará el nombre del xml de la receta.");
                                    System.out.println("Introduce el nombre de la receta a exportar");
                                    respuesta = scanner.nextLine();
                                    modelo.leerBytes(modelo.exportarReceta(respuesta + ".xml", respuesta), respuesta);
                                    limpiarTerminal(10);
                                    break;

                                default:
                                    System.out.println("Error, introduzca un numero del 0 al 5");

                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    opcion = -1;
                    limpiarTerminal(20);
                    break;
                case 4:
                    limpiarTerminal(20);
                    System.out.println("-----------------------Menú Validar XML-------------------------------");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Introduce el nombre del XML a validar sin extension: ");
                    respuesta = scanner.nextLine();
                    try {
                        System.out.println();
                        System.out.println(modelo.validarXSD(new File(ruta + "/files/xml/" + respuesta + ".xml")));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    System.out.println("Error, introduzca un numero del 0 al 3");

            }
        }

    }

    private void limpiarTerminal(int valor) {
        for (int i = 0; i < valor; i++) {
            System.out.println();
        }

    }

    private ArrayList pedirIngredientes() {
        ArrayList<String> ingredientes = new ArrayList();
        System.out.println("Introduce los ingredientes, para finalizar introduce como ultimo ingrediente un 0");
        while (respuesta.equals("0") != true) {
            respuesta = scanner.nextLine();
            if (respuesta.equals("0") == false) {

                ingredientes.add(respuesta);
            }

        }
        return ingredientes;
    }

    private boolean listarRecetario(Recetario recetario) {
        if (recetario == null) {
            return false;
        } else {
            System.out.println("El recetario " + recetario.getNombre());
            System.out.println("Cuesta: " + recetario.getPrecio());
            try {

                String recetas = " ";
                for (Receta receta : recetario.getRecetas()) {
                    recetas = recetas + receta.getNombre() + ", ";
                }
                System.out.println("Posee las siguientes recetas:" + recetas);
            } catch (Exception e) {
                System.out.println("No posee recetas");
            }

            return true;
        }

    }

    private boolean listarReceta(Receta receta) {
        if (receta.getNombre() == null) {
            return false;
        } else {
            System.out.println("Su nombre es: " + receta.getNombre());
            System.out.println("La dificultad es: " + receta.getDificultad());

            String ing = " ";
            for (String ele : receta.getIngrediente()) {
                ing = ing + ele + ", ";
            }
            System.out.println("tiene los siguientes ingredientes:" + ing);
            System.out.println("Su precio es: " + receta.getPrecio());
            return true;
        }

    }

}
