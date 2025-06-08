package Modelo;

import Interfaces.IServicioPrestamos;
import Interfaces.IServiciosRecursos;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Dayanna
 */
public class Usuarios {
abstract class Usuario {
    protected String id_codigo;
    protected String contraseña;
    protected String nombre;
    protected String apellido;
    protected String tipoDeUser;
    protected List<Penalidad> penalidades = new ArrayList<>();

    public String getId_codigo() { return id_codigo; }
    public void setId_codigo(String id_codigo) { this.id_codigo = id_codigo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTipoDeUser() { return tipoDeUser; }
    public void setTipoDeUser(String tipoDeUser) { this.tipoDeUser = tipoDeUser; }

    public List<Penalidad> getPenalidades() { return penalidades; }
    public void setPenalidades(List<Penalidad> penalidades) { this.penalidades = penalidades; }

    public abstract void mostrarInfo();

    public boolean estaPenalizado() {
        return !penalidades.isEmpty();
    }
}

class Alumno extends Usuario implements IServicioPrestamos {
    private String carrera;

    public Alumno(String id_codigo, String contraseña, String nombre, String apellido, String carrera) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Alumno";
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

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
}

class Docente extends Usuario implements IServicioPrestamos {
    private String especialidad;

    public Docente(String id_codigo, String contraseña, String nombre, String apellido, String especialidad) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Docente";
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public boolean validarDisponibilidadReserva() {
        return !estaPenalizado();
    }

    public boolean solicitarReserva() {
        return validarDisponibilidadReserva();
    }

    public void cancelarReserva() {
        System.out.println("Reserva cancelada por el docente.");
    }

    public void mostrarInfo() {
        System.out.println("Docente: " + nombre + " " + apellido + ", Especialidad: " + especialidad);
    }
}

class Recepcionista extends Usuario {
    public Recepcionista(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Recepcionista";
    }

    public void registrarDevolucion() {
        System.out.println("Devolución registrada correctamente.");
    }

    public void mostrarInfo() {
        System.out.println("Recepcionista: " + nombre + " " + apellido);
    }
}

class Administrador extends Usuario implements IServiciosRecursos {
    public Administrador(String id_codigo, String contraseña, String nombre, String apellido) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDeUser = "Administrador";
    }

    public void agregarMaterial() {
        System.out.println("Material agregado.");
    }

    public void agregarRecursoTecnologico() {
        System.out.println("Recurso tecnológico agregado.");
    }

    public void gestionarRecursos() {
        System.out.println("Recursos gestionados.");
    }

    public void verificarReservas() {
        System.out.println("Reservas verificadas.");
    }

    public void generadorReporte() {
        System.out.println("Reporte generado.");
    }

    public void exportarInfo() {
        System.out.println("Información exportada.");
    }

    public void mostrarInfo() {
        System.out.println("Administrador: " + nombre + " " + apellido);
    }
}

class Penalidad {
    private Date fechaAplicacion;
    private String motivo;
    private int diasRetraso;
    private Usuario usuario;

    public Penalidad(Date fechaAplicacion, String motivo, int diasRetraso, Usuario usuario) {
        this.fechaAplicacion = fechaAplicacion;
        this.motivo = motivo;
        this.diasRetraso = diasRetraso;
        this.usuario = usuario;
    }

    public Date getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(Date fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public int getDiasRetraso() { return diasRetraso; }
    public void setDiasRetraso(int diasRetraso) { this.diasRetraso = diasRetraso; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void aplicar() {
        usuario.getPenalidades().add(this);
        System.out.println("Penalidad aplicada a " + usuario.getNombre());
    }
}
}

