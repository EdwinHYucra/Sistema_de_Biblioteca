
package Clases;

import java.util.Date;

public class Libro extends Material {
    private boolean disponibilidad;
    private String titulo;
    private String genero;

    public Libro(String codigo, String nombre, String estado, String autor, Date fechaPublicacion,
                 boolean disponibilidad, String titulo, String genero) {
        super(codigo, nombre, estado, autor, fechaPublicacion);
        this.disponibilidad = disponibilidad;
        this.titulo = titulo;
        this.genero = genero;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}