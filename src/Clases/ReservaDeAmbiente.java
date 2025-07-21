package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Interfaces.IBloqueo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ReservaDeAmbiente extends Reserva implements IBloqueo {

    private String codigo_id;
    private LocalDateTime fechahorainicio;
    private LocalDateTime fechahorafin;
    private int duracion;
    private List<Alumno> listaAlumnos;
    private Sala sala;

    public ReservaDeAmbiente(String codigo_id, int duracion, Sala sala, int codigo, LocalDate fechaReserva, Usuario usuario) {
        super(codigo, fechaReserva, usuario);
        this.codigo_id = codigo_id;
        this.duracion = duracion;
        this.listaAlumnos = new ArrayList<>();
        this.sala = sala;
    }

    public ReservaDeAmbiente(String codigo_id, int duracion, Sala sala, int codigo, LocalDate fechaReserva, String estado, Usuario usuario) {
        super(codigo, fechaReserva, estado, usuario);
        this.codigo_id = codigo_id;
        this.duracion = duracion;
        this.sala = sala;
    }
    

    public String getCodigo_id() {
        return codigo_id;
    }

    public void setCodigo_id(String codigo_id) {
        this.codigo_id = codigo_id;
    }

    public LocalDateTime getFechahorainicio() {
        return fechahorainicio;
    }

    public void setFechahorainicio(LocalDateTime fechahorainicio) {
        this.fechahorainicio = fechahorainicio;
    }

    public LocalDateTime getFechahorafin() {
        return fechahorafin;
    }

    public void setFechahorafin(LocalDateTime fechahorafin) {
        this.fechahorafin = fechahorafin;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void agregarAlumno(Alumno alumno) {
        if (listaAlumnos.size() >= sala.getCapacidadMax()) {
            System.out.println("No se puede agregar más alumnos. Capacidad máxima alcanzada.");
        } else {
            listaAlumnos.add(alumno);
            System.out.println("Alumno agregado: " + alumno.getNombre());
        }
    }

    public void verificarEstado() {
        System.out.println("Sala [" + codigo + "] - Estado: " + estado + " - Tiempo restante: ");
    }

    @Override
    public void restringirAcceso() {
        estado = "Bloqueada";
        //tiempoRestante = 0;
        System.out.println("Sala [" + codigo + "] bloqueada por tiempo expirado.");
    }

    @Override
    public void mostrarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
