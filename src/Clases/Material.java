
package Clases;

import java.time.LocalDate;

public class Material {
    private int codigo;
    private String estado;
    
    private String nombre;
    private String autor;
    private LocalDate fechaPublicacion;
    

    public Material(int codigo, String nombre, String estado, String autor, LocalDate fechaPublicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }
    public Material(int codigo, String nombre, String autor, LocalDate fechaPublicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
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
