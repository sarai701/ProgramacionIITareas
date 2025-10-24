package dao;

import modelo.Cliente;
import java.util.List;

public class TestearCliente {

    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
 
        int idClienteAProbar = 1; 

        System.out.println("--- 1.Agregando cliente ---");
        Cliente nuevoCliente = new Cliente("Prueba", "Temp", "111-222", "temp@test.com");
        dao.agregarCliente(nuevoCliente); 
        
        System.out.println("\n--- 2. Consulta ---");
        List<Cliente> clientes = dao.listarClientes();
        clientes.forEach(c -> System.out.println("ID: " + c.getIdCliente() + ", Nombre: " + c.getNombre() + ", Email: " + c.getEmail()));

        if (!clientes.isEmpty()) {
            idClienteAProbar = clientes.get(clientes.size() - 1).getIdCliente();
        } else {
            System.out.println("No hay clientes para probar Cambio y Baja.");
            return;
        }

        System.out.println("\n--- 3. Actualizando Cliente " + idClienteAProbar + " ---");
        Cliente clienteAActualizar = new Cliente(idClienteAProbar, "Prueba", "ACTUALIZADO", "999-999", "nuevo@email.com", null);
        dao.actualizarCliente(clienteAActualizar);

        System.out.println("\n--- 4. Listado---");
        dao.listarClientes().forEach(c -> System.out.println("ID: " + c.getIdCliente() + ", Nombre: " + c.getNombre() + ", Apellido: " + c.getApellido()));

        System.out.println("\n--- 5. Eliminando Cliente  " + idClienteAProbar + " ---");
        dao.eliminarCliente(idClienteAProbar);

        System.out.println("\n--- 6. Listado ---");
        dao.listarClientes().forEach(c -> System.out.println("ID: " + c.getIdCliente() + ", Nombre: " + c.getNombre()));
    }
}