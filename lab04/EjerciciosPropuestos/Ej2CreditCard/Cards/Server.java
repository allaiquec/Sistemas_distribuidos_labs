package Cards;

import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            // Creando instancia del servidor
            CardImplement server = new CardImplement();
            // Publicando el objeto remoto en el registro RMI
            Naming.rebind("Server", server);
            System.out.println("Servidor listo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}