public class Client extends Thread {
    private int clientID; // Identificador único del cliente
    private long clientTime; // Tiempo del cliente en nanosegundos
    private SimulatorMonitor sm; // Referencia al monitor de la simulación
    private final boolean addDelay = true; // Indica si se debe añadir un retraso específico a cada cliente

    public Client(int clientID, SimulatorMonitor sm) {
        this.sm = sm; // Asigna el monitor de la simulación
        this.clientID = clientID; // Asigna el identificador del cliente
        this.clientTime = System.nanoTime(); // Establece el tiempo inicial del cliente en nanosegundos
    }

    public void run() {
        while (true) { // Los clientes permanecen permanentemente conectados
            // Actualiza el tiempo del cliente y añade un retardo específico a cada hilo
            // El retardo se calcula como (tiempo actual + (ID del hilo + 1) * 2)
            this.clientTime += (this.addDelay) ? (this.clientID + 1) * 2 : 0;
            System.out.println("Temporización (cliente " + clientID + ") : " + this.clientTime);
            // Establece las diferencias de tiempo del cliente en el monitor de la simulación
            // Si el servidor aún no ha configurado su tiempo, los clientes esperan
            this.sm.setDiffTimes(this.clientTime, this.clientID);
            // Actualiza el tiempo del cliente basado en el ajuste proporcionado por el servidor
            this.clientTime += this.sm.getSettingTime(this.clientID);
            System.out.println("Temporización acordada (cliente " + clientID + ") : " + this.clientTime);
        }
    }
}
