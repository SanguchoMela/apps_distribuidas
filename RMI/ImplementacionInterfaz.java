import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplementacionInterfaz extends UnicastRemoteObject implements interfaz {

    // Constructor
    public ImplementacionInterfaz() throws RemoteException{
        super();
    }

    // Implementar los métodos indicados en la interfaz
    @Override
    public String mensaje(){
        return "Servidor";
    }

    @Override
    public double suma(double a, double b){
        return a + b;
    }
    
}
