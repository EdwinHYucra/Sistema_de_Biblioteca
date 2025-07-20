package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServiciosRecursos;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Administrador extends Usuario implements IServiciosRecursos {

    Scanner ad = new Scanner(System.in);

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

    public void agregarMaterial() {
        int opcionM = 0;
        String MaterialesM
                = "=== AGREGAR MATERIAL ===\n"
                + "Seleccione tipo material\n"
                + "1. Libro\n"
                + "2. Ejemplar de Libro\n"
                + "3. Archivo Multimedia\n"
                + "4. Archivo Digital\n"
                + "5. Salir\n"
                + "=========================\n"
                + "Elija una opcion: ";

        do {
            System.out.println(MaterialesM);
            try {
                opcionM = Integer.parseInt(ad.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida numérica.");
                opcionM = 0;
            }

            switch (opcionM) {
                case 1: // LIBRO
                    System.out.println("=== Por favor ingrese los siguientes datos ==="); //debe mostrar el ultimo codigo
                    System.out.println("Codigo del libro: ");
                    int libroID = ad.nextInt();
                    if (libroID <= 0) {
                        System.out.println("Código inválido. Debe ser mayor a 0.");
                        break;
                    }

                    System.out.print("Nombre del libro: ");
                    String nombreL = ad.nextLine().trim();
                    if (nombreL.isEmpty()) {
                        System.out.println("Nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor del libro: ");
                    String autor = ad.nextLine().trim();
                    if (autor.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    LocalDate fechaPublicacionL = null;
                    boolean fechaValida = false;

                    DateTimeFormatter formatoL = DateTimeFormatter.ofPattern("dd-MM-yy");

                    while (!fechaValida) {
                        System.out.print("Fecha de publicación del libro (dd-MM-yy): ");
                        String input = ad.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("La fecha de publicación no puede estar vacía.");
                            continue;
                        }

                        try {
                            fechaPublicacionL = LocalDate.parse(input, formatoL);
                            fechaValida = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Usa dd-MM-yy.");
                        }
                    }

                    System.out.print("Género: ");
                    String genero = ad.nextLine().trim();
                    if (genero.isEmpty()) {
                        System.out.println("Género no puede estar vacío.");
                        break;
                    }

                    System.out.print("Idioma: ");
                    String idioma = ad.nextLine().trim();
                    if (idioma.isEmpty()) {
                        System.out.println("Idioma no puede estar vacío.");
                        break;
                    }

                    System.out.print("ISBN ");
                    String ISBN = ad.nextLine().trim();
                    if (ISBN.isEmpty()) {
                        System.out.println("ISBN no puede estar vacío.");
                        break;
                    }

                    System.out.print("Editorial: ");
                    String editorial = ad.nextLine().trim();
                    if (genero.isEmpty()) {
                        System.out.println("Editorial no puede estar vacío.");
                        break;
                    }

                    System.out.print("Edicion: ");
                    String edicion = ad.nextLine().trim();
                    if (edicion.isEmpty()) {
                        System.out.println("Editorial no puede estar vacío.");
                        break;
                    }

                    Libro libro = new Libro(libroID, nombreL, autor, fechaPublicacionL, genero, idioma, ISBN, editorial, edicion);
                    int id = 1;
                    if (UsuarioDA.agregarLibro(libro, id)) {
                        System.out.println("\nLibro agregado correctamente:");
                        libro.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar el libro.");
                    }
                    break;

                case 2: // ARCHIVO MULTIMEDIA
                    System.out.println("=== Por favor ingrese los siguientes datos ===");
                    System.out.println("Codigo de Archivo Multimedia: ");
                    int archivo_idM = ad.nextInt();
                    if (archivo_idM <= 0) {
                        System.out.println("Código inválido. Debe ser mayor a 0.");
                        break;
                    }

                    System.out.print("Nombre del archivo: ");
                    String nombreM = ad.nextLine().trim();
                    if (nombreM.isEmpty()) {
                        System.out.println("Nombre no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor: ");
                    String autorM = ad.nextLine().trim();
                    if (autorM.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    LocalDate fecha_publicacionM = null;
                    boolean fechaValidaM = false;

                    DateTimeFormatter formatoFM = DateTimeFormatter.ofPattern("dd-MM-yy");

                    while (!fechaValidaM) {
                        System.out.print("Fecha de publicación del libro (dd-MM-yy): ");
                        String input = ad.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("La fecha de publicación no puede estar vacía.");
                            continue;
                        }

                        try {
                            fecha_publicacionM = LocalDate.parse(input, formatoFM);
                            fechaValidaM = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Usa dd-MM-yy.");
                        }
                    }

                    System.out.print("Tamaño: ");
                    String tamañoM = ad.nextLine().trim();
                    if (tamañoM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Duracion: ");
                    String duracionM = ad.nextLine().trim();
                    if (duracionM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Formato: ");
                    String formatoM = ad.nextLine().trim();
                    if (formatoM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Resolucion: ");
                    String resolucionM = ad.nextLine().trim();
                    if (resolucionM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Tipo Multimedia: ");
                    String tipoMultimediaM = ad.nextLine().trim();
                    if (tipoMultimediaM.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    ArchivoMultimedia archivoMultimedia = new ArchivoMultimedia(archivo_idM, nombreM, autorM, fecha_publicacionM, tamañoM, duracionM, formatoM, resolucionM, tipoMultimediaM);
                    id = 2;
                    if (UsuarioDA.agregarArchivoMultimedia(archivoMultimedia, id)) {
                        System.out.println("\nArchivo multimedia agregado correctamente:");
                        archivoMultimedia.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar archivo multimedia.");
                    }
                    break;

                case 3: // ARCHIVO DIGITAL
                    System.out.println("=== Por favor ingrese los siguientes datos ===");

                    System.out.println("Codigo del libro: ");
                    int archivo_idD = ad.nextInt();
                    if (archivo_idD <= 0) {
                        System.out.println("Código inválido. Debe ser mayor a 0.");
                        break;
                    }

                    System.out.print("Nombre: ");
                    String nombreD = ad.nextLine().trim();
                    if (nombreD.isEmpty()) {
                        System.out.println("Nombre del archivo no puede estar vacío.");
                        break;
                    }

                    System.out.print("Autor: ");
                    String autorD = ad.nextLine().trim();
                    if (autorD.isEmpty()) {
                        System.out.println("Autor no puede estar vacío.");
                        break;
                    }

                    System.out.print("Formato: ");
                    String formatoD = ad.nextLine().trim();
                    if (formatoD.isEmpty()) {
                        System.out.println("Formato no puede estar vacío.");
                        break;
                    }

                    System.out.print("Tamaño: ");
                    String tamañoD = ad.nextLine().trim();
                    if (tamañoD.isEmpty()) {
                        System.out.println("Tamaño no puede estar vacío.");
                        break;
                    }

                    LocalDate fechaPublicacionD = null;
                    boolean fechaValidaD = false;

                    DateTimeFormatter formatoFD = DateTimeFormatter.ofPattern("dd-MM-yy");

                    while (!fechaValidaD) {
                        System.out.print("Fecha de publicación del libro (dd-MM-yy): ");
                        String input = ad.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("La fecha de publicación no puede estar vacía.");
                            continue;
                        }

                        try {
                            fechaPublicacionD = LocalDate.parse(input, formatoFD);
                            fechaValidaD = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Usa dd-MM-yy.");
                        }
                    }

                    System.out.print("Ruta o URL: ");
                    String ruta = ad.nextLine().trim();
                    if (ruta.isEmpty()) {
                        System.out.println("Ruta no puede estar vacía.");
                        break;
                    }

                    ArchivoDigital archivoDigital = new ArchivoDigital(archivo_idD, nombreD, autorD, formatoD, tamañoD, fechaPublicacionD, ruta);
                    id = 3;
                    if (UsuarioDA.agregarArchivoDigital(archivoDigital, id)) {
                        System.out.println("\nArchivo digital agregado correctamente:");
                        archivoDigital.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar archivo digital.");
                    }
                    break;
                case 4:

                default:
                    System.out.println("Opción inválida. Ingrese solo 1, 2, 3 o 4.");

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
            case 1: { // EDITAR LIBRO
                System.out.print("Ingrese el código del libro a editar: ");
                int codEEL = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarLibro(codEEL);

                if (materialEncontrado == null) {
                    System.out.println("Libro no encontrado con el código: " + codEEL);
                    return;
                }

                System.out.println("Libro encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                if (materialEncontrado instanceof Libro) {
                    Libro libro = (Libro) materialEncontrado;

                    System.out.print("Nuevo nombre: ");
                    String nombreL = ad.nextLine().trim();
                    if (!nombreL.isEmpty()) {
                        libro.setNombre(nombreL);
                    }

                    System.out.print("Nuevo autor: ");
                    String autorL = ad.nextLine().trim();
                    if (!autorL.isEmpty()) {
                        libro.setAutor(autorL);
                    }

                    System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                    String fechaStr = ad.nextLine().trim();
                    if (!fechaStr.isEmpty()) {
                        try {
                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
                            LocalDate fechaPublicacion = LocalDate.parse(fechaStr, formato);
                            libro.setFechaPublicacion(fechaPublicacion);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. No se actualizó.");
                        }
                    }

                    System.out.print("Nuevo género: ");
                    String genero = ad.nextLine().trim();
                    if (!genero.isEmpty()) {
                        libro.setGenero(genero);
                    }

                    System.out.print("Nuevo idioma: ");
                    String idioma = ad.nextLine().trim();
                    if (!idioma.isEmpty()) {
                        libro.setIdioma(idioma);
                    }

                    System.out.print("Nuevo ISBN: ");
                    String isbn = ad.nextLine().trim();
                    if (!isbn.isEmpty()) {
                        libro.setISBN(isbn);
                    }

                    System.out.print("Nueva editorial: ");
                    String editorial = ad.nextLine().trim();
                    if (!editorial.isEmpty()) {
                        libro.setEditorial(editorial);
                    }

                    System.out.print("Nueva edición: ");
                    String edicion = ad.nextLine().trim();
                    if (!edicion.isEmpty()) {
                        libro.setEdicion(edicion);
                    }

                    if (UsuarioDA.actualizarLibro(libro)) {
                        System.out.println("Libro editado correctamente:");
                        libro.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el libro.");
                    }
                }
                break;
            }

            case 2: { // EDITAR ARCHIVO MULTIMEDIA
                System.out.print("Ingrese el código del archivo multimedia a editar: ");
                int codEMM = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarArchivoMultimedia(codEMM);

                if (materialEncontrado == null) {
                    System.out.println("Archivo multimedia no encontrado con el código: " + codEMM);
                    return;
                }

                System.out.println("Archivo multimedia encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                if (materialEncontrado instanceof ArchivoMultimedia) {
                    ArchivoMultimedia archivo = (ArchivoMultimedia) materialEncontrado;

                    System.out.print("Nuevo nombre: ");
                    String nombreM = ad.nextLine().trim();
                    if (!nombreM.isEmpty()) {
                        archivo.setNombre(nombreM);
                    }

                    System.out.print("Nuevo autor: ");
                    String autor = ad.nextLine().trim();
                    if (!autor.isEmpty()) {
                        archivo.setAutor(autor);
                    }

                    System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                    String fechaStr = ad.nextLine().trim();
                    if (!fechaStr.isEmpty()) {
                        try {
                            DateTimeFormatter formatoEM = DateTimeFormatter.ofPattern("dd-MM-yy");
                            LocalDate fecha_publicacion = LocalDate.parse(fechaStr, formatoEM);
                            archivo.setFecha_publicacion(fecha_publicacion);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. No se actualizó.");
                        }
                    }

                    System.out.print("Nuevo tamaño: ");
                    String tamaño = ad.nextLine().trim();
                    if (!tamaño.isEmpty()) {
                        archivo.setTamaño(tamaño);
                    }

                    System.out.print("Nueva duración: ");
                    String duracion = ad.nextLine().trim();
                    if (!duracion.isEmpty()) {
                        archivo.setDuracion(duracion);
                    }

                    System.out.print("Nuevo formato: ");
                    String formato = ad.nextLine().trim();
                    if (!formato.isEmpty()) {
                        archivo.setFormato(formato);
                    }

                    System.out.print("Nueva resolución: ");
                    String resolucion = ad.nextLine().trim();
                    if (!resolucion.isEmpty()) {
                        archivo.setResolucion(resolucion);
                    }

                    System.out.print("Nuevo tipo multimedia: ");
                    String tipoMultimedia = ad.nextLine().trim();
                    if (!tipoMultimedia.isEmpty()) {
                        archivo.setTipoMultimedia(tipoMultimedia);
                    }

                    if (UsuarioDA.actualizarArchivoMultimedia(archivo)) {
                        System.out.println("Archivo multimedia editado correctamente:");
                        archivo.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el archivo multimedia.");
                    }
                }
                break;
            }

            case 3: { // EDITAR ARCHIVO DIGITAL
                System.out.print("Ingrese el código del archivo digital a editar: ");
                int codED = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarArchivoDigital(codED);

                if (materialEncontrado == null) {
                    System.out.println("Archivo digital no encontrado con el código: " + codED);
                    return;
                }

                System.out.println("Archivo digital encontrado:");
                materialEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

                if (materialEncontrado instanceof ArchivoDigital) {
                    ArchivoDigital archivo = (ArchivoDigital) materialEncontrado;

                    System.out.print("Nuevo nombre: ");
                    String nombre = ad.nextLine().trim();
                    if (!nombre.isEmpty()) {
                        archivo.setNombre(nombre);
                    }

                    System.out.print("Nuevo autor: ");
                    String autor = ad.nextLine().trim();
                    if (!autor.isEmpty()) {
                        archivo.setAutor(autor);
                    }

                    System.out.print("Nuevo formato: ");
                    String formato = ad.nextLine().trim();
                    if (!formato.isEmpty()) {
                        archivo.setFormato(formato);
                    }

                    System.out.print("Nuevo tamaño: ");
                    String tamaño = ad.nextLine().trim();
                    if (!tamaño.isEmpty()) {
                        archivo.setTamaño(tamaño);
                    }

                    System.out.print("Nueva fecha de publicación (dd-MM-yy): ");
                    String fechaStr = ad.nextLine().trim();
                    if (!fechaStr.isEmpty()) {
                        try {
                            DateTimeFormatter formatoED;
                            formatoED = DateTimeFormatter.ofPattern("dd-MM-yy");
                            LocalDate fechaPublicacion = LocalDate.parse(fechaStr, formatoED);
                            archivo.setFechaPublicacion(fechaPublicacion);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. No se actualizó.");
                        }
                    }

                    System.out.print("Nueva ruta o URL: ");
                    String ruta = ad.nextLine().trim();
                    if (!ruta.isEmpty()) {
                        archivo.setRuta(ruta);
                    }

                    if (UsuarioDA.actualizarArchivoDigital(archivo)) {
                        System.out.println("Archivo digital editado correctamente:");
                        archivo.mostrarInfo();
                    } else {
                        System.out.println("Error al actualizar el archivo digital.");
                    }
                }
                break;
            }

            case 0:
                System.out.println("Edición cancelada.");
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
    
    public void eliminarMaterial() {
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
            case 1: { // ELIMINAR LIBRO
                System.out.print("Ingrese el código del libro a eliminar: ");
                int codEL = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarLibro(codEL);

                if (materialEncontrado == null) {
                    System.out.println("Libro no encontrado con el código: " + codEL);
                    return;
                }

                System.out.println("Libro encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este libro? (si/no): ");
                String confirmacion = ad.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("si")) {
                    if (UsuarioDA.eliminarLibro(codEL)) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el libro.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            case 2: { // ELIMINAR ARCHIVO MULTIMEDIA
                System.out.print("Ingrese el código del archivo multimedia a eliminar: ");
                int codEMM = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarArchivoMultimedia(codEMM);

                if (materialEncontrado == null) {
                    System.out.println("Archivo multimedia no encontrado con el código: " + codEMM);
                    return;
                }

                System.out.println("Archivo multimedia encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este archivo multimedia? (si/no): ");
                String confirmacion = ad.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("si")) {
                    if (UsuarioDA.eliminarArchivoMultimedia(codEMM)) {
                        System.out.println("Archivo multimedia eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el archivo multimedia.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
                break;
            }

            case 3: { // ELIMINAR ARCHIVO DIGITAL
                System.out.print("Ingrese el código del archivo digital a eliminar: ");
                int codED = Integer.parseInt(ad.nextLine());
                Material materialEncontrado = UsuarioDA.buscarArchivoDigital(codED);

                if (materialEncontrado == null) {
                    System.out.println("Archivo digital no encontrado con el código: " + codED);
                    return;
                }

                System.out.println("Archivo digital encontrado:");
                materialEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar este archivo digital? (si/no): ");
                String confirmacion = ad.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("si")) {
                    if (UsuarioDA.eliminarArchivoDigital(codED)) {
                        System.out.println("Archivo digital eliminado correctamente.");
                    } else {
                        System.out.println("Error al eliminar el archivo digital.");
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

    public void agregarRecursoTecnologico() {
        int opcionRT = 0;
        String RecTec
                = "=== AGREGAR RECURSO TECNOLOGICO ===\n"
                + "Seleccione tipo material\n"
                + "1. Tablet\n"
                + "2. Computadora\n"
                + "0. Salir del menú\n"
                + "=========================\n"
                + "Elija una opcion: ";

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
                    int IDcodigoT = ad.nextInt();
                    if (IDcodigoT <= 0) {
                        System.out.println("Código inválido. Debe ser mayor a 0.");
                        break;
                    }

                    System.out.print("Ingrese el modelo de la tablet");
                    String modeloT = ad.nextLine().trim();
                    if (modeloT.isEmpty()) {
                        System.out.println("El modelo no puede estar vacío.");
                        break;
                    }

                    System.out.print("Sistema Operativo de la tablet: ");
                    String sisOpT = ad.nextLine().trim();
                    if (sisOpT.isEmpty()) {
                        System.out.println("El Sistema Operativo de la tablet no puede estar vacío.");
                        break;
                    }

                    Tablet tablet = new Tablet(IDcodigoT, modeloT, sisOpT);
                    int tipoRecurso = 1;
                    if (UsuarioDA.agregarTablet(tablet, tipoRecurso)) {
                        System.out.println("\nTablet agregado correctamente:");
                        tablet.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar la tablet.");
                    }
                    break;

                case 2: // COMPUTADORA
                    System.out.println("=== Por favor ingrese los siguientes datos ===");
                    System.out.println("Codigo del la Computadora: ");
                    int IDcodigoC = ad.nextInt();
                    if (IDcodigoC <= 0) {
                        System.out.println("Codigo invalido. Debe ser mayor a 0.");
                        break;
                    }

                    System.out.print("Ram de la computadora: ");
                    String ramC = ad.nextLine().trim();
                    if (ramC.isEmpty()) {
                        System.out.println("Ram no puede estar vacío.");
                        break;
                    }

                    System.out.print("Sistema Operativo(Ipad/Android): ");
                    String sistOpC = ad.nextLine().trim();
                    if (sistOpC.isEmpty()) {
                        System.out.println("Sistema Operativo no puede estar vacío.");
                        break;
                    }

                    System.out.print("Procesador de la computadora: ");
                    String procC = ad.nextLine().trim();
                    if (procC.isEmpty()) {
                        System.out.println("Procesador no puede estar vacío.");
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

                    Computadora computadora = new Computadora(IDcodigoC, ramC, sistOpC, procC, estadoC);
                    tipoRecurso = 2;
                    if (UsuarioDA.agregarComputadora(computadora, tipoRecurso)) {
                        System.out.println("\nComputadora agregado correctamente:");
                        computadora.mostrarInfo();
                    } else {
                        System.out.println("Error al agregar la computadora.");
                    }
                    break;
                case 0:
                //salir

                default:
                    System.out.println("Opción inválida. Ingrese solo 1, 2 o 3.");
            }

        } while (opcionRT < 0 || opcionRT > 2);
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
                int codTab = ad.nextInt();
                Tablet recursoEncontrado = UsuarioDA.buscarTablet(codTab);

                if (recursoEncontrado == null) {
                    System.out.println("Tablet no encontrada con el código: " + codTab);
                    return;
                }

                System.out.println("Tablet encontrada:");
                recursoEncontrado.mostrarInfo();
                System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):"); //necesito crear un metodo para modificar, pero si esta... no modificar

                if (recursoEncontrado instanceof RecursoTecnologico) {
                    Tablet tablet = (Tablet) recursoEncontrado;

                    System.out.print("Nuevo modelo: ");
                    String modeloT = ad.nextLine();
                    if (!modeloT.isEmpty()) {
                        recursoEncontrado.setModelo(modeloT);
                    }

                    System.out.print("Nuevo sistema Operativo: ");
                    String sisOpT = ad.nextLine();
                    if (!sisOpT.isEmpty()) {
                        recursoEncontrado.setSistemaOperativo(sisOpT);
                    }

                    if (UsuarioDA.actualizarTablet(tablet)) {
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
                int codCom = ad.nextInt();
                Computadora recursoEncontrado = UsuarioDA.buscarComputadora(codCom);

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
                    if (!ramC.isEmpty()) {
                        computadora.setRam(ramC);
                    }
                    System.out.print("Nuevo Sistema Operativo: ");
                    String sisOpC = ad.nextLine();
                    if (!sisOpC.isEmpty()) {
                        computadora.setSistemaOperativo(sisOpC);
                    }

                    System.out.print("Nuevo procesador: ");
                    String procC = ad.nextLine();
                    if (!procC.isEmpty()) {
                        computadora.setProcesador(procC);
                    }

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

                    if (UsuarioDA.actualizarComputadora(computadora)) {
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
        }
        while (opcionERT < 1 || opcionERT > 3);
    }

    public void eliminarRecursoTecnologico() {
        int opcionEER = 0;
        String menuER = "\n=== ELIMINAR RECURSO TECNOLÓGICO ===\n"
                + "Seleccione el tipo de recurso a eliminar:\n"
                + "1. Tablet\n"
                + "2. Computadora\n"
                + "0. Cancelar\n"
                + "Elija una opción: ";

        System.out.print(menuER);
        try {
            opcionEER = Integer.parseInt(ad.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        switch (opcionEER) {
            case 1 -> {
                System.out.print("Ingrese el código de la Tablet a eliminar: ");
                int codTab = ad.nextInt();
                Tablet recursoEncontrado = UsuarioDA.buscarTablet(codTab);

                if (recursoEncontrado == null) {
                    System.out.println("Tablet no encontrada con el código: " + codTab);
                    return;
                }

                System.out.println("Tablet encontrada:");
                recursoEncontrado.mostrarInfo();

                System.out.print("¿Está seguro que desea eliminar esta tablet? (si/no): ");
                String confirmacion = ad.nextLine();

                if (confirmacion.equalsIgnoreCase("si")) {
                    if (UsuarioDA.eliminarTablet(codTab)) {
                        System.out.println("Tablet eliminada correctamente.");
                    } else {
                        System.out.println("Error al eliminar la tablet.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            }

            case 2 -> {
                System.out.print("Ingrese el código de la Computadora a eliminar: ");
                int codCom = ad.nextInt();
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
                    if (UsuarioDA.eliminarComputadora(codCom)) {
                        System.out.println("Computadora eliminada correctamente.");
                    } else {
                        System.out.println("Error al eliminar la computadora.");
                    }
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            }

            case 0 ->
                System.out.println("Operación cancelada.");

            default ->
                System.out.println("Opción inválida.");
        }
    }

    public void agregarSala() {
        System.out.println("\n=== AGREGAR SALA ===");

        System.out.println("=== Por favor ingrese los siguientes datos ===");
        System.out.println("Codigo del libro: ");
        int codigoS = ad.nextInt();
        if (codigoS <= 0) {
            System.out.println("Codigo no puede estar vacío.");
        }
        System.out.print("Nombre de la sala: ");
        String nombresala = ad.nextLine().trim();
        if (nombresala.isEmpty()) {
            System.out.println("Estado no puede estar vacío.");
            return;
        }

        System.out.print("Capacidad máxima: ");
        int capacidad;
        try {
            capacidad = Integer.parseInt(ad.nextLine());
            if (capacidad <= 0) {
                System.out.println("Capacidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Capacidad inválida.");
            return;
        }

        Sala sala = new Sala(codigoS, nombresala, capacidad);

        if (UsuarioDA.agregarSala(sala)) {
            System.out.println("Ambiente agregado correctamente:");
            sala.mostrarInfo();
        } else {
            System.out.println("Error al agregar ambiente.");
        }
    }

    public void editarSala() {
        System.out.println("\n=== EDITAR AMBIENTE ===");
        System.out.print("Ingrese el código del ambiente a editar: ");
        int codS = ad.nextInt();

        Sala salaEncontrada = UsuarioDA.buscarSala(codS);
        if (salaEncontrada == null) {
            System.out.println("Ambiente no encontrado con el código: " + codS);
            return;
        }

        System.out.println("Sala encontrada:");
        salaEncontrada.mostrarInfo();
        System.out.println("Ingrese los nuevos datos (deje en blanco si no desea cambiar):");

        System.out.print("Nuevo Nombre sala: ");
        String nombresala = ad.nextLine();
        if (!nombresala.isEmpty()) {
            salaEncontrada.setNombresala(nombresala);
        }

        System.out.print("Nueva capacidad máxima: ");

        if (ad.hasNextInt()) {
            int cap = ad.nextInt();
            ad.nextLine();

            if (cap > 0) {
                salaEncontrada.setCapacidad(cap);
                System.out.println("Capacidad actualizada.");
            } else {
                System.out.println("Capacidad inválida. Debe ser mayor a 0.");
            }
        } else {
            System.out.println("Entrada inválida. Debe ingresar un número.");
            ad.nextLine();
        }

        if (UsuarioDA.actualizarSala(salaEncontrada)) {
            System.out.println("Ambiente editado correctamente:");
            salaEncontrada.mostrarInfo();
        } else {
            System.out.println("Error al actualizar ambiente.");
        }
    }

    public void eliminarSala() {
        System.out.println("\n=== ELIMINAR AMBIENTE ===");
        System.out.print("Ingrese el código del ambiente a eliminar: ");
        int codS = ad.nextInt();

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
            if (UsuarioDA.eliminarSala(codS)) {
                System.out.println("Ambiente eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar ambiente.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void exportarInfo() {

    }
}
