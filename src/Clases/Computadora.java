package Clases;

import Interfaces.IBloqueo;

public class Computadora extends RecursoTecnologico implements IBloqueo {
    private String ram;
    private String procesador;
    private String cpu;
    private boolean bloqueada;

    public Computadora(int IDcodigo, String ram, String procesador, String cpu, boolean estado) {
        super(IDcodigo);
        this.ram = ram;
        this.procesador = procesador;
        this.cpu = cpu;
        this.bloqueada = !estado;  
    }
    
    public void bloquear() {
        bloqueada = true;
        System.out.println("Computadora bloqueada.");
    }


    public void desbloquear() {
        bloqueada = false;
        System.out.println("Computadora desbloqueada.");
    }


    public boolean estaBloqueada() {
        return bloqueada;
    }

    @Override
    public void restringirAcceso() {
        if (bloqueada) {
            System.out.println("La computadora está bloqueada.");
        } else {
            System.out.println("La computadora está disponible.");
        }
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void mostrarInfo() {
        System.out.println("ID Código: " + IDcodigo);
        System.out.println("RAM: " + ram);
        System.out.println("Procesador: " + procesador);
        System.out.println("CPU: " + cpu);
        System.out.println("Estado: " + (bloqueada ? "Bloqueada" : "Desbloqueada"));
    }
}
