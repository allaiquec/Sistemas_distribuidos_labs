import java.rmi.Naming;

// Clase que representa el servidor RMI para el servicio de calculadora.
public class CalculatorServer {

    // Constructor que configura el servicio de calculadora y lo registra en el RMI registry.
    public CalculatorServer() {
        try {
            Calculator c = new CalculatorImpl();
            // Registro del servicio en el RMI registry con el nombre "CalculatorService".
            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    // Método principal para iniciar el servidor.
    public static void main(String[] args) {
        new CalculatorServer();
    }
}
