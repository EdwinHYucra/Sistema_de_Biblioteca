package Clases;

import Interfaces.IBloqueo;

public class Computadora extends RecursoTecnologico implements IBloqueo {
    private String ram;
    private String procesador;
    private String cpu;
    private boolean bloqueada;

    public Computadora(String IDcodigo, String ram, String procesador, String cpu) {
        this.IDcodigo = IDcodigo;
        this.ram = ram;
        this.procesador = procesador;
        this.cpu = cpu;
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
}
