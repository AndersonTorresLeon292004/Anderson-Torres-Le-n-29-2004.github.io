import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Ahorcado extends JFrame {

    private String palabra;
    private char[] letras;
    private int intentos;
    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JTextField txtLetra;
    private JButton btnAdivinar;

    public Ahorcado() {
        palabra = generarPalabraAleatoria();
        letras = new char[palabra.length()];
        intentos = 6;
        inicializarInterfaz();
    }

    private String generarPalabraAleatoria() {
        String[] palabras = {"java", "programacion", "juego", "ahorcado", "diego", "anderson"};
        Random random = new Random();
        return palabras[random.nextInt(palabras.length)];
    }

    private void inicializarInterfaz() {
        setTitle("Juego del Ahorcado");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(lblPalabra = new JLabel(stringConEspacios()));
        panelSuperior.add(lblIntentos = new JLabel("Intentos: " + intentos));

        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Letra:"));
        panelInferior.add(txtLetra = new JTextField(5));
        panelInferior.add(btnAdivinar = new JButton("Adivinar"));
        btnAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adivinarLetra();
            }
        });

        add(panelSuperior, BorderLayout.NORTH);
        add(panelInferior, BorderLayout.CENTER);
        setVisible(true);
    }

    private String stringConEspacios() {
        StringBuilder sb = new StringBuilder();
        for (char letra : letras) {
            if (letra == 0) {
                sb.append("_ ");
            } else {
                sb.append(letra).append(" ");
            }
        }
        return sb.toString();
    }

    private void adivinarLetra() {
        String letra = txtLetra.getText().toLowerCase();
        txtLetra.setText("");
        if (letra.length() == 1) {
            boolean acertado = false;
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra.charAt(0)) {
                    letras[i] = letra.charAt(0);
                    acertado = true;
                }
            }

            if (!acertado) {
                intentos--;
                lblIntentos.setText("Intentos: " + intentos);
            }

            lblPalabra.setText(stringConEspacios());

            if (intentos == 0) {
                JOptionPane.showMessageDialog(this, "¡Has perdido! La palabra era: " + palabra);
                System.exit(0);
            } else if (!stringConEspacios().contains("_")) {
                JOptionPane.showMessageDialog(this, "¡Has ganado!");
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ahorcado();
            }
        });
    }
}
