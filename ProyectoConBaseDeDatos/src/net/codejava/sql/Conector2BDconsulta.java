package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector2BDconsulta {

    public static void main(String[] args) {
        
    	String url = "jdbc:sqlserver://localhost:1433;databaseName=umg;integratedSecurity=true;trustServerCertificate=true;";
        
        Connection connection = null;

        try {
            // Establecer la conexión URL
            connection = DriverManager.getConnection(url); 
            System.out.println("Conexión exitosa a la base de datos!");
            
            // Consulta SQL a la tabla HOTEL
            String sql = "SELECT IdHotel, Nombre FROM HOTEL";
            
            // Ejecutar la consulta
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // Mostrar resultados
            System.out.println("\n--- Lista de Hoteles ---");
            while (result.next()) {
                String codigohotel = result.getString("IdHotel");
                String nombrehotel = result.getString("Nombre");
                System.out.printf("Codigo: %s | Nombre: %s\n", codigohotel, nombrehotel);
            }
            
            // Cerrar la conexión
            connection.close();

        } catch (SQLException e) {
            System.out.println("Hay un error:");
            e.printStackTrace();
        }
    }
}