
package Clases;

import java.time.LocalDateTime;
import java.util.Date;

public class ArchivoMultimedia extends Material {
    private int archivo_id;
    private String nombre;
    private String autor;
    private LocalDateTime fecha_publicacion;
    private String tamaño;
    private String duracion;
    private String formato;
    private String resolucion;
    private String tipoMultimedia;

    public ArchivoMultimedia(int archivo_id, String nombre, String autor, LocalDateTime fecha_publicacion, String tamaño, String duracion, String formato, String resolucion, String tipoMultimedia) {
        super();
        this.archivo_id = archivo_id;
        this.nombre = nombre;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
        this.tamaño = tamaño;
        this.duracion = duracion;
        this.formato = formato;
        this.resolucion = resolucion;
        this.tipoMultimedia = tipoMultimedia;
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

    public LocalDateTime getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(LocalDateTime fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTipoMultimedia() {
        return tipoMultimedia;
    }

    public void setTipoMultimedia(String tipoMultimedia) {
        this.tipoMultimedia = tipoMultimedia;
    }
    
    

    
}
