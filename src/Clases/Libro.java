package Clases;

import java.time.LocalDate;

public class Libro extends Material {

    private int libro_id;
    private String nombre;
    private String autor;
    private LocalDate fecha_publicacion;
    private String genero;
    private String idioma;
    private String ISBN;
    private String editorial;
    private String edicion;

    public Libro(int libro_id, String nombre, String autor, LocalDate fecha_publicacion, String genero, String idioma, String ISBN, String editorial, String edicion) {
        super();
        this.libro_id = libro_id;
        this.nombre = nombre;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
        this.genero = genero;
        this.idioma = idioma;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.edicion = edicion;
    }

    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
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

    public LocalDate getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDate fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
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
