import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaVerProductos extends JFrame {
    //Muestra la lista completa de productos y permite agregar al carrito
    // Constructor, recibe la ventana padre, la lista de productos y el carrito
    public PantallaVerProductos(JFrame parent, ArrayList<Producto> productos, ArrayList<Producto> carrito) {
        setTitle("Lista de Productos - Tienda");
        setSize(600, 400);
        setLocationRelativeTo(null);

        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código","Nombre","Precio","Disponibles"},0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable tabla = new JTable(modelo);

        // Llenar tabla con los productos
        for (Producto p : productos) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad()});
        }

        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnAgregar = new JButton("Agregar al Carrito");
        JButton btnRegresar = new JButton("Regresar al Menú Cliente");

        // agrega producto al carrito
        btnAgregar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila < 0) { JOptionPane.showMessageDialog(this,"Seleccione un producto."); return; }

            Producto seleccionado = productos.get(fila);
            if (seleccionado.getCantidad() <= 0) { JOptionPane.showMessageDialog(this,"Producto agotado."); return; }

            String input = JOptionPane.showInputDialog(this, "¿Cuántas unidades desea? (Disponibles: "+seleccionado.getCantidad()+")","1");
            if (input == null) return;

            try {
                int unidades = Integer.parseInt(input.trim());
                if(unidades <= 0 || unidades > seleccionado.getCantidad()) {
                    JOptionPane.showMessageDialog(this,"Cantidad inválida."); return;
                }
                carrito.add(new Producto(seleccionado.getCodigo(), seleccionado.getNombre(), seleccionado.getPrecio(), unidades));
                seleccionado.setCantidad(seleccionado.getCantidad() - unidades);
                modelo.setValueAt(seleccionado.getCantidad(), fila, 3);
                JOptionPane.showMessageDialog(this,"Agregado al carrito.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Debe ingresar un número válido.");
            }
        });

        // regresar al menú cliente
        btnRegresar.addActionListener(e -> { parent.setVisible(true); dispose(); });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnRegresar);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }
}
