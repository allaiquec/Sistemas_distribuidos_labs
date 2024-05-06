public class Cajera {
    private String nombre;
    
    public Cajera() {
    }
    
    // Constructor con parámetro nombre
    public Cajera(String nombre) {
        this.nombre = nombre;
    }
    
    // Método para obtener el nombre de la cajera
    public String getNombre() {
        return nombre;
    }
    
    // Método para establecer el nombre de la cajera
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Método para procesar la compra del cliente
    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println("La cajera " + this.nombre +
                " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() +
                " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                "seg");
        
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) +
                    " ->Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
                    "seg");
        }
        
        System.out.println("La cajera " + this.nombre + " HA TERMINADO DE PROCESAR " +
                cliente.getNombre() + " EN EL TIEMPO: " +
                (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
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