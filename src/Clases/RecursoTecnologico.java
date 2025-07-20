package Clases;

public class RecursoTecnologico {
    public int IDcodigo;
    private String tipo;
    private String estado;

   
    public RecursoTecnologico(int IDcodigo) {
    this.IDcodigo = IDcodigo;
    this.estado = "disponible";
    }

    public boolean VerificarDisponibilidad() {
        return "Disponible".equalsIgnoreCase(estado);
    }

    public int getIDcodigo() {
        return IDcodigo;
    }

    public void setIDcodigo(int IDcodigo) {
        this.IDcodigo = IDcodigo;
    }
    
     public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

   public void mostrarInfo() {
    System.out.println("Código: " + IDcodigo + ", Tipo: " + tipo + ", Estado: " + estado);
}
   /*void setEstado(String disponible) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}