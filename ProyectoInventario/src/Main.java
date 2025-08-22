import javax.swing.*;
import java.util.ArrayList;

public class Main {
    // Clase principal que inicializa el sistema
    // Contiene el inventario global y el carrito
    public static Inventario inventario = new Inventario();
    public static ArrayList<Producto> carrito = new ArrayList<>();

    public static void main(String[] args) {
        //Prductos del inventario
        inventario.agregarProducto(new Producto(1, "Laptop", 8000, 5));
        inventario.agregarProducto(new Producto(2, "Mouse", 150, 20));
        inventario.agregarProducto(new Producto(3, "Teclado", 300, 15));
        inventario.agregarProducto(new Producto(4, "Monitor", 2000, 8));

        new PantallaInicio();
    }
}
