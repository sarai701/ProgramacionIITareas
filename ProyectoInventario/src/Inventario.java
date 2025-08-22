import java.util.ArrayList;
import java.util.Comparator;

public class Inventario {
    // Clase que hace una lista de productos

    private ArrayList<Producto> productos;

    // Constructor, inicializa la lista vacía de productos
    public Inventario() {
        productos = new ArrayList<>();
    }

    // Agrega un nuevo producto al inventario
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Elimina un producto por el código
    public void eliminarProducto(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
    }

    // Busca y devuelve un producto por código
    public Producto buscarProducto(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    // Retorna la lista completa de productos
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // Imprime en consola todos los productos.
    public void mostrarProductos() {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    // Muestra información general y lista ordenada por stock
    public void mostrarInventario() {
        System.out.println("Total de productos: " + productos.size());
        productos.stream()
                 .sorted(Comparator.comparingInt(Producto::getCantidad).reversed())
                 .forEach(System.out::println);
    }
}
