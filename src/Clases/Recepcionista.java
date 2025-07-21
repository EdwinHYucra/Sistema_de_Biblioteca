package Clases;

import Acceso_Datos.UsuarioDA;

/**
 *
 * @author Dayanna
 */
public class Recepcionista extends Usuario {

    //constructor de prueba
    public Recepcionista(UsuarioDA usuarioda,String id_codigo, String contraseña, String nombre) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.tipoDeUser = 4;
    }

    public Recepcionista(UsuarioDA usuarioda,String id_codigo, String contraseña, String nombre, String apellido) {
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
