// Clase que implementa la interfaz Runnable para definir un proceso de ejecución
public class MainRunnable implements Runnable {
    private Cliente cliente; // Cliente para el proceso
    private Cajera cajera; // Cajera para el proceso
    private long initialTime; // Tiempo inicial
    
    // Constructor que inicializa el proceso con un cliente, una cajera y un tiempo inicial
    public MainRunnable(Cliente cliente, Cajera cajera, long initialTime) {
        this.cajera = cajera;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }
    
    // Método main que inicia la ejecución de los procesos
    public static void main(String[] args) {
        // Creación de clientes y cajeras
        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        // Creación de procesos con clientes, cajeras y tiempo inicial
        Runnable proceso1 = new MainRunnable(cliente1, cajera1, initialTime);
        Runnable proceso2 = new MainRunnable(cliente2, cajera2, initialTime);
        // Inicio de los hilos para ejecutar los procesos
        new Thread(proceso1).start();
        new Thread(proceso2).start();
    }
    
    // Método que define la ejecución del proceso
    @Override
    public void run() {
        // Procesamiento de la compra por parte de la cajera para el cliente y tiempo inicial especificados
        this.cajera.procesarCompra(this.cliente, this.initialTime);
    }
}
