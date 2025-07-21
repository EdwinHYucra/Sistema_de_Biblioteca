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

    //Metodos
    public void verCatalogoLibros() {
        System.out.println("--- Catálogo de la sala del docente ---");

        List<Libro> listaLibros = usuarioDA.obtenerLibros();

        for (Libro libro : listaLibros) {
            System.out.println("Código: " + libro.getCodigo() + libro.getNombre());
        }
    }

    public void solicitarReservaLibro() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el código del libro que desea reservar: ");
        int codigoLibro = sc.nextInt();
        sc.nextLine();

        if (usuarioDA.ValidarEjemplares(codigoLibro)) {
            System.out.println("¿Desea reservar el libro? (S/N)");
            String respuesta = sc.nextLine();

            if (respuesta.equals("S")) {
                int id_Ejemplar = usuarioDA.ObtenerEjemplar(codigoLibro);

                int id_Reserva = usuarioDA.RegistarReserva(this.getId_codigo(), 1);

                if (usuarioDA.RegistarReservadeLibro(id_Reserva, id_Ejemplar)) {
                    usuarioDA.ModificarEjemplar(id_Ejemplar);

                    System.out.println("Se realizo la reserva con exito!\n");

                    ReservaLibro reslib = usuarioDA.BuscarReservaLibro(id_Reserva);
                    
                    System.out.println("Detalle de la Reserva");
                    System.out.println("Codigo Reserva: " + reslib.getCodigo());
                    System.out.println("Libro: "+ reslib.getLibro().getNombre());
                    System.out.println("Ejemplar: "+ reslib.getEjemplar().getCodigo());
                    System.out.println("Estado: "+ reslib.getEstado());
                    System.out.println("Porfavor acercate a recepcion para recoger el libro con el codigo de reserva");
                    
                }

            }
        }
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
