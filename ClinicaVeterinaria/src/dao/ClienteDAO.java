package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Agregar Nuevo Registro
    public boolean agregarCliente(Cliente cliente) {
        // SQL para insertar un cliente. Los '?' son placeholders para evitar inyección SQL
        String sql = "INSERT INTO Clientes (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";
        
        // Uso de try-with-resources: asegura que Connection (conn) y PreparedStatement (pstmt) se cierren automáticamente
        try (Connection conn = ConexionDB.conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false; // Protección si la conexión a la DB falló
            
            // Asigna los valores del objeto Cliente a los placeholders (?) en el orden correcto
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());

            // Ejecuta la sentencia SQL, devuelve el número de filas modificadas
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Cliente " + cliente.getNombre() + " agregado exitosamente.");
                return true; // Éxito en la operación
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
            e.printStackTrace(); // Imprime el rastro del error para depuración
            return false;
        }
    }

    // Listar Todos los Registros
    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        // SQL para seleccionar todos los campos de todos los clientes ordenados por ID
        String sql = "SELECT id_cliente, nombre, apellido, telefono, email, fecha_registro FROM Clientes ORDER BY id_cliente";
        
        // Uso de try-with-resources para cerrar conn, pstmt, y ResultSet (rs)
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) { //se usa para SELECT
            
            if (conn == null) return listaClientes; // Protección si la conexión falló

            while (rs.next()) {
                // Crea un nuevo objeto Cliente usando los datos de la fila actual del ResultSet
                Cliente c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getDate("fecha_registro")
                );
                listaClientes.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
        return listaClientes; // Devuelve la lista de clientes (vacía si hubo error o no hay registros)
    }
    
    // Actualizar Registro
    public boolean actualizarCliente(Cliente cliente) {
        // SQL UPDATE. El 'WHERE id_cliente=?' es crucial para saber qué registro modificar
        String sql = "UPDATE Clientes SET nombre=?, apellido=?, telefono=?, email=? WHERE id_cliente=?";
        
        // Uso de try-with-resources
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Asigna los nuevos valores de los campos
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            // Asigna el ID del cliente para la cláusula WHERE
            pstmt.setInt(5, cliente.getIdCliente()); 

            int filasAfectadas = pstmt.executeUpdate(); // se usa para UPDATE

            if (filasAfectadas > 0) {
                System.out.println("Cliente con ID " + cliente.getIdCliente() + " actualizado.");
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    //Eliminar Registro
    public boolean eliminarCliente(int idCliente) {
        // SQL DELETE. El WHERE asegura que solo se elimine el cliente con ese ID
        String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
        
        // Uso de try-with-resources
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (conn == null) return false;

            // Asigna el ID del cliente a eliminar
            pstmt.setInt(1, idCliente);

            int filasAfectadas = pstmt.executeUpdate(); // se usa para DELETE
            
            if (filasAfectadas > 0) {
                System.out.println("Cliente con ID " + idCliente + " eliminado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró cliente con ID " + idCliente + " para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            // captura errores de clave foránea 
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}