package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRecursoTecnologico extends Reserva {

    private int codigo_id;
    private LocalDateTime fechahorainicio;
    private LocalDateTime fechahorafin;
    private int duracion;
    private RecursoTecnologico recursoTecnologico;

    public ReservaRecursoTecnologico(int codigo_id, int duracion, RecursoTecnologico recursoTecnologico, int codigo, LocalDate fechaReserva, Usuario usuario) {
        super(codigo, fechaReserva, usuario);
        this.codigo_id = codigo_id;
        this.duracion = duracion;
        this.recursoTecnologico = recursoTecnologico;
    }

    public ReservaRecursoTecnologico(int codigo_id, int duracion, RecursoTecnologico recursoTecnologico, int codigo, LocalDate fechaReserva, String estado, Usuario usuario) {
        super(codigo, fechaReserva, estado, usuario);
        this.codigo_id = codigo_id;
        this.duracion = duracion;
        this.recursoTecnologico = recursoTecnologico;
    }

    public int getCodigo_id() {
        return codigo_id;
    }

    public void setCodigo_id(int codigo_id) {
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

    public RecursoTecnologico getRecursoTecnologico() {
        return recursoTecnologico;
    }

    public void setRecursoTecnologico(RecursoTecnologico recursoTecnologico) {
        this.recursoTecnologico = recursoTecnologico;
    }
    


    public void realizar() {
        System.out.println("Reserva de recurso tecnológico realizada.");
    }

    public void cancelar() {
        System.out.println("Reserva cancelada.");
    }

    public boolean verificarDisponibilidad() {
        return recursoTecnologico.VerificarDisponibilidad();
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }

    Object getRecurso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
