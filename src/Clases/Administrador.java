package Clases;

import Interfaces.IServiciosRecursos;

/**
 *
 * @author Dayanna
 */
public class Administrador extends Usuario implements IServiciosRecursos {

    public Administrador(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.tipoDeUser = "Administrador";
    }

    //Constructor de Prueba
    public Administrador(String id_codigo, String contraseña, String nombre) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;

        this.tipoDeUser = "Administrador";
    }

    public Administrador(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Administrador";
    }

    public void agregarMaterial() {
        System.out.println("Material agregado.");
    }

    public void agregarRecursoTecnologico() {
        System.out.println("Recurso tecnológico agregado.");
    }

    public void gestionarRecursos() {
        System.out.println("Recursos gestionados.");
    }

    public void verificarReservas() {
        System.out.println("Reservas verificadas.");
    }

    public void generadorReporte() {
        System.out.println("Reporte generado.");
    }

    public void exportarInfo() {
        System.out.println("Información exportada.");
    }

    public void mostrarInfo() {
        System.out.println("Administrador: " + nombre + " " + apellido);
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
