package ejemplos;


//Clase que representa un empleado con nombre y cédula
public class Empleado {
    private String nombre;  // Nombre del empleado
    private String cedula;  // Cédula del empleado

    // Establece el nombre del empleado
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    // Devuelve el nombre del empleado
    public String getNombre() {
        return nombre;
    }

    // Establece la cédula del empleado
    public void setCedula(String pCedula) {
        cedula = pCedula;
    }

    // Devuelve la cédula del empleado
    public String getCedula() {
        return cedula;
    }

    // Devuelve la información del empleado como texto
    public String toString() {
        return "Empleado: " + nombre + ", Cédula: " + cedula + "\n";
    }
}
