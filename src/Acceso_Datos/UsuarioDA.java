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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ehuan
 */
public class UsuarioDA {

    private final Connection conn;

    public UsuarioDA(Connection conn) {
        this.conn = conn;
    }

    public Usuario autenticar(String codigo, String password, UsuarioDA usuarioDA) {
        Usuario usuario = null;
        String sql = "SELECT codigo, nombre, apellido, tipo_usuario_id FROM Usuario WHERE codigo=? AND contrasenia=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int tipo = rs.getInt("tipo_usuario_id");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");

                    switch (tipo) {
                        case 1:
                            usuario = new Administrador(usuarioDA, codigo, password, nombre, apellido);

                            break;
                        case 2:
                            usuario = new Alumno(usuarioDA, codigo, password, nombre, apellido);

                            break;
                        case 3:
                            usuario = new Docente(usuarioDA, codigo, password, nombre, apellido);
                            break;
                        case 4:
                            usuario = new Recepcionista(usuarioDA, codigo, password, nombre, apellido);
                            break;
                        default:
                            System.out.println("Tipo de usuario no soportado.");
                    }
                } else {
                    System.out.println("Credenciales incorrectas.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return usuario;
    }

    // Alumno
    public List<Libro> obtenerLibros() {
        List<Libro> listaLibros = new ArrayList<>();

        String sql = "SELECT libro_id, nombre, autor, fecha_publicacion, genero, idioma, ISBN, editorial, edicion FROM Libro";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("nombre"),
                        rs.getString("autor"),
                        LocalDate.parse(rs.getString("fecha_publicacion")),
                        rs.getString("genero"),
                        rs.getString("idioma"),
                        rs.getString("ISBN"),
                        rs.getString("editorial"),
                        rs.getString("edicion")
                );
                listaLibros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }

        return listaLibros;
    }

    public boolean ValidarEjemplares(int Libro_id) {

        String sql = "SELECT count()as 'Cantidad', ejemplar_id, estado from Ejemplar WHERE ESTADO = 'disponible' and libro_id = ? ";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Libro_id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int cantidad = rs.getInt("Cantidad");
                if (cantidad > 0) {
                    System.out.println("Ejemplares diaponibles");
                    return true;

                } else {
                    System.out.println("Ejemplares de libro no disponible");
                }
                return false;
            } else {
                System.out.println("EJEMPLAR NO ENCONTRADO");
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
            return false;
        }
    }

    public Libro buscarLibro(int id) {
        Libro libro = null;

        String sql = "SELECT libro_id, nombre, autor, fecha_publicacion, genero,idioma,ISBN,editorial,edicion "
                + "FROM Libro WHERE libro_id=?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("nombre"),
                        rs.getString("autor"),
                        LocalDate.parse(rs.getString("fecha_publicacion")),
                        rs.getString("genero"),
                        rs.getString("idioma"),
                        rs.getString("ISBN"),
                        rs.getString("editorial"),
                        rs.getString("edicion")
                );
                return libro;
            } else {
                System.out.println("No se encontró un libro con el código: " + id);
                return libro;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());

            return libro;
        }
    }

    public int ObtenerEjemplar(int id) {
        String sql = "Select ejemplar_id from ejemplar where libro_id = ? and estado = 'disponible'";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ejemplar_id");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return 0;
        }
    }

    public int RegistarReserva(String usuario, int tipodeReserva) {
        String sql = "INSERT INTO Reserva(fechaReserva, tipoReserva_id, estado_id, usuario_responsable_id) VALUES (date('now'), ?, 1, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tipodeReserva);
            stmt.setString(2, usuario);
            stmt.executeUpdate();

            String sql2 = "SELECT last_insert_rowid() AS 'id'";

            try (PreparedStatement stmt2 = conn.prepareStatement(sql2); ResultSet rs = stmt2.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar reserva: " + e.getMessage());
        }
        return 0;

    }

    public boolean RegistarReservadeLibro(int reservar_id, int ejemplar_id) {
        String sql = "insert into ReservaLibro(reserva_id, ejemplar_id)\n"
                + "values\n"
                + "(?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reservar_id);
            stmt.setInt(2, ejemplar_id);
            stmt.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return false;
        }

    }

    public boolean ModificarEjemplar(int id_ejemplar) {
        String sql = "UPDATE Ejemplar\n"
                + "set estado = 'no disponible'\n"
                + "where ejemplar_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_ejemplar);
            stmt.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarUsuario(String codigo_usuario) {
        Usuario usuario = null;
        String sql = "select u.codigo, u.correo, u.nombre, u.apellido, u.tipo_usuario_id,e.nombre as 'especialidad_nombre', c.nombre as 'carrera_nombre' from Usuario u \n"
                + "LEFT join Especialidad e on e.id = u.especialidad_id\n"
                + "LEFT join Carrera c on c.id = u.carrera_id\n"
                + "WHERE u.codigo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo_usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int tipo = rs.getInt("tipo_usuario_id");

                    switch (tipo) {
                        case 1:
                            Administrador uadmin = new Administrador(
                                    rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    tipo
                            );

                            return uadmin;

                        case 2:

                            Alumno ualunno = new Alumno(
                                    rs.getString("carrera_nombre"),
                                    rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    tipo);

                            return ualunno;
                        case 3:
                            Docente udocente = new Docente(
                                    rs.getString("especialidad_nombre"),
                                    rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    tipo);
                            return udocente;
                        case 4:
                            Recepcionista urecepcionista = new Recepcionista(
                                    rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    tipo);
                            return urecepcionista;
                        default:
                            System.out.println("Tipo de usuario no soportado.");
                            return null;
                    }
                } else {
                    System.out.println("Usuario no encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Usuario no encontrado: " + e.getMessage());
            return null;
        }
    }

    public Ejemplar buscarEjemplar(int id) {
        String sql = "Select ejemplar_id, libro_id, estado from ejemplar where ejemplar_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Ejemplar ej = new Ejemplar(
                        rs.getInt("ejemplar_id"),
                        rs.getInt("libro_id"),
                        rs.getString("estado"));
                return ej;
            } else {
                System.out.println("No se encontro el Ejemplar:");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return null;
        }
    }

    public ReservaLibro BuscarReservaLibro(int reserva_id) {
        ReservaLibro rl = null;
        String sql = "Select rl.reserva_id, r.fechaReserva,er.estado as 'EstadoReserva',\n"
                + "r.usuario_responsable_id, \n"
                + "l.libro_id,\n"
                + "e.ejemplar_id from ReservaLibro rl \n"
                + "join Reserva r on r.id = rl.reserva_id \n"
                + "join EstadoReserva er on er.id = r.estado_id\n"
                + "join Ejemplar e on e.ejemplar_id = rl.ejemplar_id\n"
                + "join Libro l on l.libro_id = e.libro_id\n"
                + "where rl.reserva_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reserva_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                rl = new ReservaLibro(
                        reserva_id,
                        LocalDate.parse(rs.getString("fechaReserva")),
                        rs.getString("EstadoReserva"),
                        buscarUsuario(rs.getString("usuario_responsable_id")));

                rl.setLibro(buscarLibro(rs.getInt("libro_id")));
                rl.setEjemplar(buscarEjemplar(rs.getInt("ejemplar_id")));

                return rl;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar Reserva Libro: " + e.getMessage());
            return null;
        }
    }

    public boolean validarDisponibilidadRecursoTec(String tipo) {
        String sql = "SELECT count()as 'Cantidad' from RecursoTecnologico where tipo = ? and estado = 'operativo'";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            int cantidad = rs.getInt("Cantidad");

            if (cantidad > 0) {
                return true;
            } else {
                System.out.println("No hay tablets disponibles, intente mas tarde.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar Tablets: " + e.getMessage());
            return false;
        }
    }

    public int obtenerRecursoTecID(String tipo) {
        String sql = "SELECT codigo, tipo, estado from RecursoTecnologico where tipo = ? and estado = 'operativo' order by codigo asc";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el recurso: " + e.getMessage());
            return 0;
        }
    }

    public RecursoTecnologico buscarRecursoTecnologico(int id_recurso_tec) {
        RecursoTecnologico rtec = null;
        String sql = "SELECT codigo, tipo, estado from RecursoTecnologico where codigo = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_recurso_tec);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rtec = new RecursoTecnologico(
                        rs.getInt("codigo"),
                        rs.getString("tipo"),
                        rs.getString("estado")
                );
                return rtec;
            } else {
                System.out.println("No se encontro el recurso");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el recurso: " + e.getMessage());
            return null;
        }
    }

    public boolean RegistarRecursoTecnologico(int id_reserva, int id_recurs, int duracion) {

        String sql = "INSERT into ReservaRecursoTecnologico (reserva_id,recurso_id,duracionHoras) values (?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_reserva);
            stmt.setInt(2, id_recurs);
            stmt.setInt(3, duracion);

            stmt.execute();

            System.out.println("Se reservo correctamente el Recurso");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al reservar el Recurso: " + e.getMessage());
            return false;
        }
    }

    public ReservaRecursoTecnologico BuscarReservaRecursoTecnologico(int id_reserva) {
        ReservaRecursoTecnologico rrt = null;
        String sql = "Select rrt.id as 'cod_reservaRTec',\n"
                + " rrt.reserva_id as 'cod_reserva_id',\n"
                + " rrt.recurso_id,\n"
                + " rrt.duracionHoras,\n"
                + "r.fechaReserva,\n"
                + "er.estado,\n"
                + "r.usuario_responsable_id \n"
                + "from ReservaRecursoTecnologico rrt\n"
                + "join Reserva r on r.id = rrt.reserva_id\n"
                + "join EstadoReserva er on er.id = r.estado_id\n"
                + "where rrt.reserva_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_reserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                rrt = new ReservaRecursoTecnologico(
                        rs.getInt("cod_reservaRTec"),
                        rs.getInt("duracionHoras"),
                        buscarRecursoTecnologico(rs.getInt("recurso_id")),
                        rs.getInt("cod_reserva_id"),
                        LocalDate.parse(rs.getString("fechaReserva")),
                        rs.getString("estado"),
                        buscarUsuario(rs.getString("usuario_responsable_id")));

                return rrt;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar Reserva de Recurso Tecgnologico: " + e.getMessage());
            return null;
        }
    }

    public boolean RegistarReservaAmbiente(int id_reserva, List<Alumno> lista, int id_sala, int duracion) {

        String sql = "insert into ReservaAmbiente (reserva_id,sala_codigo,duracionHoras) VALUES}(?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_reserva);
            stmt.setInt(2, id_sala);
            stmt.setInt(3, duracion);

            stmt.execute();

            System.out.println("Se reservo correctamente el ambiente");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al reservar el ambiente: " + e.getMessage());
            return false;
        }

    }

    public boolean validarDisponibilidaSala(int opcionReserva) {

        String sql = "Select count() as 'cantidad' from Sala where estado = 'disponible' and capacidad >= ?";
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, opcionReserva);
            ResultSet rs = stmt.executeQuery();

            int cantidad = rs.getInt("cantidad");

            if (cantidad > 0) {
                return true;
            } else {
                System.out.println("No hay salas disponibles, intente mas tarde.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar salas: " + e.getMessage());
            return false;
        }
    }

    public int obtenerSala() {
        String sql = "Select codigo, nombresala,capacidad, estado from Sala where estado = 'disponible' and capacidad >= 5 order by capacidad asc";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener la sala: " + e.getMessage());
            return 0;
        }
    }

    public Sala BuscarSala(int id_sala) {
        String sql = "select codigo, nombresala, capacidad,estado from Sala where codigo = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_sala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sala sala = new Sala(
                        rs.getInt("codigo"),
                        rs.getString("nombresala"),
                        rs.getInt("capacidad"),
                        rs.getString("estado"));
                return sala;
            } else {
                System.out.println("No se encontro la sala:");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return null;
        }
    }

    public void agregarListaReservaAmbiente(int res_id ,String codigo){

        String sql = "Insert into DetalleReservaAmbienteAlumnos (reserva_ambiente_id,usuario_id) values (?,?) ";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, res_id);
            stmt.setString(2, codigo);
            stmt.execute();
            

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
        }
    }
    public ReservaDeAmbiente BuscarReservaAmbiente(int id_reserva) {
        ReservaDeAmbiente ra = null;
        String sql = "Select ra.id as 'cod_reservaAmb',\n"
                + "                ra.reserva_id as 'cod_reserva_id',\n"
                + "                ra.sala_codigo,\n"
                + "                ra.duracionHoras,\n"
                + "                r.fechaReserva,\n"
                + "                er.estado,\n"
                + "                r.usuario_responsable_id\n"
                + "                from ReservaAmbiente ra\n"
                + "                join Reserva r on r.id = ra.reserva_id\n"
                + "                join EstadoReserva er on er.id = r.estado_id\n"
                + "                where ra.reserva_id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_reserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                ra = new ReservaDeAmbiente(
                        rs.getString("cod_reservaAmb"),
                        rs.getInt("duracionHoras"),
                        BuscarSala(rs.getInt("sala_codigo")),
                        rs.getInt("cod_reserva_id"),
                        LocalDate.parse(rs.getString("fechaReserva")),
                        rs.getString("estado"),
                        buscarUsuario(rs.getString("usuario_responsable_id")));

                return ra;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar Reserva de Recurso Tecgnologico: " + e.getMessage());
            return null;
        }

    }

    //  
