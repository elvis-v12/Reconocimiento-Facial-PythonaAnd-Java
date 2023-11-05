import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LOGIN extends JFrame {
    private JButton startDetectionButton;

    public LOGIN() {
        this.setLocationRelativeTo(null);
        setTitle("Inicio de Sesión");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        startDetectionButton = new JButton("Iniciar Detección de Rostros y Ojos");
        startDetectionButton.setPreferredSize(new Dimension(200, 50));
        startDetectionButton.setBackground(Color.yellow);
        int buttonX = (getWidth() - startDetectionButton.getPreferredSize().width) / 2;
        int buttonY = (getHeight() - startDetectionButton.getPreferredSize().height) / 2;
        startDetectionButton.setBounds(buttonX, buttonY, startDetectionButton.getPreferredSize().width, startDetectionButton.getPreferredSize().height);
        add(startDetectionButton);

        startDetectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFaceAndEyeDetection();
            }
        });
    }

    private void startFaceAndEyeDetection() {
        try {
            Process process = Runtime.getRuntime().exec("python Opencv.py");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();

            // Muestra un mensaje de registro exitoso
            JOptionPane.showMessageDialog(LOGIN.this, "¡Registro de rostro exitoso!");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LOGIN login = new LOGIN();
                login.setVisible(true);
            }
        });
    }
}
