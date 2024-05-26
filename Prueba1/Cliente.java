import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            int puertoServidor = 5000;
            Scanner scanner = new Scanner(System.in);

            // Iniciar comunicación con el servidor
            String mensajeInicial = "Cliente conectado";
            byte[] bufferInicial = mensajeInicial.getBytes();
            DatagramPacket paqueteInicial = new DatagramPacket(bufferInicial, bufferInicial.length, address, puertoServidor);
            socket.send(paqueteInicial);

            System.out.println("Cliente conectado al servidor");

            // Recibir preguntas, enviar respuestas y esperar puntaje final
            for (int i = 0; i < 5; i++) {
                // Recibir pregunta del servidor
                byte[] bufferPregunta = new byte[1024];
                DatagramPacket paquetePregunta = new DatagramPacket(bufferPregunta,bufferPregunta.length);
                socket.receive(paquetePregunta);

                String pregunta = new String(paquetePregunta.getData(), 0, paquetePregunta.getLength());
                System.out.println("\n" + pregunta);

                // Enviar respuesta al servidor
                System.out.print("Ingrese su respuesta: ");
                String respuesta = scanner.nextLine();
                byte[] bufferRespuesta = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, address, puertoServidor);
                socket.send(paqueteRespuesta);

                // Recibir confirmacion del servidor 
                byte[] bufferEvaluacion = new byte[1024];
                DatagramPacket paqueteEvaluacion = new DatagramPacket(bufferEvaluacion, bufferEvaluacion.length);
                socket.receive(paqueteEvaluacion);

                String evaluacion = new String(paqueteEvaluacion.getData(), 0, paqueteEvaluacion.getLength());
                System.out.println(evaluacion);
            }
            // Recibir puntaje final del servidor
            byte[] bufferPuntaje = new byte[1024];
            DatagramPacket paquetePuntaje = new DatagramPacket(bufferPuntaje, bufferPuntaje.length);
            socket.receive(paquetePuntaje);

            String puntajeFinal = new String(paquetePuntaje.getData(), 0, paquetePuntaje.getLength());
            System.out.println(puntajeFinal);

            System.out.println("Cliente desconectado");

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
