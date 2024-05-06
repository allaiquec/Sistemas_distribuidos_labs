public class Consumidor extends Thread {
    private CubbyHole cubbyhole; // Referencia al CubbyHole
    private int numero; // Identificador del consumidor

    public Consumidor(CubbyHole c, int numero) { // Constructor
        cubbyhole = c; // Asigna el CubbyHole
        this.numero = numero; // Asigna el número de identificación
    }

    public void run() { // Método que se ejecuta cuando se inicia el hilo
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyhole.get(); // Obtiene el contenido del CubbyHole
            System.out.println("Consumidor #" + this.numero + " obtiene: " + value);
        }
    }
}
