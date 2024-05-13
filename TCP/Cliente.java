import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socket_cliente;
        Scanner escaner = new Scanner(System.in);

        try {
            
            System.out.println("Cliente conectado");
            
            while (true) {
                socket_cliente = new Socket("localhost",5000);
                // Crear buffers para recibir y enviar datos al cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket_cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socket_cliente.getOutputStream(),true);
    
                // Escribir los dotos a enviar en el buffer de salida
                System.out.println("Cliente: ");
                String mensaje_enviar = escaner.nextLine();
                salida.println(mensaje_enviar);

                // Leer los datos recibidos por el cliente
                String datos_recibidos = entrada.readLine();
                System.out.println("Mensaje servidor: "+datos_recibidos);
    
                // socket_cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
