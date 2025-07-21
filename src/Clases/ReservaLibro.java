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
    private Libro libro;
    private Ejemplar ejemplar;

    public ReservaLibro(LocalDate fechadevolucion, Libro libro, int codigo, LocalDate fechaReserva, String estado, Usuario usuario) {
        super(codigo, fechaReserva, estado, usuario);
        this.fechadevolucion = fechadevolucion;
        this.libro = libro;
    }
    public ReservaLibro(int codigo, LocalDate fechaReserva, String estado, Usuario usuario) {
        super(codigo, fechaReserva, estado, usuario);
    }


    public LocalDate getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(LocalDate fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }
}
