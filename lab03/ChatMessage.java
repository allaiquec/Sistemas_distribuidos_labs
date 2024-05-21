import java.io.*;

// Esta clase define los diferentes tipos de mensajes que se intercambiarán entre los Clientes y el Servidor.
// Al comunicarse entre un Cliente Java y un Servidor Java, es mucho más fácil pasar objetos Java, 
// no es necesario contar bytes o esperar un salto de línea al final del marco.
public class ChatMessage implements Serializable {

    // Los diferentes tipos de mensajes enviados por el Cliente:
    // WHOISIN para recibir la lista de usuarios conectados
    // MESSAGE un mensaje de texto ordinario
    // LOGOUT para desconectarse del Servidor
    static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;
    
    // Tipo de mensaje
    private int type;
    // Contenido del mensaje
    private String message;
    
    // Constructor
    ChatMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }
    
    // Obtener el tipo de mensaje
    int getType() {
        return type;
    }
    
    // Obtener el contenido del mensaje
    String getMessage() {
        return message;
    }
}
