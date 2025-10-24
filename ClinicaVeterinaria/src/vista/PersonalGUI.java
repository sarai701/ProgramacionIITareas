package vista;

import javax.swing.*; // Componentes gráficos 
import java.awt.BorderLayout; // Administrador de diseño principal
import java.awt.GridLayout; // Administrador de diseño para el formulario
import java.util.List; // Para manejar colecciones de personal
import dao.PersonalDAO; // Clase para acceso a datos interacción con la DB
import modelo.Personal; // Clase del modelo de datos de Personal
import javax.swing.table.DefaultTableModel; // Modelo para la JTable

public class PersonalGUI extends JFrame { 

    private PersonalDAO personalDAO; // Objeto para realizar operaciones CRUD en la BD
    private JTextField txtNombre, txtApellido, txtCargo, txtLicencia;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnListar;
    private JTable tablaPersonal; // Tabla para mostrar la lista de personal
    private JTextField txtIdPersonal; 
    
    //Constructor
    public PersonalGUI() {
        personalDAO = new PersonalDAO(); // Inicializa el objeto DAO
        txtIdPersonal = new JTextField(); // Inicializa el campo de ID.
        txtIdPersonal.setVisible(false); 
        setTitle("Gestión de Personal (Veterinarios)"); 
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

        panelFormulario.add(new JLabel("Cargo:"));
        txtCargo = new JTextField(20);
        panelFormulario.add(txtCargo);

        panelFormulario.add(new JLabel("Licencia (Única):")); // Indica que el valor debe ser único
        txtLicencia = new JTextField(20);
        panelFormulario.add(txtLicencia);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar ");
        
        // Agregar botones al panel
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        tablaPersonal = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaPersonal);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Manejo de eventos
        btnListar.addActionListener(e -> cargarPersonalATabla()); //acción de listar
        btnGuardar.addActionListener(e -> guardarPersonal()); //acción de guardar
        btnActualizar.addActionListener(e -> actualizarPersonal()); //acción de actualizar
        btnEliminar.addActionListener(e -> eliminarPersonal()); //acción de eliminar

        tablaPersonal.getSelectionModel().addListSelectionListener(e -> seleccionarPersonal());
       
        cargarPersonalATabla(); // Carga los datos al iniciar la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Consulta la BD y carga los datos de personal en la tabla
    private void cargarPersonalATabla() {
        String[] nombresColumnas = {"ID", "Nombre", "Apellido", "Cargo", "Licencia"};
        DefaultTableModel model = new DefaultTableModel(null, nombresColumnas) {
            public boolean isCellEditable(int row, int column) {
               return false; // Las celdas no son editables.
            }
        };
        
        // Obtiene la lista de personal desde la BD
        List<Personal> personal = personalDAO.listarPersonal();

        for (Personal p : personal) {
            Object[] fila = new Object[5]; 
            fila[0] = p.getIdPersonal();
            fila[1] = p.getNombre();
            fila[2] = p.getApellido();
            fila[3] = p.getCargo();
            fila[4] = p.getLicencia();
            
            model.addRow(fila); 
        }
        
        tablaPersonal.setModel(model);
        limpiarCampos();
    }
    
    // guarda un nuevo registro de personal
    private void guardarPersonal() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String cargo = txtCargo.getText();
            String licencia = txtLicencia.getText();

            if (nombre.isEmpty() || licencia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y Licencia son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crea el objeto Personal
            Personal nuevoPersonal = new Personal(nombre, apellido, cargo, licencia);
            
            // Llama al DAO para guardar
            if (personalDAO.agregarPersonal(nuevoPersonal)) {
                JOptionPane.showMessageDialog(this, "Personal registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPersonalATabla(); 
            } else {
                // Posible error, la licencia ya existe
                JOptionPane.showMessageDialog(this, "Fallo al registrar. Verifique que la Licencia sea única.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // actualiza un registro de personal
    private void actualizarPersonal() {
        if (txtIdPersonal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(txtIdPersonal.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String cargo = txtCargo.getText();
            String licencia = txtLicencia.getText();
            if (nombre.isEmpty() || licencia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y Licencia son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Crea el objeto Personal actualizado
            Personal personalActualizado = new Personal(id, nombre, apellido, cargo, licencia);
            
            // Llama al DAO para actualizar
            if (personalDAO.actualizarPersonal(personalActualizado)) {
                JOptionPane.showMessageDialog(this, "Registro actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPersonalATabla(); 
            } else {
                // Posible error, intenta cambiar la licencia a una ya existente
                JOptionPane.showMessageDialog(this, "Fallo al actualizar. Verifique que la Licencia sea única.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de Personal debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Elimina un registro de personal
    private void eliminarPersonal() {
        if (txtIdPersonal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(txtIdPersonal.getText());
        // Pide confirmación al usuario
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar al personal ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llama al DAO para eliminar
            if (personalDAO.eliminarPersonal(id)) {
                JOptionPane.showMessageDialog(this, "Registro eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPersonalATabla();
            } else {
                // Posible error, tiene citas asociadas (restricción de clave foránea)
                JOptionPane.showMessageDialog(this, "Fallo al eliminar. El personal podría tener citas asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarPersonal() {
        int filaSeleccionada = tablaPersonal.getSelectedRow();
        if (filaSeleccionada != -1 && !tablaPersonal.getSelectionModel().getValueIsAdjusting()) { 
            try {
                txtIdPersonal.setText(tablaPersonal.getValueAt(filaSeleccionada, 0).toString()); 
                txtNombre.setText(tablaPersonal.getValueAt(filaSeleccionada, 1).toString());
                txtApellido.setText(tablaPersonal.getValueAt(filaSeleccionada, 2).toString());
                txtCargo.setText(tablaPersonal.getValueAt(filaSeleccionada, 3).toString());
                txtLicencia.setText(tablaPersonal.getValueAt(filaSeleccionada, 4).toString());
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Error al cargar datos de la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpia el contenido de todos los campos del formulario
    private void limpiarCampos() {
        txtIdPersonal.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCargo.setText("");
        txtLicencia.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonalGUI());
    }
}