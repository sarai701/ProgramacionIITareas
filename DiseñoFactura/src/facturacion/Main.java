package facturacion;

public class Main {
	public static void main(String[] args) {
     // Crea una factura para el cliente Juan Pérez
     Factura factura;
		factura = new Factura("0065433","Juan Perez",27,07, 2025);

     // Agrega productos a la factura
		factura.agregarLinea(2,"3702","anillos",5300);
     factura.agregarLinea(1,"2805","pulsera",8200);
     factura.agregarLinea(1,"1531","cadena",4100);

     // Muestra la factura completa en la consola
     System.out.println(factura.toString());
 }
}

