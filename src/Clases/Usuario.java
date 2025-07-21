package Clases;

import Acceso_Datos.UsuarioDA;
import java.util.List;
import java.util.ArrayList;
import Interfaces.IAutenticacion;

/**
 * @author Dayanna
 */
public class Usuario implements IAutenticacion {

    protected UsuarioDA usuarioDA;

    protected String id_codigo;
    protected String contraseña;
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected int tipoDeUser;
    protected List<Penalidad> penalidades = new ArrayList<>();

    public Usuario(UsuarioDA usuarioDA) {
        this.usuarioDA = usuarioDA;
    }

    public Usuario(String id_codigo, String nombre, String apellido, String correo, int tipoDeUser) {
        this.id_codigo = id_codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tipoDeUser = tipoDeUser;
    }

    public Usuario() {
    }
    public Usuario(String id_codigo, String contraseña) {
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
    }

    public UsuarioDA getUsuarioDA() {
        return usuarioDA;
    }

    public void setUsuarioDA(UsuarioDA usuarioDA) {
        this.usuarioDA = usuarioDA;
    }

    public String getId_codigo() {
        return id_codigo;
    }

    public void setId_codigo(String id_codigo) {
        this.id_codigo = id_codigo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTipoDeUser() {
        return tipoDeUser;
    }

    public void setTipoDeUser(int tipoDeUser) {
        this.tipoDeUser = tipoDeUser;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Penalidad> getPenalidades() {
        return penalidades;
    }

    public void setPenalidades(List<Penalidad> penalidades) {
        this.penalidades = penalidades;
    }

    public void mostrarInfo() {
    }

    public boolean estaPenalizado() {
        return !penalidades.isEmpty();
    }

    @Override
    public void verificarCredenciales() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bloquearUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cerrarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
