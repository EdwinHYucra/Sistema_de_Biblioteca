/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso_Datos;

import Clases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehuan
 */
public class UsuarioDA {

    //Login
    public static Usuario autenticar(String codigo, String password) {
        Usuario usuario = null;

        String sql = "SELECT codigo, nombre, apellido, tipo_usuario FROM Usuario WHERE codigo=? AND contrasenia=?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String tipo = rs.getString("tipo_usuario");

                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                // factoría polimórfica
                switch (tipo) {
                    case "Alumno":
                        usuario = new Alumno(codigo, password, nombre, apellido);
                        break;
                    case "Docente":
                        usuario = new Docente(codigo, password, nombre, apellido);
                        break;
                    case "Administrador":
                        usuario = new Administrador(codigo, password, nombre, apellido);
                        break;
                    case "Recepcionista":
                        usuario = new Recepcionista(codigo, password, nombre, apellido);
                        break;
                    default:
                        System.out.println("Tipo de usuario no soportado.");
                }
            } else {
                System.out.println("Credenciales incorrectas.");
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return usuario;
    }

    //Crud Libro
    // 1 Consultar
    public List<Libro> obtenerLibros() {
        List<Libro> listalibros = new ArrayList();

        // consulta para buscar los libro
        return listalibros;
    }

    public static Libro buscarLibro(String id) { //busca el objeto... c: libro
        Libro libro = null; //instancia

        // consulta para buscar los libro
        return libro;
    }

    //2 Agregar Libro
    public static boolean agregarLibro(Libro libro){
        /*try (Connection conn = ConexionSQLServer.conectar();*/ //llama al sql, ya no es conectar 
        return true;
    }

    //3 Modificar Libro
    public static boolean editarLibro(Libro libro){
        return true;
    }
    
    public static boolean actualizarLibro(Libro libro){
        return true;
    }

    //4 Eliminar Libro
    public static boolean eliminarLibro(Libro libro) {
        return true;
    }

    /*Crud Recurso Tecnologico*/
    //1 insetar Recurso Tecnologico
    
    /*****AGREGAR ARCHIVOS, EDITAR Y ELIMINAR*****/
    public static Tablet buscarTablet(String id) { //busca el objeto... c: libro
        Tablet tablet = null; //instancia

        // consulta para buscar los libro
        return tablet;
    }
    public static boolean agregarTablet(Tablet tablet) {
        return true;
    }
    
    public static boolean editarTablet(Tablet tablet) {
        return true;
    }
    
    public static boolean actualizarTablet(Tablet tablet){
        return true;
    }
    
    public static boolean eliminarTablet(Tablet tablet) {
        return true;
    }
    
    public static Computadora buscarComputadora(String id) { //busca el objeto... c: libro
        Computadora computadora = null; //instancia

        // consulta para buscar los libro
        return computadora;
    }

    public static boolean agregarComputadora(Computadora computadora) {
        return true;
    }

    //2 Eliminar Recurso Tecnologico
    public static boolean editarComputadora(Computadora computadora) {
        return true;
    }
    
    public static boolean actualizarComputadora(Computadora computadora){
        return true;
    }

    public static boolean eliminarComputadora(Computadora computadora) {
        return true;
    }

    //consultar Recurso tecnologico
    public List<Tablet> obtenerTablets() {
        List<Tablet> listaTablets = new ArrayList();

        // consulta para buscar los libro
        return listaTablets;
    }

    public List<Computadora> obtenerComputadoras() {
        List<Computadora> listaComputadoras = new ArrayList();

        // consulta para buscar los libro
        return listaComputadoras;
    }


    /*Crud Sala*/
    //1 Crud Sala
    public static boolean agregarSala(Sala sala) {
        return true;
    }
    
    public static Sala buscarSala(String id) {
        Sala sala = null;

        // consulta para buscar los libro
        return sala;
    }
    public static boolean editarSala(Sala sala) {
        return true;
    }
    
    public static boolean actualizarSala(Sala sala){
        return true;
    }
    
    public static boolean eliminarSala(Sala sala) {
        return true;
    }
    public List<Sala> obtenerSalas() {
        List<Sala> listaSala = new ArrayList();

        // consulta para buscar los libro
        return listaSala;
    }



    /*Crud Reservas*/
    /*1 insertar Reserva de Libro*/
    public int agregarReserva(){
        return 0;
    }
    public boolean eliminarReserva(String id){
        return true;
    }
    public boolean agregarReservaLibro(String id){
        return true;
    }
    
}
