package valoresNumericos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Clase principal que crea la ventana para ingresar números y calcular suma y promedio
public class Numeros extends JFrame {
    // Campos de texto para ingresar cantidad de números, cada número, nombre y carnet
    private JTextField txtCantidad;
    private JButton btnSetCantidad;
    private JTextField txtNumero;
    private JButton btnAgregar;
    private JButton btnCalcular;
    private JTextArea txtSalida;
    private JLabel lblEstado;

    private JTextField txtNombre;
    private JTextField txtCarnet;

    // Lista para guardar los números ingresados
    private ArrayList<Integer> lista;
    private int cantidadEsperada = 0;   // Cantidad de números que el usuario quiere ingresar
    private int cantidadIngresada = 0;  // Cantidad de números que ya se han ingresado

    // Constructor de la ventana
    public Numeros() {
        super("Gestor de Números");
        lista = new ArrayList<>();
        initComponents(); // Inicializa los componentes de la interfaz
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setSize(650, 500); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    // Método para crear y organizar los componentes en la ventana
    private void initComponents() {
        // PANEL SUPERIOR: Nombre, carnet y cantidad de números
        JPanel pTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5); // Espacios entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Nombre y Carnet
        gbc.gridx = 0; gbc.gridy = 0;
        pTop.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        pTop.add(txtNombre, gbc);

        gbc.gridx = 2;
        pTop.add(new JLabel("Carnet:"), gbc);

        gbc.gridx = 3;
        txtCarnet = new JTextField(10);
        pTop.add(txtCarnet, gbc);

        // Cantidad de números a ingresar y botón
        gbc.gridx = 0; gbc.gridy = 1;
        pTop.add(new JLabel("Cantidad de números a ingresar:"), gbc);

        gbc.gridx = 1;
        txtCantidad = new JTextField(5);
        pTop.add(txtCantidad, gbc);

        gbc.gridx = 2;
        btnSetCantidad = new JButton("Establecer");
        gbc.gridwidth = 2; 
        pTop.add(btnSetCantidad, gbc);
        gbc.gridwidth = 1;

        // ingreso de números y botones de acción
        JPanel pMiddle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pMiddle.add(new JLabel("Número entero:"));
        txtNumero = new JTextField(10);
        txtNumero.setEnabled(false); // Deshabilitado hasta que se establezca la cantidad
        pMiddle.add(txtNumero);
        btnAgregar = new JButton("Agregar");
        btnAgregar.setEnabled(false); // Deshabilitado hasta que se establezca la cantidad
        pMiddle.add(btnAgregar);

        btnCalcular = new JButton("Calcular suma y promedio");
        btnCalcular.setEnabled(false); // Se habilita cuando se han ingresado todos los números
        pMiddle.add(btnCalcular);

        lblEstado = new JLabel("Estado: esperando cantidad..."); // Muestra el estado actual

        //se muestran los resultados
        txtSalida = new JTextArea(15, 60);
        txtSalida.setEditable(false); // Solo lectura
        JScrollPane scroll = new JScrollPane(txtSalida);

        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pTop, BorderLayout.NORTH);
        getContentPane().add(pMiddle, BorderLayout.CENTER);

        JPanel pBottom = new JPanel(new BorderLayout());
        pBottom.add(lblEstado, BorderLayout.NORTH);
        pBottom.add(scroll, BorderLayout.CENTER);
        getContentPane().add(pBottom, BorderLayout.SOUTH);

        btnSetCantidad.addActionListener(e -> establecerCantidad());
        btnAgregar.addActionListener(e -> agregarNumero());
        btnCalcular.addActionListener(e -> calcularYMostrar());
        txtCantidad.addActionListener(e -> establecerCantidad());
        txtNumero.addActionListener(e -> agregarNumero());
    }

    // Método para establecer la cantidad de números que se van a ingresar
    private void establecerCantidad() {
        String t = txtCantidad.getText().trim();
        if (t.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cantidad de números a capturar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int c = Integer.parseInt(t);
            if (c <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cantidadEsperada = c; // Guardar cantidad esperada
            cantidadIngresada = 0; 
            lista.clear(); // Limpiar lista de números
            txtNumero.setEnabled(true); // Activar ingreso de números
            btnAgregar.setEnabled(true);
            btnCalcular.setEnabled(false);
            txtSalida.setText(""); // Limpiar área de resultados
            lblEstado.setText("Estado: lista vacía. Ingrese los números (0/" + cantidadEsperada + ")");
            txtNumero.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar un número a la lista
    private void agregarNumero() {
        if (cantidadEsperada <= 0) {
            JOptionPane.showMessageDialog(this, "Primero establezca la cantidad de números a ingresar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String t = txtNumero.getText().trim();
        if (t.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int n = Integer.parseInt(t);
            lista.add(n); // Agregar número a la lista
            cantidadIngresada++;
            txtSalida.append("Ingresado (" + cantidadIngresada + ") : " + n + "\n");
            txtNumero.setText("");
            lblEstado.setText("Estado: Ingresados " + cantidadIngresada + " de " + cantidadEsperada);

            // Si se ingresaron todos los números, deshabilitar ingreso y habilitar cálculo
            if (cantidadIngresada >= cantidadEsperada) {
                txtNumero.setEnabled(false);
                btnAgregar.setEnabled(false);
                btnCalcular.setEnabled(true);
                lblEstado.setText("Estado: captura completa. Puede calcular suma y promedio.");
            }
            txtNumero.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El valor debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para calcular suma y promedio, y mostrar resultados
    private void calcularYMostrar() {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay números capturados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        long suma = 0;
        for (Integer v : lista) suma += v;
        double promedio = (double) suma / lista.size();

        String nombre = txtNombre.getText().trim();
        String carnet = txtCarnet.getText().trim();

        // Construir texto de resultados
        StringBuilder sb = new StringBuilder();
        sb.append("=== DATOS DEL USUARIO ===\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Carnet: ").append(carnet).append("\n\n");

        sb.append("=== RESULTADOS ===\n");
        sb.append("Suma = ").append(suma).append("\n");
        sb.append(String.format("Promedio = %.4f\n", promedio));
        sb.append("\nListado de valores:\n");

        int contadorSobrePromedio = 0;
        for (int i = 0; i < lista.size(); i++) {
            int val = lista.get(i);
            boolean sobre = val > promedio;
            if (sobre) contadorSobrePromedio++;
            sb.append(String.format("[%d] = %d %s\n", i + 1, val, (sobre ? "--> supera el promedio" : "")));
        }

        sb.append("\nCantidad de valores que superan el promedio: ")
          .append(contadorSobrePromedio).append(" de ").append(lista.size()).append("\n");

        txtSalida.append("\n" + sb.toString());

        // Mostrar resultados en un cuadro
        JOptionPane.showMessageDialog(this,
                "Nombre: " + nombre +
                "\nCarnet: " + carnet +
                "\nSuma = " + suma +
                "\nPromedio = " + String.format("%.4f", promedio) +
                "\nValores que superan el promedio: " + contadorSobrePromedio,
                "Resultados",
                JOptionPane.INFORMATION_MESSAGE);

        // Reiniciar estado para nueva captura
        btnCalcular.setEnabled(false);
        btnSetCantidad.setEnabled(true);
        txtCantidad.setText("");
        cantidadEsperada = 0;
        cantidadIngresada = 0;
    }

    // Método principal que inicia la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Numeros app = new Numeros();
            app.setVisible(true); // Mostrar ventana
        });
    }
}
