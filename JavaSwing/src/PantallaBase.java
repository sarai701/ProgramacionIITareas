import javax.swing.JFrame;

public class PantallaBase extends JFrame {

    private static final long serialVersionUID = 1L;

    public PantallaBase() {
        getContentPane().setLayout(null);
    }

    public static void main(String[] args) {
        PantallaBase formulario1 = new PantallaBase();
        formulario1.setBounds(10, 10, 400, 300);
        formulario1.setVisible(true);
    }
}
