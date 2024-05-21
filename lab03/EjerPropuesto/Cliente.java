package EjerPropuesto;
import java.io.*;
import java.net.*;

public class Cliente {
    // Dirección del servidor (en este caso, la misma máquina)
    static final String HOST = "localhost";
    // Puerto en el que el servidor está escuchando
    static final int PUERTO = 5000;

    public Cliente() {
        try {
            // Crear un socket para conectarse al servidor en la dirección y puerto
            // especificados
            Socket skCliente = new Socket(HOST, PUERTO);

            // Obtener el flujo de entrada del socket para recibir datos del servidor
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);

            // Leer el mensaje enviado por el servidor y mostrarlo en la consola
            System.out.println(flujo.readUTF());

            // Cerrar la conexión con el servidor
            skCliente.close();
        } catch (Exception e) {
            // En caso de error, imprimir el mensaje de excepción
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        // Crear una nueva instancia del cliente, lo que iniciará el proceso de conexión
        // y recepción de datos
        new Cliente();
    }
}
