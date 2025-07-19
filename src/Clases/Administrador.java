package Clases;

import Interfaces.IServiciosRecursos;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;  

public class Administrador extends Usuario implements IServiciosRecursos {

    public Administrador(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.tipoDeUser = 1;
    }

    //Constructor de Prueba
    public Administrador(String id_codigo, String contraseña, String nombre) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;

        this.tipoDeUser = 1;
    }

    public Administrador(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 1;
    }

    /* 
    public void gestionarRecursos(List<Material> materiales, List<RecursoTecnologico> recursos) {
        Scanner ad = new Scanner(System.in);
        int opcionGeneral = 0;

        do {
            System.out.println("\n=== GESTIÓN DE RECURSOS ===");
            System.out.println("1. Gestionar Materiales");
            System.out.println("2. Gestionar Recursos Tecnológicos");
            System.out.println("3. Salir de gestión de recursos");
            System.out.print("Opción: ");

            try {
                opcionGeneral = Integer.parseInt(ad.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            switch (opcionGeneral) {
                case 1:
                    int opcionMaterial = 0;
                    do {
                        System.out.println("\n--- GESTIÓN DE MATERIALES ---");
                        System.out.println("1. Agregar Material");
                        System.out.println("2. Editar Material");
                        System.out.println("3. Eliminar Material");
                        System.out.println("4. Volver");
                        System.out.print("Opción: ");

                        try {
                            opcionMaterial = Integer.parseInt(ad.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opción inválida. Intente de nuevo.");
                            continue;
                        }

                        switch (opcionMaterial) {
                            case 1:
                                agregarMaterial();
                                break;
                            case 2:
                                editarMaterial(materiales);
                                break;
                            case 3:
                                eliminarMaterial(materiales);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    } while (opcionMaterial != 4);
                    break;

                case 2:
                    int opcionRecurso = 0;
                    do {
                        System.out.println("\n--- GESTIÓN DE RECURSOS TECNOLÓGICOS ---");
                        System.out.println("1. Agregar Recurso Tecnológico");
                        System.out.println("2. Editar Recurso Tecnológico");
                        System.out.println("3. Eliminar Recurso Tecnológico");
                        System.out.println("4. Volver");
                        System.out.print("Opción: ");

                        try {
                            opcionRecurso = Integer.parseInt(ad.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Opción inválida. Intente de nuevo.");
                            continue;
                        }

                        switch (opcionRecurso) {
                            case 1:
                                agregarRecursoTecnologico();
                                break;
                            case 2:
                                editarRecursoTecnologico(recursos);
                                break;
                            case 3:
                                eliminarRecursoTecnologico(recursos);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    } while (opcionRecurso != 4);
                    break;

                case 3:
                    System.out.println("Saliendo de la gestión de recursos...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcionGeneral != 3);
    }
    */

    @Override
    public void verificarReservas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void generadorReporte() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exportarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}