package facturacion;

public class Linea {
 private float cantidad; // Cantidad comprada
 private String codigo;  // Código del producto
 private String descripcion;// Descripción del producto
 private float precio;// Precio unitario

 // Constructor que inicializa todos los atributos
 public Linea(float pcantidad, String pcodigo, String pdescripcion, float pprecio) {
     setCantidad(pcantidad);
     setCodigo(pcodigo);
     setDescripcion(pdescripcion);
     setPrecio(pprecio);
 }

 // Métodos para establecer los atributos
 public void setCantidad(float cantidad) { this.cantidad = cantidad; }
 public void setCodigo(String codigo) { this.codigo = codigo; }
 public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
 public void setPrecio(float precio) { this.precio = precio; }

 // Calcula el costo total 
 public float calcularCosto() {
     return cantidad * precio;
 }

 // Devuelve la información de esta línea en una sola cadena
 public String toString() {
     String msg = "";
     msg += cantidad + "\t";
     msg += codigo + "\t";
     msg += descripcion + "\t";
     msg += precio + "\t";
     msg += calcularCosto();
     return msg;
 }
}
