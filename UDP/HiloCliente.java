import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class HiloCliente extends Thread {

    private DatagramSocket socket;
    private DatagramPacket paquete;

    public HiloCliente(DatagramSocket socket, DatagramPacket paquete) {
        this.socket = socket;
        this.paquete = paquete;
    }

    public void run() {

        Scanner escaner = new Scanner(System.in);
        String mensajeSalida;

        try {
            // Extraer la información del paquete
            String mensajeRecibido = new String(paquete.getData());
            System.out.println("Cliente: " + mensajeRecibido);

            // Obtener la dirección del cliente
            InetAddress direccionIp_cliente = paquete.getAddress();
            int puerto_cliente = paquete.getPort();

            // Crear arreglo de bytes para enviar los datos del cliente
            System.out.print("Servidor: ");
            mensajeSalida = escaner.nextLine();

            byte[] bufferSalida = mensajeSalida.getBytes();

            // Crear objeto datagrama para enviar los datos
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIp_cliente, puerto_cliente);

            socket.send(paqueteSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
