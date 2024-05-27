import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Crear una instancia de la implementación de la interfaz
            Interfaz objetoRemoto = new ImplementacionInterfaz();
    
            // Crear registro RMI en un puerto especifico
            Registry registro = LocateRegistry.createRegistry(1099);

            // Vincular la implementacion remota con un nombre especifico
            registro.rebind("ClienteRemoto", objetoRemoto);

            System.out.println("Servidor iniciado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
