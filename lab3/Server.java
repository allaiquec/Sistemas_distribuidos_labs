import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

// el servidor que puede ejecutarse como una consola
public class Server {

    // un ID único para cada conexión
    private static int uniqueId;
    // un ArrayList para mantener la lista de los Clientes
    private ArrayList<ClientThread> al;
    // para mostrar la hora
    private SimpleDateFormat sdf;
    // el número de puerto para escuchar conexiones
    private int port;
    // para verificar si el servidor está funcionando
    private boolean keepGoing;
    // notificación
    private String notif = " *** ";
    
    // constructor que recibe el puerto para escuchar conexiones como parámetro
    public Server(int port) {
        // el puerto
        this.port = port;
        // para mostrar hh:mm:ss
        sdf = new SimpleDateFormat("HH:mm:ss");
        // un ArrayList para mantener la lista de los Clientes
        al = new ArrayList<ClientThread>();
    }
    
    // iniciar el servidor
    public void start() {
        keepGoing = true;
        // crear socket del servidor y esperar solicitudes de conexión
        try {
            // el socket usado por el servidor
            ServerSocket serverSocket = new ServerSocket(port);
            // bucle infinito para esperar conexiones (solicitudes)
            while(keepGoing) {
                display("Server waiting for Clients on port " + port + ".");
                
                // aceptar la conexión y crear una instancia de hilo para manejarla
                Socket socket = serverSocket.accept();
                // salir si se recibe la señal de parada
                if(!keepGoing)
                    break;
                // si se acepta una conexión, crear un hilo para manejarla
                ClientThread t = new ClientThread(socket);
                // almacenar la instancia del hilo
                al.add(t);
                t.start();
            }
            // intentar detener el servidor
            try {
                serverSocket.close();
                for(int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    }
                    catch(IOException ioE) {
                    }
                }
            }
            catch(Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        }
        // si falla
        catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }
    
    // para detener el servidor
    protected void stop() {
        keepGoing = false;
        // conectar al servidor para dejarlo salir del bucle
        try {
            new Socket("localhost", port);
        }
        catch(Exception e) {
        }
    }
    
    // mostrar evento (notificación)
    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        System.out.println(time);
    }
    
    // para enviar un mensaje a todos los clientes
    private synchronized void broadcast(String message) {
        // agregar la hora al mensaje
        String time = sdf.format(new Date());
        String messageLf = time + " " + message + "\n";
        
        // imprimir en la consola del servidor
        System.out.print(messageLf);
        
        // transmitir el mensaje a todos los clientes conectados
        for(int i = al.size(); --i >= 0;) {
            ClientThread ct = al.get(i);
            // eliminar si no se puede escribir en el Cliente
            if(!ct.writeMsg(messageLf)) {
                al.remove(i);
                display("Disconnected Client " + ct.username + " removed from list.");
            }
        }
    }
    
    // si un cliente envía LOGOUT
    synchronized void remove(int id) {
        // buscar el hilo cliente
        for(int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            // encontrado
            if(ct.id == id) {
                al.remove(i);
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        // puerto por defecto
        int portNumber = 1500;
        // para obtener el número de puerto
        switch(args.length) {
            case 1:
                try {
                    portNumber = Integer.parseInt(args[0]);
                }
                catch(Exception e) {
                    System.out.println("Invalid port number.");
                    System.out.println("Usage is: > java Server [portNumber]");
                    return;
                }
            case 0:
                break;
            default:
                System.out.println("Usage is: > java Server [portNumber]");
                return;
        }
        // crear una instancia del servidor
        Server server = new Server(portNumber);
        // iniciar el servidor
        server.start();
    }

    // el hilo para manejar cada cliente
    class ClientThread extends Thread {
        // el socket para aceptar la conexión
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        // ID único (incremental)
        int id;
        // el nombre del usuario
        String username;
        // el mensaje que se enviará
        ChatMessage cm;
        // la fecha de conexión
        String date;
        
        // constructor
        ClientThread(Socket socket) {
            // ID único
            id = ++uniqueId;
            this.socket = socket;
            // crear flujos de datos
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                // leer el nombre de usuario
                username = (String) sInput.readObject();
                broadcast(notif + username + " has joined the chat room." + notif);
            }
            catch(IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            }
            catch(ClassNotFoundException e) {
            }
            // mostrar mensaje de conexión
            date = new Date().toString() + "\n";
        }
        
        // bucle infinito para leer y enviar mensajes
        public void run() {
            // bucle hasta que se reciba LOGOUT
            boolean keepGoing = true;
            while(keepGoing) {
                // leer el mensaje
                try {
                    cm = (ChatMessage) sInput.readObject();
                }
                catch(IOException e) {
                    display(username + " Exception reading Streams: " + e);
                    break;                
                }
                catch(ClassNotFoundException e2) {
                    break;
                }
                // el tipo de mensaje
                String message = cm.getMessage();
                // Switch sobre el tipo de mensaje
                switch(cm.getType()) {
                case ChatMessage.MESSAGE:
                    broadcast(username + ": " + message);
                    break;
                case ChatMessage.LOGOUT:
                    display(username + " disconnected with a LOGOUT message.");
                    keepGoing = false;
                    break;
                case ChatMessage.WHOISIN:
                    writeMsg("List of the users connected at " + sdf.format(new Date()) + "\n");
                    // enumerar todos los usuarios
                    for(int i = 0; i < al.size(); ++i) {
                        ClientThread ct = al.get(i);
                        writeMsg((i+1) + ") " + ct.username + " since " + ct.date);
                    }
                    break;
                }
            }
            // eliminar el cliente de la lista
            remove(id);
            close();
            broadcast(notif + username + " has left the chat room." + notif);
        }
        
        // cerrar todo
        private void close() {
            try {
                if(sOutput != null) sOutput.close();
            }
            catch(Exception e) {}
            try {
                if(sInput != null) sInput.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch(Exception e) {}
        }
        
        // escribir un mensaje al cliente
        private boolean writeMsg(String msg) {
            // si el cliente está aún conectado
            if(!socket.isConnected()) {
                close();
                return false;
            }
            // escribir al flujo de salida
            try {
                sOutput.writeObject(msg);
            }
            catch(IOException e) {
                display(notif + "Error sending message to " + username + notif);
                display(e.toString());
            }
            return true;
        }
    }
}
