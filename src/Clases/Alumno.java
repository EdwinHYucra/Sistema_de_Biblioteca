package Clases;

import Interfaces.IServicioPrestamos;
/**
 * @author Dayanna
 */
public class Alumno extends Usuario implements IServicioPrestamos {
    private String carrera;

    public Alumno(String id_codigo, String contraseña, String nombre, String apellido, String carrera) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Alumno";
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReserva() {
        return validarDisponibilidadReserva();
    }

    public void cancelarReserva() {
        System.out.println("Reserva cancelada por el alumno.");
    }

    public void mostrarInfo() {
        System.out.println("Alumno: " + nombre + " " + apellido + ", Carrera: " + carrera);
    }
}

