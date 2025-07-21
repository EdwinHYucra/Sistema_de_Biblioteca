package Clases;

public class RecursoTecnologico {
    
    private int IDcodigo;
    private String tipo;
    private String estado;
//

    public RecursoTecnologico(int IDcodigo, String tipo, String estado) {
        this.IDcodigo = IDcodigo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getIDcodigo() {
        return IDcodigo;
    }

    public void setIDcodigo(int IDcodigo) {
        this.IDcodigo = IDcodigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean VerificarDisponibilidad() {
        return true; 
    }

    void mostrarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}