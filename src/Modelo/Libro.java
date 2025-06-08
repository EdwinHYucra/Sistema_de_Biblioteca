
package Modelo;

public class Libro extends Material {
    private boolean disponibilidad;
    private String titulo;
    private String genero;

    public Libro(String codigo, String nombre, String estado, String autor, int añoPublicacion,
                 boolean disponibilidad, String titulo, String genero) {
        super(codigo, nombre, estado, autor, añoPublicacion);
        this.disponibilidad = disponibilidad;
        this.titulo = titulo;
        this.genero = genero;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Libro: " + titulo + " (" + genero + ") - " + (disponibilidad ? "Disponible" : "No disponible"));
    }
}
