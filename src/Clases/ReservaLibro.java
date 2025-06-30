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
    private String estado;

    public ReservaLibro(LocalDate fechadevolucion, Libro libro, String estado, LocalDate fechaReserva, double duracion, LocalTime horaReserva, Usuario usuario) {
        super(fechaReserva, duracion, horaReserva, usuario);
        this.fechadevolucion = fechadevolucion;
        this.libro = libro;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @Override
    public void mostrarInfo(){
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }
}
