
import Clases.*;
import Consola.MenuPrincipal;
import Acceso_Datos.*;
import java.util.Scanner;
import Consola.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here


        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        while (!salir) {
            System.out.println("=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Elige una opción: ");

            String opcionStr = sc.nextLine();
            int opcion;

            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                continue;
            }
            switch (opcion) {
                case 1:
                    MenuPrincipal.iniciarLogin();
                    break;
                case 2:
                    System.out.println("Gracias por usar el sistema.");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
        /*Usuario usuario = new Recepcionista ("FGEE", "EEEEE", "HOLI", "EFE");
        usuario.setTipoDeUser("Recepcionista");
        MenuPrincipal.mostrarMenu(usuario);
        ConexionBD.conectar();*/
    

