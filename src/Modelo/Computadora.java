package Modelo;

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

    @Override
    public void bloquear() {
        bloqueada = true;
        System.out.println("Computadora bloqueada.");
    }

    @Override
    public void desbloquear() {
        bloqueada = false;
        System.out.println("Computadora desbloqueada.");
    }

    @Override
    public boolean estaBloqueada() {
        return bloqueada;
    }

    public void verificarBloqueo() {
        if (bloqueada) {
            System.out.println("La computadora está bloqueada.");
        } else {
            System.out.println("La computadora está disponible.");
        }
    }
}
