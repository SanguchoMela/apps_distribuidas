import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfaz extends Remote {

    // Metodos que el cliente puede invocar de forma remota
    String mensaje() throws RemoteException;
    double suma(double a, double b) throws RemoteException;
}