//Docente
    //Recepcionista
    public Reserva buscarReserva(int idReserva) {

        String sql = "Select id, fechaReserva, tipoReserva_id, estado_id,usuario_responsable_id"
                + "From Reserva where id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idReserva);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                switch (rs.getInt("tipoReserva_id")) {
                    case 1:
                        return BuscarReservaLibro(idReserva);

                    case 2:

                        break;
                    case 3:
                        break;
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar Reserva Libro: " + e.getMessage());
            return null;
        }

        System.out.println("No se encontró ninguna reserva con el ID: " + idReserva);
        return null;
    }

    // Este es el método interno para buscar un solo ReservaRecursoTecnologico por ID
    /*private static ReservaRecursoTecnologico buscarReservaRecursoTecnologico(int reservaId) {
        ReservaRecursoTecnologico reservaRecTec = null;
        String sql = "SELECT RT.recurso_id, RT.fechaHoraInicio, RT.fechaHoraFin, RT.duracionHoras, "
                + "R.id, R.fechaReserva, R.usuario_responsable_id, "
                + "ER.estado, TR.tipo_reserva "
                + "FROM Reserva R "
                + "JOIN ReservaRecursoTecnologico RT ON R.id = RT.reserva_id "
                + "JOIN EstadoReserva ER ON R.estado_id = ER.id "
                + "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id "
                + "WHERE R.id = ? AND TR.tipo_reserva = 'Tecnológico'";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    }*/

 /*// Este es el método interno para buscar un solo ReservaDeAmbiente por ID
    private static ReservaDeAmbiente buscarReservaAmbiente(int reservaId) {
        ReservaDeAmbiente reservaAmb = null;
        String sql = "SELECT RA.sala_codigo, RA.fechaHoraInicio, RA.fechaHoraFin, RA.duracionHoras, "
                + "R.id, R.fechaReserva, R.usuario_responsable_id, "
                + "ER.estado, TR.tipo_reserva "
                + "FROM Reserva R "
                + "JOIN ReservaAmbiente RA ON R.id = RA.reserva_id "
                + "JOIN EstadoReserva ER ON R.estado_id = ER.id "
                + "JOIN TipoReserva TR ON R.tipoReserva_id = TR.id "
                + "WHERE R.id = ? AND TR.tipo_reserva = 'Ambiente'";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    }*/
}
