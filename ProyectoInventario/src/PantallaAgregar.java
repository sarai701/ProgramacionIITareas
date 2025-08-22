import javax.swing.*;
import java.awt.*;

public class PantallaAgregar extends JFrame {
    //Permite al administrador añadir nuevos productos al inventario
    // Constructor, configura la interfaz gráfica para agregar productos
    public PantallaAgregar() {
        setTitle("Agregar Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con GridLayout para los campos y botones
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Componentes de entrada
        JLabel lblCodigo = new JLabel("Código:");
        JTextField txtCodigo = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();

        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();

        // Botones
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        //Crea un nuevo producto y lo agrega al inventario
        btnGuardar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText().trim());
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());

                Main.inventario.agregarProducto(new Producto(codigo, nombre, precio, cantidad));
                JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
                // Limpia los campos
                txtCodigo.setText(""); txtNombre.setText(""); txtPrecio.setText(""); txtCantidad.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: valores inválidos.");
            }
        });

        //Regresa al menú administrador
        btnCancelar.addActionListener(e -> new PantallaAdmin());

        // Agrega componentes al panel
        panel.add(lblCodigo); panel.add(txtCodigo);
        panel.add(lblNombre); panel.add(txtNombre);
        panel.add(lblPrecio); panel.add(txtPrecio);
        panel.add(lblCantidad); panel.add(txtCantidad);
        panel.add(btnGuardar); panel.add(btnCancelar);

        // Añadir panel a la ventana y mostrar
        add(panel);
        setVisible(true);
    }
}
