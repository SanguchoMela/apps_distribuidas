import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionInterfaz extends UnicastRemoteObject implements Interfaz {

    // Constructor
    public ImplementacionInterfaz() throws RemoteException{
        super();
    }

    // Implementar los métodos indicados en la interfaz
    @Override
    public String mensaje(){
        return "Calculadora";
    }

    @Override
    public double suma(double a, double b){
        return a + b;
    }

    @Override
    public double resta(double a, double b){
        return a - b;
    }

    @Override
    public double multiplicacion(double a, double b){
        return a * b;
    } 

    @Override
    public double division(double a, double b){
        return a / b;
    }
    
}
