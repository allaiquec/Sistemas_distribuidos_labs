public class Simulation {
    public static void main(String args[]) {
        SimulatorMonitor sm = new SimulatorMonitor(); // Instancia del monitor del simulador
        // Crear un hilo servidor e iniciarlo //
        Server srv = new Server(sm); // Instancia del servidor
        srv.start(); // Inicia el servidor
        Client clv[] = new Client[3]; // Arreglo de clientes
        // Crear hilos clientes e iniciarlos //
        for (int i = 0; i < 3; i++) {
            clv[i] = new Client(i, sm); // Instancia de cliente
            clv[i].start(); // Inicia el cliente
        }

    }
}
