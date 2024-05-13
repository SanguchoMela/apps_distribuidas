import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args) {

        int puerto = 5000;

        try {
            DatagramSocket socket = new DatagramSocket();

            // Dirección IP del servidor
            InetAddress direccionIP_servidor = InetAddress.getByName("localhost");

            // Crear arreglo de bytes para enviar los datos del cliente
            String mensajeSalida = "Hola soy el cliente";
            byte[] bufferSalida = mensajeSalida.getBytes();

            // Crear objeto datagrama para enviar los datos
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIP_servidor,puerto);

            // Enviar el paquete
            socket.send(paqueteSalida);

            // Recibir el paquete --------------------------------------
            // Crear arreglo de bytes para recibir los datos del cliente
            byte[] bufferEntrada = new byte[1024];

            // Crear objeto datagrama para recibir los datos
            DatagramPacket paquete = new DatagramPacket(bufferEntrada, bufferEntrada.length);

            // Recibir el paquete
            socket.receive(paquete);

            String mensajeRecibido = new String(paquete.getData());
            System.out.println("El mensaje es: " + mensajeRecibido);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
