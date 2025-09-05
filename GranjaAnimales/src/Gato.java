
public class Gato extends Animal {
    public Gato(int patas, int ojos, String nombre) {
        super(patas, ojos, nombre);
    }

    // Sobreescribe el comportamiento de habla
    public String habla() {
        return "Soy un Gato";
    }

    // Implementa clonación
    public Gato clone() {
        return (Gato) super.clone();
    }
}
