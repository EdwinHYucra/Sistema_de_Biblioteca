
package Clases;

public class Material {
    private int codigo;
    private int tipo_material_id;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getTipo_material_id() {
        return tipo_material_id;
    }

    public void setTipo_material_id(int tipo_material_id) {
        this.tipo_material_id = tipo_material_id;
    }
    
    
    public void mostrarInfo() {
       System.out.println("Codigo: "+codigo);
       System.out.println("Tipo Material id: " + tipo_material_id);
    
    }

    
}
