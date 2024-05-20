import java.rmi.Naming;

// Clase que representa el cliente RMI para consumir el servicio de calculadora.
public class CalculatorClient {

    // Método principal para ejecutar el cliente.
    public static void main(String[] args) {
        // Verificación de argumentos.
        if (args.length < 2) {
            System.out.println("Please provide two integers as arguments.");
            return;
        }

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        try {
            // Búsqueda del servicio remoto en el RMI registry.
            Calculator c = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            // Invocación de los métodos remotos y muestra de los resultados.
            System.out.println("The addition of " + num1 + " and " + num2 + " is: " + c.add(num1, num2));
            System.out.println("The subtraction of " + num1 + " and " + num2 + " is: " + c.sub(num1, num2));
            System.out.println("The multiplication of " + num1 + " and " + num2 + " is: " + c.mul(num1, num2));
            System.out.println("The division of " + num1 + " and " + num2 + " is: " + c.div(num1, num2));
        } catch (Exception e) {
            // Manejo de excepciones con mensajes de error.
            System.out.println("Exception: " + e);
        }
    }
}

}
