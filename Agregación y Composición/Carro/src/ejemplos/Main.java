package ejemplos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir los datos del motor al usuario
        Motor motor = new Motor();
        System.out.print("Ingrese VIN del motor: ");
        motor.setVin(sc.nextLine());
        System.out.print("Ingrese número de cilindros del motor: ");
        motor.setCilindros(sc.nextLine());

        // Pedir los datos del vehículo al usuario
        System.out.print("Ingrese marca del vehículo: ");
        String marca = sc.nextLine();
        System.out.print("Ingrese modelo del vehículo: ");
        String modelo = sc.nextLine();

        // Crear objeto Vehiculo y asignarle el motor creado
        Vehiculo vehiculo = new Vehiculo(marca, modelo);
        vehiculo.setMotor(motor);  // Relacionar motor con vehículo

        // Imprimir los datos del vehículo y su motor
        System.out.println("\n--- Datos del Vehículo ---");
        System.out.println(vehiculo);
    }
}
