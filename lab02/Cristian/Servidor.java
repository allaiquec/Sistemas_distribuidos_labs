package Cristian;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException, InterruptedException{
        // Crear un servidor socket en el puerto 8080
        ServerSocket servidorSocket = new ServerSocket(8080);

        // Aceptar la conexión entrante del cliente
        Socket clienteSocket = servidorSocket.accept();

        // Stream de salida para enviar datos al cliente
        DataOutputStream salidaServidor = new DataOutputStream(clienteSocket.getOutputStream());

        // Obtener la hora del servidor
        long horaServidor = System.currentTimeMillis();

        // Enviar la hora del servidor al cliente
        salidaServidor.writeLong(horaServidor);

        // Cerrar la conexión con el cliente y el servidor
        clienteSocket.close();
        servidorSocket.close();
    }
}
