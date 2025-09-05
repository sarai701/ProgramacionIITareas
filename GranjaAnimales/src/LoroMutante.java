// Clase que hereda de Loro
public class LoroMutante extends Loro {
    public LoroMutante(int patas, int ojos, String nombre) {
        super(patas, ojos, nombre);
    }

    // Método propio de LoroMutante
    public String vuela() {
        return "Vuelo por los cielos...";
    }

    // Implementa clonación
    public LoroMutante clone() {
        return (LoroMutante) super.clone();
    }
}
