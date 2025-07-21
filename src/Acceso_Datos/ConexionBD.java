/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:\\Users\\ehuan\\Documents\\DB_SqLite\\db_library_system.db";
            conn = DriverManager.getConnection(url);
            //System.out.println("Conexión a SQLite establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQLite: " + e.getMessage());
        }
        return conn;
    }
}
