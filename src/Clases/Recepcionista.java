package Clases;

import Acceso_Datos.UsuarioDA;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dayanna
 */
public class Recepcionista extends Usuario {

    public Recepcionista(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.tipoDeUser = "Recepcionista";
    }

    //constructor de prueba
    public Recepcionista(String id_codigo, String contraseña, String nombre) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.tipoDeUser = "Recepcionista";
    }

    public Recepcionista(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Recepcionista";
    }

       public void registrarDevolucion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el codigo: ");
        String inputCodigo = scanner.nextLine();

        /*LocalDate fechaBuscada;
        try {
            fechaBuscada = LocalDate.parse(inputFecha);
        } catch (Exception e) {
            System.out.println("Fecha inválida");
            return;
        }
       
        */
        Reserva reservaEncontrada = UsuarioDA.buscarReserva(inputCodigo);
        
        if (reservaEncontrada == null) {
            System.out.println("No se encontró la reserva");
            return;
        }

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaDevolucion = reservaEncontrada.getFechaReserva().plusDays((long) reservaEncontrada.getDuracion());

        if (fechaActual.isAfter(fechaDevolucion)) {
            int diasRetraso = (int) (fechaActual.toEpochDay() - fechaDevolucion.toEpochDay());
            Penalidad penalidad = new Penalidad(LocalDate.now(),LocalDate.now().plusDays(7), "Devolución tardía", diasRetraso, Usuario);
            UsuarioDA.agregarPenalidad(penalidad);
            System.out.println("Devolución registrada. Penalidad por " + diasRetraso + " días de retraso");
        } else {
            System.out.println("Devolución registrada correctamente");
        }

        reservaEncontrada.setEstado("Finalizado");
        update UsuarioDA.modificarReserva(reservaEncontrada.getId());

        if (reservaEncontrada instanceof ReservaLibro reservaLibro) {
            reservaLibro.getLibro().setEstado("Disponible");
        } else if (reservaEncontrada instanceof ReservaDeAmbiente reservaDeAmbiente) {
            reservaDeAmbiente.getSala().setEstado("Disponible");
        } else if (reservaEncontrada instanceof ReservaRecursoTecnologico reservaRecursoTecnologico) {
            reservaRecursoTecnologico.getRecursoTecnologico().setEstado("Disponible");
        }
    }
       
    public void mostrarInfo() {
        System.out.println("Recepcionista: " + nombre + " " + apellido);
    }

    public void validarReservaLibro(ReservaLibro reslibro) {
        if (reslibro != null && reslibro.getLibro() != null) {
            Libro libro = reslibro.getLibro();
            libro.setEstado("No disponible");  // Marcar el libro como prestado
            reslibro.setEstado("Reservado");  // Marcar la reserva como efectuada
            System.out.println("Reserva de libro recepcionada para: " + reslibro.getUsuario().getNombre());
        } else {
            System.out.println("️ Libro o reserva inválida.");
        }
    }

    public void validarReservaAmbiente(ReservaDeAmbiente resAmbiente) {
        if (resAmbiente != null && resAmbiente.getSala() != null) {
            resAmbiente.setEstado("Reservado");
            System.out.println("Reserva de ambiente recepcionada: Sala " + resAmbiente.getSala().getCodigo());
        } else {
            System.out.println("️ Sala o reserva inválida.");
        }
    }

    public void validarReservaTecnologica(ReservaRecursoTecnologico resTECno) {
        if (resTECno != null && resTECno.verificarDisponibilidad()) {
            resTECno.realizar(); // Aquí puedes hacer que cambie el estado si fuera necesario
            System.out.println("Reserva de recurso tecnológico recepcionada para: " + resTECno.getUsuario().getNombre());
        } else {
            System.out.println("️ Recurso no disponible o reserva nula.");
        }
    }
}
