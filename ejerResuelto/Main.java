public class Main {
    public static void main(String[] args) {
        // Creación de clientes
        Cliente cliente1 = new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2, 3 });
        Cliente cliente2 = new Cliente("Cliente 2", new int[] { 1, 3, 5, 1, 1 });
        // Creación de cajeras
        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        // Procesamiento de la compra del cliente 1 por la cajera 1
        cajera1.procesarCompra(cliente1, initialTime);
        // Procesamiento de la compra del cliente 2 por la cajera 2
        cajera2.procesarCompra(cliente2, initialTime);
    }
}

