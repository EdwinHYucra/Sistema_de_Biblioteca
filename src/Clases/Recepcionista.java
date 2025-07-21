package Clases;

import Acceso_Datos.UsuarioDA;
import java.util.Scanner;

/**
 *
 * @author Dayanna
 */
public class Recepcionista extends Usuario {

    Scanner sc = new Scanner(System.in);

    //constructor de prueba
    public Recepcionista(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.tipoDeUser = 4;
    }

    public Recepcionista(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre, String apellido) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 4;
    }

    public Recepcionista(String id_codigo, String nombre, String apellido, String correo, int tipoDeUser) {
        super(id_codigo, nombre, apellido, correo, tipoDeUser);
    }

    public void registrarDevolucion() {
        System.out.println("Devolución registrada correctamente.");
    }

    public void mostrarInfo() {
        System.out.println("Recepcionista: " + nombre + " " + apellido);
    }

    public void validarReservaLibro() {
        System.out.print("Ingrese el ID de reserva del libro: ");
        String codigo = sc.nextLine();
        int idReserva;

        try {
            idReserva = Integer.parseInt(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID de reserva debe ser un número entero válido.");
            return;
        }
        Reserva reserva = usuarioDA.buscarReserva(idReserva);

        if (reserva instanceof ReservaLibro) {
            
            System.out.print("Ingrese fecha de devolución (yyyy-MM-dd): ");
            String fechaDev = sc.nextLine();

            sc.nextLine();
            System.out.print("¿Deseas cambiar el estado de la reserva? (s/n): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                System.out.print("Nuevo estado (1-9): ");
                String nuevoEstado = sc.nextLine();
                
                
                //UsuarioDA.modificarEstadoReserva(idReserva, nuevoEstado);
                System.out.println("Estado actualizado.");
            }
        } else {
            System.out.println(" No se encontró una reserva de libro con ese código.");
        }


        /*if (reslibro != null && reslibro.getLibro() != null) {
            Libro libro = reslibro.getLibro();
            libro.setEstado("No disponible");  // Marcar el libro como prestado
            reslibro.setEstado("Reservado");  // Marcar la reserva como efectuada
            reslibro.setFechaDevolucion(fechaDevolucion);
            System.out.println("Reserva de libro recepcionada para: " + reslibro.getUsuario().getNombre());
        } else {
            System.out.println("️ Libro o reserva inválida.");
        }*/
    }

    /*public void validarReservaAmbiente(ReservaDeAmbiente resAmbiente) {
        if (resAmbiente != null && resAmbiente.getSala() != null) {
            resAmbiente.calcularFechaHoraFinDesdeAhora(); // Nuevo método en clase reserva
            resAmbiente.setEstado("Reservado");

            System.out.println("Reserva de ambiente recepcionada:");
            System.out.println("Sala: " + resAmbiente.getSala().getCodigo());
            System.out.println("Inicio: " + resAmbiente.getFechaReserva() + " " + resAmbiente.getHoraReserva());
            System.out.println("Fin: " + resAmbiente.getFechaHoraFin());
        } else {
            System.out.println("️ Sala o reserva inválida.");
        }
    }

    public void validarReservaTecnologica(ReservaRecursoTecnologico resTECno) {
        if (resTECno != null && resTECno.verificarDisponibilidad()) {
            resTECno.calcularFechaHoraFinDesdeAhora(); // Método definido en clase reserva
            resTECno.setEstado("Reservado");

            System.out.println("Reserva de recurso tecnológico recepcionadaa: ");
            System.out.println("Usuario: " + resTECno.getUsuario().getNombre());
            System.out.println("Inicio: " + resTECno.getFechaReserva() + " " + resTECno.getHoraReserva());
            System.out.println("Fin: " + resTECno.getFechaHoraFin());
        } else {
            System.out.println("️ Recurso no disponible o reserva nula.");
        }
    }*/
}
