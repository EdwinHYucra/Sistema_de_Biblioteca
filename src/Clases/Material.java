
package Clases;

public class Material {
    private int codigo;
    private int tipo_Material_id;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getTipo_Material_id() {
        return tipo_Material_id;
    }

    public void setTipo_Material_id(int tipo_Material_id) {
        this.tipo_Material_id = tipo_Material_id;
    }
    
    
    public void mostrarInfo() {
       System.out.println("Codigo: "+codigo);
       System.out.println("Tipo Material id: " + tipo_Material_id);
    
    }

    
}
