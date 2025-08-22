import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaInicio extends JFrame {
    //pantalla inicial para elegir entre Administrador o Cliente
    // Constructor, configura la interfaz inicial
    public PantallaInicio() {
        setTitle("Sistema de Inventario - Tienda");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titulo = new JLabel("Sistema de Inventario - Tienda", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnAdmin = new JButton("Ingresar como Administrador");
        JButton btnCliente = new JButton("Ingresar como Cliente");

        // para abrir pantallas
        btnAdmin.addActionListener(e -> {
            new PantallaAdmin();
            dispose();
        });

        btnCliente.addActionListener(e -> {
            new PantallaClienteMenu();
            dispose();
        });

        panel.add(titulo);
        panel.add(btnAdmin);
        panel.add(btnCliente);

        add(panel);
        setVisible(true);
    }

    // Método principal, inicia la aplicación mostrando esta pantalla
    public static void main(String[] args) {
        new PantallaInicio();
    }
}

