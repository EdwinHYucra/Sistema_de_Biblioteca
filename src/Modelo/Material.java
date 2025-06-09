
package Modelo;

import java.util.Date;

public class Material {
    private String codigo;
    private String nombre;
    private String estado;
    private String autor;
    private Date fechaPublicacion;

    public Material(String codigo, String nombre, String estado, String autor, Date fechaPublicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public void mostrarInfo() {
        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Estado: " + estado);
        System.out.println("Autor: " + autor);
        System.out.println("Fecha de publicación: " + fechaPublicacion);
    }
    
}
