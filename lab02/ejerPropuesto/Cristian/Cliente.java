package Cristian;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.Date;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {

        // Conectarse al servidor en localhost y puerto 8080
        Socket socket =  new Socket("127.0.0.1", 8080);

        // Streams de entrada y salida para comunicarse con el servidor
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        // Enviar solicitud al servidor para obtener la hora
        outputStream.writeUTF("Hora");

        // Registrar el tiempo de envío al servidor
        long tiempoEnvio = System.currentTimeMillis();

        // Leer la hora del servidor
        long horaServidor = inputStream.readLong();

        // Registrar el tiempo de recepción de la respuesta del servidor
        long tiempoRecepcion = System.currentTimeMillis();

        // Calcular la latencia de la comunicación
        long latencia = (tiempoRecepcion - tiempoEnvio) ;

        // Imprimir la hora del servidor y la hora ajustada del cliente
        System.out.println("Tiempo del Servidor: " + new Date(horaServidor));
        
        // Simulamos una solicitud de sincronización de reloj
        System.out.println("Sincronizando relojes...");
        try {
            Thread.sleep((long) (Math.random() * 20000)); // Simulamos un tiempo de red variable
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Calcular el tiempo ajustado basado en la latencia
        long tiempoAjustado = horaServidor + latencia;
        System.out.println("Tiempo del Cliente: " + new Date(tiempoAjustado));

        // Cerrar la conexión con el servidor
        socket.close();
    }
}