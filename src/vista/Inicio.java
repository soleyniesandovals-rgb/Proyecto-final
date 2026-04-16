package vista;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datab.Conexion;
import datab.SesionActual;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Inicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        // Nimbus 
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // Manejo de excepción
            }
        } // fin Nimbus

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inicio frame = new Inicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            });
    }

    public Inicio() {
        setTitle("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 338, 410);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(140, 198, 255));
        panel.setBounds(32, 29, 260, 319);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bienvenido");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblNewLabel.setBounds(72, 11, 118, 29);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Empresa:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(16, 80, 68, 23);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Contraseña:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(16, 166, 97, 14);
        panel.add(lblNewLabel_2);

        JTextArea textempresa = new JTextArea();
        textempresa.setBounds(16, 107, 202, 29);
        panel.add(textempresa);

        JTextArea textcont = new JTextArea();
        textcont.setBounds(16, 190, 202, 29);
        panel.add(textcont);

        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton.setBounds(72, 243, 108, 38);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empresa = textempresa.getText().trim();
                String contrasena = textcont.getText().trim();

                if (empresa.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                        "Por favor complete todos los campos.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Connection cn = Conexion.getConexion();
                    PreparedStatement ps = cn.prepareStatement(
                        "SELECT idusuarios FROM usuarios WHERE empresa = ? AND contraseña = ?"
                    );
                    ps.setString(1, empresa);
                    ps.setString(2, contrasena);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        SesionActual.setIdUsuario(rs.getInt("idusuarios"));
                        SesionActual.setEmpresa(empresa);
                        rs.close(); ps.close(); cn.close();
                        new Transacciones().setVisible(true);
                        dispose();
                    } else {
                        rs.close(); ps.close(); cn.close();
                        JOptionPane.showMessageDialog(null,
                            "Empresa o contraseña incorrectos.",
                            "Error de acceso",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error de conexión: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}