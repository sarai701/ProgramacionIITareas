package vista;

import javax.swing.*; // Componentes gráficos 
import java.awt.BorderLayout; // Administrador de diseño principal
import java.awt.GridLayout; // Administrador de diseño para el formulario
import java.math.BigDecimal; // Se usa para manejar valores monetarios 
import java.util.List; // Para manejar colecciones de servicios
import dao.ServicioDAO; // Clase para acceso a datos interacción con la BD
import modelo.Servicio; // Clase del modelo de datos de Servicio
import javax.swing.table.DefaultTableModel; // Modelo para la JTable

public class ServicioGUI extends JFrame { 

    private ServicioDAO servicioDAO; // Objeto para realizar operaciones CRUD en la BD
    private JTextField txtNombreServicio, txtPrecio;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnListar;
    private JTable tablaServicios; 
    private JTextField txtIdServicio;

    //Constructor
    public ServicioGUI() {
        servicioDAO = new ServicioDAO(); // Inicializa el objeto DAO
        txtIdServicio = new JTextField(); // Inicializa el campo de ID
        txtIdServicio.setVisible(false);
        setTitle("Gestión de Servicios"); 
        setSize(750, 450);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.add(new JLabel("Nombre del Servicio (Único):")); // Indica que el nombre debe ser único
        txtNombreServicio = new JTextField(20);
        panelFormulario.add(txtNombreServicio);

        panelFormulario.add(new JLabel("Precio (0.00):")); // Indica el formato de precio
        txtPrecio = new JTextField(20);
        panelFormulario.add(txtPrecio);
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");
 
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        tablaServicios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaServicios);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        //Manejo de eventos
        btnListar.addActionListener(e -> cargarServiciosATabla()); //acción de listar
        btnGuardar.addActionListener(e -> guardarServicio()); //acción de guardar
        btnActualizar.addActionListener(e -> actualizarServicio()); //acción de actualizar
        btnEliminar.addActionListener(e -> eliminarServicio()); //acción de eliminar

        tablaServicios.getSelectionModel().addListSelectionListener(e -> seleccionarServicio());

        
        cargarServiciosATabla(); // Carga los datos al iniciar la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Consulta la BD y carga los datos
    private void cargarServiciosATabla() {
        String[] nombresColumnas = {"ID Servicio", "Nombre", "Precio"};
        DefaultTableModel model = new DefaultTableModel(null, nombresColumnas) {
            
            public boolean isCellEditable(int row, int column) {
               return false; // Las celdas no son editables
            }
        };
        
        // Obtiene la lista de servicios desde la BD
        List<Servicio> servicios = servicioDAO.listarServicios();

        for (Servicio s : servicios) {
            Object[] fila = new Object[3]; 
            fila[0] = s.getIdServicio();
            fila[1] = s.getNombreServicio();
            // Convierte BigDecimal a String para mostrar el precio correctamente
            fila[2] = s.getPrecio().toString(); 
            
            model.addRow(fila); 
        }
        
        tablaServicios.setModel(model); // Asigna el modelo a la tabla
        limpiarCampos(); // Limpia los campos de texto
    }
    
    // Recoge datos del formulario y guarda un nuevo servicio 
    private void guardarServicio() {
        try {
            String nombre = txtNombreServicio.getText();
            // Convierte el texto del precio a BigDecimal
            BigDecimal precio = new BigDecimal(txtPrecio.getText());
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del servicio es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crea el objeto Servicio
            Servicio nuevoServicio = new Servicio(nombre, precio);
            
            // Llama al DAO para guardar
            if (servicioDAO.agregarServicio(nuevoServicio)) {
                JOptionPane.showMessageDialog(this, "Servicio registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarServiciosATabla(); 
            } else {
                // Posible error, el nombre ya existe
                JOptionPane.showMessageDialog(this, "Fallo al registrar. Verifique que el nombre sea único.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // Error si el precio no es un número válido
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Recoge datos y actualiza un servicio existente
    private void actualizarServicio() {
        // Verifica que haya un ID seleccionado
        if (txtIdServicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(txtIdServicio.getText());
            String nombre = txtNombreServicio.getText();
            BigDecimal precio = new BigDecimal(txtPrecio.getText());
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del servicio es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Crea el objeto Servicio actualizado
            Servicio servicioActualizado = new Servicio(id, nombre, precio);
            
            // Llama al DAO para actualizar
            if (servicioDAO.actualizarServicio(servicioActualizado)) {
                JOptionPane.showMessageDialog(this, "Servicio actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarServiciosATabla(); 
            } else {
                // Posible error, intenta cambiar el nombre a uno ya existente
                JOptionPane.showMessageDialog(this, "Fallo al actualizar. Verifique que el nombre sea único.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido o el ID es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Elimina un servicio de la BD
    private void eliminarServicio() {
        // Verifica que haya un ID seleccionado
        if (txtIdServicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un servicio de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(txtIdServicio.getText());
        
        // Pide confirmación al usuario
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el servicio ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llama al DAO para eliminar
            if (servicioDAO.eliminarServicio(id)) {
                JOptionPane.showMessageDialog(this, "Servicio eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarServiciosATabla(); 
            } else {
                // Posible error, está asociado a citas (restricción de clave foránea)
                JOptionPane.showMessageDialog(this, "Fallo al eliminar. Este servicio está asociado a citas existentes.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Carga los datos de la fila seleccionada 
    private void seleccionarServicio() {
        int filaSeleccionada = tablaServicios.getSelectedRow();
        if (filaSeleccionada != -1 && !tablaServicios.getSelectionModel().getValueIsAdjusting()) { 
            try {
                txtIdServicio.setText(tablaServicios.getValueAt(filaSeleccionada, 0).toString());
                txtNombreServicio.setText(tablaServicios.getValueAt(filaSeleccionada, 1).toString());
                txtPrecio.setText(tablaServicios.getValueAt(filaSeleccionada, 2).toString());
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Error al cargar datos de la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpia el contenido de todos los campos del formulario
    private void limpiarCampos() {
        txtIdServicio.setText("");
        txtNombreServicio.setText("");
        txtPrecio.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServicioGUI());
    }
}