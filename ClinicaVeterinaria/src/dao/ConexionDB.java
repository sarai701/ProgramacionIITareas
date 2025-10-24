package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ConexionDB clase estática encargada de establecer y devolver una conexión activa 
//con la base de datos SQL Server "ClinicaVeterinaria".
public class ConexionDB {

    // Define la instancia del servidor SQL
    private static final String SERVER = "localhost\\SQLEXPRESS"; 
    private static final String DATABASE = "ClinicaVeterinaria";

    // URL de conexión completa
    private static final String URL = "jdbc:sqlserver://" + SERVER + 
            ";databaseName=" + DATABASE + 
            // Indica el uso de la autenticación de Windows
            ";integratedSecurity=true;" + 
            ";encrypt=true;trustServerCertificate=true;"; 

    // MÉTODO PRINCIPAL DE CONEXIÓN
	    public static Connection conectar() {
	        Connection connection = null;
	        try {
	            // La llamada getConnection intenta establecer la conexión usando la URL definida
	            connection = DriverManager.getConnection(URL);
	            System.out.println("Conexión exitosa a ClinicaVeterinaria");
	        } catch (SQLException e) {
	            // Captura errores si la conexión falla
	            System.err.println("Error al conectar a SQL Server: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return connection; // Devuelve la conexión establecida o null
	    }

    // Este método main permite ejecutar la clase por separado para verificar rápidamente la conexión
    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            try {
                // Si la conexión fue exitosa
                conn.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}