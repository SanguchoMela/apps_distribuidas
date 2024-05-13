package Ej3;
public class Hilos extends Thread {
    
    private int tipo; // Un campo para indicar el tipo de hilo (1 o 2)

    public Hilos(int tipo) {
        this.tipo = tipo; // Constructor que recibe el tipo de hilo
    }
    
    public void run() {
        // Este método se ejecutará cuando se inicie el hilo
        // Dependiendo del tipo, el hilo realizará diferentes tareas.

        switch(tipo) {
            case 1 -> {
                for(int i = 1; i < 30; i++) {
                    System.out.println(i); // Imprimir números del 1 al 29
                }
            }
            case 2 -> {
                for(char i = 'a'; i < 'z'; i++){
                    System.out.println(i); // Imprimir letras minúsculas de 'a' a 'y'
                }                
            }
        }
    }
}
