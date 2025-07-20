/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalTime;
import java.time.LocalDate;

/**
 *
 * @author ehuan
 */
public abstract class Reserva {
    protected int id;
    protected LocalDate fechaReserva;
    protected int tipoReserva;
    protected String usuario;
    protected String estado; //metodo nueovo

    public Reserva(LocalDate fechaReserva, String estado, String usuario) {
        this.fechaReserva = fechaReserva;
        this.usuario = usuario;
        this.estado = "Pendiente"; //  Estado por defecto
        }

    public Reserva(int id, LocalDate fechaReserva, int tipoReserva, String usuario, String estado) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.tipoReserva = tipoReserva;
        this.usuario = usuario;
        this.estado = estado;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    // nuevo met para estad
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
