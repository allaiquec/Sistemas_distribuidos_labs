// Importación necesaria para utilizar el método Naming.rebind en RMI.
import java.rmi.Naming;

// Clase que representa el servidor RMI para el servicio de calculadora.
public class CalculatorServer {

    // Constructor del servidor.
    public CalculatorServer() {
        try {
            // Creación de una instancia del servicio de calculadora.
            Calculator c = new CalculatorImpl();
            
            // Registro del servicio de calculadora en el RMI registry bajo el nombre "CalculatorService".
            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            // Manejo de excepciones y muestra del mensaje de error si ocurre algún problema.
            System.out.println("Trouble: " + e);
        }
    }

    // Método principal para iniciar el servidor.
    public static void main(String args[]) {
        // Creación de una nueva instancia de CalculatorServer, lo que invoca al constructor y registra el servicio.
        new CalculatorServer();
    }
}
