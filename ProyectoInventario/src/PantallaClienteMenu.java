import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PantallaClienteMenu extends JFrame {
    //Muestra el menú de opciones para el cliente
    private ArrayList<Producto> productos; // Productos disponibles
    private ArrayList<Producto> carrito; // Carrito del cliente

    // Constructor, carga productos y muestra opciones al cliente
    public PantallaClienteMenu() {
        setTitle("Menú Cliente - Tienda");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productos = Main.inventario.getProductos();
        carrito = Main.carrito;

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnBuscar = new JButton("Buscar Producto");
        JButton btnVerTodos = new JButton("Ver Todos los Productos");
        JButton btnVerCarrito = new JButton("Ver Carrito");
        JButton btnSalir = new JButton("Salir");

        // Eventos de los botones
        btnBuscar.addActionListener(e -> buscarProductoCodigoONombre());
        btnVerTodos.addActionListener(e -> new PantallaVerProductos(this, productos, carrito));
        btnVerCarrito.addActionListener(e -> new PantallaCarrito(this, carrito));
        btnSalir.addActionListener(e -> { new PantallaInicio(); dispose(); });

        panel.add(btnBuscar);
        panel.add(btnVerTodos);
        panel.add(btnVerCarrito);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);
    }

    // Método para buscar producto por código o nombre
    private void buscarProductoCodigoONombre() {
        String entrada = JOptionPane.showInputDialog(this, "Ingrese nombre del producto:");
        if (entrada == null) return;
        entrada = entrada.trim();
        if (entrada.isEmpty()) return;

        Producto encontrado = null;
        try {
            int cod = Integer.parseInt(entrada);
            for (Producto p : productos) {
                if (p.getCodigo() == cod) { encontrado = p; break; }
            }
        } catch (NumberFormatException ex) {
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(entrada)) { encontrado = p; break; }
            }
        }

        if (encontrado == null) { JOptionPane.showMessageDialog(this, "Producto no encontrado."); return; }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "Producto encontrado:\n" +
                        "Código: " + encontrado.getCodigo() + "\n" +
                        "Nombre: " + encontrado.getNombre() + "\n" +
                        "Precio: Q" + encontrado.getPrecio() + "\n" +
                        "Stock: " + encontrado.getCantidad() + "\n\n" +
                        "¿Desea agregarlo al carrito?",
                "Producto encontrado",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            String input = JOptionPane.showInputDialog(
                    this,
                    "¿Cuántas unidades desea? (Disponibles: " + encontrado.getCantidad() + ")",
                    "1"
            );
            if (input == null) return;

            try {
                int unidades = Integer.parseInt(input.trim());
                if (unidades > 0 && unidades <= encontrado.getCantidad()) {
                    carrito.add(new Producto(
                            encontrado.getCodigo(),
                            encontrado.getNombre(),
                            encontrado.getPrecio(),
                            unidades
                    ));
                    encontrado.setCantidad(encontrado.getCantidad() - unidades);
                    JOptionPane.showMessageDialog(this, "Agregado al carrito.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida.");
                }
            } catch (NumberFormatException ex2) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.");
            }
        }
    }
}
