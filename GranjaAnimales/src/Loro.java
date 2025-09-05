
public class Loro extends Animal {
    public Loro(int patas, int ojos, String nombre) {
        super(patas, ojos, nombre);
    }

    public String habla() {
        return "Soy un Loro y " + super.habla();
    }

    // Implementa clonación
    public Loro clone() {
        return (Loro) super.clone();
    }
}
