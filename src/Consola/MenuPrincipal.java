package Consola;

import Clases.*;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;
    private Usuario usuario;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        //this.usuario = usuario;
    }
    public MenuPrincipal(Scanner scanner, Usuario usuario) {
        this.scanner = scanner;
        this.usuario = usuario;
    }

    public void iniciar() {
        int opcion;

        do {
            System.out.println("==============================");
            System.out.println("  SISTEMA BIBLIOTECA - UTP");
            System.out.println("==============================");
            System.out.println("Seleccione tipo de usuario:");
            System.out.println("1. Alumno");
            System.out.println("2. Docente");
            System.out.println("3. Recepcionista");
            System.out.println("4. Administrador");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            Usuario usuario = null;

            switch (opcion) {
                case 1:
                    usuario = new Alumno("alumno01", "1234", "Carlos");
                    break;
                case 2:
                    usuario = new Docente("docente01", "abcd", "Dra. Gómez");
                    break;
                case 3:
                    usuario = new Recepcionista("recep01", "xyz", "Luis");
                    break;
                case 4:
                    usuario = new Administrador("admin", "admin123", "Lucía");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

            if (usuario != null) {
                mostrarMenuPorUsuario(usuario);
            }

        } while (opcion != 5);
    }

    private void mostrarMenuPorUsuario(Usuario usuario) {
        if (usuario instanceof Alumno || usuario instanceof Docente) {
            menuAlumnoDocente(usuario);
        } else if (usuario instanceof Recepcionista) {
            menuRecepcionista(usuario);
        } else if (usuario instanceof Administrador) {
            menuAdministrador(usuario);
        }
    }

    private void menuAlumnoDocente(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\n--- Menú Alumno/Docente ---");
            System.out.println("1. Buscar libro");
            System.out.println("2. Ver mis préstamos");
            System.out.println("3. Solicitar préstamo");
            System.out.println("4. Devolver libro");
            System.out.println("5. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Buscando libro...");
                    break;
                case 2:
                    System.out.println("Viendo préstamos...");
                    break;
                case 3:
                    System.out.println("Solicitar préstamo...");
                    break;
                case 4:
                    System.out.println("Devolviendo libro...");
                    break;
                case 5:
                    //usuario.cerrarSesion();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void menuRecepcionista(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\n--- Menú Recepcionista ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Consultar disponibilidad");
            System.out.println("4. Buscar usuario");
            System.out.println("5. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Registrar préstamo...");
                    break;
                case 2:
                    System.out.println("Registrar devolución...");
                    break;
                case 3:
                    System.out.println("Consultar disponibilidad...");
                    break;
                case 4:
                    System.out.println("Buscar usuario...");
                    break;
                case 5:
                    //usuario.cerrarSesion();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private void menuAdministrador(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Editar libro");
            System.out.println("4. Eliminar usuario/libro");
            System.out.println("5. Ver reportes");
            System.out.println("6. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Registrar libro...");
                    break;
                case 2:
                    System.out.println("Registrar usuario...");
                    break;
                case 3:
                    System.out.println("Editar libro...");
                    break;
                case 4:
                    System.out.println("Eliminar datos...");
                    break;
                case 5:
                    System.out.println("Ver reportes...");
                    break;
                case 6:
                    //usuario.cerrarSesion();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }
}
