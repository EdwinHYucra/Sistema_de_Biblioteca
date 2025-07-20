package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Interfaces.IBloqueo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReservaDeAmbiente extends Reserva {

    private int codigo;
    private int reserva_id;
    private int sala_codigo;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private int duracion;

    public ReservaDeAmbiente(int codigo, int reserva_id,int sala_codigo,String fechaHoraInicio, String fechaHoraFin, int duracion) {
        super();

       

        this.codigo = codigo;
        this.reserva_id = reserva_id;
        this.sala_codigo = sala_codigo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.duracion = duracion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getSala_codigo() {
        return sala_codigo;
    }

    public void setSala_codigo(int sala_codigo) {
        this.sala_codigo = sala_codigo;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio (String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraInicio;
    }

    public void setFechaHoraFin (String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    @Override
    public void mostrarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
