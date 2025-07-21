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
            System.out.println("Código: " + libro.getCodigo() + " Nombre: " + libro.getNombre());
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

                    /*Convertir metodo*/
                    System.out.println("Detalle de la Reserva");
                    System.out.println("Codigo Reserva: " + reslib.getCodigo());
                    System.out.println("Libro: " + reslib.getLibro().getNombre());
                    System.out.println("Ejemplar: " + reslib.getEjemplar().getCodigo());
                    System.out.println("Estado: " + reslib.getEstado());
                    System.out.println("Porfavor acercate a recepcion para recoger el libro con el codigo de reserva");
                    
                }
                
            }
        }
    }
    
    public void solicitarReservaRecursoTecnologico() {
        System.out.println("\n=== ¿Que deseas reservar? ===");
        System.out.println("1. Tablet");
        System.out.println("2. Ordenador");
        System.out.println("Eligue una opcion (1-2)");
        
        Scanner sc = new Scanner(System.in);
        int opcionReserva = sc.nextInt();
        sc.nextLine();
        
        switch (opcionReserva) {
            case 1:
                
                if (usuarioDA.validarDisponibilidadRecursoTec("tablet")) {
                    System.out.println("¿Desea reservar el recurso? (S/N)");
                    String rpta = sc.nextLine();
                    if (rpta.equalsIgnoreCase("s")) {
                        
                        System.out.println("Indique cuanto tiempo sera la duracion de la reserva, Maximo 1 a 3 horas");
                        int duracion = sc.nextInt();
                        
                        sc.nextLine();
                        int id_tablet = usuarioDA.obtenerRecursoTecID("tablet");
                        
                        int id_reserva = usuarioDA.RegistarReserva(this.getId_codigo(), 3);
                        
                        if (usuarioDA.RegistarRecursoTecnologico(id_reserva, id_tablet, duracion)) {
                            
                            
                            System.out.println("Se realizo la reserva con exito!\n");
                            
                            ReservaRecursoTecnologico resRecTec = usuarioDA.BuscarReservaRecursoTecnologico(id_reserva);
                            usuarioDA.ModificarEstadoRecTec(resRecTec.getRecursoTecnologico().getCodigo());
                            resRecTec.mostrarInfo();
                        }
                        
                    } else {
                        System.out.println("Se cancelo la reserva de tablet");
                        break;
                    }
                    
                } else {
                    System.out.println("En este momento no hay tablets disponibles intenta en otro momento!.");
                }
                break;
            case 2:
                if (usuarioDA.validarDisponibilidadRecursoTec("computadora")) {
                    System.out.println("¿Desea reservar el recurso? (S/N)");
                    String rpta = sc.nextLine();
                    if (rpta.equalsIgnoreCase("s")) {
                        
                        System.out.println("Indique cuanto tiempo sera la duracion de la reserva, Maximo 1 a 3 horas");
                        int duracion = sc.nextInt();
                        
                        sc.nextLine();
                        
                        int id_compu = usuarioDA.obtenerRecursoTecID("computadora");
                        
                        int id_reserva = usuarioDA.RegistarReserva(this.getId_codigo(), 3);
                        
                        if (usuarioDA.RegistarRecursoTecnologico(id_reserva, id_compu, duracion)) {
                            
                            System.out.println("Se realizo la reserva con exito!\n");
                            ReservaRecursoTecnologico resRecTec = usuarioDA.BuscarReservaRecursoTecnologico(id_reserva);
                            usuarioDA.ModificarEstadoRecTec(resRecTec.getRecursoTecnologico().getCodigo());
                            
                            resRecTec.mostrarInfo();
                        }
                        
                    } else {
                        System.out.println("Se cancelo la reserva de tablet");
                        break;
                    }
                    
                } else {
                    System.out.println("En este momento no hay Ordenadores disponibles intenta en otro momento!.");
                    break;
                }
                break;
            default:
                break;
        }
        
    }
    
    public void solicitarReservaAmbiente() {
        
        List<Alumno> listaalumnos = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int opcionReserva = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            System.out.println("\n=== ¿Cuántos integrantes conformarán la sala? ===");
            System.out.println("El mínimo de alumnos es 3 y el máximo es 5.");
            System.out.print("Ingrese la cantidad: ");
            
            String entrada = sc.nextLine();
            sc.nextLine();
            
            try {
                opcionReserva = Integer.parseInt(entrada);
                
                if (opcionReserva >= 3 && opcionReserva <= 5) {
                    entradaValida = true;
                } else {
                    System.out.println("El número debe estar entre 3 y 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar solo números.");
            }
        }
        
        if (usuarioDA.validarDisponibilidaSala(opcionReserva)) {
            System.out.println("Si hay salas disponibles");
            for (int i = 0; i < opcionReserva; i++) {
                System.out.println("Ingresa el código del estudiante:");
                String entrada = sc.nextLine();
                
                Usuario ualumno = usuarioDA.buscarUsuario(entrada);
                
                if (ualumno instanceof Alumno alumno) {
                    listaalumnos.add(alumno);
                    System.out.println("Alumno agregado: " + alumno.getNombre());
                } else {
                    System.out.println("Código inválido o el usuario no es un alumno.");
                    i--;
                }
            }
            System.out.println("¿Cuando sera el tiempo estimado en horas de la duracion de la reserva?");
            int duracion = sc.nextInt();
            sc.nextLine();
            
            System.out.println("¿Desea reservar Ambiente? (S/N)");
            String rpta = sc.nextLine();
            sc.nextLine();
            
            if (rpta.equalsIgnoreCase("s")) {
                
                int id_sala = usuarioDA.obtenerSala();
                int id_reserva = usuarioDA.RegistarReserva(this.getId_codigo(), 2);
                
                if (usuarioDA.RegistarReservaAmbiente(id_reserva, listaalumnos, id_sala, duracion)) {
                    
                    System.out.println("Se realizo la reserva con exito!\n");
                    
                    ReservaDeAmbiente resAmb = usuarioDA.BuscarReservaAmbiente(id_reserva);
                    
                    usuarioDA.ModificarEstadoSala(resAmb.getSala().getCodigo());
                    resAmb.setListaAlumnos(listaalumnos);
                    resAmb.mostrarInfo();
                }
                
            } else {
                System.out.println("Se cancelo la reserva ambiente");
            }
        } else {
            
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
