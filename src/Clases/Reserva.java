/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author ehuan
 */
public abstract class Reserva {
    protected LocalDate fechaReserva;
    protected double duracion;
    protected LocalTime horaReserva;
    protected Usuario usuario;
    protected String estado; //metodo nueovo
    protected LocalDateTime fechaHoraFin;// nuevo
        

    public Reserva(LocalDate fechaReserva, double duracion, LocalTime horaReserva, Usuario usuario) {
        this.fechaReserva = fechaReserva;
        this.duracion = duracion;
        this.horaReserva = horaReserva;
        this.usuario = usuario;
        this.estado = "Pendiente"; //  Estado por defecto
    
    }
    
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // nuevo met para estad
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public LocalDateTime getFechaHoraFin(){
        return fechaHoraFin;
    }
    // NUEVO: cálculo de inicio y fin desde LocalDate.now() + duración
    public void calcularFechaHoraFinDesdeAhora() {
        LocalDateTime inicio = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        this.fechaReserva = inicio.toLocalDate();
        this.horaReserva = inicio.toLocalTime();
        this.fechaHoraFin = inicio.plusMinutes((long) (duracion * 60));
    }

    // Método abstracto común a todos los hijos
    /*
    public boolean verificarDisponivilidad(){
        System.out.println("Se esta verificando");
        
        if(true){
            return true;
        }
        else{
            return false;
        }
    }*/
    public abstract void mostrarInfo();
    
}
