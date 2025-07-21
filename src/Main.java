
import Clases.*;
import java.sql.Connection;
import Acceso_Datos.*;
import java.util.Scanner;
import Consola.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        try (Connection conn = ConexionBD.conectar()) {

            while (!salir) {
                System.out.println("=== SISTEMA DE BIBLI11OTECA ===");
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
                        MenuPrincipal mp = new MenuPrincipal(conn);
                        mp.iniciarLogin();
                        break;
                    case 2:
                        System.out.println("Gracias por usar el sistema.");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }

            }catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        }
    }
