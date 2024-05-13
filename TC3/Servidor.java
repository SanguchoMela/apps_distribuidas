import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {

    public static void main(String[] args) {

        int puerto = 5000;

        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor esperando conexiones ...");

            while (true) {
                // Crear arreglo de bytes para recibir los datos del cliente
                byte[] bufferEntrada = new byte[1024];
    
                // Crear objeto datagrama para recibir los datos
                DatagramPacket paquete = new DatagramPacket(bufferEntrada, bufferEntrada.length);
    
                // Recibir el paquete
                socket.receive(paquete);
    
                // Iniciar un hilo para cada cliente
                Thread hiloCliente = new HiloCliente(socket, paquete);
                hiloCliente.start();
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}