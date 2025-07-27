package ejemplos;

import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear un nuevo empleado y pedir sus datos
        Empleado emp = new Empleado();
        System.out.print("Ingrese nombre del empleado: ");
        emp.setNombre(sc.nextLine());
        System.out.print("Ingrese DPI del empleado: ");
        emp.setCedula(sc.nextLine());

        // Pedir los datos de la computadora
        System.out.print("Ingrese serie de la computadora: ");
        String serie = sc.nextLine();
        System.out.print("Ingrese marca de la computadora: ");
        String marca = sc.nextLine();

        // Crear la computadora y asignarle el empleado responsable
        Computadora comp = new Computadora(serie, marca);
        comp.setResponsable(emp); 

        // Mostrar los datos
        System.out.println("\n--- Datos ---");
        System.out.println(comp); 
    }
}

