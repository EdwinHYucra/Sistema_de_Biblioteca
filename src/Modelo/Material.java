
package Modelo;

public abstract class Material {
    protected String codigo;
    protected String nombre;
    protected String estado;
    protected String autor;
    protected int añoPublicacion;

    public Material(String codigo, String nombre, String estado, String autor, int añoPublicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
    }

    public abstract void mostrarInfo();
}
