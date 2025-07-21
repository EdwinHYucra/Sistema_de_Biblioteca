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
    protected int codigo;
    protected LocalDate fechaReserva; 
    protected String estado;
    protected Usuario usuario;


    public Reserva(int codigo, LocalDate fechaReserva, Usuario usuario) {
        this.codigo = codigo;
        this.fechaReserva = fechaReserva;
        this.usuario = usuario;
    }
    public Reserva(int codigo, LocalDate fechaReserva, String estado, Usuario usuario) {
        this.codigo = codigo;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
