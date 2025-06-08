
package Modelo;

public class ArchivoMultimedia extends Material {
    private double tamaño;
    private double duracion;
    private String formato;

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
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
 
    public ArchivoMultimedia(String codigo, String nombre, String estado, String autor, int añoPublicacion,
                             double tamaño, double duracion, String formato) {
        super(codigo, nombre, estado, autor, añoPublicacion);
        this.tamaño = tamaño;
        this.duracion = duracion;
        this.formato = formato;
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

    @Override
    public void mostrarInfo() {
        System.out.println("Archivo Multimedia: " + nombre + " [" + formato + "] - " + tamaño + "MB, " + duracion + "min");
    }
}
