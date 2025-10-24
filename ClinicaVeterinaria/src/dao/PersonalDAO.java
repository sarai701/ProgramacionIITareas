package dao;

import modelo.Personal; // Importa la clase Personal, que es el modelo de datos.
import java.sql.*; // Clases para trabajar con JDBC (conexión, sentencias, resultados).
import java.util.ArrayList; // Clase para manejar listas dinámicas.
import java.util.List; // Interfaz para colecciones tipo lista.

public class PersonalDAO {

    public boolean agregarPersonal(Personal personal) {
        // Sentencia SQL preparada para insertar un nuevo registro de personal.
        String sql = "INSERT INTO Personal (nombre, apellido, cargo, licencia) VALUES (?, ?, ?, ?)";
        
        // Uso de try-with-resources: asegura que la conexión y el PreparedStatement se cierren.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false; // Verifica si la conexión a la DB falló.

            // Asigna los valores del objeto Personal a los parámetros de la consulta SQL.
            pstmt.setString(1, personal.getNombre());
            pstmt.setString(2, personal.getApellido());
            pstmt.setString(3, personal.getCargo());
            pstmt.setString(4, personal.getLicencia()); // Por ejemplo, un número de licencia profesional.

            // Ejecuta el INSERT y devuelve true si se afectó al menos una fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la inserción.
            System.err.println("Error al agregar personal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //Recupera todos los registros de personal de la base de datos.
    public List<Personal> listarPersonal() {
        List<Personal> listaPersonal = new ArrayList<>();
        // Sentencia SQL para seleccionar todos los campos, ordenada por ID de personal.
        String sql = "SELECT id_personal, nombre, apellido, cargo, licencia FROM Personal ORDER BY id_personal";
        
        // try-with-resources para Connection, PreparedStatement y ResultSet.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta (SELECT).

            if (conn == null) return listaPersonal;

            // Itera sobre los resultados de la consulta.
            while (rs.next()) {
                // Crea un nuevo objeto Personal con los datos de la fila actual del ResultSet.
                Personal p = new Personal(
                    rs.getInt("id_personal"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("cargo"),
                    rs.getString("licencia")
                );
                listaPersonal.add(p); // Agrega el objeto Personal a la lista.
            }
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la lectura de datos.
            System.err.println("Error al listar personal: " + e.getMessage());
        }
        return listaPersonal; // Devuelve la lista completa de personal.
    }

    //Actualiza los datos de un empleado existente en la base de datos.
    public boolean actualizarPersonal(Personal personal) {
        // Sentencia SQL para actualizar campos, usando id_personal para el filtro (WHERE).
        String sql = "UPDATE Personal SET nombre=?, apellido=?, cargo=?, licencia=? WHERE id_personal=?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Establece los nuevos valores para los campos (parámetros 1 al 4).
            pstmt.setString(1, personal.getNombre());
            pstmt.setString(2, personal.getApellido());
            pstmt.setString(3, personal.getCargo());
            pstmt.setString(4, personal.getLicencia()); 
            
            pstmt.setInt(5, personal.getIdPersonal()); // Parámetro 5: El ID del empleado a modificar.

            // Ejecuta el UPDATE y retorna el resultado.
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores de SQL durante la actualización.
            System.err.println("Error al actualizar personal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //Elimina un empleado de la base de datos utilizando su ID.
    public boolean eliminarPersonal(int idPersonal) {
        // Sentencia SQL para eliminar una fila basada en su ID.
        String sql = "DELETE FROM Personal WHERE id_personal = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;
            
            pstmt.setInt(1, idPersonal); // Establece el ID del empleado a eliminar.
            
            // Ejecuta el DELETE y devuelve true si se eliminó alguna fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la eliminación.
            System.err.println("Error al eliminar personal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}