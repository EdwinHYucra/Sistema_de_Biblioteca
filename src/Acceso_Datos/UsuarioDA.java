/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso_Datos;

import Acceso_Datos.ConexionBD;
import Clases.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
        

        String sql = "SELECT codigo, nombre, apellido, tipo_usuario_id FROM Usuario WHERE codigo=? AND contrasenia=?"; //AGREGAR

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int tipo = rs.getInt("tipo_usuario_id");

                String nombre = rs.getString("nombre");     
                String apellido = rs.getString("apellido");

                // factoría polimórfica
                switch (tipo) {
                    case 2:
                        usuario = new Alumno(codigo, password, nombre, apellido);
                        break;
                    case 3:
                        usuario = new Docente(codigo, password, nombre, apellido);
                        break;
                    case 1:
                        usuario = new Administrador(codigo, password, nombre, apellido);
                        break;
                    case 4:
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

       public static Usuario BuscarUsuario(String id) {
        Usuario usuario = null;
        String sql = "SELECT codigo, correo, contrasenia, nombre, apellido, tipo_usuario_id " + "FROM Usuario WHERE codigo = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getString("codigo"),
                        rs.getString("contrasenia")
                );
       
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTipoDeUser(rs.getInt("tipo_usuario_id"));
                
            } else {
                
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return usuario;
    }

    // Método para buscar un Libro por ID 
    public static Libro buscarLibro(int id) {
        Libro libro = null;
        String sql = "SELECT libro_id, nombre, autor, fecha_publicacion, genero, idioma, ISBN, editorial, edicion" + " FROM Libro WHERE libro_id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                libro = new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("nombre"), 
                        "Disponible",
                        rs.getString("autor"),
                        rs.getDate("fecha_publicacion"), 
                        true,
                        rs.getString("nombre"), 
                        rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return libro;
    }

    // Método para buscar una Sala por ID 
    public static Sala buscarSala(int id) {
        Sala sala = null;
 
        String sql = "SELECT codigo, nombresala, capacidad FROM Sala WHERE codigo = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sala = new Sala(
                        rs.getInt("codigo"),
                        rs.getString("nombresala"), 
                        rs.getInt("capacidad")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar sala: " + e.getMessage());
        }
        return sala;
    }

    public static RecursoTecnologico buscarRecursoTecnologico(int id) {
        RecursoTecnologico recurso = null;
     
        String sql = "SELECT RT.codigo, RT.tipo, " +
                     "T.modelo, T.sistemaOperativo AS tablet_so, " +
                     "C.ram, C.procesador, C.sistemaOperativo AS compu_so, C.estado AS compu_estado " +
                     "FROM RecursoTecnologico RT " +
                     "LEFT JOIN Tablet T ON RT.codigo = T.codigo " +
                     "LEFT JOIN Computadora C ON RT.codigo = C.codigo " +
                     "WHERE RT.codigo = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipoRecurso = rs.getString("tipo"); // Esto será 'Tablet', 'Computadora' u otro tipo
                int codigoRecurso = rs.getInt("codigo");

                if ("Tablet".equalsIgnoreCase(tipoRecurso)) {
                    String modelo = rs.getString("modelo");
                
                    recurso = new Tablet(codigoRecurso, modelo);
                } else if ("Computadora".equalsIgnoreCase(tipoRecurso)) {
                   
                    String ram = rs.getString("ram");
                    String procesador = rs.getString("procesador");
                    String sistemaOperativoCompu = rs.getString("compu_so");
                    boolean estadoDesbloqueada = rs.getBoolean("compu_desbloqueada");
                    recurso = new Computadora(codigoRecurso, ram, procesador, sistemaOperativoCompu, estadoDesbloqueada);
                } else {
                    
                    recurso = new RecursoTecnologico(codigoRecurso);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar recurso tecnológico: " + e.getMessage());
        }
        return recurso;
    }

    // --- Métodos de Obtención de Listas de Reservas (para generarReporte) ---

    // get`
    public static List<ReservaLibro> getReservasLibro() {
        List<ReservaLibro> lista = new ArrayList<>();
        String sql = "SELECT R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado , TR.tipo_reserva , RL.libro_id " +
                     "FROM Reserva R " +
                     "JOIN ReservaLibro RL ON R.id = RL.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE TR.tipo_reserva = 'Libro'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));
                Libro libro = buscarLibro(rs.getInt("libro_id"));

                LocalDate fechaDevolucion = null; // Si no hay fecha de devolución en DB para la obtención general
                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));
                LocalTime horaRes = LocalTime.of(0, 0); // Si no hay hora específica en DB para ReservaLibro
                double duracion = 0.0; // Si no hay duración específica

                if (usuario != null && libro != null) { // Solo si encontramos el usuario y el libro relacionados
                    ReservaLibro res = new ReservaLibro(
                            fechaDevolucion, // Podría venir de DB si lo guardas
                            libro,
                            rs.getString("estado"),
                            fechaRes,
                            duracion,
                            horaRes,
                            usuario
                    );
                    // Si ReservaLibro tiene un setter para el ID, podrías hacer: res.setId(rs.getInt("id"));
                    lista.add(res);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas de libro: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error de formato de fecha/hora al obtener reservas de libro: " + e.getMessage());
        }
        return lista;
    }

   //gets
    public static List<ReservaDeAmbiente> getReservasAmbiente() {
        List<ReservaDeAmbiente> lista = new ArrayList<>();
        String sql = "SELECT R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado , TR.tipo_reserva , RA.sala_codigo, " +
                     "RA.fechaHoraInicio, RA.fechaHoraFin, RA.duracionHoras " +
                     "FROM Reserva R " +
                     "JOIN ReservaAmbiente RA ON R.id = RA.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE TR.tipo_reserva = 'Ambiente'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));
                Sala sala = buscarSala(rs.getInt("sala_codigo"));

                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));

                LocalDateTime fechaHoraInicio = LocalDateTime.parse(rs.getString("fechaHoraInicio").replace(" ", "T"));
                LocalTime horaInicio = fechaHoraInicio.toLocalTime();
                LocalDateTime fechaHoraFin = LocalDateTime.parse(rs.getString("fechaHoraFin").replace(" ", "T"));
                double duracion = rs.getDouble("duracionHoras");
                int tiempoEnMinutos = (int) (duracion * 60);

                if (usuario != null && sala != null) { 
                    ReservaDeAmbiente res = new ReservaDeAmbiente(
                            rs.getInt("id"),
                            tiempoEnMinutos,
                            sala,
                            fechaRes,
                            duracion,
                            horaInicio,
                            usuario
                    );
                    res.setEstado(rs.getString("estado"));
                    res.setFechaHoraInicio(fechaHoraInicio);
                    res.setFechaHoraFin(fechaHoraFin);
                    lista.add(res);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas de ambiente: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error de formato de fecha/hora al obtener reservas de ambiente: " + e.getMessage());
        }
        return lista;
    }


    public static List<ReservaRecursoTecnologico> getReservasTecnologicas() {
        List<ReservaRecursoTecnologico> lista = new ArrayList<>();
        String sql = "SELECT R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado , TR.tipo_reserva, RT.recurso_id, " +
                     "RT.fechaHoraInicio, RT.fechaHoraFin, RT.duracionHoras " +
                     "FROM Reserva R " +
                     "JOIN ReservaRecursoTecnologico RT ON R.id = RT.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE TR.tipo_reserva = 'Tecnológico'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));

                RecursoTecnologico recursoTec = buscarRecursoTecnologico(rs.getInt("recurso_id"));

                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));
                LocalDateTime fechaHoraInicio = LocalDateTime.parse(rs.getString("fechaHoraInicio").replace(" ", "T"));
                LocalTime horaInicio = fechaHoraInicio.toLocalTime();
                LocalDateTime fechaHoraFin = LocalDateTime.parse(rs.getString("fechaHoraFin").replace(" ", "T"));
                double duracion = rs.getDouble("duracionHoras");

                if (usuario != null && recursoTec != null) { 
                    ReservaRecursoTecnologico res = new ReservaRecursoTecnologico(
                            "RecursoTecnologico", 
                            rs.getInt("id"),      
                            recursoTec,
                            fechaRes,
                            duracion,
                            horaInicio,
                            usuario
                    );
                    res.setEstado(rs.getString("estado"));
                    res.setFechaHoraInicio(fechaHoraInicio);
                    res.setFechaHoraFin(fechaHoraFin);
                    lista.add(res);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reservas tecnológicas: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Error de formato de fecha/hora al obtener reservas tecnológicas: " + e.getMessage());
        }
        return lista;
    }

    // --- Métodos en Validar Reserva ---

    // Método general para buscar cualquier tipo de Reserva por su ID (estático)
    public static Reserva buscarReserva(int idReserva) {
        ReservaLibro reservaLibro = buscarReservaLibro(idReserva);
        if (reservaLibro != null) {
            return reservaLibro;
        }

        ReservaRecursoTecnologico reservaRecTec = buscarReservaRecursoTecnologico(idReserva);
        if (reservaRecTec != null) {
            return reservaRecTec;
        }

        ReservaDeAmbiente reservaAmb = buscarReservaAmbiente(idReserva);
        if (reservaAmb != null) {
            return reservaAmb;
        }

        System.out.println("No se encontró ninguna reserva con el ID: " + idReserva);
        return null;
    }

    // Método para modificar el estado de una Reserva (estático)
    public static boolean modificarEstadoReserva(int idReserva, String nuevoEstadoNombre) {
        String sqlEstadoId = "SELECT id FROM EstadoReserva WHERE nombre_estado = ?";
        String sqlUpdateReserva = "UPDATE Reserva SET estado_id = ? WHERE id = ?";
        boolean exito = false;

        try (Connection conn = ConexionBD.conectar()) {
            conn.setAutoCommit(false); 


            int nuevoEstadoId = -1;
            try (PreparedStatement pstmtEstado = conn.prepareStatement(sqlEstadoId)) {
                pstmtEstado.setString(1, nuevoEstadoNombre);
                ResultSet rs = pstmtEstado.executeQuery();
                if (rs.next()) {
                    nuevoEstadoId = rs.getInt("id");
                } else {
                    System.out.println("Error: El estado '" + nuevoEstadoNombre + "' no es válido o no existe.");
                    conn.rollback();
                    return false;
                }
            }


            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateReserva)) {
                pstmtUpdate.setInt(1, nuevoEstadoId);
                pstmtUpdate.setInt(2, idReserva);
                int filasAfectadas = pstmtUpdate.executeUpdate();

                if (filasAfectadas > 0) {
                    conn.commit();
                    exito = true;
                    System.out.println("Estado de la reserva " + idReserva + " actualizado a '" + nuevoEstadoNombre + "'.");
                } else {
                    conn.rollback();
                    System.out.println("No se pudo actualizar el estado de la reserva " + idReserva + ". Puede que no exista.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al modificar el estado de la reserva: " + e.getMessage());
            try (Connection connRollback = ConexionBD.conectar()) {
                if (connRollback != null) {
                    connRollback.rollback();
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error al intentar rollback después de un fallo: " + rollbackEx.getMessage());
            }
        }
        return exito;
    }

    // metodos auxi para buscar reserva

    // Este es el método interno para buscar un solo ReservaLibro por ID
    private static ReservaLibro buscarReservaLibro(int reservaId) {
        ReservaLibro reservaLibro = null;
        String sql = "SELECT RL.libro_id, R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado, TR.tipo_reserva " +
                     "FROM Reserva R " +
                     "JOIN ReservaLibro RL ON R.id = RL.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE R.id = ? AND TR.tipo_reserva = 'Libro'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));
                Libro libro = buscarLibro(rs.getInt("libro_id"));

                LocalDate fechaDevolucion = null;
                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));
                LocalTime horaRes = LocalTime.of(0, 0);
                double duracion = 0.0;

                if (usuario != null && libro != null) {
                    reservaLibro = new ReservaLibro(
                            fechaDevolucion,
                            libro,
                            rs.getString("estado_nombre"),
                            fechaRes,
                            duracion,
                            horaRes,
                            usuario
                    );

                }
            }
        } catch (SQLException | DateTimeParseException e) {

        }
        return reservaLibro;
    }

    // Este es el método interno para buscar un solo ReservaRecursoTecnologico por ID
    private static ReservaRecursoTecnologico buscarReservaRecursoTecnologico(int reservaId) {
        ReservaRecursoTecnologico reservaRecTec = null;
        String sql = "SELECT RT.recurso_id, RT.fechaHoraInicio, RT.fechaHoraFin, RT.duracionHoras, " +
                     "R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado, TR.tipo_reserva " +
                     "FROM Reserva R " +
                     "JOIN ReservaRecursoTecnologico RT ON R.id = RT.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE R.id = ? AND TR.tipo_reserva = 'Tecnológico'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));
                RecursoTecnologico recursoTec = buscarRecursoTecnologico(rs.getInt("recurso_id")); // Usa el método mejorado

                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));
                LocalDateTime fechaHoraInicio = LocalDateTime.parse(rs.getString("fechaHoraInicio").replace(" ", "T"));
                LocalTime horaInicio = fechaHoraInicio.toLocalTime();
                LocalDateTime fechaHoraFin = LocalDateTime.parse(rs.getString("fechaHoraFin").replace(" ", "T"));
                double duracion = rs.getDouble("duracionHoras");

                if (usuario != null && recursoTec != null) {
                    reservaRecTec = new ReservaRecursoTecnologico(
   
                            "RecursoTecnologico",
                            rs.getInt("id"),
                            recursoTec,
                            fechaRes,
                            duracion,
                            horaInicio,
                            usuario
                    );
                    reservaRecTec.setEstado(rs.getString("estado_nombre"));
                    reservaRecTec.setFechaHoraInicio(fechaHoraInicio);
                    reservaRecTec.setFechaHoraFin(fechaHoraFin);
                }
            }
        } catch (SQLException | DateTimeParseException e) {

        }
        return reservaRecTec;
    }

    // Este es el método interno para buscar un solo ReservaDeAmbiente por ID
    private static ReservaDeAmbiente buscarReservaAmbiente(int reservaId) {
        ReservaDeAmbiente reservaAmb = null;
        String sql = "SELECT RA.sala_codigo, RA.fechaHoraInicio, RA.fechaHoraFin, RA.duracionHoras, " +
                     "R.id, R.fechaReserva, R.usuario_responsable_id, " +
                     "ER.estado, TR.tipo_reserva " +
                     "FROM Reserva R " +
                     "JOIN ReservaAmbiente RA ON R.id = RA.reserva_id " +
                     "JOIN EstadoReserva ER ON R.estado_id = ER.id " +
                     "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id " +
                     "WHERE R.id = ? AND TR.tipo_reserva = 'Ambiente'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = BuscarUsuario(rs.getString("usuario_responsable_id"));
                Sala sala = buscarSala(rs.getInt("sala_codigo"));

                LocalDate fechaRes = LocalDate.parse(rs.getString("fechaReserva"));
                LocalDateTime fechaHoraInicio = LocalDateTime.parse(rs.getString("fechaHoraInicio").replace(" ", "T"));
                LocalTime horaInicio = fechaHoraInicio.toLocalTime();
                LocalDateTime fechaHoraFin = LocalDateTime.parse(rs.getString("fechaHoraFin").replace(" ", "T"));
                double duracion = rs.getDouble("duracionHoras");
                int tiempoEnMinutos = (int) (duracion * 60);

                if (usuario != null && sala != null) {
                    reservaAmb = new ReservaDeAmbiente(
                            rs.getInt("id"),
                            tiempoEnMinutos,
                            sala,
                            fechaRes,
                            duracion,
                            horaInicio,
                            usuario
                    );
                    reservaAmb.setEstado(rs.getString("estado_nombre"));
                    reservaAmb.setFechaHoraInicio(fechaHoraInicio);
                    reservaAmb.setFechaHoraFin(fechaHoraFin);
                }
            }
        } catch (SQLException | DateTimeParseException e) {
        }
        return reservaAmb;
    }

    /*Crud Sala*/
    //1 Crud Sala
    public boolean agregarSala() {
        return true;
    }

    public boolean eliminarSala(String id) {
        return true;
    }
    public Sala buscarSala(String id) {
        Sala sala = null;

        // consulta para buscar los libro
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

    // RUC - DOCENTE
    // Buscar un docente por código
    public Docente buscarDocente(String codigoDocente) {
        Docente docente = null;

        String sql = "SELECT codigo, nombre, apellido, contrasenia, especialidad FROM Usuario WHERE tipo_usuario='Docente' AND codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoDocente);

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar docente: " + e.getMessage());
        }

        return false;
    }

}
