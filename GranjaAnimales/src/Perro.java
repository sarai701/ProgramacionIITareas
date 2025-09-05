
public class Perro extends Animal {
    private boolean pulgas; // Atributo

    public Perro(int patas, int ojos, boolean pulgas, String nombre) {
        super(patas, ojos, nombre);
        this.pulgas = pulgas;
    }

    public void setPulgas(boolean pulgas) { this.pulgas = pulgas; }
    public boolean getPulgas() { return pulgas; }

    // Método que demuestra acceso a atributos heredados
    public void cambiaPadre() {
        this.patas = 3; 
        setOjos(2);     
        setPatas(4);    
    }

    public String habla() {
        return "Soy un Perro";
    }

    public Perro clone() {
        return (Perro) super.clone();
    }
}
