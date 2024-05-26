public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;
        HiloCliente hiloCliente = new HiloCliente(puerto);
        hiloCliente.start();
    }
}
