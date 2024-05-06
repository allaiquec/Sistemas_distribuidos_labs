public class CubbyHole {
    private int contents; // Contenido almacenado en el CubbyHole
    private boolean available = false; // Indica si el contenido está disponible para ser consumido

    public synchronized int get() { // Método para obtener el contenido
        while (available == false) { // Espera activa mientras no haya contenido disponible
            try {
                wait(); // Espera hasta que se llame a notify() o notifyAll() desde otro hilo
            } catch (InterruptedException e) { }
        }
        available = false; // Marca el contenido como no disponible
        notifyAll(); // Notifica a todos los hilos en espera
        return contents; // Devuelve el contenido
    }

    public synchronized void put(int value) { // Método para colocar contenido
        while (available == true) { // Espera activa mientras haya contenido disponible
            try {
                wait(); // Espera hasta que se llame a notify() o notifyAll() desde otro hilo
            } catch (InterruptedException e) { }
        }
        contents = value; // Coloca el valor en el CubbyHole
        available = true; // Marca el contenido como disponible
        notifyAll(); // Notifica a todos los hilos en espera
    }
}
