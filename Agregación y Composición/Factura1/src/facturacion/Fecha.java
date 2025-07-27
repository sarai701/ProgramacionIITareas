package facturacion;

public class Fecha {
 private int dia;
 private int mes;
 private int anio;

 // Constructor que inicializa la fecha
 public Fecha(int pdia, int pmes, int panio) {
     setDia(pdia);
     setMes(pmes);
     setAnio(panio);
 }

 // Métodos para establecer día, mes y año
 public void setDia(int dia) { this.dia = dia; }
 public void setMes(int mes) { this.mes = mes; }
 public void setAnio(int anio) { this.anio = anio; }

 // Devuelve la fecha como una cadena 
 public String toString() {
     return dia + "/" + mes + "/" + anio;
 }
}
