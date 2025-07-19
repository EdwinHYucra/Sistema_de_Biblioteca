package Clases;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservaRecursoTecnologico extends Reserva {
    private String tipoReserva;
    private int IDcodigo;
    private RecursoTecnologico recursoTecnologico;

    public ReservaRecursoTecnologico(String tipoReserva, int IDcodigo, RecursoTecnologico recursoTecnologico, LocalDate fechaReserva, double duracion, LocalTime horaReserva, Usuario usuario) {
        super(fechaReserva, duracion, horaReserva, usuario);
        this.tipoReserva = tipoReserva;
        this.IDcodigo = IDcodigo;
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
    public void mostrarInfo(){
        System.out.println("Reserva de recurso tecnologico"+IDcodigo);
    }

    /*Object getRecurso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setFechaDevolucion(String fechaDevolucion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    public int getCodigo() {
        return IDcodigo;
    }
    public RecursoTecnologico getRecursoTecnologico() {
        return recursoTecnologico;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
