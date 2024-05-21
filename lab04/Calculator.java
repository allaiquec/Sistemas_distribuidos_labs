// Definición de la interfaz remota para el servicio de calculadora.
// Esta interfaz extiende java.rmi.Remote para indicar que sus métodos pueden ser llamados desde otra JVM.
public interface Calculator extends java.rmi.Remote {

    // Método para sumar dos números.
    // Debe declarar RemoteException para manejar posibles problemas de comunicación en RMI.
    public int add(int a, int b) throws java.rmi.RemoteException;

    // Método para restar dos números.
    // También debe declarar RemoteException.
    public int sub(int a, int b) throws java.rmi.RemoteException;

    // Método para multiplicar dos números.
    // Declaración de RemoteException requerida.
    public int mul(int a, int b) throws java.rmi.RemoteException;

    // Método para dividir dos números.
    // La declaración de RemoteException es necesaria.
    public int div(int a, int b) throws java.rmi.RemoteException;
}

