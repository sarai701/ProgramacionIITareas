package vista;

import javax.swing.*; // Componentes gráficos 
import java.awt.BorderLayout; // Administrador de diseño principal
import java.awt.GridLayout; // Administrador de diseño para el formulario
import java.text.ParseException; // Manejo de errores al convertir texto a fecha
import java.text.SimpleDateFormat; // Formato para manejar fechas
import java.util.Date; // Clase para representar fechas
import java.util.List; // Para manejar la lista
import dao.MascotaDAO; // Clase para acceso a datos 
import modelo.Mascota; // Clase del modelo de datos de Mascota
import javax.swing.table.DefaultTableModel; // Modelo para la JTable

public class MascotaGUI extends JFrame { 

    private MascotaDAO mascotaDAO; // Objeto para realizar operaciones CRUD en la BD
    // Formato de fecha esperado para la entrada y salida de datos
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private JTextField txtNombre, txtEspecie, txtRaza, txtFechaNacimiento, txtIdCliente;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnListar;
    private JTable tablaMascotas; 
    private JTextField txtIdMascota;
    //Constructor
    public MascotaGUI() {
        mascotaDAO = new MascotaDAO(); // Inicializa el objeto DAO
        txtIdMascota = new JTextField(); // Inicializa el campo de ID
        txtIdMascota.setVisible(false); 
        setTitle("Gestión de Mascotas"); 
        setSize(750, 450); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Especie:"));
        txtEspecie = new JTextField(20);
        panelFormulario.add(txtEspecie);

        panelFormulario.add(new JLabel("Raza:"));
        txtRaza = new JTextField(20);
        panelFormulario.add(txtRaza);

        panelFormulario.add(new JLabel("Fecha Nac. (YYYY-MM-DD):")); 
        panelFormulario.add(txtFechaNacimiento);

        panelFormulario.add(new JLabel("ID Cliente (Dueño):")); // Clave foránea al dueño
        txtIdCliente = new JTextField(20);
        panelFormulario.add(txtIdCliente);
 
        JPanel panelBotones = new JPanel();
        // Inicialización de los botones
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        tablaMascotas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaMascotas);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        //Manejo de eventos
        btnListar.addActionListener(e -> cargarMascotasATabla()); //acción de listar
        btnGuardar.addActionListener(e -> guardarMascota()); // acción de guardar
        btnActualizar.addActionListener(e -> actualizarMascota()); //acción de actualizar
        btnEliminar.addActionListener(e -> eliminarMascota()); //acción de eliminar

