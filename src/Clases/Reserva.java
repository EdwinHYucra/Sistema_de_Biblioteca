/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDateTime;

/**
 *
 * @author ehuan
 */

public abstract class Reserva {
    protected int id;
    protected LocalDateTime fechaReserva;
    protected int tipoReserva_id;
    protected int estado_id;
    protected String usuario_responsable_id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDateTime getfechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    public int getTipoReserva_id() {
        return tipoReserva_id;
    }

    public void setTipoReserva_id( int tipoReserva_id) {
        this.tipoReserva_id = tipoReserva_id;
    }
    
    public int getEstado_id() {
        return id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }
    
    public String getUsuario_responsable_id(){
        return usuario_responsable_id;
    }
    
    public void setUsuario_Responsable_id(String usuario_responsable_id) {
        this.usuario_responsable_id = usuario_responsable_id;
    }
    

    public abstract void mostrarInfo();
    
}
