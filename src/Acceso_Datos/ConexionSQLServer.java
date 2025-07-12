package Acceso_Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    public static Connection conectar() {
        Connection conn = null;
        try {
            // URL de conexión a SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=db_library_system;encrypt=false;trustServerCertificate=true"; 
            String user = "Edwin";
            String password = "123";
            
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a SQL Server establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQL Server: " + e.getMessage());
        }
        return conn;
    }
}