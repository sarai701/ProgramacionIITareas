package dao;

import modelo.Cita; // Importa la clase Cita, que es el modelo de datos.
import java.sql.*; // Clases para trabajar con la base de datos (conexiones, sentencias, resultados).
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter; // Clase para escribir en archivos (usada para CSV).
import java.io.IOException; // Manejo de excepciones de entrada/salida.

public class CitaDAO {

	//Agrega una nueva cita a la base de datos.
    public boolean agregarCita(Cita cita) {
        // Sentencia SQL para insertar una nueva fila en la tabla Citas.
        String sql = "INSERT INTO Citas (fecha_hora, motivo, estado, id_mascota, id_personal, id_servicio) VALUES (?, ?, ?, ?, ?, ?)";
        
        // Uso de try-with-resources para asegurar que Connection y PreparedStatement se cierren automáticamente.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false; // Verifica si la conexión a la DB falló.
            
            // Establece los valores de los parámetros de la sentencia SQL.
            pstmt.setTimestamp(1, new Timestamp(cita.getFechaHora().getTime())); // Convierte java.util.Date a java.sql.Timestamp
            
            pstmt.setString(2, cita.getMotivo());
            pstmt.setString(3, cita.getEstado());
            pstmt.setInt(4, cita.getIdMascota());
            pstmt.setInt(5, cita.getIdPersonal());
            pstmt.setInt(6, cita.getIdServicio());

            // Ejecuta la actualización (INSERT) y devuelve true si se afectó al menos una fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la inserción.
            System.err.println("Error al agregar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Cita> listarCitas() {
        List<Cita> listaCitas = new ArrayList<>();
        // Sentencia SQL para seleccionar todos los campos de las citas, ordenadas por fecha descendente.
        String sql = "SELECT id_cita, fecha_hora, motivo, estado, id_mascota, id_personal, id_servicio FROM Citas ORDER BY fecha_hora DESC";
        
        // try-with-resources para Connection, PreparedStatement y ResultSet.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (conn == null) return listaCitas; // Si no hay conexión, devuelve la lista vacía.

            // Itera sobre los resultados de la consulta.
            while (rs.next()) {
                // Crea un nuevo objeto Cita con los datos de la fila actual del ResultSet.
                Cita c = new Cita(
                    rs.getInt("id_cita"),
                    rs.getTimestamp("fecha_hora"),
                    rs.getString("motivo"),
                    rs.getString("estado"),
                    rs.getInt("id_mascota"),
                    rs.getInt("id_personal"),
                    rs.getInt("id_servicio")
                );
                listaCitas.add(c); // Agrega la cita a la lista.
            }
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la lectura.
            System.err.println("Error al listar citas: " + e.getMessage());
        }
        return listaCitas; // Devuelve la lista de citas.
    }

    //Actualiza los datos de una cita existente en la base de datos.
    public boolean actualizarCita(Cita cita) {
        // Sentencia SQL para actualizar todos los campos de una cita, identificada por id_cita.
        String sql = "UPDATE Citas SET fecha_hora=?, motivo=?, estado=?, id_mascota=?, id_personal=?, id_servicio=? WHERE id_cita=?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Establece los nuevos valores de los campos (los primeros 6 parámetros).
            pstmt.setTimestamp(1, new Timestamp(cita.getFechaHora().getTime()));
            pstmt.setString(2, cita.getMotivo());
            pstmt.setString(3, cita.getEstado());
            pstmt.setInt(4, cita.getIdMascota());
            pstmt.setInt(5, cita.getIdPersonal());
            pstmt.setInt(6, cita.getIdServicio());
            
            pstmt.setInt(7, cita.getIdCita()); // Establece el ID de la cita a actualizar (cláusula WHERE).

            // Ejecuta la actualización (UPDATE) y retorna el resultado.
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores de SQL. El mensaje sugiere verificar IDs foráneos.
            System.err.println("Error al actualizar cita. Verifique que todos los IDs (Mascota, Personal, Servicio) existan: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
     //Elimina una cita de la base de datos usando su ID.
    public boolean eliminarCita(int idCita) {
        // Sentencia SQL para eliminar una fila basada en su ID.
        String sql = "DELETE FROM Citas WHERE id_cita = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;
            
            pstmt.setInt(1, idCita); // Establece el ID de la cita a eliminar.
            // Ejecuta la eliminación (DELETE) y retorna si se eliminó alguna fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la eliminación.
            System.err.println("Error al eliminar cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    

 //Genera un reporte de todas las citas y lo guarda en un archivo CSV.
    public boolean generarReporteCSV(String rutaArchivo) {
        // Consulta para obtener todos los datos necesarios para el reporte.
        String sql = "SELECT id_cita, fecha_hora, motivo, estado, id_mascota, id_personal, id_servicio FROM Citas ORDER BY fecha_hora DESC";
        
        // try-with-resources que maneja la conexión, la consulta y el escritor de archivos.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             FileWriter writer = new FileWriter(rutaArchivo)) { // Se abre el archivo para escribir.

            if (conn == null) return false;

            // Escribe la fila de encabezados (nombres de las columnas) en el CSV.
            writer.append("ID Cita,Fecha y Hora,Motivo,Estado,ID Mascota,ID Personal,ID Servicio\n");

            // Itera sobre los resultados de la base de datos.
            while (rs.next()) {
                // Escribe cada campo seguido de una coma (separador de CSV).
                writer.append(String.valueOf(rs.getInt("id_cita"))).append(",");
                writer.append(rs.getTimestamp("fecha_hora").toString()).append(",");
                // Maneja comillas dentro del campo 'motivo' para evitar problemas en CSV.
                writer.append("\"").append(rs.getString("motivo").replace("\"", "\"\"")).append("\"").append(","); 
                writer.append(rs.getString("estado")).append(",");
                writer.append(String.valueOf(rs.getInt("id_mascota"))).append(",");
                writer.append(String.valueOf(rs.getInt("id_personal"))).append(",");
                writer.append(String.valueOf(rs.getInt("id_servicio"))).append("\n"); // Fin de la fila
            }
            
            writer.flush(); // Asegura que todos los datos se escriban al disco.
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error de SQL al generar reporte: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // Maneja el error si no se puede escribir el archivo (permisos, ruta, etc.).
            System.err.println("Error de I/O al escribir el archivo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}