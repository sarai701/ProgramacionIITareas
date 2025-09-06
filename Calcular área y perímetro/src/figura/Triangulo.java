package figura;

public class Triangulo extends FiguraGeometrica {

    private double base;
    private double altura;

    public Triangulo(double b, double h) {
        super("Triángulo");
        this.base = b;
        this.altura = h;
    }

    public double getArea() {
        return (this.base * this.altura) / 2;
    }

    public double getPerimetro() {
        return this.base * 3;
    }

    public String toString() {
        return super.toString() + " Triángulo{" +
                "base=" + base +
                ", altura=" + altura +
                '}';
    }
}
