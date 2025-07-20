package Clases;

import Acceso_Datos.UsuarioDA;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dayanna
 */
public class Recepcionista extends Usuario {

    public Recepcionista(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.tipoDeUser = "Recepcionista";
    }

    //constructor de prueba
    public Recepcionista(String id_codigo, String contraseña, String nombre) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.tipoDeUser = "Recepcionista";
    }

    public Recepcionista(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Recepcionista";
    }

    public void registrarDevolucion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el codigo: ");
        int inputCodigo = scanner.nextInt();

        Reserva reservaEncontrada = UsuarioDA.buscarReserva(inputCodigo);

        if (reservaEncontrada == null) {
            System.out.println("No se encontró la reserva");
            return;
        }
        if (reservaEncontrada instanceof ReservaLibro rLibro) {
            validarFechaDevolucion(rLibro);
        } else if (reservaEncontrada instanceof ReservaDeAmbiente rAmbiente) {
            validarDevolucion(rAmbiente);
        } else if (reservaEncontrada instanceof ReservaRecursoTecnologico rTec) {
            validarDevolucionRT(rTec);
        }

    }

    public boolean validarFechaDevolucion(ReservaLibro rEjemplar) {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isAfter(rEjemplar.getFechadevolucion())) {
            int diasRetraso = (int) (fechaActual.toEpochDay() - rEjemplar.getFechadevolucion().toEpochDay());
            Penalidad penalidad = new Penalidad(LocalDate.now(), LocalDate.now().plusDays(7), "Devolución tardía", diasRetraso, null);
            UsuarioDA.agregarPenalidad(penalidad, rEjemplar.getUsuario());
            UsuarioDA.modificarEstadoEjemplar(rEjemplar.getLibro());
            System.out.println("Devolución registrada. Penalidad por " + diasRetraso + " días de retraso");
        } else {
            UsuarioDA.modificarEstadoEjemplar(rEjemplar.getLibro());
            System.out.println("Devolución registrada correctamente");
        }
        return true;
    }

    public boolean validarDevolucion(ReservaDeAmbiente rAmbiente) {
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fechaActual.isAfter(rAmbiente.getFechaHoraFin())) {
        } else {
            System.out.println("Devolución registrada correctamente");
        }
        return true;
    }

    public boolean validarDevolucionRT(ReservaRecursoTecnologico rRT) {
        LocalDateTime fechaActual = LocalDateTime.now();
        if (fechaActual.isAfter(rRT.getFechaHoraFin())) {
            Penalidad penalidad = new Penalidad(LocalDate.now(), LocalDate.now().plusDays(7), "Devolución tardía", 1, null);
            UsuarioDA.agregarPenalidad(penalidad, rRT.getUsuario());
            UsuarioDA.modificarEstadoRecursoTecnologico(rRT.getRecurso_id());
        } else {
            UsuarioDA.modificarEstadoRecursoTecnologico(rRT.getRecurso_id());
            System.out.println("Devolución registrada correctamente");
        }
        return true;
    }
}
