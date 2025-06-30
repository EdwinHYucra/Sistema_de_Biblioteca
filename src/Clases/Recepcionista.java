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
