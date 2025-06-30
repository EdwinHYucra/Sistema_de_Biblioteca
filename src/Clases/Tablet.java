package Clases;

import Clases.RecursoTecnologico;

public class Tablet extends RecursoTecnologico {
    private String modelo;
    private String estado;

    public Tablet(String IDcodigo, String modelo ) {
        this.IDcodigo = IDcodigo;
        this.modelo = modelo;
        this.estado = "disponible";
    }
}
