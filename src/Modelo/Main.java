/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;

// ------------------ CLASES BASE ------------------
abstract class Usuario {
    protected String id_codigo;
    protected String contrasena;
    protected String nombre;
    protected String apellido;
    protected String tipoDeUser;

    public boolean iniciarSesion(String id, String clave) {
        return this.id_codigo.equals(id) && this.contrasena.equals(clave);
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}

interface IServicioPrestamos {
    boolean solicitarReserva(String nombreRecurso, int cantidad);
    void cancelarReserva();
    void mostrarInfo();
}

// ------------------ CLASES DE RECURSO ------------------
abstract class Recurso {
    protected String nombre;
    protected String estado = "Disponible";

    public Recurso(String nombre) {
        this.nombre = nombre;
    }

    public boolean estaDisponible() {
        return estado.equals("Disponible");
    }

    public void reservar() {
        estado = "Reservado";
    }

    public void devolver() {
        estado = "Disponible";
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }
}

class Libro extends Recurso {
    public Libro(String nombre) { super(nombre); }
}

class ArchivoMultimedia extends Recurso {
    public ArchivoMultimedia(String nombre) { super(nombre); }
}

class ArchivoDigital extends Recurso {
    public ArchivoDigital(String nombre) { super(nombre); }
}

class RecursoTecnologico extends Recurso {
    public RecursoTecnologico(String nombre) { super(nombre); }
}

class Sala extends Recurso {
    private int capacidadMax;

    public Sala(String nombre, int capacidadMax) {
        super(nombre);
        this.capacidadMax = capacidadMax;
    }

    public boolean verificarDisponibilidad(int solicitada) {
        return estaDisponible() && capacidadMax >= solicitada;
    }
}

// ------------------ RESERVA ------------------
class Reserva {
    Recurso recurso;
    String estado; // Reservado, Usado, Devuelto, Vencido

    public Reserva(Recurso recurso) {
        this.recurso = recurso;
        this.estado = "Reservado";
    }

    public void usar() { estado = "Usado"; }
    public void devolver() { estado = "Devuelto"; recurso.devolver(); }
    public void vencer() { estado = "Vencido"; recurso.devolver(); }

    public String getEstado() { return estado; }
    public String getNombreRecurso() { return recurso.getNombre(); }
}

// ------------------ ALUMNO ------------------
class Alumno extends Usuario implements IServicioPrestamos {
    private List<Reserva> historial = new ArrayList<>();
    private List<Recurso> recursosDisponibles;

    public Alumno(String id, String pass, String nombre, String apellido, List<Recurso> recursos) {
        this.id_codigo = id;
        this.contrasena = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Alumno";
        this.recursosDisponibles = recursos;
    }

    @Override
    public boolean solicitarReserva(String nombreRecurso, int cantidad) {
        for (Recurso r : recursosDisponibles) {
            if (r.getNombre().equalsIgnoreCase(nombreRecurso) && r.estaDisponible()) {
                if (r instanceof Sala) {
                    Sala sala = (Sala) r;
                    if (!sala.verificarDisponibilidad(cantidad)) {
                        System.out.println("Sala no disponible o capacidad insuficiente.");
                        return false;
                    }
                }
                r.reservar();
                Reserva reserva = new Reserva(r);
                historial.add(reserva);
                System.out.println("Reserva confirmada: " + r.getNombre());
                return true;
            }
        }
        System.out.println("Recurso no disponible.");
        return false;
    }

    @Override
    public void cancelarReserva() {
        for (Reserva r : historial) {
            if (r.getEstado().equals("Reservado")) {
                r.vencer();
                System.out.println("Reserva cancelada: " + r.getNombreRecurso());
                return;
            }
        }
        System.out.println("No hay reservas activas para cancelar.");
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Alumno: " + getNombreCompleto());
    }

    public void verCatalogo() {
        System.out.println("--- Catálogo de Recursos ---");
        for (Recurso r : recursosDisponibles) {
            System.out.println("- " + r.getNombre() + " (" + r.getClass().getSimpleName() + ") - " + r.getEstado());
        }
    }

    public void devolverRecurso() {
        for (Reserva r : historial) {
            if (r.getEstado().equals("Reservado") || r.getEstado().equals("Usado")) {
                r.devolver();
                System.out.println("Recurso devuelto: " + r.getNombreRecurso());
                return;
            }
        }
        System.out.println("No hay recursos para devolver.");
    }

    public void verHistorial() {
        System.out.println("--- Historial de Reservas ---");
        for (Reserva r : historial) {
            System.out.println("- " + r.getNombreRecurso() + " → Estado: " + r.getEstado());
        }
    }
}

// ------------------ MAIN ------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Recurso> recursos = new ArrayList<>();

        // Recursos de prueba
        recursos.add(new Libro("Libro: Anatomia"));
        recursos.add(new ArchivoMultimedia("Video: Enfermeria"));
        recursos.add(new ArchivoDigital("PDF: Farmacologia"));
        recursos.add(new RecursoTecnologico("Tablet A"));
        recursos.add(new RecursoTecnologico("Computadora B"));
        recursos.add(new Sala("Sala 1", 4));

        // Alumno simulado
        Alumno alumno = new Alumno("20230001", "1234", "Luciana", "Palomino", recursos);

        System.out.println("--- SISTEMA DE RESERVAS ---");
        System.out.print("ID de alumno: ");
        String id = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!alumno.iniciarSesion(id, pass)) {
            System.out.println("Credenciales incorrectas. Saliendo...");
            return;
        }

        System.out.println("Bienvenido/a, " + alumno.getNombreCompleto());

        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Reservar recurso");
            System.out.println("3. Devolver recurso");
            System.out.println("4. Ver historial");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> alumno.verCatalogo();
                case 2 -> {
                    System.out.print("Nombre del recurso: ");
                    String nombre = sc.nextLine();
                    int cantidad = 1;
                    if (nombre.toLowerCase().contains("sala")) {
                        System.out.print("¿Cuántas personas ingresarán?: ");
                        cantidad = sc.nextInt(); sc.nextLine();
                    }
                    alumno.solicitarReserva(nombre, cantidad);
                }
                case 3 -> alumno.devolverRecurso();
                case 4 -> alumno.verHistorial();
                case 5 -> alumno.cancelarReserva();
                case 6 -> System.out.println("Sesión finalizada. Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}
