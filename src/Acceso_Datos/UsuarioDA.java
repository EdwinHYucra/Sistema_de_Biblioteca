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

        String sql = "SELECT codigo, nombre, apellido, tipo_usuario_id FROM Usuario WHERE codigo=? AND contrasenia=?";

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

    //Crud Libro
    // 1 Consultar
    public List<Libro> obtenerLibros() {
        List<Libro> listaLibros = new ArrayList<>();

        String sql = "SELECT codigo, nombre, autor, fechaPublicacion, genero, idioma, ISBN, editorail, edicion FROM Libro";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("autor"),
                        rs.getDate("fechaPublicacion").toLocalDate(),
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

    public static Libro buscarLibro(int id) {
        Libro libro = null;

        String sql = "SELECT codigo, nombre, autor, fechaPublicacion, genero, idioma, ISBN, editorial, edicion "
                + "FROM Libro WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("autor"),
                        rs.getDate("fechaPublicacion").toLocalDate(),
                        rs.getString("genero"),
                        rs.getString("idioma"),
                        rs.getString("ISBN"),
                        rs.getString("editorial"),
                        rs.getString("edicion")
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
    public static int agregarMaterial(int id) {
        int lastId = -1;

        String sql = "INSERT INTO Material (id) VALUES (?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            String sql2 = "SELECT last_insert_rowid()";
            try (PreparedStatement stmt2 = conn.prepareStatement(sql2); ResultSet rs = stmt2.executeQuery()) {

                if (rs.next()) {
                    lastId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar material: " + e.getMessage());
        }

        return lastId;
    }

    public static boolean agregarLibro(Libro libro, int id) {
        String sql = "INSERT INTO Libro (codigo, nombre, autor, fechaPublicacion, genero, idioma, ISBN, editorial, edicion) VALUES (?,?,?,?,?,?,?,?)";

        libro.setCodigo(agregarMaterial(id));
        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, libro.getCodigo());
            stmt.setString(2, libro.getNombre());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getFechaPublicacion().toString());
            stmt.setString(5, libro.getGenero());
            stmt.setString(6, libro.getIdioma());
            stmt.setString(7, libro.getISBN());
            stmt.setString(8, libro.getEditorial());
            stmt.setString(9, libro.getEdicion());
            

            stmt.executeQuery();
            System.out.println("Se agrego libro");
            agregarEjemplar(libro.getCodigo());
        } catch (SQLException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
        return true;

    }

    public static boolean agregarEjemplar(int id) {
        String sql = "INSERT INTO Ejemplar (codigo) VALUES (?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();

            System.out.println("Ejemplar agregado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar ejemplar: " + e.getMessage());
            return false;
        }
    }

    //3 Modificar Libro
    public static boolean actualizarLibro(Libro libro) {
        String sql = "UPDATE Libro SET nombre=?, estado=?, autor=?, fechaPublicacion=?, disponibilidad=?, titulo=?, genero=? WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getNombre());
            stmt.setString(2, libro.getEstado());
            stmt.setString(3, libro.getAutor());
            stmt.setString(4, libro.getFechaPublicacion().toString());
            stmt.setBoolean(5, libro.isDisponibilidad());
            stmt.setString(6, libro.getTitulo());
            stmt.setString(7, libro.getGenero());
            stmt.setInt(8, libro.getCodigo());

            stmt.executeUpdate();
            System.out.println("Libro editado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al editar libro: " + e.getMessage());
            return false;
        }
    }

    //4 Eliminar Libro
    public static boolean eliminarLibro(int codigo) {
        String sql = "DELETE FROM Libro WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("Libro eliminado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
            return false;
        }
    }


    /*Crud Recurso Tecnologico*/
    //1 insetar Recurso Tecnologico
    /**
     * ***AGREGAR ARCHIVOS, EDITAR Y ELIMINAR****
     */
    public List<Tablet> obtenerTablet() {
        List<Tablet> listaTablet = new ArrayList();
        String sql = "SELECT codigo, modelo, sistemaOperativo FROM Tablet";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tablet tablet = new Tablet(
                        rs.getInt("codigo"),
                        rs.getString("modelo"),
                        rs.getString("sistemaOperativo")
                );
                listaTablet.add(tablet);
           
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tablets: " + e.getMessage());
        }
        return listaTablet;
    }

    public static Tablet buscarTablet(int tipoRecurso) {
        Tablet tablet = null; //instancia
        String sql = "SELECT codigo, modelo, sistemaOperativo"
                + "FROM Tablet WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tipoRecurso);
           
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tablet = new Tablet(
                        rs.getInt("codigo"),
                        rs.getString("modelo"),
                        rs.getString("sistemaOperativo")
                );
            } else {
                System.out.println("No se encontró un libro con el código: " + tipoRecurso);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return tablet;
    }

    public static int agregarRecursoTecnologico(int tipoRecurso) {
        int lastId = -1;

        String sql = "INSERT INTO RecursoTecnologico (tipoRecurso) VALUES (?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tipoRecurso);
            stmt.executeUpdate();

            String sql2 = "SELECT last_insert_rowid()";
            try (PreparedStatement stmt2 = conn.prepareStatement(sql2); ResultSet rs = stmt2.executeQuery()) {

                if (rs.next()) {
                    lastId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar recurso tecnológico: " + e.getMessage());
        }
        return lastId;
    }

    public static boolean agregarTablet(Tablet tablet, int tipoRecurso) {
        String sql = "INSERT INTO Tablet (codigo, modelo, sistemaOperativo) VALUES (?,?,?,?,?,?,?,?)";

        tablet.setIDcodigo(agregarRecursoTecnologico(tipoRecurso));

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tablet.getIDcodigo());
            stmt.setString(2, tablet.getModelo());
            stmt.setString(3, tablet.getSistemaOperativo());

            stmt.executeUpdate();
            System.out.println("Se agrego correctamente tablet");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar tablet: " + e.getMessage());
            return false;
        }
    }

    public static boolean actualizarTablet(Tablet tablet) {
        String sql = "UPDATE Tablet SET modelo=?, sistemaOperativo=? WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tablet.getModelo());
            stmt.setString(2, tablet.getSistemaOperativo());
            
            stmt.executeUpdate();
            System.out.println("Se actualizo correctamente tablet");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al editar tablet: " + e.getMessage());
            return false;
        }
    }

    public static boolean eliminarTablet(int codigo) {
        String sql = "DELETE FROM Tablet WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Se elimino correctamente tablet");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar tablet: " + e.getMessage());
            return false;
        }
    }

    public List<Computadora> obtenerComputadoras() {
        List<Computadora> listaComputadora = new ArrayList();
        String sql = "SELECT codigo, ram, sistemaOperativo, procesador FROM Computadora";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Computadora computadora = new Computadora(
                        rs.getInt("codigo"),
                        rs.getString("modelo"),
                        rs.getString("sistemaOperativo"),
                        rs.getString("procesador"),
                        rs.getBoolean("estado")
                );
                listaComputadora.add(computadora);
           
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener tablets: " + e.getMessage());
        }
        return listaComputadora;
    }

    public static Computadora buscarComputadora(int tipoRecurso) { //busca el objeto... c: libro
        Computadora computadora = null; //instancia
        String sql = "SELECT codigo, ram, sistemaOperativo, procesador FROM Computadora WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tipoRecurso);
           
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                computadora = new Computadora(
                        rs.getInt("codigo"),
                        rs.getString("ram"),
                        rs.getString("sistemaOperativo"),
                        rs.getString("procesador"),
                        rs.getBoolean("estado")
                );
            } else {
                System.out.println("No se encontró una computadora con el código: " + tipoRecurso);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar computadora: " + e.getMessage());
        }
        return computadora;
    }

    public static boolean agregarComputadora(Computadora computadora, int tipoRecurso) {
        String sql = "INSERT INTO Computadora (codigo, ram, sistemaOperativo, procesador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        computadora.setIDcodigo(agregarRecursoTecnologico(tipoRecurso));

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, computadora.getIDcodigo());
            stmt.setString(2, computadora.getRam());
            stmt.setString(3, computadora.getSistemaOperativo());
            stmt.setString(4, computadora.getProcesador());
         
            stmt.executeUpdate();
            System.out.println("Se agrego correctamente computadora ");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar computadora: " + e.getMessage());
            return false;
        }
    }

    //2 Eliminar Recurso Tecnologico
    public static boolean actualizarComputadora(Computadora computadora) {
        String sql = "UPDATE Computadora SET ram=?, sistemaOperativo=?, procesador=? WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, computadora.getRam());
            stmt.setString(2, computadora.getSistemaOperativo());
            stmt.setString(3, computadora.getProcesador());
                       
            stmt.executeUpdate();
            System.out.println("Se actualizo correctamente computadora ");
            return true;
            

        } catch (SQLException e) {
            System.out.println("Error al editar computadora: " + e.getMessage());
            return false;
        }
    }

    public static boolean eliminarComputadora(int codigo) {
        String sql = "DELETE FROM Computadora WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("Se elimino correctamente computadora ");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar computadora: " + e.getMessage());
            return false;
        }
    }


    /*Crud Sala*/
    //1 Crud Sala
    public List<Sala> obtenerSalas() {
        List<Sala> listaSala = new ArrayList();
        String sql = "SELECT codigo, nombresala, capacidad FROM Sala";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Sala sala = new Sala(
                        rs.getInt("codigo"),
                        rs.getString("nombresala"),
                        rs.getInt("capacidad")
                );
                listaSala.add(sala);
           
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener Sala: " + e.getMessage());
        }
        return listaSala;
    }
    
     public static Sala buscarSala(int codigo) {
        Sala sala = null;
        String sql = "SELECT codigo, nombresala, capacidad"
                + "FROM Sala WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
           
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sala = new Sala(
                        rs.getInt("codigo"),
                        rs.getString("nombresala"),
                        rs.getInt("capacidad")
                );
            } else {
                System.out.println("No se encontró un libro con el código: " + codigo);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return sala;
    }

    public static boolean agregarSala(Sala sala) {
        String sql = "INSERT INTO Sala (codigo, nombre, capacidad) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sala.getCodigo());
            stmt.setString(2, sala.getNombresala());
            stmt.setInt(3, sala.getCapacidad());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al agregar sala: " + e.getMessage());
            return false;
        }
    }

   

    public static boolean actualizarSala(Sala sala) {
        String sql = "UPDATE Sala SET nombresala=?, capacidad=? WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombresala());
            stmt.setInt(2, sala.getCapacidad());

            stmt.executeUpdate();
            System.out.println("Se actualizo sala: " );
            return true;

        } catch (SQLException e) {
            System.out.println("Error al editar sala: " + e.getMessage());
            return false;
        }
    }

    public static boolean eliminarSala(int codigo) {
        String sql = "DELETE FROM Sala WHERE codigo=?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar sala: " + e.getMessage());
            return false;
        }
    }
}