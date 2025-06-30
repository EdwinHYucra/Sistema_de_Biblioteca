package Clases;

import Interfaces.IServicioPrestamos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Docente extends Usuario implements IServicioPrestamos {

    private String especialidad;
    private List<ReservaLibro> reservas = new ArrayList<>();
    private List<Libro> salaDocente = new ArrayList<>();

    public void agregarReserva(ReservaLibro RLibro) {
        reservas.add(RLibro);
    }

    /*
    public void verReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No tienes reservas registradas.");
        } else {
            System.out.println("--- Tus reservas ---");
            for (String titulo : reservas) {
                System.out.println("• " + titulo);
            }
        }
    }
*/
    
    public Docente(String id_codigo, String contraseña, String nombre, String apellido, String especialidad) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Docente";
        this.especialidad = especialidad;
    }

    public Docente(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Docente";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void agregarLibroASala(Libro libro) {
        salaDocente.add(libro);
    }

    public void verCatalogoLibros() {
        List<Libro> lista = obtenerLibros();

        System.out.println("--- Catálogo de la sala del docente ---");
        if (lista.isEmpty()) {
            System.out.println("No hay libros en la sala.");
        } else {
            for (Libro libro : lista) {
                System.out.println("Código: " + libro.getCodigo() + " | Título: " + libro.getTitulo() + " | Disponibilidad: " + (libro.isDisponibilidad() ? "Disponible" : "No disponible"));

            }
        }
    }

    @Override
    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    //@Override
    public boolean solicitarReservaLibro(String codigoLibro) {
        List<Libro> lista = obtenerLibros();
        Libro libroRecep = null;
        for (Libro libro : lista) {
            if (libro.getCodigo() == codigoLibro){              
            libroRecep = libro;
            break;
            }
        }
        Scanner sc = new Scanner(System.in);  
         System.out.print("¿Desea reservar el libro \"" + libroRecep.getTitulo() + "\"? (S/N): ");
                        String confirmar = sc.nextLine();
                        if (confirmar.equalsIgnoreCase("S")) {
                            
                            ReservaLibro reservaL = new ReservaLibro(null, libroRecep, "pendiente", LocalDate.now(),12.2, LocalTime.now(), this);
                            libroRecep.setDisponibilidad(false);
                            agregarReserva(reservaL); 
                            System.out.println("✅ Reserva realizada con éxito para el libro \"" + libroRecep.getTitulo() + "\".");
                        } else {
                            System.out.println("Reserva cancelada.");
                        }
        return true;
    }

    //Metodo de prueba
    public List<Libro> obtenerLibros() {
        Libro libro1 = new Libro(
                "M001",
                "Programación en Java",
                "Disponible",
                "James Gosling",
                new java.util.Date(),
                true,
                "Programación en Java",
                "Programación"
        );

        Libro libro2 = new Libro(
                "M002",
                "Estructuras de Datos",
                "Disponible",
                "Robert Lafore",
                new java.util.Date(),
                true,
                "Estructuras de Datos",
                "Computación"
        );
        List<Libro> lista = new ArrayList();
        lista.add(libro1);
        lista.add(libro2);
        return lista;
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
