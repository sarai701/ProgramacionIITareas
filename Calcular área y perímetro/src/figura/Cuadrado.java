package figura;

public class Cuadrado extends FiguraGeometrica {

    private double lado;

    public Cuadrado(double l) {
        super("Cuadrado");
        this.lado = l;
    }

    public double getArea() {
        return this.lado * this.lado;
    }

    public double getPerimetro() {
        return 4 * this.lado;
    }

    public String toString() {
        return super.toString() + " Cuadrado{" + "lado=" + lado + '}';
    }
}
