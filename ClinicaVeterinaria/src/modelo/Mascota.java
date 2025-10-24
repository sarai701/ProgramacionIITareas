package modelo;

import java.util.Date; // Se usa para manejar la fecha de nacimiento de la mascota.

public class Mascota {
    //Atributos
    // clave primaria en la BD
    private int idMascota;
    private String nombre;
    private String especie;
    private String raza;
    private Date fechaNacimiento;
   
    //clave foránea
    private int idCliente; 
    public Mascota() {}
    public Mascota(String nombre, String especie, String raza, Date fechaNacimiento, int idCliente) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idCliente = idCliente;
    }

    public Mascota(int idMascota, String nombre, String especie, String raza, Date fechaNacimiento, int idCliente) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idCliente = idCliente;
    }

    public Mascota(int idMascota, String nombre, String especie, String raza, Date fechaNacimiento, int idCliente, Date fechaRegistro) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idCliente = idCliente;
    }

    //Getters y Setters 
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}