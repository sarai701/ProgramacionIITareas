package vista;

import javax.swing.*; // Componentes de la interfaz gráfica
import java.awt.BorderLayout; // Administrador de diseño principal
import java.awt.GridLayout; // Administrador de diseño para el formulario
import java.text.ParseException; // Manejo de errores al parsear fechas
import java.text.SimpleDateFormat; // Formato para manejar fechas y horas
import java.util.Date; // Clase para representar fecha y hora
import java.util.List; // Interfaz para colecciones tipo lista
import dao.CitaDAO; // Clase de Acceso a Datos para interactuar con la DB
import modelo.Cita; // Clase del modelo de datos de Cita
import javax.swing.table.DefaultTableModel; // Modelo para la tabla de la GUI

public class CitaGUI extends JFrame { 

    private CitaDAO citaDAO; // Objeto para realizar operaciones de base de datos
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Campos de texto para la entrada de datos del usuario
    private JTextField txtFechaHora, txtMotivo, txtEstado, txtIdMascota, txtIdPersonal, txtIdServicio;
    // Botones para las acciones (CRUD y Reporte)
    private JButton btnGuardar, btnActualizar, btnEliminar, btnListar, btnReporte; 
    private JTable tablaCitas; 
    private JTextField txtIdCita; 

    // Constructor de la Ventana
    public CitaGUI() {
        citaDAO = new CitaDAO(); // Inicializa el objeto DAO para la DB
        txtIdCita = new JTextField(); 
        txtIdCita.setVisible(false); 

        setTitle("Gestión de Citas"); // Título de la ventana
        setSize(750, 450);  // Tamaño inicial de la ventana.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        // Panel para el formulario de entrada de datos
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 10, 10));

        // Campos de entrada con sus etiquetas
        panelFormulario.add(new JLabel("Fecha/Hora (YYYY-MM-DD HH:MM:SS):"));
        txtFechaHora = new JTextField(30);
        panelFormulario.add(txtFechaHora);

        panelFormulario.add(new JLabel("Motivo:"));
        txtMotivo = new JTextField(20);
        panelFormulario.add(txtMotivo);

        panelFormulario.add(new JLabel("Estado:"));
        txtEstado = new JTextField(20);
        panelFormulario.add(txtEstado);

        panelFormulario.add(new JLabel("ID Mascota:"));
        txtIdMascota = new JTextField(10);
        panelFormulario.add(txtIdMascota);
        
        panelFormulario.add(new JLabel("ID Personal (Veterinario):"));
        txtIdPersonal = new JTextField(10);
        panelFormulario.add(txtIdPersonal);
        
        panelFormulario.add(new JLabel("ID Servicio:"));
        txtIdServicio = new JTextField(10);
        panelFormulario.add(txtIdServicio);

        // Panel para los botones de acción
        JPanel panelBotones = new JPanel();
        // Inicialización de botones.
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");
        btnReporte = new JButton("Generar Reporte");
        // Agregar botones al panel
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);
        panelBotones.add(btnReporte); 

        tablaCitas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaCitas);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Manejo de eventos
        btnListar.addActionListener(e -> cargarCitasATabla()); // Llama a listar 
        btnGuardar.addActionListener(e -> guardarCita()); // Llama a guardar 
        btnActualizar.addActionListener(e -> actualizarCita()); // Llama a actualizar
        btnEliminar.addActionListener(e -> eliminarCita()); // Llama a eliminar 
        btnReporte.addActionListener(e -> generarReporteCitas()); // Llama a generar reporte 
        
        tablaCitas.getSelectionModel().addListSelectionListener(e -> seleccionarCita());

        cargarCitasATabla(); 
        setVisible(true);
    }

    //Métodos de Lógica de la GUI y la BD
    private void cargarCitasATabla() {
        String[] nombresColumnas = {"ID Cita", "Fecha/Hora", "Motivo", "Estado", "ID Mascota", "ID Personal", "ID Servicio"};
        DefaultTableModel model = new DefaultTableModel(null, nombresColumnas) {
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        
        // Obtiene la lista de citas desde la base de datos
        List<Cita> citas = citaDAO.listarCitas();
        
        for (Cita c : citas) {
            Object[] fila = new Object[7]; 
            fila[0] = c.getIdCita();
            // Formatea la fecha y hora antes de mostrarla
            fila[1] = dateTimeFormat.format(c.getFechaHora()); 
            fila[2] = c.getMotivo();
            fila[3] = c.getEstado();
            fila[4] = c.getIdMascota();
            fila[5] = c.getIdPersonal();
            fila[6] = c.getIdServicio();
            
            model.addRow(fila); 
        }
        
        tablaCitas.setModel(model); 
        limpiarCampos(); // Limpia los campos de texto después de cargar
    }

    // Método para generar y guardar un reporte en formato CSV
    private void generarReporteCitas() {
        // Abre un diálogo para que el usuario elija dónde guardar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte de Citas");

        // Sugiere un nombre de archivo por defecto con fecha y hora
        fileChooser.setSelectedFile(new java.io.File("Reporte_Citas_" + new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) + ".csv"));

        // Muestra el diálogo de guardar
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) { 
            java.io.File fileToSave = fileChooser.getSelectedFile();
            
            CitaDAO citaDAO = new CitaDAO();
            
            // Llama al método del DAO para generar el archivo
            if (citaDAO.generarReporteCSV(fileToSave.getAbsolutePath())) {
                
                JOptionPane.showMessageDialog(this, "Reporte guardado con éxito en: " + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mensaje de error.
                JOptionPane.showMessageDialog(this, "Fallo al generar el reporte. Verifique permisos o el estado de la conexión.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void guardarCita() {
   
    }
    private void actualizarCita() {
       
    }
    private void eliminarCita() {
     
    }
    private void seleccionarCita() {
      
    }
    
    // Método para limpiar todos los campos de entrada de datos
    private void limpiarCampos() {
        txtIdCita.setText("");
        txtFechaHora.setText("");
        txtMotivo.setText("");
        txtEstado.setText("");
        txtIdMascota.setText("");
        txtIdPersonal.setText("");
        txtIdServicio.setText("");
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        // Asegura que la GUI se ejecute 
        SwingUtilities.invokeLater(() -> new CitaGUI());
    }
}