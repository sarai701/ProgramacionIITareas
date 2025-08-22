public class Producto {
    // Representa un producto
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor, inicializa todos los atributos del producto
    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters para obtener información del producto
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    // Setter para modificar la cantidad disponible
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    
    public String toString() {
        return codigo + " | " + nombre + " | Q" + precio + " | Stock: " + cantidad;
    }
}
