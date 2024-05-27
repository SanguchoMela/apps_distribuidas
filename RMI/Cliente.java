import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Obtener el registro 
            Registry registro = LocateRegistry.getRegistry("172.31.115.130", 1099);
    
            // Crear instancia de la implementación la interfaz 
            interfaz objetoRemoto = (interfaz) registro.lookup("ClienteRemoto");

            // 
            String mensaje = objetoRemoto.mensaje();
            System.out.println(mensaje);

            double respuesta = objetoRemoto.suma(8, 4);
            System.out.println(respuesta);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
