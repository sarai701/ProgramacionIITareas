package ejemplos;
 
public class Vehiculo {

    private String marca; // Marca del vehículo 
    private String modelo; // Modelo del vehículo
    private Motor motor;  // Representa el motor del vehículo

    //Constructor que inicializa la marca y el modelo del vehículo.
    public Vehiculo(String pMarca, String pModelo) 
    {
        setMarca(pMarca);
        setModelo(pModelo);
    }

    // Establece la marca del vehículo
    public void setMarca(String pMarca) 
    {marca = pMarca;}

    // Devuelve la marca del vehículo
    public String getMarca() 
    {return marca;}

    // Establece el modelo del vehículo
    public void setModelo(String pModelo) 
    {modelo = pModelo;}

    // Devuelve el modelo del vehículo
    public String getModelo() 
    {return modelo;}

    // Asigna un motor al vehículo
    public void setMotor(Motor pMotor) 
    {motor=pMotor;}

    // Devuelve el motor del vehículo
    public Motor getMotor() {
        return motor;
    }

    // Muestra los datos del vehículo y su motor
    public String toString() 
    {
        return "Vehiculo:" + marca + ",Modelo: " + modelo + "\n" +
               "\t" + motor.toString(); // Llama al toString del motor
    }
}

