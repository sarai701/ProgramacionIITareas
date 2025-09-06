package figura;

// Clase base abstracta para figuras geométricas
// solo gestiona nombre y  calcula área/perímetro
public abstract class FiguraGeometrica implements Calcular {

    private String nombreFigura;

    public FiguraGeometrica(String nombre) {
        this.nombreFigura = nombre;
    }

    public String getNombreFigura() {
        return nombreFigura;
    }

    public void setNombreFigura(String nombreFigura) {
        this.nombreFigura = nombreFigura;
    }

    public String toString() {
        return "FiguraGeometrica{" +
                "nombreFigura=" + nombreFigura +
                ", area=" + getArea() +
                ", perimetro=" + getPerimetro() +
                '}';
    }
}
