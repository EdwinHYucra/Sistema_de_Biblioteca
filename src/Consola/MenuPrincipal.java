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

        if (usuario instanceof Administrador admin) {
            mostrarMenuAdministrador(admin);
        } else if (usuario instanceof Alumno alumno) {
            mostrarMenuAlumno(alumno);
        } else if (usuario instanceof Docente docente) {
            mostrarMenuDocente(docente);
        } else if (usuario instanceof Recepcionista recep) {
            mostrarMenuRecepcionista(recep);
        } else {
            System.out.println("Tipo de usuario no reconocido.");
        }
    }

    private void mostrarMenuAdministrador(Clases.Administrador admin) {
        //Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Agregar Material");
            System.out.println("2. Editar Material");
            System.out.println("3. Eliminar Material");
            System.out.println("4. Agregar Recursos Tecnologicos");
            System.out.println("5. Editar Recursos Tecnologicos");
            System.out.println("6. Eliminar Recursos Tecnologicos");
            System.out.println("7. Agregar Ambientes");
            System.out.println("8. Editar Ambientes");
            System.out.println("9. Eliminar Ambientes");
            System.out.println("10. Exportar datos");
            System.out.println("11. Cerrar sesión");

            System.out.print("Opción: ");

            if (sc.hasNextInt()) {
                int op = sc.nextInt();
                sc.nextLine();

                switch (op) {
                    case 1:
                        //admin.agregarMaterial();
                        break;
                    case 2:
                        //admin.editarMaterial();
                        break;
                    case 3:
                        //admin.eliminarMaterial();
                        break;
                    case 4:
                        //admin.agregarRecursoTecnologico();
                        break;
                    case 5:
                        //admin.editarRecursoTecnologico();
                        break;
                    case 6:
                        //admin.eliminarRecursoTecnologico();
                        break;
                    case 7:
                        //admin.agregarSala();
                        break;
                    case 8:
                        // admin.editarSala();
                        break;
                    case 9:
                        //admin.eliminarSala();
                        break;
                    case 10:
                        //admin.exportarInfo();
                        break;
                    case 11:
                        System.out.println("Sesión cerrada.");
                        activo = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;

                }
            } else {
                System.out.println("Entrada no valida. Por favor ingrese un numero valido");
                sc.nextLine();
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
            System.out.println("4. Mis Reservas");
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

    public static void mostrarMenuRecepcionista(Recepcionista recep) {
        Scanner sc = new Scanner(System.in);
        boolean activo = true;

        while (activo) {
            System.out.println("\n=== MENÚ RECEPCIONISTA ===");
            System.out.println("1. Validar reserva de libro");
            System.out.println("2. Validar reserva de recurso tecnológico");
            System.out.println("3. Validar reserva de ambiente");
            System.out.println("4. RegistrarDevolucion");
            System.out.println("5. Generar reporte");
            System.out.println("9. Cerrar sesión");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (op) {
                case 1: {

                    break;
                }

                case 2: {
                    System.out.print("Ingrese el ID de reserva tecnológica: "); // Cambiado "código" a "ID" para claridad
                    String codigoStr = sc.nextLine();
                    int idReserva;

                    try {
                        idReserva = Integer.parseInt(codigoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El ID de reserva debe ser un número entero válido.");
                        break;
                    }

                    /*Reserva reserva = UsuarioDA.buscarReserva(idReserva);
                    if (reserva instanceof ReservaRecursoTecnologico) {
                        ReservaRecursoTecnologico resTec = (ReservaRecursoTecnologico) reserva;
                        recep.validarReservaTecnologica(resTec);

                        System.out.println("Reserva tecnológica validada.");
                        System.out.println("Inicio: " + resTec.getFechaReserva() + " " + resTec.getHoraReserva());
                        System.out.println("Fin: " + resTec.getFechaHoraFin());

                        // sc.nextLine(); // Esta línea también es un error aquí.
                        System.out.print("¿Deseas cambiar el estado de la reserva? (s/n): ");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("s")) {
                            System.out.print("Nuevo estado: ");
                            String nuevoEstado = sc.nextLine();
                            UsuarioDA.modificarEstadoReserva(idReserva, nuevoEstado);
                            System.out.println("Estado actualizado.");
                        }
                    } else {
                        System.out.println("No se encontró una reserva tecnológica con ese ID o el tipo no coincide.");
                    }*/
                    break;
                }

                case 3: {
                    System.out.print("Ingrese el ID de reserva de ambiente: "); // Cambiado "código" a "ID" para claridad
                    String codigoStr = sc.nextLine();
                    int idReserva;

                    try {
                        idReserva = Integer.parseInt(codigoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El ID de reserva debe ser un número entero válido.");
                        break;
                    }

                    /*Reserva reserva = UsuarioDA.buscarReserva(idReserva);
                    if (reserva instanceof ReservaDeAmbiente) {
                        ReservaDeAmbiente resAmb = (ReservaDeAmbiente) reserva;
                        recep.validarReservaAmbiente(resAmb);

                        System.out.println("Reserva de ambiente validada.");
                        System.out.println("Inicio: " + resAmb.getFechaReserva() + " " + resAmb.getHoraReserva());
                        System.out.println("Fin: " + resAmb.getFechaHoraFin());

                        // sc.nextLine(); // Esta línea también es un error aquí.
                        System.out.print("¿Deseas cambiar el estado de la reserva? (s/n): ");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("s")) {
                            System.out.print("Nuevo estado: ");
                            String nuevoEstado = sc.nextLine();
                            UsuarioDA.modificarEstadoReserva(idReserva, nuevoEstado);
                            System.out.println("Estado actualizado.");
                        }
                    } else {
                        System.out.println("No se encontró una reserva de ambiente con ese ID o el tipo no coincide.");
                    }*/
                    break;
                }

                case 4: {
                    /*System.out.print("Ingrese fecha de inicio (yyyy-MM-dd): ");
                    String fechaInicioStr = sc.nextLine();
                    System.out.print("Ingrese fecha de fin (yyyy-MM-dd): ");
                    String fechaFinStr = sc.nextLine();

                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_LOCAL_DATE);

                    recep.generarReporte(fechaInicio, fechaFin);*/
                    break;
                }

                case 9:
                    System.out.println(" Sesión cerrada.");
                    activo = false;
                    break;

                default:
                    System.out.println(" Opción inválida.");
            }
        }
    }
}
