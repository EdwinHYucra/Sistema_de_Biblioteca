
package Clases;

import java.time.LocalDate;

public class ArchivoMultimedia extends Material {
    
    private String tamaño;
    private double duracion;
    private String formato;
    private String resolucion;
    private String tipoMultimedia;

    public ArchivoMultimedia(int codigo, String nombre, String estado, String autor, LocalDate fechaPublicacion,
                             String tamaño, double duracion, String formato) {
        super(codigo, nombre, estado, autor, fechaPublicacion);
        this.tamaño = tamaño;
        this.duracion = duracion;
        this.formato = formato;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
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

    
    
    
    public void reproducir() {
        System.out.println("Reproduciendo archivo multimedia...");
    }

    public void detener() {
        System.out.println("Deteniendo archivo multimedia...");
    }

    public void descargar() {
        System.out.println("Descargando archivo multimedia...");
    }
}
