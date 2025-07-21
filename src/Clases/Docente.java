package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServicioPrestamos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Docente extends Usuario implements IServicioPrestamos {

    private String especialidad;

    private List<ReservaLibro> reservas = new ArrayList<>();

    public void agregarReserva(ReservaLibro RLibro) {
        reservas.add(RLibro);
    }

    public Docente(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre, String apellido, String especialidad) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 3;
        this.especialidad = especialidad;

    }

    public Docente(String especialidad, String id_codigo, String nombre, String apellido, String correo, int tipoDeUser) {
        super(id_codigo, nombre, apellido, correo, tipoDeUser);
        this.especialidad = especialidad;
    }
    

    public Docente(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre, String apellido) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 2;

    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }


    @Override
    public void cancelarReserva() {
        System.out.println("Reserva cancelada por el docente (simulación).");
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Docente: " + nombre + " " + apellido + ", Especialidad: " + especialidad);
    }

    @Override
    public boolean solicitarReserva() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
