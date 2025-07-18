//Priscila Guzmán
import java.util.Scanner;

public class IVA{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Define la constante del 12% del IVA
        final double IVA_PORCENTAJE = 0.12;
        //Variable que acumula el total de todos los productos comprados
        double totalGeneral = 0;
        //Varable para continuar agregando productos
        String continuar;

        System.out.println("==== SISTEMA CON IVA ====");
        
        //Bucle para ingresar varios productos
        do {
            double precioUnitario = 0;
            int cantidad = 0;

            // Validar que el precio sean números y positivos
            do {
                System.out.print("Ingrese el precio unitario del producto (Q): ");
                //Se muestra un mensaje de error si no se ingresa un número
                while (!sc.hasNextDouble()) {
                    System.out.println("ERROR: Ingrese un número válido.");
                    sc.next();
                }
                precioUnitario = sc.nextDouble();
                
                //Que no sea negativo el número
                if (precioUnitario < 0) {
                    System.out.println("El precio no puede ser negativo.");
                }
            } while (precioUnitario < 0);

            // Validar la cantidad sean números y positivos
            do {
                System.out.print("Ingrese la cantidad de unidades: ");
                //Se muestra un mensaje de error si no se ingresa un número 
                while (!sc.hasNextInt()) {
                    System.out.println("ERROR: Ingrese un número válido.");
                    sc.next();
                }
                cantidad = sc.nextInt();
                
                //Que no sea negativo el número
                if (cantidad < 0) {
                    System.out.println("ERROR: La cantidad no puede ser negativa.");
                }
            } while (cantidad < 0);

            //Calcula el subtotal, IVA y el total del producto 
            double subtotal = precioUnitario * cantidad;
            double iva = subtotal * IVA_PORCENTAJE;
            double total = subtotal + iva;
            totalGeneral += total;

            System.out.println("\n==== PRODUCTO INGRESADO ====");
            System.out.printf("Subtotal: Q%.2f\n", subtotal);
            System.out.printf("IVA (12%%): Q%.2f\n", iva);
            System.out.printf("Total por este producto: Q%.2f\n", total);

            //Para agregar nuevos productos
            System.out.print("\n¿Desea ingresar otro producto? (s/n): ");
            sc.nextLine(); // limpia el salto de línea (buffer)
            continuar = sc.nextLine().toLowerCase();

            //Se repite si responde que si agregar producto
        } while (continuar.equals("s") || continuar.equals("si"));

        System.out.println("\n===== TOTAL DE COMPRA =====");
        System.out.printf("Total a pagar (incluye IVA): Q%.2f\n", totalGeneral);

        sc.close();
    }
}