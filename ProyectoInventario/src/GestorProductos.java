import java.util.ArrayList;

public class GestorProductos {
    // Clase encargada de las operaciones sobre productos a través del inventario

    private Inventario inventario;

    // Constructor, inicializa un nuevo objeto inventario vacío
    public GestorProductos() {
        inventario = new Inventario();
    }

    // Método para agregar un producto al inventario
    // Parámetros, código, nombre, precio y cantidad
    public void agregarProducto(int codigo, String nombre, double precio, int cantidad) {
        inventario.agregarProducto(new Producto(codigo, nombre, precio, cantidad));
    }

    // Método para eliminar un producto del inventario por su código
    public void eliminarProducto(int codigo) {
        inventario.eliminarProducto(codigo);
    }

    // Busca un producto por el código
    public Producto buscarProducto(int codigo) {
        return inventario.buscarProducto(codigo);
    }

    // Devuelve la lista completa de productos en el inventario
    public ArrayList<Producto> getProductos() {
        return inventario.getProductos();
    }

    // Muestra los productos en consola
    public void mostrarProductos() {
        inventario.mostrarProductos();
    }

    // Muestra el inventario
    public void mostrarInventario() {
        inventario.mostrarInventario();
    }
}

