
package Clases;

import java.time.LocalDate;
import java.util.Date;

public class Libro extends Material {
    private String genero;
    private String idioma;
    private String isbn;
    private String editorial;
    private String edicion;

    public Libro(int codigo, String nombre,String autor, LocalDate fechaPublicacion, 
            String genero, String idioma, String isbn, String editorial, String edicion) {
        super(codigo, nombre, autor, fechaPublicacion);
        this.genero = genero;
        this.idioma = idioma;
        this.isbn = isbn;
        this.editorial = editorial;
        this.edicion = edicion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
