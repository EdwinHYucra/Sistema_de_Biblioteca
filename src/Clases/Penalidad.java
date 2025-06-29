/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Dayanna
 */
public class Penalidad {
    private Date fechaAplicacion;
    private String motivo;
    private int diasRetraso;
    private Usuario usuario;

    public Penalidad(Date fechaAplicacion, String motivo, int diasRetraso, Usuario usuario) {
        this.fechaAplicacion = fechaAplicacion;
        this.motivo = motivo;
        this.diasRetraso = diasRetraso;
        this.usuario = usuario;
    }

    public Date getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(Date fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

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
