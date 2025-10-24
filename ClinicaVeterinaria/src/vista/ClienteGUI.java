package vista;

import javax.swing.*; // Componentes gráficos 
import java.awt.BorderLayout; // Administrador de diseño principal
import java.awt.GridLayout; // Administrador de diseño para el formulario
import java.awt.event.ActionEvent; // Clase para manejar eventos de botones
import java.awt.event.ActionListener; // Interfaz para manejar eventos de botones
import dao.ClienteDAO; // Clase para acceso a datos interacción con la BD
import modelo.Cliente; // Clase del modelo de datos de Cliente
import javax.swing.table.DefaultTableModel; // Modelo para la JTable
import java.util.List; // Para manejar colecciones de clientes

public class ClienteGUI extends JFrame { 

    private ClienteDAO clienteDAO; // Objeto para realizar operaciones CRUD en la BD

    private JTextField txtNombre, txtApellido, txtTelefono, txtEmail;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnListar;
    private JTable tablaClientes; 
    private JScrollPane scrollPane; 

    private JTextField txtIdCliente; 

    //  Constructor
    public ClienteGUI() {
        clienteDAO = new ClienteDAO(); // Inicializa el objeto DAO
        txtIdCliente = new JTextField(); // Inicializa el campo de ID
        txtIdCliente.setVisible(false); // Lo mantiene oculto al usuario

        setTitle("Gestión de Clientes - Clínica Veterinaria"); 
        setSize(750, 450); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout(10, 10)); 

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        panelFormulario.add(new JLabel("Nombre:")); 
        txtNombre = new JTextField(20); 
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Apellido:"));
        txtApellido = new JTextField(20);
        panelFormulario.add(txtApellido);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(20);
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Email:"));
        txtEmail = new JTextField(20);
        panelFormulario.add(txtEmail);
       
        JPanel panelBotones = new JPanel();
        // Inicialización de los botones
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar"); 
        // Agregar botones al panel
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        // Configuración de la JTable para mostrar datos
        tablaClientes = new JTable();
        scrollPane = new JScrollPane(tablaClientes);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Manejo de eventos
        btnListar.addActionListener(e -> cargarClientesATabla()); // Asigna la acción de listar.
        btnGuardar.addActionListener(e -> guardarCliente()); // Asigna la acción de guardar.
        btnActualizar.addActionListener(e -> actualizarCliente()); // Asigna la acción de actualizar.
        btnEliminar.addActionListener(e -> eliminarCliente()); // Asigna la acción de eliminar.
        tablaClientes.getSelectionModel().addListSelectionListener(e -> seleccionarCliente());

        
        setVisible(true); // Hace visible la ventana.
        cargarClientesATabla(); // Carga los datos al iniciar la ventana.
    }

    private void cargarClientesATabla() {
        String[] nombresColumnas = {"ID", "Nombre", "Apellido", "Teléfono", "Email", "Fecha Reg."};
        DefaultTableModel model = new DefaultTableModel(null, nombresColumnas) {
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        List<Cliente> clientes = clienteDAO.listarClientes();
       
        for (Cliente c : clientes) {
            Object[] fila = new Object[6]; 
            fila[0] = c.getIdCliente();
            fila[1] = c.getNombre();
            fila[2] = c.getApellido();
            fila[3] = c.getTelefono();
            fila[4] = c.getEmail();
            fila[5] = c.getFechaRegistro(); 
            
            model.addRow(fila); 
        }
        
        tablaClientes.setModel(model);
        limpiarCampos();
    }

    // Recoge datos y guarda un nuevo cliente en la BD
    private void guardarCliente() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        
        // Validación simple de campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre y el apellido son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crea un nuevo objeto Cliente sin el ID
        Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email);
        
        // Llama al método del DAO para guardar
        if (clienteDAO.agregarCliente(nuevoCliente)) {
            JOptionPane.showMessageDialog(this, "Cliente registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesATabla(); 
        } else {
            JOptionPane.showMessageDialog(this, "Fallo al registrar el cliente. Revise los logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCliente() {

        if (txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        int id = Integer.parseInt(txtIdCliente.getText());
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();

        Cliente clienteActualizado = new Cliente(id, nombre, apellido, telefono, email);
        if (clienteDAO.actualizarCliente(clienteActualizado)) { 
            JOptionPane.showMessageDialog(this, "Cliente actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesATabla(); 
        } else {
            JOptionPane.showMessageDialog(this, "Fallo al actualizar el cliente. Revise la consola para detalles del SQL.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarCliente() {
        if (txtIdCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(txtIdCliente.getText());
        
        // Pide confirmación al usuario antes de eliminar.
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el cliente ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llama al método del DAO para eliminar.
            if (clienteDAO.eliminarCliente(id)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarClientesATabla(); 
            } else {
               // Mensaje de error, a menudo por restricciones de clave foránea.
                JOptionPane.showMessageDialog(this, "Fallo al eliminar. El cliente puede tener registros dependientes (ej. mascotas).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada != -1 && !tablaClientes.getSelectionModel().getValueIsAdjusting()) { 
            try {
                txtIdCliente.setText(tablaClientes.getValueAt(filaSeleccionada, 0).toString()); 
                txtNombre.setText(tablaClientes.getValueAt(filaSeleccionada, 1).toString());
                txtApellido.setText(tablaClientes.getValueAt(filaSeleccionada, 2).toString());
                txtTelefono.setText(tablaClientes.getValueAt(filaSeleccionada, 3).toString());
                txtEmail.setText(tablaClientes.getValueAt(filaSeleccionada, 4).toString());
                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Error al cargar datos de la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpia el contenido de todos los campos del formulario.
    private void limpiarCampos() {
        txtIdCliente.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}