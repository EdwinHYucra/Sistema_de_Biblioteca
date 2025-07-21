package Clases;

public class Tablet extends RecursoTecnologico {
    
    private String modelo;
    private String sistemaOperativo;

    public Tablet(String modelo, String sistemaOperativo, int IDcodigo, String tipo, String estado) {
        super(IDcodigo, tipo, estado);
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }
    
    public void mostrarInfo() {
        System.out.println("ID Código: " + getIDcodigo());
        System.out.println("Modelo: " + modelo);
        //System.out.println("Stock: " + stock);
        System.out.println("Disponible: " + (VerificarDisponibilidad() ? "Sí" : "No"));
    }
}