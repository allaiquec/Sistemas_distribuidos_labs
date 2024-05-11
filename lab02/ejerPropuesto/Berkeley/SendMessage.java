import java.io.BufferedReader;
import java.util.List;

public class SendMessage extends Thread {
    protected List<ClientHandler> clients; // Lista de manejadores de clientes
    protected String userInput; // Entrada de usuario (no parece utilizarse)
    protected BufferedReader console; // Entrada desde la consola (no parece utilizarse)
    Long frequency; // Frecuencia de envío de mensajes

    // Constructor de la clase para enviar mensajes a los clientes
    public SendMessage(List<ClientHandler> clients, long freq) {
        this.clients = clients; // Asigna la lista de clientes
        this.userInput = null; // Inicializa la entrada de usuario
        this.start(); // Inicia el hilo
        this.frequency = freq; // Asigna la frecuencia de envío de mensajes
    }

    // Método que se ejecuta cuando inicia el hilo
    public void run() {
        try {
            // Espera a que al menos un cliente se conecte
            while(clients.size() == 0) {}

            // Si hay al menos un cliente conectado
            if (clients.size() > 0) {
                while (true) {
                    Thread.sleep(frequency);  // Espera según la frecuencia especificada
                    for (ClientHandler client : clients) {
                        // Envía la marca de tiempo actual del sistema al cliente
                        client.out.writeLong(System.currentTimeMillis());
                        client.out.flush();  // Limpia el flujo de salida
                        Thread.currentThread(); // Retorna una referencia al hilo actual
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
