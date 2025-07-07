package Clases;

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
// metodos recep
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


    // Méto
    @Override
    public void verificarCredenciales() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void bloquearUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
