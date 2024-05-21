import java.util.HashMap;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Clase que representa el inventario de la farmacia
public class Stock extends UnicastRemoteObject implements StockInterface {
    // HashMap que almacena los medicamentos por su nombre
    private HashMap<String, MedicineInterface> medicines = new HashMap<>();

    // Constructor
    public Stock() throws RemoteException {
        super();
    }

    // Método para agregar un nuevo medicamento al inventario
    public void addMedicine(String name, float price, int stock) throws Exception {
        // Crear un nuevo objeto de medicamento y añadirlo al HashMap
        medicines.put(name, new Medicine(name, price, stock));
    }

    // Método remoto para comprar un medicamento del inventario
    @Override
    public MedicineInterface buyMedicine(String name, int amount) throws Exception {
        // Obtener el medicamento del HashMap por su nombre
        MedicineInterface aux = medicines.get(name);
        // Verificar si el medicamento existe en el inventario
        if (aux == null) {
            throw new Exception("Imposible to find " + name);
        }
        // Realizar la compra del medicamento y devolverlo
        MedicineInterface element = aux.getMedicine(amount);
        return element;
    }

    // Método remoto para obtener el inventario completo de medicamentos
    @Override
    public HashMap<String, MedicineInterface> getStockProducts() throws Exception {
        return this.medicines;
    }
}
