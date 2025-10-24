package modelo;

public class Personal {
    private int idPersonal;
    private String nombre;
    private String apellido;
    private String cargo;
    private String licencia; 

    public Personal() {}

    public Personal(String nombre, String apellido, String cargo, String licencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.licencia = licencia;
    }

    public Personal(int idPersonal, String nombre, String apellido, String cargo, String licencia) {
        this.idPersonal = idPersonal;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.licencia = licencia;
    }

    public int getIdPersonal() { return idPersonal; }
    public void setIdPersonal(int idPersonal) { this.idPersonal = idPersonal; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }
}