package Clases;
import Acceso_Datos.UsuarioDA;
import java.time.LocalDate;
import java.util.List;
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
        System.out.println("Devolución registrada correctamente.");
    }

    public void mostrarInfo() {
        System.out.println("Recepcionista: " + nombre + " " + apellido);
    }

    public void validarReservaLibro(ReservaLibro reslibro, String fechaDevolucion) {
        if (reslibro != null && reslibro.getLibro() != null) {
            Libro libro = reslibro.getLibro();
            libro.setEstado("No disponible");  // Marcar el libro como prestado
            reslibro.setEstado("Reservado");  // Marcar la reserva como efectuada
            reslibro.setFechaDevolucion(fechaDevolucion);
            System.out.println("Reserva de libro recepcionada para: " + reslibro.getUsuario().getNombre());
        } else {
            System.out.println("️ Libro o reserva inválida.");
        }
    }

    public void validarReservaAmbiente(ReservaDeAmbiente resAmbiente, String fechaDevolucion) {
        if (resAmbiente != null && resAmbiente.getSala() != null) {
            resAmbiente.setEstado("Reservado");
            resAmbiente.setFechaDevolucion(fechaDevolucion);
            System.out.println("Reserva de ambiente recepcionada: Sala " + resAmbiente.getSala().getCodigo());
        } else {
            System.out.println("️ Sala o reserva inválida.");
        }
    }

    public void validarReservaTecnologica(ReservaRecursoTecnologico resTECno, String fechaDevolucion) {
        if (resTECno != null && resTECno.verificarDisponibilidad()) {
            resTECno.realizar(); // Aquí puedes hacer que cambie el estado si fuera necesario
            resTECno.setEstado("Reservado");
            resTECno.setFechaDevolucion(fechaDevolucion);
            System.out.println("Reserva de recurso tecnológico recepcionada para: " + resTECno.getUsuario().getNombre());
        } else {
            System.out.println("️ Recurso no disponible o reserva nula.");
        }
    }
    /*public void generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = (List<Reserva>) UsuarioDA.generarReporte(fechaInicio, fechaFin);
        System.out.println("\n=== REPORTE DE RESERVAS DEL " + fechaInicio + " AL " + fechaFin + " ===");
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas en ese rango de fechas.");
            return;
        }

        for (Reserva r : reservas) {
            System.out.println(" - [" + r.getEstado() + "] " + r.getUsuario().getNombre()
                    + " reservó en fecha: " + r.getFechaReserva());
            r.mostrarInfo(); // este método debe estar implementado en cada tipo de reserva
            System.out.println("----------------------------------");
        }
    }*/

    public void generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
    System.out.println("\n=== REPORTE DE RESERVAS DEL " + fechaInicio + " AL " + fechaFin + " ===");

     for (ReservaLibro r : UsuarioDA.getReservasLibro()) {
        if (!r.getFechaReserva().isBefore(fechaInicio) && !r.getFechaReserva().isAfter(fechaFin)) {
            System.out.println("Libro reservado por " + r.getUsuario().getNombre() + " el " + r.getFechaReserva());
        }
     }

     for (ReservaDeAmbiente r : UsuarioDA.getReservasAmbiente()) {
        if (!r.getFechaReserva().isBefore(fechaInicio) && !r.getFechaReserva().isAfter(fechaFin)) {
            System.out.println("Ambiente reservado por " + r.getUsuario().getNombre() + " el " + r.getFechaReserva());
        }
     }

     for (ReservaRecursoTecnologico r : UsuarioDA.getReservasTecnologicas()) {
        if (!r.getFechaReserva().isBefore(fechaInicio) && !r.getFechaReserva().isAfter(fechaFin)) {
            System.out.println("Recurso tecnológico reservado por " + r.getUsuario().getNombre() + " el " + r.getFechaReserva());
        }
     }
    }
}


   

