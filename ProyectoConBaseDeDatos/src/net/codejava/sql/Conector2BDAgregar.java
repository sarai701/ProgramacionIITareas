package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector2BDAgregar {

    public static void main(String[] args) {
        
    	String url = "jdbc:sqlserver://.\\SQLEXPRESS;databaseName=umg;trustServerCertificate=true;";
        
        String user = "sa"; 
        String password = "TU_CONTRASEÑA_SA"; 
        Connection connection = null;

        try {
            
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a la base de datos!");
          
            String sqlInsert = "INSERT INTO CLIENTE (Nombre, Apellido, Email) " +
                               "VALUES ('Fernando', 'López', 'fernando.lopez@email.com')";
            
        
            Statement statement = connection.createStatement();
            int filasAfectadas = statement.executeUpdate(sqlInsert);

          
            if (filasAfectadas > 0) {
                System.out.println("✅ Inserción de 1 registro en la tabla CLIENTE exitosa.");
            } else {
                System.out.println("⚠️ No se pudo insertar el registro.");
            }
         
            connection.close();

        } catch (SQLException e) {
            System.out.println("❌ Hay un error en la conexión o la consulta SQL.");
            e.printStackTrace();
        }
    }
}