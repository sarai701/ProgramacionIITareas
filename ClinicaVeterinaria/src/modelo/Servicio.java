package modelo;

import java.math.BigDecimal; // Importa BigDecimal para manejar valores monetarios con precisión.

public class Servicio {
    //Atributos
    //clave primaria en la BD
    private int idServicio;
    private String nombreServicio; 
    private BigDecimal precio;   

    //Constructor por defecto 
    public Servicio() {}
    public Servicio(String nombreServicio, BigDecimal precio) {
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }
    public Servicio(int idServicio, String nombreServicio, BigDecimal precio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
    }

    //Getters y Setters 
    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }
    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
}