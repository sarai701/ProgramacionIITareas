import java.util.ArrayList;

public class arregloEmpleado {
	public static void  main(String[] args) {
		ArrayList <Empleado> listaEmpleado= new ArrayList<Empleado>();
		listaEmpleado.ensureCapacity(11);
		
		listaEmpleado.add(new Empleado("Juan", 50, 7000));
		listaEmpleado.add(new Empleado("Juan", 150, 17000));
		listaEmpleado.add(new Empleado("Juan", 250, 27000));
		
		listaEmpleado.trimToSize();
		
		System.out.println(listaEmpleado.size());
		for(Empleado e: listaEmpleado) {
			System.out.println(e.datos());
		}
	}

}
