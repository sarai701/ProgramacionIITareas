package ejemplos;

public class Motor {

    private String vin; //Número del motor
    private String cilindros; // Número de cilindros del motor 

    // Establece el VIN del motor
    public void setVin(String pVin) 
    {vin = pVin;}

    // Devuelve el VIN del motor
    public String getVin() 
    {return vin;}

    // Establece los cilindros del motor
    public void setCilindros(String pCilindros) 
    {cilindros = pCilindros;}

    // Devuelve los cilindros del motor
    public String getCilindros() 
    {return cilindros;}

    // Muestra los datos del motor
    public String toString() 
    {return "Motor: " + vin + ", Cilindros: " + cilindros + "\n";}
}
