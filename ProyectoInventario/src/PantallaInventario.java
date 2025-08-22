import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PantallaInventario extends JFrame {
    //Muestra los productos del inventario en una tabla
    private JTable tabla; // Tabla para mostrar los productos
    private DefaultTableModel modelo; // Modelo para la tabla
    private JLabel lblTotal; // Muestra el total de productos
    private JLabel lblMayorMenor; // Muestra productos ordenados por cantidad
    private ArrayList<Producto> productos; // Lista de productos

    // Constructor, recibe la ventana padre y carga los datos del inventario
    public PantallaInventario(JFrame parent) {
        setTitle("Inventario de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productos = Main.inventario.getProductos();

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad"}, 0);
        tabla = new JTable(modelo);
        cargarDatosTabla();

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelInferior = new JPanel(new GridLayout(2, 1));
        JPanel panelNorte = new JPanel();
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JButton btnRegresar = new JButton("Regresar al menú Admin");
        panelNorte.add(btnRegresar);
        btnRegresar.addActionListener(e -> { parent.setVisible(true); dispose(); });

        lblTotal = new JLabel("Total de productos: " + productos.size());
        panelNorte.add(lblTotal);

        lblMayorMenor = new JLabel(mayorMenorExistencia());
        panelNorte.add(lblMayorMenor);

        getContentPane().add(panelPrincipal, BorderLayout.NORTH);
        setVisible(true);
    }

    // Carga los productos en la tabla
    private void cargarDatosTabla() {
        modelo.setRowCount(0);
        for (Producto p : productos) {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad()});
        }
    }

    // Retorna una cadena con los productos ordenados por cantidad de mayor a menor
    private String mayorMenorExistencia() {
        if (productos.isEmpty()) return "Inventario vacío.";

        productos.sort(Comparator.comparingInt(Producto::getCantidad).reversed());

        StringBuilder sb = new StringBuilder("Productos ordenados por stock (mayor a menor): ");
        for (Producto p : productos) {
            sb.append(p.getNombre()).append(" (").append(p.getCantidad()).append("), ");
        }
        return sb.toString();
    }
}
