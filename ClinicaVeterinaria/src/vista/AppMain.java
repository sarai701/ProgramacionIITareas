package vista;

import javax.swing.SwingUtilities; 

public class AppMain {

    public static void main(String[] args) {
        
        // Ejecuta la lógica de la GUI 
        SwingUtilities.invokeLater(() -> {
            
            // Define un valor de desplazamiento para que las ventanas no se superpongan completamente
            int offset = 30;
            
            // Venta de cliente
            ClienteGUI clienteGUI = new ClienteGUI();
            // Ventana de mascotas
            MascotaGUI mascotaGUI = new MascotaGUI();
            // Posiciona la ventana de Mascotas desplazada respecto a la de Clientes.
            mascotaGUI.setLocation(clienteGUI.getX() + offset, clienteGUI.getY() + offset);
          
            // Ventana de personal
            PersonalGUI personalGUI = new PersonalGUI();
            // Posiciona la ventana de Personal con un doble desplazamiento.
            personalGUI.setLocation(clienteGUI.getX() + offset * 2, clienteGUI.getY() + offset * 2);

            // Ventana de servicio
            ServicioGUI servicioGUI = new ServicioGUI();
            // Posiciona la ventana de Servicios con un triple desplazamiento.
            servicioGUI.setLocation(clienteGUI.getX() + offset * 3, clienteGUI.getY() + offset * 3);
         
            // Ventana de cita
            CitaGUI citaGUI = new CitaGUI();
            // Posiciona la ventana de Citas con un cuádruple desplazamiento.
            citaGUI.setLocation(clienteGUI.getX() + offset * 4, clienteGUI.getY() + offset * 4);
        });
    }
}