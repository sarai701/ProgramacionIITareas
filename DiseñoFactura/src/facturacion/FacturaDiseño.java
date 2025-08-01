package facturacion;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FacturaDiseño {

    private JFrame frame;
    private JTextField textField, textField_1, textField_2;
    private JTextField textField_3, textField_4, textField_5;
    private JTextField textField_6, textField_7, textField_8;
    private JTextField textField_9, textField_10, textField_11;
    private JTextField textField_12, textField_13, textField_14;
    private JTextField textField_15, textField_16, textField_17;
    private JTextArea textArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FacturaDiseño window = new FacturaDiseño();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FacturaDiseño() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 480, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblTitulo = new JLabel("JOYERIA");
        JLabel lblNumero = new JLabel("No.");
        JLabel lblCliente = new JLabel("Cliente");
        JLabel lblFecha = new JLabel("Fecha");

        JLabel lblCantidad = new JLabel("Cantidad");
        JLabel lblCodigo = new JLabel("Codigo");
        JLabel lblDescripcion = new JLabel("Descripcion");
        JLabel lblPrecio = new JLabel("Precio");

        JLabel lblSubtotal = new JLabel("Subtotal");
        JLabel lblImpuestos = new JLabel("Impuestos");
        JLabel lblTotal = new JLabel("Total");

        textField = new JTextField();        textField.setColumns(10);
        textField_1 = new JTextField();      textField_1.setColumns(10);
        textField_2 = new JTextField();      textField_2.setColumns(10); 

        textField_3 = new JTextField();      textField_3.setColumns(10); 
        textField_4 = new JTextField();      textField_4.setColumns(10); 
        textField_5 = new JTextField();      textField_5.setColumns(10); 

        textField_6 = new JTextField();      textField_6.setColumns(10); 
        textField_7 = new JTextField();      textField_7.setColumns(10); 
        textField_8 = new JTextField();      textField_8.setColumns(10); 

        textField_9 = new JTextField();      textField_9.setColumns(10); 
        textField_10 = new JTextField();     textField_10.setColumns(10); 
        textField_11 = new JTextField();     textField_11.setColumns(10); 

        textField_12 = new JTextField();     textField_12.setColumns(10);
        textField_13 = new JTextField();     textField_13.setColumns(10); 
        textField_14 = new JTextField();     textField_14.setColumns(10);

        textField_15 = new JTextField();     textField_15.setColumns(10); 
        textField_16 = new JTextField();     textField_16.setColumns(10); 
        textField_17 = new JTextField();     textField_17.setColumns(10); 

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnCalcular = new JButton("CALCULAR");

        btnCalcular.addActionListener(e -> {
            try {
                String numero = textField.getText();
                String cliente = textField_1.getText();
                String[] partesFecha = textField_2.getText().split("/"); 
                int dia = Integer.parseInt(partesFecha[0]);
                int mes = Integer.parseInt(partesFecha[1]);
                int anio = Integer.parseInt(partesFecha[2]);

                Factura factura = new Factura(numero, cliente, dia, mes, anio);

                agregarLineaDesdeCampos(textField_3, textField_6, textField_9, textField_12, factura);
                agregarLineaDesdeCampos(textField_4, textField_7, textField_10, textField_13, factura);
                agregarLineaDesdeCampos(textField_5, textField_8, textField_11, textField_14, factura);

                textField_15.setText(String.format("%.2f", factura.calcularSubtotal()));
                textField_16.setText(String.format("%.2f", factura.calcularImpuesto()));
                textField_17.setText(String.format("%.2f", factura.calcularTotal()));

                textArea.setText(factura.toString());
                System.out.println(factura.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al calcular factura:\n" + ex.getMessage());
            }
        });

        
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.CENTER)
        		.addComponent(lblTitulo)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(textField_3, 107, 107, 107)
        			.addComponent(textField_6, 107, 107, 107)
        			.addComponent(textField_9, 107, 107, 107)
        			.addComponent(textField_12, 107, 107, 107))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(textField_4, 107, 107, 107)
        			.addComponent(textField_7, 107, 107, 107)
        			.addComponent(textField_10, 107, 107, 107)
        			.addComponent(textField_13, 107, 107, 107))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(textField_5, 107, 107, 107)
        			.addComponent(textField_8, 107, 107, 107)
        			.addComponent(textField_11, 107, 107, 107)
        			.addComponent(textField_14, 107, 107, 107))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(lblSubtotal)
        			.addComponent(textField_15, 106, 106, 106)
        			.addComponent(lblImpuestos)
        			.addComponent(textField_16, 106, 106, 106)
        			.addComponent(lblTotal)
        			.addComponent(textField_17, 106, 106, 106))
        		.addComponent(btnCalcular)
        		.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addComponent(lblCliente)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(textField_1, 117, GroupLayout.DEFAULT_SIZE, 117)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblCantidad)
        					.addGap(81)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblNumero)
        					.addComponent(textField, 117, 117, 117)
        					.addComponent(lblFecha)
        					.addComponent(textField_2, 119, 119, 119))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(lblCodigo)
        					.addGap(66)
        					.addComponent(lblDescripcion)
        					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
        					.addComponent(lblPrecio)
        					.addGap(53))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(lblTitulo)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblCliente)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNumero)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblFecha)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblCantidad)
        					.addComponent(lblCodigo))
        				.addComponent(lblDescripcion)
        				.addComponent(lblPrecio))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblSubtotal)
        				.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblImpuestos)
        				.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblTotal)
        				.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addComponent(btnCalcular)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        frame.getContentPane().setLayout(layout);
    }

    private void agregarLineaDesdeCampos(JTextField tfCant, JTextField tfCod, JTextField tfDesc, JTextField tfPrecio, Factura factura) {
        String scant = tfCant.getText().trim();
        String scod = tfCod.getText().trim();
        String sdesc = tfDesc.getText().trim();
        String spre = tfPrecio.getText().trim();

        if (scant.isEmpty() || scod.isEmpty() || sdesc.isEmpty() || spre.isEmpty()) {
            return;
        }

        try {
            float cantidad = Float.parseFloat(scant);
            float precio = Float.parseFloat(spre);
            factura.agregarLinea(cantidad, scod, sdesc, precio);
        } catch (NumberFormatException ex) {
            System.err.println("Error al convertir datos numéricos: " + ex.getMessage());
        }
    }

}
