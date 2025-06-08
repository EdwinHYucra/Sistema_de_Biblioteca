package Modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Interfaces.IBloqueo;
/**
 *
 * @author user
 */
public class ReservaDeAmbiente implements IBloqueo {
    private String codigo;
    private int capacidadMax;
    private String estado = "Disponible";
    private int tiempoRestante;

    public ReservaDeAmbiente(String codigo, int capacidad, int tiempoEnMinutos) {
        if (tiempoEnMinutos < 0 || tiempoEnMinutos > 120) {
            throw new IllegalArgumentException("Tiempo debe estar entre 60 y 120 minutos.");
        }

        this.codigo = codigo;
        this.capacidadMax = capacidad;
        this.tiempoRestante = tiempoEnMinutos;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
    
    public void verificarEstado() {
        System.out.println("Sala [" + codigo + "] - Estado: " + estado + " - Tiempo restante: " + tiempoRestante + " min");
    }


    @Override
    public void restringirAcceso() {
        estado = "Bloqueada";
        tiempoRestante = 0;
        System.out.println(" Sala [" + codigo + "] bloqueada por tiempo expirado.");
    }
}
     