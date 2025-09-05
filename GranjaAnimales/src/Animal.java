
// Implementa clonación y atributos
public class Animal implements Cloneable {
    protected int patas;   
    private int ojos;    
    private String nombre;

    // Constructor 
    public Animal(int patas, int ojos, String nombre) {
        if (patas < 0 || patas > 4) throw new IllegalArgumentException("Las patas no pueden ser negativas.");
        if (ojos < 0 || ojos > 2) throw new IllegalArgumentException("Número de ojos inválido.");
        this.patas = patas;
        this.ojos = ojos;
        this.nombre = nombre;
    }

    // Métodos getter y setter de nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Métodos getter y setter de patas
    public int getPatas() { return patas; }
    public void setPatas(int patas) {
        if (patas < 0 || patas > 4) throw new IllegalArgumentException("Las patas no pueden ser negativas.");
        this.patas = patas;
    }

    // Métodos getter y setter de ojos
    public int getOjos() { return ojos; }
    public void setOjos(int ojos) {
        if (ojos < 0 || ojos > 2) throw new IllegalArgumentException("Número de ojos inválido.");
        this.ojos = ojos;
    }

    // Método que puede ser sobrescrito por las subclases
    public String habla() {
        return "Soy un animal";
    }

    // Implementación del método clone()
    public Animal clone() {
        try {
            return (Animal) super.clone(); // Clonación superficial
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // No debería ocurrir
        }
    }

    public String toString() {
        return habla() + ", Nombre: " + nombre + ", Patas: " + patas + ", Ojos: " + ojos;
    }
}
