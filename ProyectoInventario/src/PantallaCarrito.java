import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaCarrito extends JFrame {
    //Muestra los productos que el cliente ha agregado al carrito
  
    private final JFrame parent; // Ventana anterior, para regresar
    private ArrayList<Producto> carrito; // Lista de productos en el carrito
    private JTable tabla; // Tabla para mostrar productos
    private JLabel lblTotal; // Etiqueta para mostrar el total de la compra
    private DefaultTableModel modelo; // Modelo de la tabla

    // Constructor, recibe la ventana anterior y el carrito actual
    public PantallaCarrito(JFrame parent, ArrayList<Producto> carrito) {
        this.parent = parent;
        this.carrito = carrito;

        setTitle("Carrito de Compras");
        setSize(600, 350);
        setLocationRelativeTo(null);

        // Columnas de la tabla
        String[] columnas = {"Código", "Nombre", "Precio Unitario", "Cantidad", "Subtotal"};
        modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        // Llenar la tabla con los productos del carrito y calcular el total
        double total = 0;
        for (Producto p : carrito) {
            double subtotal = p.getPrecio() * p.getCantidad();
            modelo.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad(), subtotal});
            total += subtotal;
        }

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        lblTotal = new JLabel("Total: Q" + total, SwingConstants.RIGHT);

        // Botones
        JButton btnFinalizar = new JButton("Finalizar Compra");
        JButton btnRegresar = new JButton("Regresar al Menú Cliente");

        //Muestra el recibo, limpia el carrito y la tabla
        btnFinalizar.addActionListener(e -> {
            if (carrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío.");
                return;
            }

            StringBuilder recibo = new StringBuilder("RECIBO DE COMPRA\n\n");
            double totalFinal = 0;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String codigo = String.valueOf(modelo.getValueAt(i, 0));
                String nombre = String.valueOf(modelo.getValueAt(i, 1));
                double precio = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 2)));
                int cantidad = Integer.parseInt(String.valueOf(modelo.getValueAt(i, 3)));
                double subtotal = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 4)));

                recibo.append(codigo).append(" - ").append(nombre)
                      .append(" x").append(cantidad)
                      .append("  Q").append(subtotal).append("\n");
                totalFinal += subtotal;
            }
            recibo.append("\nTOTAL: Q").append(totalFinal);

            JOptionPane.showMessageDialog(this, recibo.toString(), "Compra realizada", JOptionPane.INFORMATION_MESSAGE);

            carrito.clear();
            modelo.setRowCount(0);
            lblTotal.setText("Total: Q0.00");
        });

        //vuelve al menú anterior
        btnRegresar.addActionListener(e -> { parent.setVisible(true); dispose(); });

        // Panel para botones y total
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnFinalizar);
        panelBotones.add(btnRegresar);

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(lblTotal, BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        setVisible(true);
    }
}
