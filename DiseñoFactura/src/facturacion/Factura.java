package facturacion;

import java.util.Vector;

public class Factura {
 private String numero; // Número de la factura
 private String cliente; // Nombre del cliente
 private Fecha fecha; // Fecha de la factura 
 private Vector<Linea> detalle; // Lista de productos

 // Constructor que recibe los datos y crea la fecha y lista vacía de líneas
 public Factura(String pnumero, String pcliente, int pdia, int pmes, int panio) {
     setNumero(pnumero);
     setCliente(pcliente);
     fecha = new Fecha(pdia, pmes, panio);
     detalle = new Vector<>();
 }

 // Métodos para establecer número y cliente
 public void setNumero(String numero) { this.numero = numero; }
 public void setCliente(String cliente) { this.cliente = cliente; }

 // Calcula el subtotal sumando el costo de cada línea
 public float calcularSubtotal() {
     float subtotal = 0;
     for (int i = 0; i < detalle.size(); i++) {
         Linea lineaDetalle = detalle.get(i);
         subtotal += lineaDetalle.calcularCosto();
     }
     return subtotal;
 }

 // Calcula el impuesto del 12% sobre el subtotal
 public float calcularImpuesto() {
     return calcularSubtotal() * 12 / 100;
 }

 // Calcula el total sumando subtotal más impuesto
 public float calcularTotal() {
     return calcularSubtotal() + calcularImpuesto();
 }

 // Agrega una línea a la factura
 public void agregarLinea(float pcantidad, String pcodigo, String pdescripcion, float pprecio) {
     detalle.add(new Linea(pcantidad, pcodigo, pdescripcion, pprecio));
 }

 // Devuelve una representación en texto de toda la factura
 public String toString() {
     String msg = "";
     msg += "===============================\n";
     msg += "\tNo. " + numero + "\n";
     msg += "\tCliente: " + cliente + "\n";
     msg += "\tFecha: " + fecha.toString() + "\n";
     msg += "-------------------------------\n";
     msg += "Cant\tCodigo\tDescrp\tPrecio\tCosto\n";

     for (Linea linea : detalle) {
         msg += linea.toString() + "\n";
     }

     msg += "-------------------------------\n";
     msg += "\tSubtotal: " + calcularSubtotal() + "\n";
     msg += "\tImpuesto: " + calcularImpuesto() + "\n";
     msg += "\tTotal: " + calcularTotal() + "\n";
     msg += "===============================\n";
     return msg;
 }

 
}