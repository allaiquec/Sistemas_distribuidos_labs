import java.rmi.Naming;
import java.util.Scanner;
import java.util.HashMap;

// Clase que representa el lado del cliente en la aplicación de la farmacia
public class ClienteSide {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Buscar el objeto remoto de la farmacia utilizando RMI
        StockInterface pharm = (StockInterface) Naming.lookup("PHARMACY");

        // Mostrar opciones al usuario
        System.out.println("Ingresa la opción:\n" +
                "1: Listar productos\n" +
                "2: Comprar Producto\n");

        // Leer la selección del usuario
        int selection = sc.nextInt();

        if (selection == 1) {
            // Obtener el stock de productos desde el servidor
            HashMap<String, MedicineInterface> aux = (HashMap<String, MedicineInterface>) pharm.getStockProducts();
            // Mostrar la información de cada producto en el stock
            for (String key : aux.keySet()) {
                MedicineInterface e = (MedicineInterface) aux.get(key);
                System.out.println(e.print());
                System.out.println("*--------------*");
            }
        } else if (selection == 2) {
            // Comprar un producto
            System.out.println("Ingrese nombre de la medicina");
            String medicine = sc.next();
            System.out.println("Ingrese cantidad a comprar");
            int amount = sc.nextInt();
            // Realizar la compra del medicamento y mostrar la información
            MedicineInterface aux = pharm.buyMedicine(medicine, amount);
            System.out.println("Usted acaba de comprar:");
            System.out.println(aux.print());
        } else {
            // Opción inválida
            System.out.println("Seleccione una opción válida");
        }
        sc.close(); // Cerrar el scanner
    }
}
