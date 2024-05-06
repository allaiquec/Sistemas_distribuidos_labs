public class CajeraThread extends Thread {
    private String nombre;
    private Cliente cliente;
    private long initialTime;
    
    public CajeraThread() {
    }

    // Constructor con parámetros
    public CajeraThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public long getInitialTime() {
        return initialTime;
    }
    
    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método run que define la ejecución del hilo
    @Override
    public void run() {
        // Imprime un mensaje indicando que la cajera comienza a procesar la compra del cliente
        System.out.println("La cajera " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " +
                this.cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - this.initialTime) / 1000 +
                "seg");
        
        // Recorre el carro de compras del cliente
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            // Espera el tiempo necesario para procesar el producto
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            // Imprime un mensaje indicando que el producto ha sido procesado
            System.out.println("Procesado el producto " + (i + 1) +
                    " del cliente " + this.cliente.getNombre() + "->Tiempo: " +
                    (System.currentTimeMillis() - this.initialTime) / 1000 +
                    "seg");
        }
        
        // Imprime un mensaje indicando que la cajera ha terminado de procesar la compra del cliente
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
                this.cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - this.initialTime) / 1000 +
                "seg");
    }
    
    // Método privado para simular la espera de segundos
    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}