import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        String continuar;

        do {
            
            System.out.println("\nIngrese producto:");
            System.out.print("Código: ");
            String codigo = sc.nextLine();
            System.out.print("Descripción: ");
            String descripcion = sc.nextLine();
            System.out.print("Precio Unitario: ");
            double precio = sc.nextDouble();
            sc.nextLine();

            // Crear objeto producto y lo agrega a la lista
            Producto prod = new Producto(codigo, descripcion, precio);
            listaProductos.add(prod);

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            continuar = sc.nextLine().trim().toLowerCase();

        } while (continuar.equals("s"));

        System.out.println("\n=== Productos Ingresados ===");
        for (Producto p : listaProductos) {
            p.mostrarInfo();
        }

        sc.close();
    }
}

