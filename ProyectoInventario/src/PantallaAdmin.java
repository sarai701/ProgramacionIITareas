import javax.swing.*;
import java.awt.*;

public class PantallaAdmin extends JFrame {
    //Representa el menú para el administrador
    // Constructor, inicializa los componentes gráficos y eventos del menú administrador
    public PantallaAdmin() {
        setTitle("Menú Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con GridLayout (6 filas, 1 columna)
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        // Botones del menú
        JButton btnBuscar = new JButton("Buscar producto");
        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnEliminar = new JButton("Eliminar producto");
        JButton btnInventario = new JButton("Inventario");
        JButton btnSalir = new JButton("Salir");

        //Busca productos por código
        btnBuscar.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(this, "Ingrese código del producto:");
            if (entrada == null) return;
            try {
                int codigo = Integer.parseInt(entrada.trim());
                Producto p = Main.inventario.buscarProducto(codigo);
                JOptionPane.showMessageDialog(this, p != null ? p.toString() : "Producto no encontrado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.");
            }
        });

        // Abre pantalla para agregar producto
        btnAgregar.addActionListener(e -> new PantallaAgregar());

        //Eliminar producto por código
        btnEliminar.addActionListener(e -> {
            String entrada = JOptionPane.showInputDialog(this, "Ingrese código del producto a eliminar:");
            if (entrada == null) return;
            try {
                int codigo = Integer.parseInt(entrada.trim());
                Main.inventario.eliminarProducto(codigo);
                JOptionPane.showMessageDialog(this, "Producto eliminado si existía.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.");
            }
        });

        //Mostrar pantalla de inventario
        btnInventario.addActionListener(e -> new PantallaInventario(this));

        //Salir al inicio
        btnSalir.addActionListener(e -> { new PantallaInicio(); dispose(); });

        //Agregar botones al panel
        panel.add(btnBuscar);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnInventario);
        panel.add(btnSalir);

        // Añadir panel a la ventana y mostrar
        add(panel);
        setVisible(true);
    }
}
