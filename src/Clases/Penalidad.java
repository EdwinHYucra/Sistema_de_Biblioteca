/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Dayanna
 */
public class Penalidad {
    private LocalDate fechaAsignacion;
    private LocalDate fechaFinalizacion;
    private String motivo;
    private int diasRetraso;
    private Usuario usuario;

    public Penalidad(LocalDate fechaAsignacion, LocalDate fechaFinalizacion, String motivo, int diasRetraso, Usuario usuario ) {
        this.fechaAsignacion = fechaAsignacion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.motivo = motivo;
        this.diasRetraso = diasRetraso;
        this.usuario = usuario;
    }

    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAsignacion = fechaAplicacion; }

    public LocalDate getFechaFinalizacion() { return fechaFinalizacion; }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void aplicar() {
        usuario.getPenalidades().add(this);
        System.out.println("Penalidad aplicada a " + usuario.getNombre());
    }
}
