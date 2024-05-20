import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implementación de la interfaz remota Calculator.
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    // Constructor que declara RemoteException.
    protected CalculatorImpl() throws RemoteException {
        super();
    }

    // Implementación del método para sumar dos números.
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    // Implementación del método para restar dos números.
    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }

    // Implementación del método para multiplicar dos números.
    @Override
    public int mul(int a, int b) throws RemoteException {
        return a * b;
    }

    // Implementación del método para dividir dos números.
    @Override
    public int div(int a, int b) throws RemoteException {
        // Verificación de división por cero.
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
