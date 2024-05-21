import java.rmi.server.UnicastRemoteObject;

/**
 * Esta es la clase Medicina para este proyecto. Permite comprar y consultar la
 * lista de medicinas.
 * Implementa la interfaz MedicineInterface para proporcionar métodos remotos.
 */
public class Medicine extends UnicastRemoteObject implements MedicineInterface {
    private String name; // Nombre del medicamento
    private float unitPrice; // Precio unitario del medicamento
    private int stock; // Stock actual del medicamento

    // Constructor por defecto
    public Medicine() throws Exception {
        super();
    }

    // Constructor para inicializar el medicamento con nombre, precio y stock
    public Medicine(String name, float price, int stock) throws Exception {
        super();
        this.name = name;
        unitPrice = price;
        this.stock = stock;
    }

    // Método remoto para obtener una cantidad específica de medicamento
    @Override
    public Medicine getMedicine(int amount) throws Exception {
        // Verificar si hay suficiente stock disponible
        if (this.stock <= 0)
            throw new StockException("Stock empty");
        if (this.stock - amount < 0)
            throw new StockException("Stock not amount of medicine");

        // Actualizar el stock y calcular el precio total
        this.stock -= amount;
        Medicine aux = new Medicine(name, unitPrice * amount, stock);
        return aux;
    }

    // Método remoto para obtener el stock actual del medicamento
    @Override
    public int getStock() throws Exception {
        return this.stock;
    }

    // Método para imprimir información sobre el medicamento
    public String print() throws Exception {
        return this.name + "\nPrice: " + this.unitPrice + "\nStock: " + this.stock;
    }
}
