/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static void Conectar() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            if (conn != null) {
                System.out.println("Conexión a SQLite establecida correctamente.");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static Connection conectar() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:resources/db_library_system.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión a SQLite establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQLite: " + e.getMessage());
        }
        return conn;
    }
}
