import javax.swing.*;
import java.awt.*;

// Ventana gráfica principal
public class MainVentana extends JFrame {
    private JComboBox<String> comboAnimal; // Selector de tipo de animal
    private JTextField txtNombre, txtPatas, txtOjos; // Campos de entrada
    private JTextArea txtResultado; // Muestra los resultados
    private Animal ultimoAnimal; // Guarda el último animal creado

    public MainVentana() {
        setTitle("Granja de Animales");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes gráficos
        comboAnimal = new JComboBox<>(new String[]{"Gato", "Perro", "Loro", "LoroMutante"});
        txtNombre = new JTextField(10);
        txtPatas = new JTextField(5);
        txtOjos = new JTextField(5);

        JButton btnCrear = new JButton("Crear");
        JButton btnClonar = new JButton("Clonar");

        txtResultado = new JTextArea(8, 35);
        txtResultado.setEditable(false);

        // Componentes a la ventana
        add(new JLabel("Animal:"));
        add(comboAnimal);
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Patas:"));
        add(txtPatas);
        add(new JLabel("Ojos:"));
        add(txtOjos);
        add(btnCrear);
        add(btnClonar);
        add(new JScrollPane(txtResultado));

        // Acción para crear un animal
        btnCrear.addActionListener(e -> {
            try {
                String tipo = (String) comboAnimal.getSelectedItem();
                String nombre = txtNombre.getText();
                int patas = Integer.parseInt(txtPatas.getText()); // lanza el error
                int ojos = Integer.parseInt(txtOjos.getText()); //lanza el error
                
                if (patas < 0 || patas > 4) {
                    JOptionPane.showMessageDialog(null, "El número de patas debe estar entre 0 y 4");
                    return;
                }

                // Dependiendo del tipo, se crea el objeto correspondiente
                switch (tipo) {
                    case "Gato": ultimoAnimal = new Gato(patas, ojos, nombre); break;
                    case "Perro": ultimoAnimal = new Perro(patas, ojos, true, nombre); break;
                    case "Loro": ultimoAnimal = new Loro(patas, ojos, nombre); break;
                    case "LoroMutante": ultimoAnimal = new LoroMutante(patas, ojos, nombre); break;
                }

                txtResultado.setText("Original -> " + ultimoAnimal);

            } catch (NumberFormatException ex) {
            	//si se ingresa letras en lugar de números
                JOptionPane.showMessageDialog(null, "Debes ingresar números válidos, no letras");
            } catch (IllegalArgumentException ex) {
            	// si el constructor detecta valores negativos o >2
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        // Acción para clonar el último animal creado
        btnClonar.addActionListener(e -> {
            if (ultimoAnimal != null) {
                Animal clon = ultimoAnimal.clone();
                txtResultado.append("\nClon -> " + clon);
            } else {
                JOptionPane.showMessageDialog(null, "Primero crea un animal antes de clonar");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainVentana().setVisible(true));
    }
}
