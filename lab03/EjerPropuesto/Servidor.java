package EjerPropuesto;
import java.io.*;
import java.net.*;

public class Servidor {
    // Puerto en el que el servidor escuchará las conexiones
    static final int PUERTO = 5000;

    public Servidor() {
        try {
            // Crear un ServerSocket que escucha en el puerto especificado
            @SuppressWarnings("resource")
            ServerSocket skServidor = new ServerSocket(PUERTO);

            // Imprimir mensaje indicando que el servidor está escuchando en el puerto
            System.out.println("Escucho el puerto " + PUERTO);

            // Aceptar conexiones de hasta tres clientes
            for (int numCli = 0; numCli < 3; numCli++) {
                // Esperar y aceptar una conexión de cliente
                Socket skCliente = skServidor.accept();
                System.out.println("Sirvo al cliente " + numCli);

                // Obtener el flujo de salida del socket para enviar datos al cliente
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujo = new DataOutputStream(aux);

                // Enviar un mensaje al cliente
                flujo.writeUTF("Hola cliente " + numCli);

                // Cerrar la conexión con el cliente
                skCliente.close();
            }

            // Imprimir mensaje indicando que no se aceptarán más clientes por hoy
            System.out.println("Demasiados clientes por hoy");
        } catch (Exception e) {
            // En caso de error, imprimir el mensaje de excepción
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        // Crear una nueva instancia del servidor, lo que iniciará el proceso de aceptación de clientes
        new Servidor();
    }
}
