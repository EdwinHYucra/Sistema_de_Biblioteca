package Controladores;

import Clases.Usuario;
import Clases.Administrador;
import Clases.Alumno;
import Clases.Docente;
import Clases.Recepcionista;
import Acceso_Datos.ConexionSQLServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    /**
     * Valida usuario y contraseña en la base de datos
     * Retorna la subclase adecuada del objeto Usuario
     * o null si no se encuentra
     */
    public static Usuario autenticar(String codigo, String password) {
        Usuario usuario = null;

        String sql = "SELECT codigo, nombre, apellido, tipo_usuario FROM Usuario WHERE codigo=? AND contrasenia=?";

        try (Connection conn = ConexionSQLServer.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                String tipo = rs.getString("tipo_usuario");
                
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                // factoría polimórfica
                switch(tipo) {
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
}