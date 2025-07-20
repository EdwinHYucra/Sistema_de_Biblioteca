package Clases;

public class Tablet extends RecursoTecnologico {
    private String modelo;
   
    public Tablet(int IDcodigo, String modelo) {
        super(IDcodigo);
        this.modelo = modelo;
        setEstado("disponible");
    }

   @Override
    public boolean VerificarDisponibilidad() {
        return getEstado().equalsIgnoreCase("disponible");
    }
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /*public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }*/
@Override
    public void mostrarInfo() {
        System.out.println("ID Código: " + IDcodigo);
        System.out.println("Modelo: " + modelo);
        //System.out.println("Stock: " + stock);
        System.out.println("Disponible: " + (VerificarDisponibilidad() ? "Sí" : "No"));
    }
}