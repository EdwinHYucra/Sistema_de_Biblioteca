package Clases;

public class RecursoTecnologico {
    
    private int codigo;
    private String tipo;
    private String estado;
//

    public RecursoTecnologico(int codigo, String tipo, String estado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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