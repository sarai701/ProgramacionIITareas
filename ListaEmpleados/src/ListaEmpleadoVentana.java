import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListaEmpleadoVentana extends JFrame {

    private ArrayList<Empleado> listaEmpleado = new ArrayList<>();
    private JTextField txtNombre, txtEdad, txtSalario;
    private JTextArea areaListado;

    public ListaEmpleadoVentana() {
        setTitle("Registro de Empleados");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de ingreso
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 10, 10));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelEntrada.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelEntrada.add(txtEdad);

        panelEntrada.add(new JLabel("Salario:"));
        txtSalario = new JTextField();
        panelEntrada.add(txtSalario);

        JButton btnAgregar = new JButton("Agregar Empleado");
        panelEntrada.add(btnAgregar);

        JButton btnMostrar = new JButton("Mostrar Listado");
        panelEntrada.add(btnMostrar);

        add(panelEntrada, BorderLayout.NORTH);

        // Área de texto para mostrar listado
        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);
        add(scroll, BorderLayout.CENTER);

        // Acción del botón Agregar empleado
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String edadStr = txtEdad.getText().trim();
                String salarioStr = txtSalario.getText().trim();

                if(nombre.isEmpty() || edadStr.isEmpty() || salarioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                try {
                    int edad = Integer.parseInt(edadStr);
                    double salario = Double.parseDouble(salarioStr);

                    // Crear el empleado y agregar a la lista
                    Empleado emp = new Empleado(nombre, edad, salario);
                    listaEmpleado.add(emp);

                    JOptionPane.showMessageDialog(null, "Empleado agregado correctamente.");

                    // Limpiar campos
                    txtNombre.setText("");
                    txtEdad.setText("");
                    txtSalario.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Edad y salario deben ser números.");
                }
            }
        });

        // Acción del botón Mostrar listado
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaListado.setText(""); 
                if(listaEmpleado.isEmpty()) {
                    areaListado.setText("No hay empleados registrados.");
                } else {
                    for(Empleado emp : listaEmpleado) {
                        areaListado.append(emp.datos() + "\n");
          
                        System.out.println(emp.datos());
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ListaEmpleadoVentana().setVisible(true);
            }
        });
    }
}

