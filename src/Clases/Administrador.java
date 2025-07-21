package Clases;

import Acceso_Datos.UsuarioDA;
import Interfaces.IServiciosRecursos;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;  

public class Administrador extends Usuario implements IServiciosRecursos {

    //Constructor de Prueba
    public Administrador(UsuarioDA usuarioda,String id_codigo, String contraseña, String nombre) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

    public Administrador(UsuarioDA usuarioda,String id_codigo, String contraseña, String nombre, String apellido) {
        super(usuarioda);
        this.id_codigo = id_codigo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        
        this.tipoDeUser = 1;
    }

    public Administrador(String id_codigo, String nombre, String apellido, String correo, int tipoDeUser) {
        super(id_codigo, nombre, apellido, correo, tipoDeUser);
    }
    

    public void exportarInfo() {
        try {
            FileWriter writer = new FileWriter("exportacion.txt");
            writer.write("Exportación de información:\n");
            writer.write("Aquí puedes poner los datos que quieras exportar.\n");
            writer.write("Por ejemplo: Último material agregado o información fija.\n");
            writer.close();
            System.out.println("Información exportada correctamente a exportacion.txt");
        } catch (IOException e) {
            System.out.println("Error al exportar información: " + e.getMessage());
        }
    }

    @Override
    public void verificarReservas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void generadorReporte() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
