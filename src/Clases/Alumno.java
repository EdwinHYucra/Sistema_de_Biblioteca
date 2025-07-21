package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServicioPrestamos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alumno extends Usuario implements IServicioPrestamos {

    private String carrera;
    private List<ReservaLibro> reservas = new ArrayList<>();
    private List<ReservaRecursoTecnologico> reservasRT = new ArrayList<>();
    private List<ReservaDeAmbiente> reservasSala = new ArrayList<>();

    //Constructor de Prueba
    public Alumno(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre, String apellido) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 2;

    }

    public Alumno(String carrera, String id_codigo, String nombre, String apellido, String correo, int tipoDeUser) {
        super(id_codigo, nombre, apellido, correo, tipoDeUser);
        this.carrera = carrera;
    }

    public Alumno(UsuarioDA usuarioda, String id_codigo, String contraseña, String nombre, String apellido, String carrera) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = 2;
        this.carrera = carrera;
    }

    //Getters y Setters
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
        //Sala sala1 = new Sala("SALA101", "Disponible",6);
        /*ReservaDeAmbiente reservaAmb = new ReservaDeAmbiente(
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
        System.out.println("Ha reservado la sala correctamente.");*/
    }

    public void ReservarRecursoTecnologico() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        /*while (!salir) {
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

                    Tablet tablet1 = new Tablet("T001", "Samsung Galaxy Tab A8");

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
        }*/
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    //Metodos
    public void verCatalogoLibros() {
        System.out.println("--- Catálogo de libros ---");

        List<Libro> listaLibros = usuarioDA.obtenerLibros();

        for (Libro libro : listaLibros) {
            System.out.println("Código: " + libro.getCodigo()+ " Nombre: " + libro.getNombre());
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

    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReserva() {
        return validarDisponibilidadReserva();
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
