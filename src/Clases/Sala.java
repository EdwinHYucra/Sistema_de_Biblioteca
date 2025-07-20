/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Clase que representa una sala o ambiente.
 */
public class Sala {
    private int codigo;
    private String estado;
    private int capacidadMax;

    // Constructor con validación
    public Sala(int codigo, String estado, int capacidadMax) {
        if (capacidadMax < 3 || capacidadMax > 6) {
            throw new IllegalArgumentException("La capacidad máxima debe estar entre 3 y 6 personas.");
        }
        this.codigo = codigo;
        this.estado = estado;
        this.capacidadMax = capacidadMax;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        if (capacidadMax < 3 || capacidadMax > 6) {
            throw new IllegalArgumentException("La capacidad máxima debe estar entre 3 y 6 personas.");
        }
        this.capacidadMax = capacidadMax;
    }

    @Override
    public String toString() {
        return "Sala{" +
               "codigo='" + codigo + '\'' +
               ", estado='" + estado + '\'' +
               ", capacidadMax=" + capacidadMax +
               '}';
    }

    String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
