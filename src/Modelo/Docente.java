/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Interfaces.IServicioPrestamos;

/**
 *
 * @author Dayanna
 */
public class Docente extends Usuario implements IServicioPrestamos {
    private String especialidad;

    public Docente(String id_codigo, String contraseña, String nombre, String apellido, String especialidad) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Docente";
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReserva() {
        return validarDisponibilidadReserva();
    }

    public void cancelarReserva() {
        System.out.println("Reserva cancelada por el docente.");
    }

    public void mostrarInfo() {
        System.out.println("Docente: " + nombre + " " + apellido + ", Especialidad: " + especialidad);
    }
}
    
