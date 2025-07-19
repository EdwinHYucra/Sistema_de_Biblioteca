package Clases;

import Interfaces.IBloqueo;

public class Computadora extends RecursoTecnologico implements IBloqueo {
    private String ram;
    private String procesador;
    private String sistemaOperativo;
    private boolean bloqueada;

    public Computadora(int IDcodigo, String ram, String sistemaOperativo, String procesador, boolean estado) {
        super();
        this.ram = ram;
        this.procesador = procesador;
        this.sistemaOperativo = sistemaOperativo;
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
    
    public String getSistemaOperativo(){
        return sistemaOperativo;
        
    }
    
    public void setSistemaOperativo(String sistemaOperativo){
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void mostrarInfo() {
        System.out.println("ID Código: " + IDcodigo);
        System.out.println("RAM: " + ram);
        System.out.println("Sistema Operativo: " + sistemaOperativo);
        System.out.println("Procesador: " + procesador);
        System.out.println("Estado: " + (bloqueada ? "Bloqueada" : "Desbloqueada"));
    }
}
