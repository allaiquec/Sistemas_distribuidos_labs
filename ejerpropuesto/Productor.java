public class Productor extends Thread {
    private CubbyHole cubbyhole; // Referencia al CubbyHole
    private int numero; // Identificador del productor

    public Productor(CubbyHole c, int numero) { // Constructor
        cubbyhole = c; // Asigna el CubbyHole
        this.numero = numero; // Asigna el número de identificación
    }

    public void run() { // Método que se ejecuta cuando se inicia el hilo
        for (int i = 0; i < 10; i++) {
            cubbyhole.put(i); // Coloca el valor en el CubbyHole
            System.out.println("Productor #" + this.numero + " pone: " + i);
            try {
                sleep((int)(Math.random() * 100)); // Espera aleatoria para simular producción
            } catch (InterruptedException e) { }
        }
    }
}
