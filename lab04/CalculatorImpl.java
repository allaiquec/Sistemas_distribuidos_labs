// Clase que implementa la interfaz remota Calculator y extiende UnicastRemoteObject
// para permitir su uso en RMI.
public class CalculatorImpl extends java.rmi.server.UnicastRemoteObject implements Calculator {

    // Constructor explícito que debe declarar RemoteException.
    // Esto es necesario para que la clase pueda ser utilizada como un objeto remoto.
    public CalculatorImpl() throws java.rmi.RemoteException {
        super();  // Llama al constructor de la superclase UnicastRemoteObject.
    }

    // Implementación del método remoto para sumar dos números.
    @Override
    public int add(int a, int b) throws java.rmi.RemoteException {
        return a + b;  // Retorna la suma de a y b.
    }

    // Implementación del método remoto para restar dos números.
    @Override
    public int sub(int a, int b) throws java.rmi.RemoteException {
        return a - b;  // Retorna la resta de a y b.
    }

    // Implementación del método remoto para multiplicar dos números.
    @Override
    public int mul(int a, int b) throws java.rmi.RemoteException {
        return a * b;  // Retorna la multiplicación de a y b.
    }

    // Implementación del método remoto para dividir dos números.
    @Override
    public int div(int a, int b) throws java.rmi.RemoteException {
        // Verificación de división por cero.
        if (b == 0) {
            throw new ArithmeticException("Division by zero");  // Lanza excepción si b es cero.
        }
        return a / b;  // Retorna la división de a y b.
    }
}
