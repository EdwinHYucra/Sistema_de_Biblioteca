package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServicioPrestamos;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Docente extends Usuario implements IServicioPrestamos {

    private String especialidad;
    private List<ReservaLibro> reservas;

    public Docente(String id_codigo, String contraseña, String nombre, String apellido, String especialidad) {
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 3;
        this.especialidad = especialidad;
        this.reservas = new ArrayList<>();
    }

    public Docente(String id_codigo, String contraseña, String nombre, String apellido) {
        this(id_codigo, contraseña, nombre, apellido, "Sin especialidad");
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void agregarReserva(ReservaLibro reservaLibro) {
        reservas.add(reservaLibro);
    }

    public void verCatalogoLibros() {
        System.out.println("--- Catálogo de la sala del docente ---");

        UsuarioDA dao = new UsuarioDA();
        List<Libro> listaLibros = dao.obtenerLibros();

        for (Libro libro : listaLibros) {
            System.out.println("Código: " + libro.getCodigo() + libro.getNombre());
        }
        /*if (listaLibros.isEmpty()) {
            System.out.println("No hay libros en la sala.");
        } else {
            for (Libro libro : listaLibros) {
                System.out.println("Código: " + libro.getCodigo()+ libro.getNombre());
            }
        }*/
    }

    @Override
    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReservaLibro() throws SQLException {
        Scanner Li = new Scanner(System.in);

        System.out.print("Ingrese el código del libro que desea reservar: ");
        int bro = Li.nextInt();
        Li.nextLine();

        UsuarioDA dao = new UsuarioDA();

        if (dao.ValidarEjemplares(bro)) {
            System.out.println("¿Desea reservar el libro? (S/N)");
            String respuesta = Li.nextLine();
            
            if (respuesta.equals("S")){
                int id_Ejemplar = dao.ObtenerEjemplar(bro);
 
                int id_Reserva = dao.RegistarReserva(this.getId_codigo(), 1);
                
                if (dao.RegistarReservadeLibro(id_Reserva, id_Ejemplar)){
                    dao.ModificarEjemplar(id_Ejemplar);
                }
                
            }
        }

       return true; 
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
        System.out.println("Este método no está implementado para esta clase.");
        return false;
    }
}
