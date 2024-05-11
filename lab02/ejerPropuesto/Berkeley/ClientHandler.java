import java.io.*;
import java.net.Socket;

public class ClientHandler {  
    protected Socket client; // Socket del cliente
    protected DataOutputStream out; // Flujo de salida para enviar datos al cliente

    // Constructor de la clase ClientHandler
    public ClientHandler(Socket client) {
        this.client = client; // Asigna el socket del cliente recibido como argumento
        try {
            this.out = new DataOutputStream(client.getOutputStream()); // Inicializa el flujo de salida
        } catch (IOException e) {
            e.printStackTrace(); // Maneja las excepciones de entrada/salida
        }
    }
}
