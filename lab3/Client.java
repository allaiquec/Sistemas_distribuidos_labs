import java.net.*;
import java.io.*;
import java.util.*;

// El Cliente que puede ejecutarse como una consola
public class Client {
    
    // Notificación
    private String notif = " *** ";
    // Para I/O
    private ObjectInputStream sInput; // para leer del socket
    private ObjectOutputStream sOutput; // para escribir en el socket
    private Socket socket; // objeto socket
    private String server, username; // servidor y nombre de usuario
    private int port; // puerto
    
    // Obtener el nombre de usuario
    public String getUsername() {
        return username;
    }
    
    // Establecer el nombre de usuario
    public void setUsername(String username) {
        this.username = username;
    }

    // Constructor para establecer los siguientes parámetros
    // server: la dirección del servidor
    // port: el número de puerto
    // username: el nombre de usuario
    Client(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }
    
    // Para iniciar el chat
    public boolean start() {
        // Intentar conectar con el servidor
        try {
            socket = new Socket(server, port);
        } 
        // Manejador de excepción si falla
        catch(Exception ec) {
            display("Error connecting to server: " + ec);
            return false;
        }
        
        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        display(msg);

        // Crear ambos flujos de datos
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // Crear el Thread para escuchar desde el servidor
        new ListenFromServer().start();

        // Enviar nuestro nombre de usuario al servidor, este es el único mensaje que enviaremos como String.
        // Todos los demás mensajes serán objetos ChatMessage.
        try {
            sOutput.writeObject(username);
        }
        catch (IOException eIO) {
            display("Exception doing login: " + eIO);
            disconnect();
            return false;
        }
        
        // Éxito, informamos al llamador que funcionó
        return true;
    }
    
    // Para enviar un mensaje a la consola
    private void display(String msg) {
        System.out.println(msg);
    }
    
    // Para enviar un mensaje al servidor
    void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
        }
        catch(IOException e) {
            display("Exception writing to server: " + e);
        }
    }
    
    // Cuando algo sale mal
    // Cerrar los flujos de entrada/salida y desconectar
    private void disconnect() {
        try {
            if(sInput != null) sInput.close();
        }
        catch(Exception e) {}
        
        try {
            if(sOutput != null) sOutput.close();
        }
        catch(Exception e) {}
        
        try {
            if(socket != null) socket.close();
        }
        catch(Exception e) {}
    }

    // Para iniciar el Cliente en modo consola use uno de los siguientes comandos
    // > java Client
    // > java Client username
    // > java Client username portNumber
    // > java Client username portNumber serverAddress
    // en el prompt de la consola
    // Si el portNumber no está especificado se usa 1500
    // Si el serverAddress no está especificado se usa "localHost"
    // Si el username no está especificado se usa "Anonymous"
    public static void main(String[] args) {
        // valores por defecto si no se ingresan
        int portNumber = 1500;
        String serverAddress = "localhost";
        String userName = "Anonymous";
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the username: ");
        userName = scan.nextLine();
        
        // caso diferente según la longitud de los argumentos.
        switch(args.length) {
            case 3:
                // para > javac Client username portNumber serverAddr
                serverAddress = args[2];
            case 2:
                // para > javac Client username portNumber
                try {
                    portNumber = Integer.parseInt(args[1]);
                }
                catch(Exception e) {
                    System.out.println("Invalid port number.");
                    System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
                    return;
                }
            case 1:
                // para > javac Client username
                userName = args[0];
            case 0:
                // para > java Client
                break;
            // si el número de argumentos es inválido
            default:
                System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
                return;
        }
        
        // crear el objeto Client
        Client client = new Client(serverAddress, portNumber, userName);
        // intentar conectar con el servidor y retornar si no conectado
        if(!client.start())
            return;
        
        System.out.println("\nHello! Welcome to the chatroom.");
        System.out.println("Instructions:");
        System.out.println("1. Simply type the message to send broadcast to all active clients");
        System.out.println("2. Type '@username<space>yourmessage' without quotes to send message to desired client");
        System.out.println("3. Type 'WHOISIN' without quotes to see list of active clients");
        System.out.println("4. Type 'LOGOUT' without quotes to logoff from server");
        
        // bucle infinito para obtener la entrada del usuario
        while(true) {
            System.out.print("> ");
            // leer mensaje del usuario
            String msg = scan.nextLine();
            // cerrar sesión si el mensaje es LOGOUT
            if(msg.equalsIgnoreCase("LOGOUT")) {
                client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, ""));
                break;
            }
            // mensaje para verificar quién está presente en la sala de chat
            else if(msg.equalsIgnoreCase("WHOISIN")) {
                client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, ""));
            }
            // mensaje de texto regular
            else {
                client.sendMessage(new ChatMessage(ChatMessage.MESSAGE, msg));
            }
        }
        
        // cerrar recurso
        scan.close();
        // el cliente completó su trabajo. desconectar cliente.
        client.disconnect();
    }

    // una clase que espera el mensaje del servidor
    class ListenFromServer extends Thread {
        public void run() {
            while(true) {
                try {
                    // leer el mensaje del flujo de datos de entrada
                    String msg = (String) sInput.readObject();
                    // imprimir el mensaje
                    System.out.println(msg);
                    System.out.print("> ");
                }
                catch(IOException e) {
                    display(notif + "Server has closed the connection: " + e + notif);
                    break;
                }
                catch(ClassNotFoundException e2) {
                }
            }
        }
    }
}
