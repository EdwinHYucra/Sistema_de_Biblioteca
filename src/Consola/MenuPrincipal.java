package Consola;

import Acceso_Datos.UsuarioDA;
import Clases.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner sc = new Scanner(System.in);
    private Usuario usuario;

    public MenuPrincipal() {
        this.sc = new Scanner(System.in);
        //this.usuario = usuario;
    }

    public MenuPrincipal(Scanner scanner, Usuario usuario) {
        this.sc = scanner;
        this.usuario = usuario;
    }

    public static void iniciarLogin() {
        //Scanner sc = new Scanner(System.in);
        boolean volverAlMenuGeneral = false;

        while (!volverAlMenuGeneral) {
            System.out.print("Código de usuario: ");
            String codigo = sc.nextLine();
            System.out.print("Contraseña: ");
            String password = sc.nextLine();

            Usuario usuario = UsuarioDA.autenticar(codigo, password);

            if (usuario != null) {
                System.out.println("Bienvenido, " + usuario.getNombre());

                mostrarMenu(usuario);  // ya autenticado

                volverAlMenuGeneral = true;  // al cerrar sesión vuelve al menú general
            } else {
                System.out.println("Credenciales incorrectas.");
                System.out.print("¿Deseas intentar de nuevo? (S/N): ");
                String retry = sc.nextLine();
                if (retry.equalsIgnoreCase("N")) {
                    volverAlMenuGeneral = true;  // regresa al menú principal
                }
            }
        }
    }

    public static void mostrarMenu(Usuario usuario) {

        switch (usuario.getTipoDeUser()) {
            case 1:
                mostrarMenuAdministrador((Clases.Administrador) usuario);
                break;
            case 2:
                mostrarMenuAlumno((Clases.Alumno) usuario);
                break;
            case 3:
                mostrarMenuDocente((Clases.Docente) usuario);
                break;
            case 4:
                mostrarMenuRecepcionista((Clases.Recepcionista) usuario);
                break;
            default:
                System.out.println("Tipo de usuario no reconocido.");
        }
    }

    private static void mostrarMenuAdministrador(Clases.Administrador admin) {
        //Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Agregar Material");
            System.out.println("3. Exportar datos");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    admin.agregarMaterial();
                    break;
                case 2:
                    //admin.gestionarRecursos();
                    break;
                case 3:
                    admin.exportarInfo();
                    break;
                case 9:
                    System.out.println("Sesión cerrada.");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuAlumno(Clases.Alumno alumno) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ ALUMNO ===");
            System.out.println("1. Reservar Libro");
            System.out.println("2. Ver Catalogo de Libro");
            System.out.println("3. Reservar Salas");
            System.out.println("4. Reservar RecursoTecnologico");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Ingrese el código del libro que desea reservar: ");
                    String codigoSeleccionado = sc.nextLine();
                    alumno.solicitarReservaLibro(codigoSeleccionado);
                    break;
                case 2:
                    alumno.verCatalogoLibros();
                    break;
                case 3:
                    alumno.Reservarsala();
                    break;
                case 4:
                    alumno.ReservarRecursoTecnologico();
                    break;
                case 9:
                    System.out.println("Sesión cerrada.");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuDocente(Clases.Docente docente) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ DOCENTE ===");
            System.out.println("1. Ver información personal");
            System.out.println("2. Reservar libro");
            System.out.println("3. Ver mis reservas");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    docente.mostrarInfo();
                    break;
                case 2:
                    // Mostrar catálogo
                    docente.verCatalogoLibros();
                    System.out.print("Ingrese el código del libro que desea reservar: ");
                    String codigoSeleccionado = sc.nextLine();
                    docente.solicitarReservaLibro(codigoSeleccionado);
                    break;
                case 3:
                    // docente.verReservas(); 
                    break;
                case 9:
                    System.out.println("Sesión cerrada.");
                    activo = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuRecepcionista(Clases.Recepcionista recep) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ RECEPCIONISTA ===");
            System.out.println("1. Validar reserva de libro");
            System.out.println("2. Validar reserva de recurso tecnológico");
            System.out.println("3. Validar reserva de ambiente");
            System.out.println("4. Cerrar sesión");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1: {
                    // Simulación: Reserva de Libro
                    Libro libro = new Libro("L001", "Cien Años de Soledad", "Disponible", "Gabriel García Márquez", new Date(), true, ",", "accion");
                    Usuario estudiante = new Alumno("EST123", "pass123", "Sofía", "López");
                    ReservaLibro reservaLibro = new ReservaLibro(
                            LocalDate.now().plusDays(7), // Fecha devolución
                            libro,
                            "Pendiente", // Estado inicial
                            LocalDate.now(), // Fecha reserva
                            1.0, // Duración
                            LocalTime.of(9, 30), // Hora
                            estudiante
                    );
                    recep.validarReservaLibro(reservaLibro);
                    break;
                }

                case 2: {
                    // Simulación: Reserva de Recurso Tecnológico
                    RecursoTecnologico tablet = new Tablet("D001", "samsung");
                    Usuario docente = new Docente("DOC001", "docpass", "Luis", "Gonzales");

                    ReservaRecursoTecnologico reservaTEC = new ReservaRecursoTecnologico(
                            "Reserva tecnológica",
                            "TEC123",
                            tablet,
                            LocalDate.now(),
                            2.0,
                            LocalTime.of(10, 0),
                            docente
                    );
                    recep.validarReservaTecnologica(reservaTEC);
                    break;
                }

                case 3: {
                    // Simulación: Reserva de Ambiente
                    Sala sala = new Sala("A201", "diponible", 5);
                    Alumno alumno1 = new Alumno("ADM456", "adminpass", "Daniela", "Ramos");
                    Alumno alumno2 = new Alumno("ADM436", "adminpass", "Angel", "Rsacmos");
                    Alumno alumno3 = new Alumno("ADM426", "adminpass", "roland", "choque");
                    Alumno alumno4 = new Alumno("ADM446", "adminpass", "gonalo", "mamani");
                    ReservaDeAmbiente reservaAmb = new ReservaDeAmbiente(
                            "C011",
                            2,
                            sala, LocalDate.now(), 2, LocalTime.now(), alumno1
                    );
                    reservaAmb.agregarAlumno(alumno1);
                    reservaAmb.agregarAlumno(alumno2);
                    reservaAmb.agregarAlumno(alumno3);
                    reservaAmb.agregarAlumno(alumno4);

                    recep.validarReservaAmbiente(reservaAmb);
                    break;
                }

                case 4:
                    System.out.println("Sesión cerrada.");
                    activo = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
