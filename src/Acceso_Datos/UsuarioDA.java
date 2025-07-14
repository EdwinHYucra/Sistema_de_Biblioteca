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
    List<Libro> listaLibros = new ArrayList<>();

    String sql = "SELECT codigo, nombre, estado, autor, fechaPublicacion, disponibilidad, titulo, genero FROM Libro";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Libro libro = new Libro(
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getString("estado"),
                rs.getString("autor"),
                rs.getDate("fechaPublicacion"),
                rs.getBoolean("disponibilidad"),
                rs.getString("titulo"),
                rs.getString("genero")
            );
            listaLibros.add(libro);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener libros: " + e.getMessage());
    }

    return listaLibros;
}


  public Libro buscarLibro(String id) {
    Libro libro = null;

    String sql = "SELECT codigo, nombre, estado, autor, fechaPublicacion, disponibilidad, titulo, genero "
               + "FROM Libro WHERE codigo=?";

    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            libro = new Libro(
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getString("estado"),
                rs.getString("autor"),
                rs.getDate("fechaPublicacion"),
                rs.getBoolean("disponibilidad"),
                rs.getString("titulo"),
                rs.getString("genero")
            );
        } else {
            System.out.println("No se encontró un libro con el código: " + id);
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar libro: " + e.getMessage());
    }

    return libro;
}


    //2 Agregar Libro
    public boolean agregarLibro()/* <-- Todos los parametros*/ {
        return true;
    }

    //3 Modificar Libro
    public boolean modificarLibro()/* <-- Todos los parametros*/ {
        return true;
    }

    //4 Eliminar Libro
    public boolean eliminarLibro(String id) {
        return true;
    }

    /*Crud Recurso Tecnologico*/
    //1 insetar Recurso Tecnologico
    public boolean agregarTablet() {
        return true;
    }

    public boolean agregarComputadora() {
        return true;
    }

    //2 Eliminar Recurso Tecnologico
    public boolean eliminarTablet(String id) {
        return true;
    }

    public boolean eliminarComputadora(String id) {
        return true;
    }

    //consultar Recurso tecnologico
    public List<Tablet> obtenerTablets() {
        List<Tablet> listaTablets = new ArrayList();

        // consulta para buscar los libro
        return listaTablets;
    }

    public Tablet buscarTablet(String id) {
        Tablet Tablet = null;

        // consulta para buscar los libro
        return Tablet;
    }

    public List<Computadora> obtenerComputadoras() {
        List<Computadora> listaComputadoras = new ArrayList();

        // consulta para buscar los libro
        return listaComputadoras;
    }

    public Computadora buscarComputadora(String id) {
        Computadora computadora = null;

        // consulta para buscar los libro
        return computadora;
    }

    /*Crud Sala*/
    //1 Crud Sala
    public boolean agregarSala() {
        return true;
    }

    public boolean eliminarSala(String id) {
        return true;
    }
    public List<Sala> obtenerSalas() {
        List<Sala> listaSala = new ArrayList();

        // consulta para buscar los libro
        return listaSala;
    }

    public Sala buscarSala(String id) {
        Sala sala = null;

        // consulta para buscar los libro
        return sala;
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
    
    // RUC - DOCENTE
    
    // Buscar un docente por código
    
   public Docente buscarDocente(String codigoDocente) {
    Docente docente = null;

    String sql = "SELECT codigo, nombre, apellido, contrasenia, especialidad FROM Usuario WHERE tipo_usuario='Docente' AND codigo=?";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, codigoDocente);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String codigo = rs.getString("codigo");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String contrasenia = rs.getString("contrasenia");
            String especialidad = rs.getString("especialidad");

            docente = new Docente(codigo, contrasenia, nombre, apellido, especialidad);
        } else {
            System.out.println("No se encontró un docente con el código: " + codigoDocente);
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar docente: " + e.getMessage());
    }

    return docente;
}
 
   // Agregar un docente
   
   public boolean agregarDocente(Docente docente) {
    String sql = "INSERT INTO Usuario (codigo, nombre, apellido, contrasenia, tipo_usuario, especialidad) VALUES (?, ?, ?, ?, 'Docente', ?)";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, docente.getId_codigo());
        stmt.setString(2, docente.getNombre());
        stmt.setString(3, docente.getApellido());
        stmt.setString(4, docente.getContraseña());
        stmt.setString(5, docente.getEspecialidad());

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.out.println("Error al agregar docente: " + e.getMessage());
    }

    return false;
}
 
    // Modificar un docente
   
    public boolean modificarDocente(Docente docente) {
    String sql = "UPDATE Usuario SET nombre=?, apellido=?, contrasenia=?, especialidad=? WHERE codigo=? AND tipo_usuario='Docente'";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, docente.getNombre());
        stmt.setString(2, docente.getApellido());
        stmt.setString(3, docente.getContraseña());
        stmt.setString(4, docente.getEspecialidad());
        stmt.setString(5, docente.getId_codigo());

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.out.println("Error al modificar docente: " + e.getMessage());
    }

    return false;
}

    // Eliminar un docente
    
    public boolean eliminarDocente(String codigoDocente) {
    String sql = "DELETE FROM Usuario WHERE codigo=? AND tipo_usuario='Docente'";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, codigoDocente);

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.out.println("Error al eliminar docente: " + e.getMessage());
    }

    return false;
}

    
    
    
    
}


