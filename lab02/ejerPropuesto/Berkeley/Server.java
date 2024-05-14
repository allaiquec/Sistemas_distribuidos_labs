public class Server extends Thread {
    private SimulatorMonitor sm; // Referencia al monitor de la simulación
    private final int sleepMSeconds; // Tiempo en milisegundos que el servidor duerme entre actualizaciones
    private long serverTime; // Tiempo del servidor en nanosegundos

    public Server(SimulatorMonitor sm) {
        this.sm = sm; // Asigna el monitor de la simulación
        this.sleepMSeconds = 10000; // 10 segundos
        this.serverTime = System.nanoTime(); // Establece el tiempo inicial del servidor en nanosegundos
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(this.sleepMSeconds); // Duerme durante el tiempo especificado
                // Despierta
                /**
                 * Configura la hora del servidor en SimulatorMonitor, avisando a los clientes
                 * de que ya está configurada. El servidor se pondrá a dormir.
                 **/
                System.out.println("Temporización (servidor) : " + this.serverTime);
                this.sm.setServerTime(this.serverTime);
                /**
                 * Una vez despierto, los clientes ya habrán configurado las diferencias en el
                 * array. Se debe calcular la media y configurar los ajustes.
                 **/
                this.sm.calcAvgAndSet();
                /** Ajustar la hora del servidor (horaServidor + media) **/
                this.serverTime += this.sm.getAverage();
                /** Imprimir la temporización acordada **/
                System.out.println("_________________________________");
                System.out.println("Temporización acordada (servidor) : " + this.serverTime);
                /** Cuando el servidor finalice, se debe restaurar el estado inicial **/
                this.sm.restartProcess();
            } catch (InterruptedException e) {
                // Maneja las excepciones de interrupción del hilo
            }
        }
    }
}
