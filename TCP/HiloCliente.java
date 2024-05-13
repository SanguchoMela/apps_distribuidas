import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HiloCliente extends Thread {
    Socket socket_cliente;

    Scanner escaner = new Scanner(System.in);
    String mensajeSalida;

    public HiloCliente(Socket socket_cliente){
        this.socket_cliente = socket_cliente;
    }

    public void run(){
        try {
            // Crear buffers para recibir y enviar datos al cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(),true);
    
            // Leer los datos recibidos por el cliente almacenados en el buffer de entrada
            String datos_recibidos = entrada.readLine();
            System.out.println("Cliente: "+datos_recibidos);
    
            // Escribir los dotos a enviar en el buffer de salida
            System.out.print("Servidor: ");
            String mensaje_enviar = escaner.nextLine();

            salida.println(mensaje_enviar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
