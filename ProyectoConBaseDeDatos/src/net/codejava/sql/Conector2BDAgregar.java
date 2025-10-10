package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector2BDAgregar {

    public static void main(String[] args) {
        
        // 1. URL de Conexión
    	// Cambiar la URL en tu código de Java
    	String url = "jdbc:sqlserver://.\\SQLEXPRESS;databaseName=umg;trustServerCertificate=true;";
        
        // 2. Credenciales (¡AJUSTA LA CONTRASEÑA!)
        String user = "sa"; 
        String password = "TU_CONTRASEÑA_SA"; // <--- ¡CAMBIA ESTO!
        
        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a la base de datos!");
            
            // 3. Sentencia SQL para INSERTAR un nuevo cliente
            // NOTA: El IdCliente es IDENTITY (se genera automáticamente), por eso no se incluye.
            String sqlInsert = "INSERT INTO CLIENTE (Nombre, Apellido, Email) " +
                               "VALUES ('Fernando', 'López', 'fernando.lopez@email.com')";
            
            // 4. Crear el objeto Statement y ejecutar la inserción
            Statement statement = connection.createStatement();
            int filasAfectadas = statement.executeUpdate(sqlInsert);

            // 5. Verificar si la inserción fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("✅ Inserción de 1 registro en la tabla CLIENTE exitosa.");
            } else {
                System.out.println("⚠️ No se pudo insertar el registro.");
            }
            
            // 6. Cerrar la conexión
            connection.close();

        } catch (SQLException e) {
            System.out.println("❌ Hay un error en la conexión o la consulta SQL.");
            e.printStackTrace();
        }
    }
}