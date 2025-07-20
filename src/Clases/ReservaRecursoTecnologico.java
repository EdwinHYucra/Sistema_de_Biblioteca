package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRecursoTecnologico extends Reserva {

    private int id;
    private int reserva_id;
    private int recurso_id;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private int duracion;

    public ReservaRecursoTecnologico(int codigo, int recurso_id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, int duracion, int id, LocalDate fechaReserva, int tipoReserva, String usuario, String estado) {
        super(id, fechaReserva, tipoReserva, usuario, estado);
        this.id = codigo;
        this.recurso_id = recurso_id;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getRecurso_id() {
        return recurso_id;
    }

    public void setRecurso_id(int recurso_id) {
        this.recurso_id = recurso_id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void realizar() {
        System.out.println("Reserva de recurso tecnológico realizada.");
    }

    public void cancelar() {
        System.out.println("Reserva cancelada.");
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }

    Object getRecurso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
