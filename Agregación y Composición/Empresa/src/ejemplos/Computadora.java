package ejemplos;


//Clase que representa una computadora con serie, marca y un empleado responsable.
public class Computadora {
    private String serie;       // Número de serie de la computadora
    private String marca;       // Marca de la computadora
    private Empleado responsable = null;  // Empleado asignado como responsable

    //Constructor que inicializa la serie y la marca.
    public Computadora(String pSerie, String pMarca) {
        setSerie(pSerie);
        setMarca(pMarca);
    }

    // Establece la serie de la computadora
    public void setSerie(String pSerie) {
        serie = pSerie;
    }

    // Devuelve la serie de la computadora
    public String getSerie() {
        return serie;
    }

    // Establece la marca de la computadora
    public void setMarca(String pMarca) {
        marca = pMarca;
    }

    // Devuelve la marca de la computadora
    public String getMarca() {
        return marca;
    }

    // Asigna un empleado como responsable de esta computadora
    public void setResponsable(Empleado pResponsable) {
        responsable = pResponsable;
    }

    // Devuelve el empleado responsable
    public Empleado getResponsable() {
        return responsable;
    }

    // Devuelve la información de la computadora como texto
    public String toString() {
        return "Computadora: " + marca + ", Serie: " + serie + "\n" +
               " \tResponsable: " + responsable.toString();
    }
}

