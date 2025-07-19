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
    private String nombresala;
    private int capacidad;

    // Constructor con validación
    public Sala(int codigo, String nombresala, int capacidad) {
        if (capacidad < 3 || capacidad > 6) {
            throw new IllegalArgumentException("La capacidad máxima debe estar entre 3 y 6 personas.");
        }
        this.nombresala = nombresala;
        this.capacidad= capacidad;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombresala() {
        return nombresala;
    }

    public void setNombresala(String nombresala) {
        this.nombresala = nombresala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad< 3 || capacidad > 6) {
            throw new IllegalArgumentException("La capacidad máxima debe estar entre 3 y 6 personas.");
        }
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Sala{" +
               "codigo='" + codigo + '\'' +
               ", nombresala='" + nombresala + '\'' +
               ", capacidad=" + capacidad +
               '}';
    }

    String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void mostrarInfo() {
        System.out.println("ID Código: " + codigo);
        System.out.println("Nombre de la sala: " + nombresala);
        System.out.println("Cantidad: " + capacidad);
    }
}
