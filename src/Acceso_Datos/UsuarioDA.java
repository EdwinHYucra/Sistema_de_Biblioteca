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
import java.time.LocalDate;
import java.time.LocalTime;
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

    public static Libro buscarLibro(int id) {
        Libro libro = null;
        String sql = "SELECT * FROM Libro WHERE id = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String genero = rs.getString("genero");
                String estado = rs.getString("estado");

                libro = new Libro(id, titulo, estado, autor, new java.util.Date(), true, titulo, genero);
            }

        } catch (SQLException e) {
            // Manejo silencioso de error
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

    public static Sala buscarSala(int id) {
        Sala sala = null;
        String sql = "SELECT * FROM Sala WHERE codigo = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int codSala = rs.getInt("codigo");
                int capacidad = rs.getInt("capacidad");
                String estado = rs.getString("estado");

                sala = new Sala(codSala, estado, capacidad);
            }

        } catch (SQLException e) {
        }

        return sala;
    }

    /*Crud Reservas*/
 /*1 insertar Reserva de Libro*/
    public int agregarReserva() {
        return 0;
    }

    public boolean eliminarReserva(String id) {
        return true;
    }

    public boolean agregarReservaLibro(String id) {
        return true;
    }

    public static RecursoTecnologico buscarRecurso(int id) {
        RecursoTecnologico recurso = null;
        String sql = "SELECT * FROM RecursoTecnologico WHERE id = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");
                String modelo = rs.getString("modelo");

                switch (tipo) {
                    case "Tablet":
                        recurso = new Tablet(id, modelo);
                        recurso.setEstado(estado);
                        break;
                    case "Computadora":
                        recurso = new Computadora(id, modelo);
                        recurso.setEstado(estado);
                        break;
                    default:
                        recurso = new RecursoTecnologico(id);
                        recurso.setEstado(estado);
                        break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar recurso: " + e.getMessage());
        }

        return recurso;
    }

    public static Reserva buscarReserva(int id) {

        String sql = "SELECT * FROM Reserva WHERE id = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int tipo = rs.getInt("tipoReserva_id");
                //String estado = rs.getString("estado");
                //LocalDate fechaReserva = rs.getDate("fecha_reserva").toLocalDate();
                //int duracion = rs.getInt("duracion");

                switch (tipo) {
                    case 1:
                        ReservaLibro rLibro = ObtenerReservaLibro(id);
                        return rLibro;

                    case 2:
                        int idSala = rs.getInt("id_sala");
                        return null;

                    case 3:
                        String idTec = rs.getString("id_tecnologico");
                        return null;

                }
            }

        } catch (SQLException e) {
            // Manejo silencioso de error
        }

        return null;
    }

    public static ReservaLibro ObtenerReservaLibro(int idReserva) {

        String sql = "SELECT * FROM ReservaLibro rl \n" + "JOIN Reserva r ON r.id = rl.reserva_id\n" + "JOIN EstadoReserva er ON er.id = r.estado_id" + "WHERE reserva_id = ?";
        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // int libro, int id, LocalDate fechaReserva, int tipoReserva, String usuario, String estado
                ReservaLibro rL = new ReservaLibro(
                        rs.getInt("ejemplar_id"),
                        rs.getInt("r.id"),
                        LocalDate.parse(rs.getString("fechaReserva")),
                        rs.getInt("tipoReserva_id"),
                        rs.getString("usuario_responsable_id"),
                        rs.getString("estado"),
                        LocalDate.parse(rs.getString("fechaDevolucion")));
                return rL;
            } else {
                return null;
            }

        } catch (SQLException e) {
            return null;
            // Manejo silencioso de error
        }
    }

    public static ReservaDeAmbiente ObtenerReservaAmbiente(int idReserva) {
        String sql = "SELECT * FROM ReservaAmbiente ra " + "JOIN Reserva r ON r.id = ra.reserva_id " + "JOIN EstadoReserva er ON er.id = r.estado_id " + "WHERE ra.reserva_id = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ReservaDeAmbiente reservaAmbiente = new ReservaDeAmbiente(
                        rs.getInt("ra.id"),
                        rs.getInt("sala_id"),
                        rs.getTimestamp("fechaHoraInicio").toLocalDateTime(),
                        rs.getTimestamp("fechaHoraFin").toLocalDateTime(),
                        rs.getInt("duracion"),
                        rs.getInt("r.id"),
                        rs.getDate("fechaReserva").toLocalDate(),
                        rs.getInt("tipoReserva_id"),
                        rs.getString("usuario_responsable_id"),
                        rs.getString("estado")
                );
                return reservaAmbiente;
            }

        } catch (SQLException e) {

        }

        return null;
    }
    


    public static ReservaRecursoTecnologico ObtenerReservaRecursoTecnologico (int idReserva){
    String sql = "SELECT * FROM ReservaRecursoTecnologico rr " +
                 "JOIN Reserva r ON r.id = rr.reserva_id " +
                 "JOIN EstadoReserva er ON er.id = r.estado_id " +
                 "WHERE rr.reserva_id = ?";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            ReservaRecursoTecnologico reserva = new ReservaRecursoTecnologico(
                    rs.getInt("rr.id"),
                    rs.getInt("recurso_id"),
                    rs.getTimestamp("fechaHoraInicio").toLocalDateTime(),
                    rs.getTimestamp("fechaHoraFin").toLocalDateTime(),
                    rs.getInt("duracion"),
                    rs.getInt("r.id"),
                    rs.getDate("fechaReserva").toLocalDate(),
                    rs.getInt("tipoReserva_id"),
                    rs.getString("usuario_responsable_id"),
                    rs.getString("estado")
            );
            return reserva;
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener reserva: " + e.getMessage());
    }

    return null;
}

    
   public void actualizarEstadoDeSala(int idSala) {
    String sql = "UPDATE Sala SET estado = 'ocupada' WHERE id = ?";

    try (Connection conn = ConexionSQLServer.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idSala);
        stmt.executeUpdate();
       
    } catch (SQLException e) {
       
    }
}

        public static void agregarPenalidad(Penalidad penalidad, String usuarioCodigo) {
        String sql = "INSERT INTO Penalidad (fechaAsignacion, fechaFinalizacion, motivo, estado, usuario_codigo)" + "VALUES (?, ?, ?, 'activa', ?)";

        try (Connection conn = ConexionSQLServer.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(penalidad.getFechaAsignacion()));
            stmt.setDate(2, java.sql.Date.valueOf(penalidad.getFechaFinalizacion()));
            stmt.setString(3, penalidad.getMotivo());
            stmt.setString(4, usuarioCodigo);

            stmt.executeUpdate();
            System.out.println("Penalidad registrada correctamente para usuario: " + usuarioCodigo);

        } catch (SQLException e) {
            System.err.println("Error al registrar penalidad: " + e.getMessage());
        }
    }
        public static void modificarEstadoEjemplar(int codigoEjemplar) {
    try (Connection conn = ConexionSQLServer.conectar()) {
        String sql = "UPDATE Libros SET estado = 'disponible' WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, codigoEjemplar);
        stmt.executeUpdate();
        
    } catch (SQLException e) {
        
    }
}
        public static void modificarEstadoRecursoTecnologico(int idRecurso) {
    try (Connection conn = ConexionSQLServer.conectar()) {
        String sql = "UPDATE RecursosTecnologicos SET estado = 'disponible' WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idRecurso);
        stmt.executeUpdate();
        System.out.println("Estado del recurso actualizado a 'disponible'.");
    } catch (SQLException e) {
        System.out.println("Error al modificar estado del recurso: " + e.getMessage());
    }
}
}

