package modelo;
import java.util.Date; // Se usa para manejar la fecha en la que se registró el cliente.

public class Cliente {
    //Atributos del Cliente
    
    //clave primaria en la BD
    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Date fechaRegistro;
    //constructor
    public Cliente() {}

    //Constructor usado para crear un nuevo cliente antes de insertarlo en la BD
    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(int idCliente, String nombre, String apellido, String telefono, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(int idCliente, String nombre, String apellido, String telefono, String email, Date fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    //Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() { 
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() { 
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() { 
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() { 
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getFechaRegistro() { 
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}