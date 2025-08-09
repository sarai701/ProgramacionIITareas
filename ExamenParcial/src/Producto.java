public class Producto {
    // Atributos privados
    private String codigo;
    private String descripcion;
    private double precioUnitario;

    // Constructor que inicializa todos los atributos
    public Producto(String codigo, String descripcion, double precioUnitario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    // Métodos públicos Get
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    // Métodos públicos Set
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Método público para mostrar la información del producto
    public void mostrarInfo() {
        System.out.println("Código: " + codigo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio Unitario: Q" + precioUnitario);
        System.out.println("-------------------------");
    }
}
