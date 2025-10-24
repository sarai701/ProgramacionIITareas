package dao;

import modelo.Servicio; // Importa la clase Servicio, que es el modelo de datos.
import java.sql.*; // Clases para trabajar con JDBC (conexión, sentencias, resultados).
import java.util.ArrayList; // Clase para manejar listas dinámicas.
import java.util.List; // Interfaz para colecciones tipo lista.
import java.math.BigDecimal; // Se usa para manejar valores monetarios (precios) con precisión.

public class ServicioDAO {
    //Agrega un nuevo servicio a la tabla 'Servicios'.
    public boolean agregarServicio(Servicio servicio) {
        // Sentencia SQL preparada para insertar un nuevo servicio.
        String sql = "INSERT INTO Servicios (nombre_servicio, precio) VALUES (?, ?)";
        
        // Uso de try-with-resources: asegura que la conexión y el PreparedStatement se cierren.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false; // Verifica si la conexión a la DB falló.

            // Asigna el nombre del servicio al parámetro 1.
            pstmt.setString(1, servicio.getNombreServicio());
            // Asigna el precio al parámetro 2. Se usa BigDecimal para alta precisión monetaria.
            pstmt.setBigDecimal(2, servicio.getPrecio()); 

            // Ejecuta el INSERT y devuelve true si se afectó al menos una fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la inserción.
            System.err.println("Error al agregar servicio: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Servicio> listarServicios() {
        List<Servicio> listaServicios = new ArrayList<>();
        // Sentencia SQL para seleccionar todos los campos de servicios, ordenada por ID.
        String sql = "SELECT id_servicio, nombre_servicio, precio FROM Servicios ORDER BY id_servicio";
        
        // try-with-resources para Connection, PreparedStatement y ResultSet.
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { // Ejecuta la consulta (SELECT).

            if (conn == null) return listaServicios;

            // Itera sobre los resultados de la consulta.
            while (rs.next()) {
                // Crea un nuevo objeto Servicio con los datos de la fila actual del ResultSet.
                Servicio s = new Servicio(
                    rs.getInt("id_servicio"),
                    rs.getString("nombre_servicio"),
                    rs.getBigDecimal("precio") // Obtiene el precio como BigDecimal.
                );
                listaServicios.add(s);
            }
        } catch (SQLException e) {
            // Manejo de errores de SQL durante la lectura de datos.
            System.err.println("Error al listar servicios: " + e.getMessage());
        }
        return listaServicios; // Devuelve la lista de servicios.
    }

    //Actualiza el nombre y el precio de un servicio existente.
    public boolean actualizarServicio(Servicio servicio) {
        // Sentencia SQL para actualizar campos, usando id_servicio para el filtro (WHERE).
        String sql = "UPDATE Servicios SET nombre_servicio=?, precio=? WHERE id_servicio=?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Establece los nuevos valores para el nombre y precio (parámetros 1 y 2).
            pstmt.setString(1, servicio.getNombreServicio());
            pstmt.setBigDecimal(2, servicio.getPrecio()); 
            
            pstmt.setInt(3, servicio.getIdServicio()); // Parámetro 3: El ID del servicio a modificar.

            // Ejecuta el UPDATE y retorna el resultado.
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores de SQL durante la actualización.
            System.err.println("Error al actualizar servicio: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

 //Elimina un servicio de la base de datos utilizando su ID.
    public boolean eliminarServicio(int idServicio) {
        // Sentencia SQL para eliminar una fila basada en su ID.
        String sql = "DELETE FROM Servicios WHERE id_servicio = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;
            
            pstmt.setInt(1, idServicio); // Establece el ID del servicio a eliminar.
            
            // Ejecuta el DELETE y devuelve true si se eliminó alguna fila.
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio. Puede haber citas asociadas: " + e.getMessage());
            return false;
        }
    }
}