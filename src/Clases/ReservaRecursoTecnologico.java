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
