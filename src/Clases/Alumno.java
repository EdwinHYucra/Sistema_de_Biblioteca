package Clases;

import Interfaces.IServicioPrestamos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dayanna
 */
public class Alumno extends Usuario implements IServicioPrestamos {

    private String carrera;
    //Variables de Prueba
    private List<ReservaLibro> reservas = new ArrayList<>();
    private List<ReservaRecursoTecnologico> reservasRT = new ArrayList<>();
    private List<Libro> listaLibros = new ArrayList<>();
    private List<ReservaDeAmbiente> reservasSala = new ArrayList<>();

    //Constructor de Prueba
    public Alumno(String contraseña, String id_codigo, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Alumno";
        this.listaLibros = new ArrayList<>();
        listaLibros.add(new Libro(
                "M001",
                "Programación en Java",
                "Disponible",
                "James Gosling",
                new java.util.Date(),
                true,
                "Programación en Java",
                "Programación"
        ));
        listaLibros.add(new Libro(
                "M002",
                "Estructuras de Datos",
                "Disponible",
                "Robert Lafore",
                new java.util.Date(),
                true,
                "Estructuras de Datos",
                "Computación"
        ));
    }

    public Alumno(String id_codigo, String contraseña, String nombre, String apellido, String carrera) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Alumno";
        this.carrera = carrera;
        this.listaLibros = new ArrayList<>();
        listaLibros.add(new Libro(
                "M001",
                "Programación en Java",
                "Disponible",
                "James Gosling",
                new java.util.Date(),
                true,
                "Programación en Java",
                "Programación"
        ));
        listaLibros.add(new Libro(
                "M002",
                "Estructuras de Datos",
                "Disponible",
                "Robert Lafore",
                new java.util.Date(),
                true,
                "Estructuras de Datos",
                "Computación"
        ));
    }

    public List<ReservaRecursoTecnologico> getReservasRT() {
        return reservasRT;
    }

    public void setReservasRT(List<ReservaRecursoTecnologico> reservasRT) {
        this.reservasRT = reservasRT;
    }

    public void Reservarsala() {
        
        // 1) Intriducir la cantidad
        int capacidad = 0;
        boolean capacidadValida = false;
        Scanner sc = new Scanner(System.in);
        while (!capacidadValida) {
            System.out.print("Ingrese el número de personas que usarán la sala (mínimo 3): ");
            String capacidadStr = sc.nextLine();

            try {
                capacidad = Integer.parseInt(capacidadStr);
                if (capacidad >= 3) {
                    capacidadValida = true;
                } else {
                    System.out.println("La capacidad mínima de la sala es de 3 personas.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
        
        // 2) Buscar la sala
        Sala sala1 = new Sala("SALA101", "Disponible",6);
        ReservaDeAmbiente reservaAmb = new ReservaDeAmbiente(
            "RES001", // código de reserva
            5,        // capacidad máxima
            90,       // tiempo en minutos
            sala1     // la sala asociada
        );
        // 3) Pedir la cantidad de alumnos, mínimo 3
        int cantidadAlumnos = 0;
        do {
            System.out.print("Ingrese la cantidad de alumnos a registrar (mínimo 3, máximo " + reservaAmb.getCapacidadMax() + "): ");
            try {
                cantidadAlumnos = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, intente nuevamente.");
                continue;
            }
            if (cantidadAlumnos < 3 || cantidadAlumnos > reservaAmb.getCapacidadMax()) {
                System.out.println("Cantidad fuera de rango. Debe ser al menos 3 y como máximo " + reservaAmb.getCapacidadMax());
            }
        } while (cantidadAlumnos < 3 || cantidadAlumnos > reservaAmb.getCapacidadMax());
        
        // 4) Crear la lista de alumnos
        List<Alumno> listaAlumnos = new ArrayList<>();
        
        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("\nDatos del alumno #" + (i + 1));
            System.out.print("Código de alumno: ");
            String codigoAlumno = sc.nextLine();

            Alumno alumno = new Alumno(codigoAlumno, "Aletorio", "Aletorio", "Aletorio");
            listaAlumnos.add(alumno);
            //reservaAmb.agregarAlumno(alumno);
        }
        
        // 5) Asignar la lista de alumnos a la reserva
        reservaAmb.setListaAlumnos(listaAlumnos);
        
        // 6) Mostrar la lista de alumnos
        System.out.println("\n=== Alumnos registrados en la reserva ===");
        for (Alumno alumno : reservaAmb.getListaAlumnos()) {
            System.out.println("- " + alumno.getId_codigo() + " | " + alumno.getNombre());
        }
        
        // 7) Mostrar estado de la reserva
        reservaAmb.verificarEstado();
        System.out.println("Ha reservado la sala correctamente.");
    }

    public void ReservarRecursoTecnologico() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Opciones de Reserva de Recurso Tecnológico ---");
            System.out.println("1. Reservar Tablet");
            System.out.println("2. Reservar Computadora");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            String opcionStr = sc.nextLine();
            int opcion = -1;

            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:

                    Tablet tablet1 = new Tablet("T001", "Samsung Galaxy Tab A8", 5);

                    ReservaRecursoTecnologico rRecTecTap = new ReservaRecursoTecnologico("Computadora", "14625", tablet1, LocalDate.now(), 12.5, LocalTime.now(), this);
                    reservasRT.add(rRecTecTap);
                    System.out.println("Ha reservado una Tablet correctamente.");

                    break;
                case 2:

                    Computadora pc1 = new Computadora("C001", "16GB", "Intel Core i5", "HP ProDesk", true);

                    ReservaRecursoTecnologico rRecTecCom = new ReservaRecursoTecnologico("Computadora", "14625", pc1, LocalDate.now(), 12.5, LocalTime.now(), this);
                    reservasRT.add(rRecTecCom);

                    System.out.println("Ha reservado una Computadora correctamente.");
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del sistema de reservas...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReserva() {
        return validarDisponibilidadReserva();
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

    public List<Libro> obtenerLibros() {
        return listaLibros;
    }

    public void agregarReserva(ReservaLibro RLibro) {
        reservas.add(RLibro);
    }

    //@Override
    public boolean solicitarReservaLibro(String codigoLibro) {
        Libro libroRecep = null;
        for (Libro libro : listaLibros) {
            if (libro.getCodigo().equals(codigoLibro)) {
                libroRecep = libro;
                break;
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Desea reservar el libro \"" + libroRecep.getTitulo() + "\"? (S/N): ");
        String confirmar = sc.nextLine();
        if (confirmar.equalsIgnoreCase("S")) {

            ReservaLibro reservaL = new ReservaLibro(null, libroRecep, "pendiente", LocalDate.now(), 12.2, LocalTime.now(), this);
            libroRecep.setDisponibilidad(false);
            agregarReserva(reservaL);
            System.out.println("Reserva realizada con éxito para el libro \"" + libroRecep.getTitulo() + "\".");
        } else {
            System.out.println("Reserva cancelada.");
        }
        return true;
    }

    public void cancelarReserva() {
        System.out.println("Reserva cancelada por el alumno.");
    }

    public void mostrarInfo() {
        System.out.println("Alumno: " + nombre + " " + apellido + ", Carrera: " + carrera);
    }

    @Override
    public void verificarCredenciales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void bloquearUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
