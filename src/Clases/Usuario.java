package Clases;

import java.util.List;
import java.util.ArrayList;


/**
 * @author Dayanna
 */
public abstract class Usuario {
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




