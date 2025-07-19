package Clases;

public class Tablet extends RecursoTecnologico {
    private String modelo;
    private String sistemaOperativo;

    public Tablet(int IDcodigo, String modelo, String sistemaOperativo) {
        super();
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
    }

    /*@Override
    public boolean VerificarDisponibilidad() {
        return stock > 0;
    }*/
    public int getIDCodigo(){
        return IDcodigo;
    }
    
    public void setCodigo(int IDCodigo){
        this.IDcodigo = IDcodigo;
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
        System.out.println("ID Código: " + IDcodigo);
        System.out.println("Modelo: " + modelo);
        System.out.println("SistemaOperativo: " + sistemaOperativo);
        System.out.println("Disponible: " + (VerificarDisponibilidad() ? "Sí" : "No"));
    }
}