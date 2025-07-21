package Clases;

import Interfaces.IBloqueo;

public class Computadora extends RecursoTecnologico implements IBloqueo {
    
    private String ram;
    private String sistemaOperativo;
    private String procesador;

    private boolean bloqueada;

    public Computadora(String ram, String procesador, String sistemaOperativo, int IDcodigo, String tipo, String estado) {
        super(IDcodigo, tipo, estado);
        this.ram = ram;
        this.procesador = procesador;
        this.sistemaOperativo = sistemaOperativo;
        this.bloqueada = false;
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

    public void mostrarInfo() {
        System.out.println("ID Código: " + getCodigo());
        System.out.println("RAM: " + ram);
        System.out.println("Procesador: " + procesador);
        System.out.println("Estado: " + (bloqueada ? "Bloqueada" : "Desbloqueada"));
    }
}
