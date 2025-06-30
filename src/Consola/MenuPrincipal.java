package Consola;

import Clases.Usuario;

import Controladores.LoginController;

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

            Usuario usuario = LoginController.autenticar(codigo, password);

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

    private static void mostrarMenu(Usuario usuario) {

        switch (usuario.getTipoDeUser()) {
            case "Administrador":
                mostrarMenuAdministrador((Clases.Administrador) usuario);
                break;
            case "Alumno":
                mostrarMenuAlumno((Clases.Alumno) usuario);
                break;
            case "Docente":
                mostrarMenuDocente((Clases.Docente) usuario);
                break;
            case "Recepcionista":
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
            System.out.println("1. Modulo Material");
            System.out.println("2. Gestionar recursos");
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
                    admin.gestionarRecursos();
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
            System.out.println("2. Consultar reservas");
            System.out.println("3. Pedir material especial");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    docente.mostrarInfo();
                    break;
                case 2:
                    System.out.println("Funcionalidad de consultar reservas (por implementar).");
                    break;
                case 3:
                    System.out.println("Funcionalidad de pedir material especial (por implementar).");
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
            System.out.println("1. Ver información personal");
            System.out.println("2. Registrar préstamo");
            System.out.println("3. Control de devoluciones");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    recep.mostrarInfo();
                    break;
                case 2:
                    System.out.println("Funcionalidad de registrar préstamo (por implementar).");
                    break;
                case 3:
                    System.out.println("Funcionalidad de control de devoluciones (por implementar).");
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
}
