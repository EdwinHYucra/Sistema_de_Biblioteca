package Clases;

import java.time.LocalDate;

public class Libro extends Material {

    private String genero;
    private String idioma;
    private String ISBN;
    private String editorial;
    private String edicion;

    public Libro(int codigo, String nombre, String estado, String autor, LocalDate fechaPublicacion,
            boolean disponibilidad, String titulo, String genero) {
        super(codigo, nombre, estado, autor, fechaPublicacion);
        this.genero = genero;
    }

    public Libro(int codigo, String nombre, String autor, LocalDate fechaPublicacion,
            String genero, String idioma, String isbn, String editorial, String edicion) {
        super(codigo, nombre, autor, fechaPublicacion);
        this.genero = genero;
        this.idioma = idioma;
        this.ISBN = isbn;
        this.editorial = editorial;
        this.edicion = edicion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

}
