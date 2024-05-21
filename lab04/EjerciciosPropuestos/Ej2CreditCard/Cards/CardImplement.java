package Cards;

import java.rmi.RemoteException; // Excepción para métodos remotos
import java.rmi.server.UnicastRemoteObject;  // Soporte para objetos remotos unicast
import java.util.ArrayList; // Implementación de lista dinámica
import java.util.HashMap; // Implementación de tabla hash
import java.util.List; // Interfaz de lista
import java.util.Map; // Interfaz de mapa

public class CardImplement extends UnicastRemoteObject implements CardInterface {
    private double saldo;
    
    private Map<String, Double> preciosProductos;


    // Constructor para inicializar el saldo al crear una instancia del servidor
    public CardImplement() throws RemoteException {
        saldo = 500; // Saldo inicial

        // Inicializar el mapa de precios de los productos
        preciosProductos = new HashMap<>();
        preciosProductos.put("Aspirina", 10.0);
        preciosProductos.put("Paracetamol", 15.0);
        preciosProductos.put("Mejoral", 12.0);
        preciosProductos.put("Amoxicilina", 13.0);
    }

    @Override
    public double verSaldo() throws RemoteException {
        return saldo;
    }

    @Override
    public void recargarSaldo(double monto) throws RemoteException {
        saldo += monto; // Sumando la recarga al saldo actual
    }

    @Override
    public void realizarCompra(double monto) throws RemoteException {
        if (monto <= saldo) {
            saldo -= monto; // Restando el monto de la compra al saldo actual
        }
    }

    @Override
    public void retirarSaldo(double monto) throws RemoteException {
        if (monto <= saldo) {
            saldo -= monto; // Restando el monto del retiro al saldo actual
        }
    }

    @Override
    public List<String> obtenerProductos() throws RemoteException {
        // Obtener los nombres de los productos disponibles
        return new ArrayList<>(preciosProductos.keySet());
    }

    @Override
    public double obtenerPrecioProducto(String producto) throws RemoteException {
        // Obtener el precio del producto especificado
        return preciosProductos.getOrDefault(producto, -1.0); // Devuelve -1 si el producto no existe
    }
}