        // Listener para cargar datos a los campos cuando se selecciona una fila
        tablaMascotas.getSelectionModel().addListSelectionListener(e -> seleccionarMascota());

        
        setVisible(true); // Hace visible la ventana
        cargarMascotasATabla(); // Carga los datos al iniciar
    }

    // Consulta la BD
    private void cargarMascotasATabla() {
        String[] nombresColumnas = {"ID Mascota", "Nombre", "Especie", "Raza", "Fecha Nac.", "ID Dueño"};
        DefaultTableModel model = new DefaultTableModel(null, nombresColumnas) {
           
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        
        // Obtiene la lista de mascotas desde la BD
        List<Mascota> mascotas = mascotaDAO.listarMascotas();

        for (Mascota m : mascotas) {
            Object[] fila = new Object[6]; 
            fila[0] = m.getIdMascota();
            fila[1] = m.getNombre();
            fila[2] = m.getEspecie();
            fila[3] = m.getRaza();
            fila[4] = dateFormat.format(m.getFechaNacimiento()); 
            fila[5] = m.getIdCliente();
            
            model.addRow(fila); 
        }
        
        tablaMascotas.setModel(model); 
        limpiarCampos(); 
    }

    private void guardarMascota() {
        try {
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            String raza = txtRaza.getText();
            // Convierte el texto de fecha a un objeto Date
            Date fechaNac = dateFormat.parse(txtFechaNacimiento.getText()); 
            // Convierte el texto de ID Cliente a un entero
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            if (nombre.isEmpty() || txtIdCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre e ID Cliente son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Crea el objeto Mascota
            Mascota nuevaMascota = new Mascota(nombre, especie, raza, fechaNac, idCliente);
            
            // Llama al DAO para agregar
            if (mascotaDAO.agregarMascota(nuevaMascota)) {
                JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarMascotasATabla(); 
            } else {
                // Posiblemente el ID Cliente no existe (clave foránea)
                JOptionPane.showMessageDialog(this, "Fallo al registrar. Verifique que el ID Cliente exista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // Error si el ID Cliente no es un número
            JOptionPane.showMessageDialog(this, "ID Cliente debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            // Error si el formato de la fecha es incorrecto
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Recoge datos y actualiza una mascota existente
    private void actualizarMascota() {
        // Verifica que haya un ID Mascota seleccionado
        if (txtIdMascota.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una mascota de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Obtiene el ID de la mascota a actualizar
            int id = Integer.parseInt(txtIdMascota.getText());
            String nombre = txtNombre.getText();
            String especie = txtEspecie.getText();
            String raza = txtRaza.getText();
            Date fechaNac = dateFormat.parse(txtFechaNacimiento.getText());
            int idCliente = Integer.parseInt(txtIdCliente.getText());
            if (nombre.isEmpty() || txtIdCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre e ID Cliente son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Mascota mascotaActualizada = new Mascota(id, nombre, especie, raza, fechaNac, idCliente);
            if (mascotaDAO.actualizarMascota(mascotaActualizada)) {
                JOptionPane.showMessageDialog(this, "Mascota actualizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarMascotasATabla();
            } else {
                // Posiblemente el ID Cliente no existe (clave foránea)
                JOptionPane.showMessageDialog(this, "Fallo al actualizar. Verifique que el ID Cliente exista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de Mascota o Cliente debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Elimina una mascota de la BD
    private void eliminarMascota() {
        if (txtIdMascota.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una mascota de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(txtIdMascota.getText());
        
        // Pide confirmación antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar la mascota ID " + id + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llama al DAO para eliminar
            if (mascotaDAO.eliminarMascota(id)) {
                JOptionPane.showMessageDialog(this, "Mascota eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarMascotasATabla(); // Recarga la tabla.
            } else {
                // Mensaje de error por restricción de clave foránea
                JOptionPane.showMessageDialog(this, "Fallo al eliminar. La mascota podría tener citas asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarMascota() {
        int filaSeleccionada = tablaMascotas.getSelectedRow();
        if (filaSeleccionada != -1 && !tablaMascotas.getSelectionModel().getValueIsAdjusting()) { 
            try {
                txtIdMascota.setText(tablaMascotas.getValueAt(filaSeleccionada, 0).toString()); 
                txtNombre.setText(tablaMascotas.getValueAt(filaSeleccionada, 1).toString());
                txtEspecie.setText(tablaMascotas.getValueAt(filaSeleccionada, 2).toString());
                txtRaza.setText(tablaMascotas.getValueAt(filaSeleccionada, 3).toString());
                txtFechaNacimiento.setText(tablaMascotas.getValueAt(filaSeleccionada, 4).toString());
                txtIdCliente.setText(tablaMascotas.getValueAt(filaSeleccionada, 5).toString());
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Error al cargar datos de la fila.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpia el contenido de todos los campos del formulario
    private void limpiarCampos() {
        txtIdMascota.setText("");
        txtNombre.setText("");
        txtEspecie.setText("");
        txtRaza.setText("");
        txtFechaNacimiento.setText("");
        txtIdCliente.setText("");
    }
    
    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        // Asegura que la GUI se ejecute en el Event Dispatch Thread (EDT) de Swing.
        SwingUtilities.invokeLater(() -> new MascotaGUI());
    }
}