import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket socket_servidor;
        try {
            // Crear socket
            socket_servidor = new ServerSocket(5000);
            System.out.println("Esperando conexiones ...");
            
            while (true) {            
                // Esperar y aceptar conexiones de clientes
                Socket socket_cliente = socket_servidor.accept();

                // Crear un hilo para cada cliente
                HiloCliente hilo = new HiloCliente(socket_cliente);
                hilo.start();

                // socket_cliente.close();
            }        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}