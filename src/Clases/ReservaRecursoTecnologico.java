package Clases;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRecursoTecnologico extends Reserva {
    private String tipoReserva;
    private String IDcodigo;
    private RecursoTecnologico recursoTecnologico;

    public ReservaRecursoTecnologico(String tipoReserva, String IDcodigo, RecursoTecnologico recursoTecnologico, LocalDate fechaReserva, double duracion, LocalTime horaReserva, Usuario usuario) {
        super(fechaReserva, duracion, horaReserva, usuario);
        this.tipoReserva = tipoReserva;
        this.IDcodigo = IDcodigo;
        this.recursoTecnologico = recursoTecnologico;
    }
    
    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setModelo(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }
    public String getIDcodigo() {
        return IDcodigo;
    }

    public void setIDcodigo(String IDcodigo) {
        this.IDcodigo = IDcodigo;
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
    public void mostrarInfo(){
        System.out.println("Mostrando informacion del la clase padre como la del hijo");
    }

    Object getRecurso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
