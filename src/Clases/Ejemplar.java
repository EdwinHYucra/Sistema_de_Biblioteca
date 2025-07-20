package Clases;

public class Ejemplar {
    private int ejemplarID;
    private int libroID;
    private String estado;

    public Ejemplar(int ejemplarID, int libroID, String estado) {
        this.ejemplarID = ejemplarID;
        this.libroID = libroID;
        this.estado = estado;
    }

    public int getEjemplarID() {
        return ejemplarID;
    }

    public void setEjemplarID(int ejemplarID) {
        this.ejemplarID = ejemplarID;
    }

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
