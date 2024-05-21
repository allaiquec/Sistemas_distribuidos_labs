// Importaciones necesarias para el manejo de excepciones y RMI.
import java.net.MalformedURLException;  // Excepción lanzada cuando una URL tiene un formato incorrecto.
import java.rmi.Naming;                 // Clase utilizada para buscar y registrar objetos remotos en el registro RMI.
import java.rmi.NotBoundException;      // Excepción lanzada cuando una referencia de nombre no está vinculada en el registro RMI.
import java.rmi.RemoteException;        // Excepción lanzada por fallos en las operaciones remotas de RMI.

// Clase que representa el cliente RMI para consumir el servicio de calculadora.
public class CalculatorClient {

    // Método principal para ejecutar el cliente.
    public static void main(String[] args) {
        // Verificación y parseo de los argumentos de la línea de comandos.
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        try {
            // Búsqueda del servicio remoto en el RMI registry utilizando Naming.lookup.
            Calculator c = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");

            // Invocación de los métodos remotos y muestra de los resultados.
            System.out.println("The subtraction of " + num1 + " and " + num2 + " is: " + c.sub(num1, num2));
            System.out.println("The addition of " + num1 + " and " + num2 + " is: " + c.add(num1, num2));
            System.out.println("The multiplication of " + num1 + " and " + num2 + " is: " + c.mul(num1, num2));
            System.out.println("The division of " + num1 + " and " + num2 + " is: " + c.div(num1, num2));
        } catch (MalformedURLException murle) {
            // Manejo de excepción cuando la URL del servicio es malformada.
            System.out.println();
            System.out.println("MalformedURLException");
            System.out.println(murle);
        } catch (RemoteException re) {
            // Manejo de excepción cuando hay un fallo en la comunicación remota.
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
        } catch (NotBoundException nbe) {
            // Manejo de excepción cuando el nombre buscado no está vinculado en el registro RMI.
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        } catch (java.lang.ArithmeticException ae) {
            // Manejo de excepción aritmética, como la división por cero.
            System.out.println();
            System.out.println("java.lang.ArithmeticException");
            System.out.println(ae);
        }
    }
}
