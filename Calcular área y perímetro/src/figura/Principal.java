package figura;

import java.util.Scanner;

// Clase principal que maneja el menú y ejecución
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FiguraGeometrica figura = null;

        int opcion;
        do {
            System.out.println("\n=== MENÚ DE FIGURAS GEOMÉTRICAS ===");
            System.out.println("1. Calcular área y perímetro de un cuadrado");
            System.out.println("2. Calcular área y perímetro de un triángulo");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el lado del cuadrado: ");
                    double lado = sc.nextDouble();
                    figura = new Cuadrado(lado);
                    break;

                case 2:
                    System.out.print("Ingrese la base del triángulo: ");
                    double base = sc.nextDouble();
                    System.out.print("Ingrese la altura del triángulo: ");
                    double altura = sc.nextDouble();
                    figura = new Triangulo(base, altura);
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

            // Mostrar resultados si se eligió una figura
            if (figura != null) {
                System.out.println("\n--- RESULTADO ---");
                System.out.println("Figura: " + figura.getNombreFigura());
                System.out.println("Área: " + figura.getArea());
                System.out.println("Perímetro: " + figura.getPerimetro());
                System.out.println(figura.toString());
                figura = null; 
            }

        } while (opcion != 3);

        sc.close();
    }
}

