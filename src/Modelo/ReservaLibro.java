/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalTime;
import java.util.Date;
/**
 *
 * @author ehuan
 */
public class ReservaLibro extends Reserva {
    private Date fechadevolucion;
    private Libro libro;
    private String estado;

    public ReservaLibro(Date fechadevolucion, Libro libro, String estado, Date fechaReserva, double duracion, LocalTime horaReserva, Object usuario) {
        super(fechaReserva, duracion, horaReserva, usuario);
        this.fechadevolucion = fechadevolucion;
        this.libro = libro;
        this.estado = estado;
    }
    
    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
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
