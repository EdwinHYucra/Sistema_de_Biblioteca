package Modelo;

public class RecursoTecnologico {
    String IDcodigo;

    public boolean VerificarDisponibilidad() {
        return true; // simula disponibilidad
    }

    public String getIDcodigo() {
        return IDcodigo;
    }

    public void setIDcodigo(String IDcodigo) {
        this.IDcodigo = IDcodigo;
    }
}