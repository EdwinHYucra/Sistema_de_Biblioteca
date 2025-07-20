/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;
import java.time.LocalTime;
//import java.util.Date;

/**
 *
 * @author ehuan
 */
public class ReservaLibro extends Reserva {

    private LocalDate fechadevolucion;
    private int libro;

    public ReservaLibro(int ejemplar, int id, LocalDate fechaReserva, int tipoReserva, String usuario, String estado, LocalDate fechadevolucion) {
        super(id, fechaReserva, tipoReserva, usuario, estado);
        this.libro = ejemplar;
        this.fechadevolucion = fechadevolucion;
    }

    public LocalDate getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(LocalDate fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public int getLibro() {
        return libro;
    }

    public void setLibro(int libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }
}
