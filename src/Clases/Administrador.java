package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServiciosRecursos;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Administrador extends Usuario implements IServiciosRecursos {
    Scanner ad = new Scanner(System.in);
    
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

    public void opcionesAdmi(){
        //agregar menu de agregar material, agregarrecursostecnologicos, editar o eliminar
    }
    public void agregarMaterial() {
        int opcionM = 0;
        String MaterialesM = 
            "=== AGREGAR MATERIAL ===\n" +      
            "Seleccione tipo material\n"+
            "1. Libro\n"+
            "2. Archivo Multimedia\n"+
            "3. Archivo Digital\n"+
            "4. Salir\n"+
            "=========================\n"+
            "Elija una opcion: ";

        do {
            System.out.println(MaterialesM);
            try {
                opcionM = Integer.parseInt(ad.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida numérica.");
                opcionM = 0;
            }

            Date fechaPublicacion;

            switch (opcionM) {
                case 1: // LIBRO
                    System.out.println("=== Por favor ingrese los siguientes datos ===");
                    System.out.println("Codigo del libro: ");
                    String codigoL = ad.nextLine().trim();
                    if (codigoL.isEmpty()) {
                        System.out.println("Codigo no puede estar vacío.");
                        break;
                    }
                    
                    System.out.print("Nombre del libro: ");
                    String nombreL = ad.nextLine().trim();
                    if (nombreL.isEmpty()) {
                        System.out.println("Nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("Estado del libro (Disponible/Prestado): ");
                    String estadoL = ad.nextLine().trim();
                    if (estadoL.isEmpty()) {
                        System.out.println("Estado no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor del libro: ");
                    String autorL = ad.nextLine().trim();
                    if (autorL.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    System.out.print("Fecha de publicación (dd-MM-yy): ");
                    String fechaStr = ad.nextLine();
                    try {
                        fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStr);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. Se usará la actual.");
                        fechaPublicacion = new Date();
                    }

                    System.out.print("¿Disponible? (true/false): ");
                    boolean disponibilidad;
                    String dispStr = ad.nextLine().trim();
                    if (dispStr.equalsIgnoreCase("true") || dispStr.equalsIgnoreCase("false")) {
                        disponibilidad = Boolean.parseBoolean(dispStr);
                    } else {
                        System.out.println("Valor inválido para disponibilidad.");
                        break;
                    }

                    System.out.print("Título: ");
                    String titulo = ad.nextLine().trim();
                    if (titulo.isEmpty()) {
                        System.out.println("Título no puede estar vacío.");
                        break;
                    }

                    System.out.print("Género: ");
                    String genero = ad.nextLine().trim();
                    if (genero.isEmpty()) {
                        System.out.println("Género no puede estar vacío.");
                        break;
                    }

                    Libro libro = new Libro(codigoL, nombreL, estadoL, autorL, fechaPublicacion, disponibilidad, titulo, genero);
                    if (UsuarioDA.agregarLibro(libro)) {
                        System.out.println("\nLibro agregado correctamente:");
                        libro.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar el libro.");
                    }
                    break;

                /*case 2: // ARCHIVO MULTIMEDIA
                    System.out.println("El código se generará automáticamente: ");

                    System.out.print("Nombre del archivo: ");
                    String nombreM = ad.nextLine().trim();
                    if (nombreM.isEmpty()) {
                        System.out.println("Nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("Estado: ");
                    String estadoM = ad.nextLine().trim();
                    if (estadoM.isEmpty()) {
                        System.out.println("Estado no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor: ");
                    String autorM = ad.nextLine().trim();
                    if (autorM.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    System.out.print("Fecha de publicación (dd-MM-yy): ");
                    String fechaStrM = ad.nextLine();
                    try {
                        fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStrM);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. Se usará la actual.");
                        fechaPublicacion = new Date();
                    }

                    System.out.print("Tamaño (MB): ");
                    double tamaño;
                    try {
                        tamaño = Double.parseDouble(ad.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Tamaño inválido.");
                        break;
                    }

                    System.out.print("Duración (min): ");
                    double duracion;
                    try {
                        duracion = Double.parseDouble(ad.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Duración inválida.");
                        break;
                    }

                    System.out.print("Formato: ");
                    String formatoM = ad.nextLine().trim();
                    if (formatoM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    ArchivoMultimedia archivoM = new ArchivoMultimedia(codigo, nombreM, estadoM, autorM, fechaPublicacion, tamaño, duracion, formatoM);
                    if (UsuarioDA.agregarArchivoMultimedia(archivoM)) {
                        System.out.println("\nArchivo multimedia agregado correctamente:");
                        archivoM.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar archivo multimedia.");
                    }
                    break;*/

                /*case 3: // ARCHIVO DIGITAL
                    System.out.println("El código se generará automáticamente: " + codigo);

                    System.out.print("Nombre del archivo: ");
                    String nombreD = ad.nextLine().trim();
                    if (nombreD.isEmpty()) {
                        System.out.println("Nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("Estado: ");
                    String estadoD = ad.nextLine().trim();
                    if (estadoD.isEmpty()) {
                        System.out.println("Estado no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor: ");
                    String autorD = ad.nextLine().trim();
                    if (autorD.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    System.out.print("Fecha de publicación (dd-MM-yy): ");
                    String fechaStrD = ad.nextLine();
                    try {
                        fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStrD);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. Se usará la actual.");
                        fechaPublicacion = new Date();
                    }

                    System.out.print("Formato: ");
                    String formatoD = ad.nextLine().trim();
                    if (formatoD.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Ruta o URL: ");
                    String ruta = ad.nextLine().trim();
                    if (ruta.isEmpty()) {
                        System.out.println("Ruta no puede estar vacía.");
                        break;
                    }

                    ArchivoDigital archivoD = new ArchivoDigital(codigo, nombreD, estadoD, autorD, fechaPublicacion, formatoD, ruta);
                    if (UsuarioDA.agregarArchivoDigital(archivoD)) {
                        System.out.println("\nArchivo digital agregado correctamente:");
                        archivoD.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar archivo digital.");
                    }
                    break;
                    case 4: // Salida del meú

                default:
                    System.out.println("Opción inválida. Ingrese solo 1, 2, 3 o 4.");*/ 
            }

        } while (opcionM < 1 || opcionM > 4);
    }

    public void editarMaterial() {
        int opcionEM = 0;
        String menuEM = "\n=== EDITAR MATERIAL ===\n"
                    + "Seleccione el tipo de material a editar:\n"
                    + "1. Libro\n"
                    + "2. Archivo Multimedia\n"
                    + "3. Archivo Digital\n"
                    + "0. Cancelar\n"
                    + "Elija una opción: ";

        System.out.print(menuEM);
        try {
            opcionEM = Integer.parseInt(ad.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (opcionEM) {
            case 1: {
                System.out.print("Ingrese el código del libro a editar: ");
                String codBusL = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarLibro(codBusL);

                if (materialEncontrado == null) {
                    System.out.println("Libro no encontrado con el código: " + codBusL);
                    return;
                }

                System.out.println("Libro encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                System.out.print("Nuevo nombre: ");
                String nombreL = ad.nextLine();
                if (!nombreL.isEmpty()) materialEncontrado.setNombre(nombreL);

                System.out.print("Nuevo estado: ");
                String estadoL = ad.nextLine();
                if (!estadoL.isEmpty()) materialEncontrado.setEstado(estadoL);

                System.out.print("Nuevo autor: ");
                String autorL = ad.nextLine();
                if (!autorL.isEmpty()) materialEncontrado.setAutor(autorL);

                System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                String fechaStr = ad.nextLine();
                if (!fechaStr.isEmpty()) {
                    try {
                        Date fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStr);
                        materialEncontrado.setFechaPublicacion(fechaPublicacion);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. No se actualizó.");
                    }
                }

                if (materialEncontrado instanceof Libro) {
                    Libro libro = (Libro) materialEncontrado;

                    System.out.print("Nueva disponibilidad (true/false): ");
                    String disponibilidadStr = ad.nextLine();
                    if (!disponibilidadStr.isEmpty()) {
                        if (disponibilidadStr.equalsIgnoreCase("true") || disponibilidadStr.equalsIgnoreCase("false")) {
                            libro.setDisponibilidad(Boolean.parseBoolean(disponibilidadStr));
                        } else {
                            System.out.println("Valor inválido. No se modificó disponibilidad.");
                        }
                    }

                    System.out.print("Nuevo título: ");
                    String titulo = ad.nextLine();
                    if (!titulo.isEmpty()) libro.setTitulo(titulo);

                    System.out.print("Nuevo género: ");
                    String genero = ad.nextLine();
                    if (!genero.isEmpty()) libro.setGenero(genero);

                    boolean actualizado = UsuarioDA.actualizarLibro(libro);
                    if (actualizado) {
                        System.out.println("Libro editado correctamente:");
                        libro.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el libro.");
                    }
                }
                break;
            }
            /*case 2: {
                System.out.print("Ingrese el código del archivo multimedia a editar: ");
                String codBus = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarArchivoMultimedia(codBus);

                if (materialEncontrado == null) {
                    System.out.println("Archivo multimedia no encontrado con el código: " + codBus);
                    return;
                }

                System.out.println("Archivo multimedia encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                System.out.print("Nuevo nombre: ");
                String nombre = ad.nextLine();
                if (!nombre.isEmpty()) materialEncontrado.setNombre(nombre);

                System.out.print("Nuevo estado: ");
                String estado = ad.nextLine();
                if (!estado.isEmpty()) materialEncontrado.setEstado(estado);

                System.out.print("Nuevo autor: ");
                String autor = ad.nextLine();
                if (!autor.isEmpty()) materialEncontrado.setAutor(autor);

                System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                String fechaStr = ad.nextLine();
                if (!fechaStr.isEmpty()) {
                    try {
                        Date fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStr);
                        materialEncontrado.setFechaPublicacion(fechaPublicacion);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. No se actualizó.");
                    }
                }

                if (materialEncontrado instanceof ArchivoMultimedia) {
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

                    boolean actualizado = UsuarioDA.actualizarArchivoMultimedia(archivo);
                    if (actualizado) {
                        System.out.println("Archivo multimedia editado correctamente:");
                        archivo.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el archivo multimedia.");
                    }
                }
                break;
            }
            case 3: {
                System.out.print("Ingrese el código del archivo digital a editar: ");
                String codBus = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarArchivoDigital(codBus);

                if (materialEncontrado == null) {
                    System.out.println("Archivo digital no encontrado con el código: " + codBus);
                    return;
                }

                System.out.println("Archivo digital encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                System.out.print("Nuevo nombre: ");
                String nombre = ad.nextLine();
                if (!nombre.isEmpty()) materialEncontrado.setNombre(nombre);

                System.out.print("Nuevo estado: ");
                String estado = ad.nextLine();
                if (!estado.isEmpty()) materialEncontrado.setEstado(estado);

                System.out.print("Nuevo autor: ");
                String autor = ad.nextLine();
                if (!autor.isEmpty()) materialEncontrado.setAutor(autor);

                System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                String fechaStr = ad.nextLine();
                if (!fechaStr.isEmpty()) {
                    try {
                        Date fechaPublicacion = new SimpleDateFormat("dd-MM-yy").parse(fechaStr);
                        materialEncontrado.setFechaPublicacion(fechaPublicacion);
                    } catch (Exception e) {
                        System.out.println("Fecha inválida. No se actualizó.");
                    }
                }

                if (materialEncontrado instanceof ArchivoDigital) {
                    ArchivoDigital archivo = (ArchivoDigital) materialEncontrado;

                    System.out.print("Nuevo formato: ");
                    String formato = ad.nextLine();
                    if (!formato.isEmpty()) archivo.setFormato(formato);

                    System.out.print("Nueva ruta o URL: ");
                    String ruta = ad.nextLine();
                    if (!ruta.isEmpty()) archivo.setRuta(ruta);

                    boolean actualizado = UsuarioDA.actualizarArchivoDigital(archivo);
                    if (actualizado) {
                        System.out.println("Archivo digital editado correctamente:");
                        archivo.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el archivo digital.");
                    }
                }
                break;
            }*/
            default:
                System.out.println("Opción inválida.");
                break;
                
        } while (opcionEM < 1 || opcionEM > 4);
    }
    
    public void eliminarMaterial(){
        int opcionEM = 0;
        String menuEM = "\n=== ELIMINAR MATERIAL ===\n"
                      + "Seleccione el tipo de material a eliminar:\n"
                      + "1. Libro\n"
                      + "2. Archivo Multimedia\n"
                      + "3. Archivo Digital\n"
                      + "0. Cancelar\n"
                      + "Elija una opción: ";

        System.out.print(menuEM);
        try {
            opcionEM = Integer.parseInt(ad.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (opcionEM) {
            case 1: {
                System.out.print("Ingrese el código del libro a eliminar: ");
                String codL = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarLibro(codL);

                if (materialEncontrado == null) {
                    System.out.println("Libro no encontrado con el código: " + codL);
                    return;
                }

                System.out.println("Libro encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este libro? (si/no): ");
                String confirmacion = ad.nextLine();
                if (confirmacion.equalsIgnoreCase("si")) {
                    boolean eliminado = UsuarioDA.eliminarLibro(codL);
                    if (eliminado) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el libro.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            /*case 2: {
                System.out.print("Ingrese el código del archivo multimedia a eliminar: ");
                String codM = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarArchivoMultimedia(codM);

                if (materialEncontrado == null) {
                    System.out.println("Archivo multimedia no encontrado con el código: " + codM);
                    return;
                }

                System.out.println("Archivo multimedia encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este archivo multimedia? (si/no): ");
                String confirmacion = ad.nextLine();
                if (confirmacion.equalsIgnoreCase("si")) {
                    boolean eliminado = UsuarioDA.eliminarArchivoMultimedia(codM);
                    if (eliminado) {
                        System.out.println("Archivo multimedia eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el archivo multimedia.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            case 3: {
                System.out.print("Ingrese el código del archivo digital a eliminar: ");
                String codD = ad.nextLine();
                Material materialEncontrado = UsuarioDA.buscarArchivoDigital(codD);

                if (materialEncontrado == null) {
                    System.out.println("Archivo digital no encontrado con el código: " + codD);
                    return;
                }

                System.out.println("Archivo digital encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este archivo digital? (si/no): ");
                String confirmacion = ad.nextLine();
                if (confirmacion.equalsIgnoreCase("si")) {
                    boolean eliminado = UsuarioDA.eliminarArchivoDigital(codD);
                    if (eliminado) {
                        System.out.println("Archivo digital eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el archivo digital.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }*/

            case 0:
                System.out.println("Operación cancelada.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
    
    
    public void agregarRecursoTecnologico() {
        int opcionRT = 0;
        String RecTec = 
            "=== AGREGAR RECURSO TECNOLOGICO ===\n" +      
            "Seleccione tipo material\n"+
            "1. Tablet\n"+
            "2. Computadora\n"+
            "3. Salir del menú\n"+
            "=========================\n"+
            "Elija una opcion: ";

        do {
            System.out.println(RecTec);
            try {
                opcionRT = Integer.parseInt(ad.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida numérica.");
                opcionRT = 0;
            }

            switch (opcionRT) {
                case 1: // TABLET
                    System.out.println("=== Por favor ingrese los siguientes datos ===");
                    System.out.println("Codigo del la tablet: ");
                    String IDcodigoT = ad.nextLine().trim();
                    if (IDcodigoT.isEmpty()) {
                        System.out.println("Codigo no puede estar vacío.");
                        break;
                    }
                    
                    System.out.print("Ingrese el modelo de la tablet");
                    String modeloT = ad.nextLine().trim();
                    if (modeloT.isEmpty()) {
                        System.out.println("El modelo no puede estar vacío.");
                        break;
                    }

                    System.out.print("Estado del tablet (Disponible/Prestado): ");
                    String estadoT = ad.nextLine().trim();
                    if (estadoT.isEmpty()) {
                        System.out.println("El estado de la tablet no puede estar vacío.");
                        break;
                    }

                    Tablet tablet = new Tablet(IDcodigoT,modeloT, estadoT);
                    if (UsuarioDA.agregarTablet(tablet)) {
                        System.out.println("\nTablet agregado correctamente:");
                        tablet.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar la tablet.");
                    }
                    break;

                case 2: // COMPUTADORA
                    System.out.println("=== Por favor ingrese los siguientes datos ===");
                    System.out.println("Codigo del la Computadora: ");
                    String IDcodigoC = ad.nextLine().trim();
                    if (IDcodigoC.isEmpty()) {
                        System.out.println("Codigo no puede estar vacío.");
                        break;
                    }

                    System.out.print("Ram de la computadora: ");
                    String ramC = ad.nextLine().trim();
                    if (ramC.isEmpty()) {
                        System.out.println("Ram no puede estar vacío.");
                        break;
                    }

                    System.out.print("Procesador de la computadora: ");
                    String procC = ad.nextLine().trim();
                    if (procC.isEmpty()) {
                        System.out.println("Procesador no puede estar vacío.");
                        break;
                    }

                    System.out.print("Cpu: ");
                    String Cpu = ad.nextLine().trim();
                    if (Cpu.isEmpty()) {
                        System.out.println("Cpu no puede estar vacío.");
                        break;
                    }

                    System.out.print("¿Disponible? (true/false): ");
                        String estadoStr = ad.nextLine().trim();

                        if (estadoStr.isEmpty()) {
                            System.out.println("Estado no puede estar vacío.");
                            break;
                        }

                        if (!estadoStr.equalsIgnoreCase("true") && !estadoStr.equalsIgnoreCase("false")) {
                            System.out.println("Ingrese 'true' o 'false'.");
                            break;
                        }

                        boolean estadoC = Boolean.parseBoolean(estadoStr);

                    Computadora computadora = new Computadora(IDcodigoC, ramC, procC, Cpu,estadoC );
                    if (UsuarioDA.agregarComputadora(computadora)) {
                        System.out.println("\nComputadora agregado correctamente:");
                        computadora.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar la computadora.");
                    }
                    break;
                case 3:
                    //salir

                default:
                    System.out.println("Opción inválida. Ingrese solo 1, 2 o 3.");
            }

        } while (opcionRT < 1 || opcionRT > 3);
    }
    
    public void editarRecursoTecnologico() { //codigos string id
        int opcionERT = 0;
        String menuERT = "\n=== EDITAR RECURSOS TECNOLOGICO ===\n"
                    + "Seleccione el tipo de recurso tecnologico a editar:\n"
                    + "1. Tablet\n"
                    + "2. Computadora\n"
                    + "0. Cancelar\n"
                    + "Elija una opción: ";

        System.out.print(menuERT);
        try {
            opcionERT = Integer.parseInt(ad.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (opcionERT) {
            case 1: {
                System.out.print("Ingrese el código del la tablet a editar: ");
                String codTab = ad.nextLine();
                RecursoTecnologico recursoEncontrado = UsuarioDA.buscarTablet(codTab);

                if (recursoEncontrado == null) {
                    System.out.println("Tablet no encontrada con el código: " + codTab);
                    return;
                }

                System.out.println("Tablet encontrada:");
                recursoEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                
                
                if (recursoEncontrado instanceof RecursoTecnologico) {
                    Tablet tablet = (Tablet) recursoEncontrado;
                    
                    System.out.print("Nuevo modelo: ");
                    String modeloT = ad.nextLine();
                    if (!modeloT.isEmpty()) recursoEncontrado.setModelo(modeloT);

                    System.out.print("Nuevo estado: ");
                    String estadoT = ad.nextLine();
                    if (!estadoT.isEmpty()) recursoEncontrado.setEstado(estadoT);
                    
                    boolean actualizado = UsuarioDA.actualizarTablet(tablet);
                    if (actualizado) {
                        System.out.println("Tablet editado correctamente:");
                        tablet.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar la tablet.");
                    }
                }
                break;
            }
            case 2: {
                System.out.print("Ingrese el código de la Computadora a editar: ");
                String codCom = ad.nextLine();
                RecursoTecnologico recursoEncontrado = UsuarioDA.buscarComputadora(codCom);

                if (recursoEncontrado == null) {
                    System.out.println("Computadora no encontrada con el código: " + codCom);
                    return;
                }

                System.out.println("Computadora encontrada:");
                recursoEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                if (recursoEncontrado instanceof Computadora) {
                    Computadora computadora = (Computadora) recursoEncontrado;

                    System.out.print("Nueva RAM: ");
                    String ramC = ad.nextLine();
                    if (!ramC.isEmpty()) computadora.setRam(ramC);

                    System.out.print("Nuevo procesador: ");
                    String procC = ad.nextLine();
                    if (!procC.isEmpty()) computadora.setProcesador(procC);

                    System.out.print("Nuevo CPU: ");
                    String cpu = ad.nextLine();
                    if (!cpu.isEmpty()) computadora.setCpu(cpu);

                    System.out.print("¿Condición desbloqueada? (true/false): ");
                    String estadoC = ad.nextLine();
                    if (!estadoC.isEmpty()) {
                        if (estadoC.equalsIgnoreCase("true")) {
                            computadora.desbloquear();
                        } else if (estadoC.equalsIgnoreCase("false")) {
                            computadora.bloquear();
                        } else {
                            System.out.println("Valor inválido. No se modificó la condición.");
                        }
                    }

                    boolean actualizado = UsuarioDA.actualizarComputadora(computadora);
                    if (actualizado) {
                        System.out.println("Computadora editada correctamente:");
                        computadora.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar la computadora.");
                    }
                } else {
                    System.out.println("El recurso no es una Computadora.");
                }
                break;
            }
 
            default:
                System.out.println("Opción inválida.");
                break;
        } while (opcionERT < 1 || opcionERT > 3);
    }
    
    public void eliminarRecursoTecnologico() {
         int opcionER = 0;
        String menuER = "\n=== ELIMINAR RECURSO TECNOLÓGICO ===\n"
                      + "Seleccione el tipo de recurso a eliminar:\n"
                      + "1. Tablet\n"
                      + "2. Computadora\n"
                      + "0. Cancelar\n"
                      + "Elija una opción: ";

        System.out.print(menuER);
        try {
            opcionER = Integer.parseInt(ad.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (opcionER) {
            case 1: {
                System.out.print("Ingrese el código de la Tablet a eliminar: ");
                String codTab = ad.nextLine();
                RecursoTecnologico recursoEncontrado = UsuarioDA.buscarTablet(codTab);

                if (recursoEncontrado == null) {
                    System.out.println("Tablet no encontrada con el código: " + codTab);
                    return;
                }

                System.out.println("Tablet encontrada:");
                recursoEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar esta tablet? (si/no): ");
                String confirmacion = ad.nextLine();

                if (confirmacion.equalsIgnoreCase("si")) {
                    boolean eliminado = UsuarioDA.eliminarTablet(codTab);
                    if (eliminado) {
                        System.out.println("Tablet eliminada correctamente.");
                    } else {
                        System.out.println("Error al eliminar la tablet.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            case 2: {
                System.out.print("Ingrese el código de la Computadora a eliminar: ");
                String codCom = ad.nextLine();
                RecursoTecnologico recursoEncontrado = UsuarioDA.buscarComputadora(codCom);

                if (recursoEncontrado == null) {
                    System.out.println("Computadora no encontrada con el código: " + codCom);
                    return;
                }

                System.out.println("Computadora encontrada:");
                recursoEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar esta computadora? (si/no): ");
                String confirmacion = ad.nextLine();

                if (confirmacion.equalsIgnoreCase("si")) {
                    boolean eliminado = UsuarioDA.eliminarComputadora(codCom);
                    if (eliminado) {
                        System.out.println("Computadora eliminada correctamente.");
                    } else {
                        System.out.println("Error al eliminar la computadora.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            case 0:
                System.out.println("Operación cancelada.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    public void agregarSala() {
        System.out.println("\n=== AGREGAR SALA ===");
        
        System.out.println("=== Por favor ingrese los siguientes datos ===");
                System.out.println("Codigo del libro: ");
                String codigoS = ad.nextLine().trim();
                if (codigoS.isEmpty()) {
                    System.out.println("Codigo no puede estar vacío.");
                        
        System.out.print("Estado del ambiente: ");
        String estadoS = ad.nextLine().trim();
        if (estadoS.isEmpty()) {
            System.out.println("Estado no puede estar vacío.");
            return;
        }

        System.out.print("Capacidad máxima: ");
        int capacidadMax;
        try {
            capacidadMax = Integer.parseInt(ad.nextLine());
            if (capacidadMax <= 0) {
                System.out.println("Capacidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Capacidad inválida.");
            return;
        }

        Sala sala = new Sala(codigoS,estadoS, capacidadMax);

        boolean agregar = UsuarioDA.agregarSala(sala);
        if (agregar) {
            System.out.println("Ambiente agregado correctamente:");
            sala.mostrarInfo();
        } else {
            System.out.println("Error al agregar ambiente.");
        }
    }
    
  
    
    public void editarSala(){

        System.out.println("\n=== EDITAR AMBIENTE ===");
        System.out.print("Ingrese el código del ambiente a editar: ");
        String codS = ad.nextLine();

        Sala salaEncontrada = UsuarioDA.buscarSala(codS);
        if (salaEncontrada == null) {
            System.out.println("Ambiente no encontrado con el código: " + codS);
            return;
        }

        System.out.println("Sala encontrada:");
        salaEncontrada.mostrarInfo();
        System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

        System.out.print("Nuevo estado: ");
        String estado = ad.nextLine();
        if (!estado.isEmpty()) salaEncontrada.setEstado(estado);

        System.out.print("Nueva capacidad máxima: ");
        String capStr = ad.nextLine();
        if (!capStr.isEmpty()) {
            try {
                int cap = Integer.parseInt(capStr);
                if (cap > 0) salaEncontrada.setCapacidadMax(cap);
                else System.out.println("Capacidad inválida. No se actualizó.");
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido. No se actualizó.");
            }
        }

        boolean actualizado = UsuarioDA.actualizarSala(salaEncontrada);
        if (actualizado) {
            System.out.println("Ambiente editado correctamente:");
            salaEncontrada.mostrarInfo();
        } else {
            System.out.println("Error al actualizar ambiente.");
        }
    }
    
    public void eliminarSala() {
        System.out.println("\n=== ELIMINAR AMBIENTE ===");
        System.out.print("Ingrese el código del ambiente a eliminar: ");
        String codS = ad.nextLine();

        Sala salaEncontrada = UsuarioDA.buscarSala(codS);
        if (salaEncontrada == null) {
            System.out.println("Ambiente no encontrado con el código: " + codS);
            return;
        }

        System.out.println("Ambiente encontrado:");
        salaEncontrada.mostrarInfo();

        System.out.print("¿Está seguro que desea eliminar este ambiente? (si/no): ");
        String confirmacion = ad.nextLine();
        if (confirmacion.equalsIgnoreCase("si")) {
            boolean eliminado = UsuarioDA.eliminarSala(codS);
            if (eliminado) {
                System.out.println("Ambiente eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar ambiente.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
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
