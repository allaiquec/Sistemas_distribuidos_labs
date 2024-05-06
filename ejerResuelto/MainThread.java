public class MainThread {
    public static void main(String[] args) {
        // Creación de clientes
        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        // Creación de cajeras con clientes y tiempo inicial
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);
        // Inicio de los hilos para ejecutar el procesamiento de compras
        cajera1.start();
        cajera2.start();
    }
}

