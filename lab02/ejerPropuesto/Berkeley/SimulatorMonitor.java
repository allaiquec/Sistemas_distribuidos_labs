public class SimulatorMonitor {
    private long serverTime; // Tiempo del servidor
    private long[] diffTimes; // Diferencias de tiempo de los clientes respecto al servidor
    private long sumDiffs; // Suma de las diferencias para calcular la media
    private final int numClients = 3; // Número de clientes
    private int countClientsOpered; // Contador de clientes que han operado

    // Constructor de la clase
    public SimulatorMonitor() {
        this.serverTime = 0;
        this.countClientsOpered = this.numClients;
        this.diffTimes = new long[this.numClients];
        this.sumDiffs = 0;
    }

    // Método para establecer el tiempo del servidor
    public synchronized void setServerTime(long serverTime) {
        this.serverTime = serverTime; // Se establece el tiempo actual del servidor
        try {
            notifyAll(); // Se avisa a todos los clientes que están esperando en la cola para establecer las diferencias
            wait(); // El servidor se pone a esperar
        } catch (InterruptedException e) {
            // Maneja las excepciones de interrupción del hilo
        }
    }

    // Método para establecer las diferencias de tiempo de un cliente
    public synchronized void setDiffTimes(long time, int n) {
        try {
            if (serverTime == 0)
                wait(); // Si el servidor todavía no ha configurado el tiempo, los hilos cliente esperan en la cola del monitor
            this.diffTimes[n] = (time - serverTime); // Se establece la diferencia de tiempo del cliente
            this.sumDiffs += time; // Se suma el tiempo del cliente a la variable sumDiffs
            countClientsOpered--; // Se decrementa el contador de clientes que han operado
            if (countClientsOpered == 0)
                notify(); // Si todos los clientes han operado, se despierta al servidor de la cola de espera
            wait(); // Los clientes esperan hasta que el servidor establezca los ajustes de tiempo en diffTime
        } catch (InterruptedException e) {
            // Maneja las excepciones de interrupción del hilo
        }
    }

    // Método para calcular la media de las diferencias y establecer los ajustes
    public synchronized void calcAvgAndSet() {
        long avg = (this.sumDiffs / (this.numClients + 1)); // Calcula la media de las diferencias
        for (int i = 0; i < this.numClients; i++)
            this.diffTimes[i] = ((-this.diffTimes[i]) + avg); // Ajusta las diferencias
        notifyAll();
    }

    // Método para obtener el ajuste de tiempo de un cliente
    public synchronized long getSettingTime(int n) {
        return this.diffTimes[n];
    }

    // Método para obtener la media de las diferencias
    public synchronized long getAverage() {
        return this.sumDiffs / (this.numClients + 1);
    }

    // Método para reiniciar el proceso
    public synchronized void restartProcess() {
        this.serverTime = 0;
        this.countClientsOpered = this.numClients;
        this.sumDiffs = 0;
    }
}
