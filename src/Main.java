
import Clases.*;
import Consola.MenuPrincipal;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su código de usuario: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String clave = scanner.nextLine();

        Usuario usuario = new Usuario(codigo, clave);*/
        
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciar();
    }

}
