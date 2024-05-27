import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);
        try {
            // Obtener el registro 
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
    
            // Crear instancia de la implementación la interfaz 
            Interfaz objetoRemoto = (Interfaz) registro.lookup("ClienteRemoto");

            // 
            String mensaje = objetoRemoto.mensaje();
            System.out.println(mensaje);

            int opcion = 0;
            double a,b = 0;
            double respuesta = 0;

            do {
                System.out.println("\nIngresa dos numeros: ");
                System.out.print("a: ");
                a = escaner.nextInt();
                System.out.print("b: ");
                b = escaner.nextInt();

                System.out.println("\nOpciones de operaciones");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicacion");
                System.out.println("4. Division");
                System.out.println("5. Salir");
                System.out.print("Ingresa la opcion: ");
                opcion = escaner.nextInt();

                switch (opcion) {
                    case 1:
                        respuesta = objetoRemoto.suma(a, b);
                        System.out.println("Suma:" + respuesta);
                        break;
                    case 2:
                        respuesta = objetoRemoto.resta(a, b);
                        System.out.println("Resta:" + respuesta);
                        break;
                    case 3:
                        respuesta = objetoRemoto.resta(a, b);
                        System.out.println("Multiplicacion:" + respuesta);
                        break;
                    case 4:
                        respuesta = objetoRemoto.resta(a, b);
                        System.out.println("Division:" + respuesta);
                        break;
                
                    default:
                        System.out.println("Adios :(");
                        break;
                }

            } while (opcion != 5);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
