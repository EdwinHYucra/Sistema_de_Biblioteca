import Clases.*;
import Consola.MenuPrincipal;
import Acceso_Datos.ConexionBD;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        //ConexionBD.conectar();
        Scanner scanner = new Scanner(System.in);

        /*System.out.print("Ingrese su código de usuario: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine();

        Usuario usuario = new Usuario(codigo, clave);*/
        
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciar();
    }
}
