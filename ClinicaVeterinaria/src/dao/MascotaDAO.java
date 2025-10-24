package dao;

import modelo.Mascota; // Importa la clase Mascota, que representa el modelo de datos.
import java.sql.*; // Clases para trabajar con JDBC (conexión, sentencias, resultados).
import java.util.ArrayList; // Clase para manejar listas dinámicas.
import java.util.List; // Interfaz para colecciones tipo lista.

public class MascotaDAO {
    //Agrega un nuevo registro de mascota a la base de datos.

    public boolean agregarMascota(Mascota mascota) {
        // Sentencia SQL preparada para insertar una nueva mascota. Los '?' son placeholders.
        String sql = "INSERT INTO Mascotas (nombre, especie, raza, fecha_nacimiento, id_cliente) VALUES (?, ?, ?, ?, ?)";

        // Uso de try-with-resources: asegura que la conexión y el PreparedStatement se cierren.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false; // Verifica que la conexión sea válida.

            // Asigna los valores del objeto Mascota a los parámetros de la consulta SQL.
            pstmt.setString(1, mascota.getNombre());
            pstmt.setString(2, mascota.getEspecie());
            pstmt.setString(3, mascota.getRaza());

            // Convierte java.util.Date a java.sql.Date para la base de datos.
            pstmt.setDate(4, new java.sql.Date(mascota.getFechaNacimiento().getTime())); 
            
            pstmt.setInt(5, mascota.getIdCliente()); // Clave foránea que relaciona la mascota con su dueño (Cliente).

            // Ejecuta el INSERT y devuelve true si se afectó al menos una fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Captura y muestra errores de SQL, como problemas de conexión o datos.
            System.err.println("Error al agregar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Mascota> listarMascotas() {
        List<Mascota> listaMascotas = new ArrayList<>();
        // Sentencia SQL para seleccionar todos los campos, ordenada por ID.
        String sql = "SELECT id_mascota, nombre, especie, raza, fecha_nacimiento, id_cliente FROM Mascotas ORDER BY id_mascota";
        
        // try-with-resources para Connection, PreparedStatement y ResultSet (resultados).
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta (SELECT). 

            if (conn == null) return listaMascotas;
            
            // Itera sobre cada fila devuelta por la base de datos.
            while (rs.next()) {
                // Crea un nuevo objeto Mascota con los datos de la fila actual.
                Mascota m = new Mascota(
                    rs.getInt("id_mascota"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getDate("fecha_nacimiento"), // Obtiene la fecha como java.sql.Date.
                    rs.getInt("id_cliente")
                );
                listaMascotas.add(m); // Añade la mascota a la lista.
            }
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la lectura de datos.
            System.err.println("Error al listar mascotas: " + e.getMessage());
        }
        return listaMascotas; // Devuelve la lista completa de mascotas.
    }

    //Actualiza los datos de una mascota existente en la base de datos.
     
    public boolean actualizarMascota(Mascota mascota) {
        // Sentencia SQL para actualizar campos, usando id_mascota para el filtro (WHERE).
        String sql = "UPDATE Mascotas SET nombre=?, especie=?, raza=?, fecha_nacimiento=?, id_cliente=? WHERE id_mascota=?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Establece los nuevos valores para los campos (parámetros 1 al 5).
            pstmt.setString(1, mascota.getNombre());
            pstmt.setString(2, mascota.getEspecie());
            pstmt.setString(3, mascota.getRaza());
            pstmt.setDate(4, new java.sql.Date(mascota.getFechaNacimiento().getTime()));
            pstmt.setInt(5, mascota.getIdCliente()); 
            
            pstmt.setInt(6, mascota.getIdMascota()); // Parámetro 6: El ID de la mascota a modificar.

            // Ejecuta el UPDATE y retorna el resultado.
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores de SQL durante la actualización.
            System.err.println("Error al actualizar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


   //Elimina una mascota de la base de datos utilizando su ID.
    public boolean eliminarMascota(int idMascota) {
        // Sentencia SQL para eliminar una fila basada en su ID.
        String sql = "DELETE FROM Mascotas WHERE id_mascota = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;
            
            pstmt.setInt(1, idMascota); // Establece el ID de la mascota a eliminar.
            
            // Ejecuta el DELETE y devuelve true si se eliminó alguna fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la eliminación.
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}