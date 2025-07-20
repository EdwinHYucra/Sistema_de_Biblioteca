
package Clases;

import java.time.LocalDate;

public class ArchivoDigital extends Material {
    private int archivo_id;
    private String nombre;
    private String autor;
    private String formato;
    private String tamaño;
    private LocalDate fechaPublicacion;
    private String ruta;

    public ArchivoDigital(int archivo_id, String nombre, String autor, String formato, String tamaño, LocalDate fechaPublicacion, String ruta) {
        super();
        this.archivo_id = archivo_id;
        this.nombre = nombre;
        this.autor = autor;
        this.formato = formato;
        this.tamaño = tamaño;
        this.fechaPublicacion = fechaPublicacion;
        this.ruta = ruta;
    }

    public int getArchivo_id() {
        return archivo_id;
    }

    public void setArchivo_id(int archivo_id) {
        this.archivo_id = archivo_id;
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
