public class Demo {
    public static void main(String[] args) {
        CubbyHole cub = new CubbyHole(); // Crea un CubbyHole
        Consumidor cons = new Consumidor(cub, 1); // Crea un consumidor
        Productor prod = new Productor(cub, 1); // Crea un productor

        prod.start(); // Inicia el hilo del productor
        cons.start(); // Inicia el hilo del consumidor
    }
}
