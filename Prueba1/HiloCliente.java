import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class HiloCliente extends Thread {

    private int puerto;

    // Diccionario para preguntas y respuesta
    private Map<String, String> bancoPreguntas;

    public HiloCliente(int puerto) {
        this.puerto = puerto;
        this.bancoPreguntas = new HashMap<>();
        this.bancoPreguntas.put("Capital de Ecuador?", "Quito");
        this.bancoPreguntas.put("Capital de Brasil?", "Brasilia");
        this.bancoPreguntas.put("Capital de Venezuela?", "Caracas");
        this.bancoPreguntas.put("Capital de Uruguay?", "Montevideo");
        this.bancoPreguntas.put("Capital de Argentina?", "Buenos Aires");
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Esperando conexiones...");

            while (true) {
                // Iniciar comunicación con el servidor
                byte[] bufferInicial = new byte[1024];
                DatagramPacket paqueteInicial = new DatagramPacket(bufferInicial, bufferInicial.length);
                socket.receive(paqueteInicial);

                InetAddress direccionIp_cliente = paqueteInicial.getAddress();
                int puerto_cliente = paqueteInicial.getPort();
                System.out.println("Cliente conectado: " + direccionIp_cliente.getHostAddress());

                int puntaje = 0;

                for (Map.Entry<String, String> parBanco : bancoPreguntas.entrySet()) {
                    String pregunta = parBanco.getKey();
                    String respuestaCorrecta = parBanco.getValue();

                    // Enviar pregunta al cliente
                    byte[] bufferPregunta = pregunta.getBytes();
                    DatagramPacket paquetePregunta = new DatagramPacket(bufferPregunta, bufferPregunta.length,
                            direccionIp_cliente, puerto_cliente);
                    socket.send(paquetePregunta);

                    // Recibir respuesta del cliente
                    byte[] bufferRespuesta = new byte[1024];
                    DatagramPacket respuestaCliente = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                    socket.receive(respuestaCliente);

                    String mensajeRespuesta = new String(respuestaCliente.getData(), 0, respuestaCliente.getLength());

                    // Evaluar la respuesta
                    String mensajeEvaluacion;
                    if (mensajeRespuesta.trim().equalsIgnoreCase(respuestaCorrecta)) {
                        mensajeEvaluacion = "Correcto";
                        puntaje += 4;
                    } else {
                        mensajeEvaluacion = "Incorrecto. La respuesta correcta es: " + respuestaCorrecta;
                    }

                    byte[] bufferEvaluacion = mensajeEvaluacion.getBytes();
                    DatagramPacket paqueteEvaluacion = new DatagramPacket(bufferEvaluacion, bufferEvaluacion.length,
                            direccionIp_cliente, puerto_cliente);
                    socket.send(paqueteEvaluacion);

                }
                // Enviar puntaje final al cliente
                String mensajePuntaje = "Tus puntos son: " + puntaje;
                byte[] bufferPuntaje = mensajePuntaje.getBytes();
                DatagramPacket paquetePuntaje = new DatagramPacket(bufferPuntaje, bufferPuntaje.length, direccionIp_cliente, puerto_cliente);
                socket.send(paquetePuntaje);

                System.out.println("Cliente desconectado");

                // socket.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
