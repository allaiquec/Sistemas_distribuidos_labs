import java.rmi.Remote;

// Interfaz remota para manipular medicamentos
public interface MedicineInterface extends Remote {

    // Método para obtener una cantidad específica de medicamento
    public Medicine getMedicine(int amount) throws Exception;

    // Método para obtener el stock actual de medicamento
    public int getStock() throws Exception;

    // Método para imprimir información sobre el medicamento
    public String print() throws Exception;
}
