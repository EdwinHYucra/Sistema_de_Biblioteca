package Clases;

public class RecursoTecnologico {
    public int IDcodigo;
    
    public RecursoTecnologico(int IDcodigo) {
    this.IDcodigo = IDcodigo;
   }
    public boolean VerificarDisponibilidad() {
        return true; 
    }

    public int getIDcodigo() {
        return IDcodigo;
    }

    public void setIDcodigo(int IDcodigo) {
        this.IDcodigo = IDcodigo;
    }

    void mostrarInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}