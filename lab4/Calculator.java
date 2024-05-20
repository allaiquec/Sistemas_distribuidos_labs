import java.rmi.Remote;
import java.rmi.RemoteException;

// Definición de la interfaz remota para el servicio de calculadora.
public interface Calculator extends Remote {
    // Método para sumar dos números.
    int add(int a, int b) throws RemoteException;
    
    // Método para restar dos números.
    int sub(int a, int b) throws RemoteException;
    
    // Método para multiplicar dos números.
    int mul(int a, int b) throws RemoteException;
    
    // Método para dividir dos números.
    int div(int a, int b) throws RemoteException;
}
