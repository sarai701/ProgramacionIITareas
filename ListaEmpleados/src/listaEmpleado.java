
public class listaEmpleado {
	
	public static void main(String[] args) {
		
		Empleado listaEmpleado[]= new Empleado[3];
		
		listaEmpleado[0] = new Empleado("Juan", 50, 5000);
		listaEmpleado[1] = new Empleado("Pedro", 150, 15000);
		listaEmpleado[2] = new Empleado("Pablo", 250, 25000);
		
		for (Empleado e: listaEmpleado) {
			System.out.println(e.datos());
		}
	}

}
