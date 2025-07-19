package Controladores;

import Acceso_Datos.ConexionBD;
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

        String sql = "SELECT codigo, nombre, apellido, correo, tipo_usuario_id, carrera_id, especialidad_id FROM Usuario WHERE codigo=? AND contrasenia=?"; //AGREGAR

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                int tipo = rs.getInt("tipo_usuario_id");
                
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                // factoría polimórfica
                switch(tipo) {
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
}