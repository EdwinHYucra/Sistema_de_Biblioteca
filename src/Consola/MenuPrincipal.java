package Consola;

import Acceso_Datos.UsuarioDA;
import Clases.*;
import java.sql.Connection;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner sc = new Scanner(System.in);
    private static Connection conn; // conexión única
    private static UsuarioDA usuarioDA;

    public MenuPrincipal(Connection conn) {
        this.conn = conn;
        this.usuarioDA = new UsuarioDA(conn);
        this.sc = new Scanner(System.in);
    }

    public void iniciarLogin() {

        boolean volverAlMenuGeneral = false;

        while (!volverAlMenuGeneral) {
            System.out.print("Código de usuario: ");
            String codigo = sc.nextLine();
            System.out.print("Contraseña: ");
            String password = sc.nextLine();

            Usuario usuario = usuarioDA.autenticar(codigo, password, usuarioDA);

            if (usuario != null) {
                System.out.println("Bienvenido, " + usuario.getNombre());
                mostrarMenu(usuario);
                volverAlMenuGeneral = true;
            } else {
                System.out.println("Credenciales incorrectas.");
                System.out.print("¿Deseas intentar de nuevo? (S/N): ");
                String retry = sc.nextLine();
                if (retry.equalsIgnoreCase("N")) {
                    volverAlMenuGeneral = true;
                }
            }
        }
    }

    public void mostrarMenu(Usuario usuario) {

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

    private void mostrarMenuAdministrador(Clases.Administrador admin) {
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
                    //admin.agregarMaterial();
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

    private void mostrarMenuAlumno(Clases.Alumno alumno) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ ALUMNO ===");
            System.out.println("1. Ver Catalogo de Libro");
            System.out.println("2. Reservar Libro");
            System.out.println("3. Reservar Salas");
            System.out.println("4. Reservar RecursoTecnologico");
            System.out.println("9. Cerrar sesión");

            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    alumno.verCatalogoLibros();
                    break;
                case 2:
                    alumno.solicitarReservaLibro();
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

    private void mostrarMenuDocente(Clases.Docente docente) {
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
                    //docente.verCatalogoLibros();
                    System.out.print("Ingrese el código del libro que desea reservar: ");
                    String codigoSeleccionado = sc.nextLine();
                    //docente.solicitarReservaLibro(codigoSeleccionado);
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

    private void mostrarMenuRecepcionista(Clases.Recepcionista recep) {
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

                    break;
                }

                case 2: {

                    break;
                }

                case 3: {

                    //recep.validarReservaAmbiente(reservaAmb);
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
