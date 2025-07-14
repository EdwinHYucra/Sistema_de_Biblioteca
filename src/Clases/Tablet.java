package Clases;

public class Tablet extends RecursoTecnologico {
    private String modelo;
    private String estado;

    public Tablet(String IDcodigo, String modelo,String estado) {
        this.IDcodigo = IDcodigo;
        this.modelo = modelo;
        this.estado = "disponible";
    }

    /*@Override
    public boolean VerificarDisponibilidad() {
        return stock > 0;
    }*/

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getEstado() {
        return modelo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public void mostrarInfo() {
        System.out.println("ID Código: " + IDcodigo);
        System.out.println("Modelo: " + modelo);
        System.out.println("Estado: " + estado);
        System.out.println("Disponible: " + (VerificarDisponibilidad() ? "Sí" : "No"));
    }
}