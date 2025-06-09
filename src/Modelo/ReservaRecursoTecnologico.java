package Modelo;

public class ReservaRecursoTecnologico extends Reserva {
    private String tipoReserva;
    private String IDcodigo;
    private RecursoTecnologico recursoTecnologico;

    public ReservaRecursoTecnologico(String tipoReserva, String IDcodigo, RecursoTecnologico recursoTecnologico) {
        this.tipoReserva = tipoReserva;
        this.IDcodigo = IDcodigo;
        this.recursoTecnologico = recursoTecnologico;
    }

    @Override
    public void realizar() {
        System.out.println("Reserva de recurso tecnológico realizada.");
    }

    @Override
    public void cancelar() {
        System.out.println("Reserva cancelada.");
    }

    @Override
    public boolean verificarDisponibilidad() {
        return recursoTecnologico.verificarDisponibilidad();
    }
}
