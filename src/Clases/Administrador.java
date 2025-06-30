package Clases;

import Interfaces.IServiciosRecursos;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;  

/**
 *
 * @author Dayanna
 */
public class Administrador extends Usuario implements IServiciosRecursos {

    public Administrador(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.tipoDeUser = "Administrador";
    }

    //Constructor de Prueba
    public Administrador(String id_codigo, String contraseña, String nombre) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;

        this.tipoDeUser = "Administrador";
    }

    public Administrador(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Administrador";
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
    
    public void agregarMaterial() {
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== AGREGAR MATERIAL ===");
        System.out.println("Seleccione tipo material");
        System.out.println("1. Libro");
        System.out.println("2. Archivo Multimedia");
        System.out.println("3. Archivo Digital");
        System.out.println("Opcion: ");
        int tipo = Integer.parseInt(ad.nextLine());
        
        System.out.println("Codigo: ");
        String codigo = ad.nextLine();
        System.out.println("Nombre: ");
        String nombre = ad.nextLine();
        System.out.println("Estado: ");
        String estado = ad.nextLine();
        System.out.println("Autor: ");
        String autor = ad.nextLine();
        System.out.println("Fecha de publicacion (dd-mm-yy: ");
        String fechaStr = ad.nextLine();
        Date fechaPublicacion;
        try {
            fechaPublicacion = new SimpleDateFormat("dd-mm-yy").parse(fechaStr);
        } catch (Exception e){
            System.out.println("Fecha invalida. Se usará lña fecha actual. ");
            fechaPublicacion = new Date();
    }
        
        if (tipo == 1){
            System.out.println("¿Disponible? (true/false): ");
            boolean disponibilidad = Boolean.parseBoolean(ad.nextLine());
            
            System.out.println("Titulo: ");
            String titulo = ad.nextLine();
            
            System.out.println("Genero: ");
            String genero = ad.nextLine();
            
            Libro libro = new Libro(codigo, nombre, estado, autor, fechaPublicacion, disponibilidad, titulo, genero);
            
            System.out.println("\nLibro agregado correctamente:  ");
            libro.mostrarInfo();
            System.out.println("Disponibilidad: " + (libro.isDisponibilidad()? "Si" : "No"));
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Genero: " + libro.getGenero());
          
        } else if (tipo == 2){
            System.out.println("Tamaño (MB): ");
            double tamaño = Double.parseDouble(ad.nextLine());
            
            System.out.println("Duracion: ");
            double duracion = Double.parseDouble(ad.nextLine());
            
            System.out.println("Formato: ");
            String formato = ad.nextLine();
            
            ArchivoMultimedia archivo = new ArchivoMultimedia(codigo, nombre, estado, autor, fechaPublicacion, tamaño, duracion, formato);
            
            System.out.println("\nArchivo Multimedia agregado correctamente:  ");
            archivo.mostrarInfo();
            
        } else if (tipo == 3){
             System.out.println("Formato: ");
            String formato = ad.nextLine();
            
            System.out.println("Ruta: ");
            String ruta = ad.nextLine();
          
            ArchivoDigital arDi= new ArchivoDigital(codigo, nombre, estado, autor, fechaPublicacion, formato, ruta);
            
            System.out.println("\nArchivo Digital agregado correctamente:  ");
            arDi.mostrarInfo();
                    
        } else {
            System.out.println("Tipo de material no valido");
        }
   
    }
    
    public void editarMaterial(List<Material> materiales) { //(SEPARAR) (FALTA VISUALIZAR) 
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== EDITAR MATERIAL ===");
        System.out.println("Ingrese el código del material a editar: ");
        String codigoBuscado = ad.nextLine();

        Material materialEncontrado = null;

        for (Material m : materiales) {
            if (m.getCodigo().equals(codigoBuscado)) {
                materialEncontrado = m;
                break;
            }
        }

        if (materialEncontrado == null) {
            System.out.println("Material no encontrado con el código: " + codigoBuscado);
            return;
        }

        System.out.println("Material encontrado:");
        materialEncontrado.mostrarInfo();

        System.out.println("\nIngrese los nuevos datos (deje en blanco si no desea cambiar):");

        System.out.print("Nuevo nombre: ");
        String nombre = ad.nextLine();
        if (!nombre.isEmpty()) materialEncontrado.setNombre(nombre);

        System.out.print("Nuevo estado: ");
        String estado = ad.nextLine();
        if (!estado.isEmpty()) materialEncontrado.setEstado(estado);

        System.out.print("Nuevo autor: ");
        String autor = ad.nextLine();
        if (!autor.isEmpty()) materialEncontrado.setAutor(autor);

        System.out.print("Nueva fecha de publicación (dd-mm-yy): ");
        String fechaStr = ad.nextLine();
        if (!fechaStr.isEmpty()) {
            try {
                Date fechaPublicacion = new SimpleDateFormat("dd-mm-yy").parse(fechaStr);
                materialEncontrado.setFechaPublicacion(fechaPublicacion);
            } catch (Exception e) {
                System.out.println("Fecha inválida. No se actualizó.");
            }
        }

        if (materialEncontrado instanceof Libro) {
            Libro libro = (Libro) materialEncontrado;

            System.out.print("Nueva disponibilidad (true/false): ");
            String disponibilidadStr = ad.nextLine();
            if (!disponibilidadStr.isEmpty()) libro.setDisponibilidad(Boolean.parseBoolean(disponibilidadStr));

            System.out.print("Nuevo título: ");
            String titulo = ad.nextLine();
            if (!titulo.isEmpty()) libro.setTitulo(titulo);

            System.out.print("Nuevo género: ");
            String genero = ad.nextLine();
            if (!genero.isEmpty()) libro.setGenero(genero);

        } else if (materialEncontrado instanceof ArchivoMultimedia) {
            ArchivoMultimedia archivo = (ArchivoMultimedia) materialEncontrado;

            System.out.print("Nuevo tamaño (MB): ");
            String tamañoStr = ad.nextLine();
            if (!tamañoStr.isEmpty()) {
                try {
                    archivo.setTamaño(Double.parseDouble(tamañoStr));
                } catch (NumberFormatException e) {
                    System.out.println("Tamaño inválido. No se actualizó.");
                }
            }

            System.out.print("Nueva duración (min): ");
            String duracionStr = ad.nextLine();
            if (!duracionStr.isEmpty()) {
                try {
                    archivo.setDuracion(Double.parseDouble(duracionStr));
                } catch (NumberFormatException e) {
                    System.out.println("Duración inválida. No se actualizó.");
                }
            }

            System.out.print("Nuevo formato: ");
            String formato = ad.nextLine();
            if (!formato.isEmpty()) archivo.setFormato(formato);

        } else if (materialEncontrado instanceof ArchivoDigital) {
            ArchivoDigital archivo = (ArchivoDigital) materialEncontrado;

            System.out.print("Nuevo formato: ");
            String formato = ad.nextLine();
            if (!formato.isEmpty()) archivo.setFormato(formato);

            System.out.print("Nueva ruta: ");
            String ruta = ad.nextLine();
            if (!ruta.isEmpty()) archivo.setRuta(ruta);
        }

        System.out.println("\nMaterial editado correctamente:");
        materialEncontrado.mostrarInfo();
    }
    
    public void eliminarMaterial(List<Material> materiales) {
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== ELIMINAR MATERIAL ===");
        System.out.println("Ingrese el código del material a eliminar: ");
        String codigoBuscado = ad.nextLine();

        Material materialEncontrado = null;

        for (Material m : materiales) {
            if (m.getCodigo().equals(codigoBuscado)) {
                materialEncontrado = m;
                break;
            }
        }

        if (materialEncontrado == null) {
            System.out.println("Material no encontrado con el código: " + codigoBuscado);
            return;
        }

        System.out.println("Material encontrado:");
        materialEncontrado.mostrarInfo();
        System.out.print("¿Está seguro que desea eliminar este material? (si/no): ");
        String confirmacion = ad.nextLine();

        if (confirmacion.equalsIgnoreCase("si")) {
            materiales.remove(materialEncontrado);
            System.out.println("Material eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void agregarRecursoTecnologico() {
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== AGREGAR RECURSO TECNOLÓGICO ===");
        System.out.println("Seleccione tipo de recurso:");
        System.out.println("1. Tablet");
        System.out.println("2. Computadora");
        System.out.print("Opción: ");
        int tipo = Integer.parseInt(ad.nextLine());

        System.out.print("ID Código: ");
        String IDcodigo = ad.nextLine();

        if (tipo == 1) {
            System.out.print("Modelo: ");
            String modelo = ad.nextLine();

            System.out.print("Stock: ");
            int stock = Integer.parseInt(ad.nextLine());

            Tablet tablet = new Tablet(IDcodigo, modelo, stock);

            System.out.println("\nTablet agregada correctamente:");
            tablet.mostrarInfo();

        } else if (tipo == 2) {
            System.out.print("RAM: ");
            String ram = ad.nextLine();

            System.out.print("Procesador: ");
            String procesador = ad.nextLine();

            System.out.print("CPU: ");
            String cpu = ad.nextLine();

            System.out.print("¿Está desbloqueada? (true/false): ");
            boolean estado = Boolean.parseBoolean(ad.nextLine());

            Computadora compu = new Computadora(IDcodigo, ram, procesador, cpu, estado);

            System.out.println("\nComputadora agregada correctamente:");
            compu.mostrarInfo();

        } else {
            System.out.println("Tipo de recurso no válido.");
        }
    }
   
    public void editarRecursoTecnologico(List<RecursoTecnologico> recursos) {
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== EDITAR RECURSO TECNOLÓGICO ===");
        System.out.print("Ingrese el ID Código del recurso a editar: ");
        String IDcodigoBuscado = ad.nextLine();

        RecursoTecnologico recursoEncontrado = null;
        for (RecursoTecnologico r : recursos) {
            if (r.getIDcodigo().equals(IDcodigoBuscado)) {
                recursoEncontrado = r;
                break;
            }
        }

        if (recursoEncontrado == null) {
            System.out.println("Recurso no encontrado con el ID: " + IDcodigoBuscado);
            return;
        }

        System.out.println("Recurso encontrado:");
        recursoEncontrado.mostrarInfo();
        System.out.println("\nIngrese los nuevos datos (deje en blanco si no desea cambiar):");

        if (recursoEncontrado instanceof Tablet) {
            Tablet tablet = (Tablet) recursoEncontrado;

            System.out.print("Nuevo modelo: ");
            String modelo = ad.nextLine();
            if (!modelo.isEmpty()) tablet.setModelo(modelo);

            System.out.print("Nuevo stock: ");
            String stockStr = ad.nextLine();
            if (!stockStr.isEmpty()) {
                try {
                    tablet.setStock(Integer.parseInt(stockStr));
                } catch (NumberFormatException e) {
                    System.out.println("Stock inválido. No se actualizó.");
                }
            }

        } else if (recursoEncontrado instanceof Computadora) {
            Computadora compu = (Computadora) recursoEncontrado;

            System.out.print("Nueva RAM: ");
            String ram = ad.nextLine();
            if (!ram.isEmpty()) compu.setRam(ram);

            System.out.print("Nuevo procesador: ");
            String procesador = ad.nextLine();
            if (!procesador.isEmpty()) compu.setProcesador(procesador);

            System.out.print("Nuevo CPU: ");
            String cpu = ad.nextLine();
            if (!cpu.isEmpty()) compu.setCpu(cpu);

            System.out.print("¿Nueva condición desbloqueada? (true/false): ");
            String estadoStr = ad.nextLine();
            if (!estadoStr.isEmpty()) {
                boolean estado = Boolean.parseBoolean(estadoStr);
                if (estado) compu.desbloquear();
                else compu.bloquear();
            }
        }

        System.out.println("\nRecurso editado correctamente:");
        recursoEncontrado.mostrarInfo();
    }
    
    public void eliminarRecursoTecnologico(List<RecursoTecnologico> recursos) {
        Scanner ad = new Scanner(System.in);
        System.out.println("\n=== ELIMINAR RECURSO TECNOLÓGICO ===");
        System.out.print("Ingrese el ID Código del recurso a eliminar: ");
        String IDcodigoBuscado = ad.nextLine();

        RecursoTecnologico recursoEncontrado = null;
        for (RecursoTecnologico r : recursos) {
            if (r.getIDcodigo().equals(IDcodigoBuscado)) {
                recursoEncontrado = r;
                break;
            }
        }

        if (recursoEncontrado == null) {
            System.out.println("Recurso no encontrado con el ID: " + IDcodigoBuscado);
            return;
        }

        System.out.println("Recurso encontrado:");
        recursoEncontrado.mostrarInfo();
        System.out.print("¿Está seguro que desea eliminar este recurso? (si/no): ");
        String confirmacion = ad.nextLine();

        if (confirmacion.equalsIgnoreCase("si")) {
            recursos.remove(recursoEncontrado);
            System.out.println("Recurso eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void generadorReporte() { //LO MISMO QUE REPCIONISTA
        System.out.println("Reporte generado.");
    }

    public void exportarInfo() {
        try {
            FileWriter writer = new FileWriter("exportacion.txt");
            writer.write("Exportación de información:\n");
            writer.write("Aquí puedes poner los datos que quieras exportar.\n");
            writer.write("Por ejemplo: Último material agregado o información fija.\n");
            writer.close();
            System.out.println("Información exportada correctamente a exportacion.txt");
        } catch (IOException e) {
            System.out.println("Error al exportar información: " + e.getMessage());
        }
    }
}