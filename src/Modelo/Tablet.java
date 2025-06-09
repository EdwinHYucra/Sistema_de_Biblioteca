package Modelo;


import Modelo.RecursoTecnologico;

public class Tablet extends RecursoTecnologico {
    private String modelo;

    public Tablet(String IDcodigo, String modelo) {
        this.IDcodigo = IDcodigo;
        this.modelo = modelo;
    }
}
