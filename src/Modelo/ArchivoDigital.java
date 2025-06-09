
package Modelo;

import java.util.Date;

public class ArchivoDigital extends Material {
    private String formato;
    private String ruta;

    public ArchivoDigital(String codigo, String nombre, String estado, String autor, Date fechaPublicacion,
                          String formato, String ruta) {
        super(codigo, nombre, estado, autor, fechaPublicacion);
        this.formato = formato;
        this.ruta = ruta;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    

    public void abrir() {
        System.out.println("Abriendo archivo digital...");
    }

    public void cerrar() {
        System.out.println("Cerrando archivo digital...");
    }

    public void descargar() {
        System.out.println("Descargando archivo digital...");
    }
}
