import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
   
    public static SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");  // Formato de hora
    public static long Timer; 

    // Convierte la hora ingresada en milisegundos
    public static Long IntToLong(int G, int M, int S) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String inputString = G + ":" + M + ":" + S; // Ajuste de la zona horaria (G-1)

        Date date = sdf.parse("1970-01-01 " + inputString); // Hora Unix
        return date.getTime();
    }

    // Clase interna que actualiza el reloj del cliente
    public static class Updater extends Thread {
        Socket client;

        public Updater(String host, int port) throws IOException {
            client = new Socket(host, port);
        }

        public void run() {
            System.out.println("Updater is working");
            InputStream inFromServer = null;
            try {
                inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                while(true) {
                    System.out.println("Test1");
                    Timer = in.readLong();  // Actualiza la hora cuando hay nuevos datos en el flujo
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    inFromServer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // Clase interna que representa el reloj interno del cliente
    public static class InternalInaccurateClock extends Thread {
        private long Drift;   // Deriva del reloj

        public InternalInaccurateClock(long how_inaccurate) {
            Drift = how_inaccurate;
            Timer = System.currentTimeMillis();
        }

        // Reinicia el reloj interno con un nuevo tiempo
        public void clockReset(Long newTime) {
            Timer = newTime;
        }

        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000 + Drift);     // 1 segundo + deriva (positiva o negativa)
                    Timer += 1000;  // Agrega un segundo al contador de tiempo
                    System.out.println(SDF.format(Timer)); // Imprime la hora inexacta
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        // Solicita al usuario que ingrese la hora en formato HH:MM:SS
        System.out.println("Ingrese la hora en formato HH");
        Integer G = sc.nextInt();
        System.out.println("Ingrese los minutos en formato MM");
        Integer M = sc.nextInt();
        System.out.println("Ingrese los segundos en formato SS");
        Integer S = sc.nextInt();
        // Solicita al usuario que ingrese la deriva del reloj en milisegundos
        System.out.println("Ingrese la deriva del reloj en milisegundos");
        Long drift = sc.nextLong();

        // Crea una instancia de InternalInaccurateClock para manejar el reloj interno del cliente
        InternalInaccurateClock IIC = new InternalInaccurateClock(drift);
        // Reinicia el reloj interno con la hora ingresada por el usuario
        IIC.clockReset(IntToLong(G, M, S));
        // Inicia el reloj interno del cliente
        IIC.start();

        // Inicia el hilo Updater para recibir actualizaciones de tiempo del servidor
        Updater U = new Updater("localhost", 1200);
        U.start();
    } 
}
