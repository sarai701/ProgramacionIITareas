package modelo;
import java.util.Date; // Se usa para manejar la fecha y hora de la cita.

public class Cita {
     //Atributos 
    //clave primaria en la BD
    private int idCita;
    private Date fechaHora; 
    private String motivo;
    private String estado;

    //Claves Foráneas Relaciones
    private int idMascota;
    private int idPersonal;
    private int idServicio;

    public Cita() {}

    public Cita(Date fechaHora, String motivo, String estado, int idMascota, int idPersonal, int idServicio) {
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.idMascota = idMascota;
        this.idPersonal = idPersonal;
        this.idServicio = idServicio;
    }

    public Cita(int idCita, Date fechaHora, String motivo, String estado, int idMascota, int idPersonal, int idServicio) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
        this.idMascota = idMascota;
        this.idPersonal = idPersonal;
        this.idServicio = idServicio;
    }

    //Getters y Setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }
    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getIdMascota() { return idMascota; }
    public void setIdMascota(int idMascota) { this.idMascota = idMascota; }
    public int getIdPersonal() { return idPersonal; }
    public void setIdPersonal(int idPersonal) { this.idPersonal = idPersonal; }
    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }
}