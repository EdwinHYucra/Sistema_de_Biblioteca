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
        this.tipoDeUser = 4;
    }

    //constructor de prueba

    public Recepcionista(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 4;
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

    public void validarReservaAmbiente(ReservaDeAmbiente resAmbiente) {
        if (resAmbiente != null && resAmbiente.getSala() != null) {
            resAmbiente.calcularFechaHoraFinDesdeAhora(); // Nuevo método en clase reserva
            resAmbiente.setEstado("Reservado");
            
            System.out.println("Reserva de ambiente recepcionada:" );
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
            
            System.out.println("Reserva de recurso tecnológico recepcionadaa: " );
            System.out.println("Usuario: " + resTECno.getUsuario().getNombre());
            System.out.println("Inicio: " + resTECno.getFechaReserva() + " " + resTECno.getHoraReserva());
            System.out.println("Fin: " + resTECno.getFechaHoraFin());
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


   

