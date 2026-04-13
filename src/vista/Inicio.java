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

import inicio.Conexion;
import inicio.SesionActual;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Inicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
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
// com
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

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Recordar usuario");
        rdbtnNewRadioButton.setBackground(new Color(140, 198, 255));
        rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        rdbtnNewRadioButton.setBounds(16, 65, 128, 23);
        panel.add(rdbtnNewRadioButton);

        JLabel lblNewLabel = new JLabel("Bienvenido");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblNewLabel.setBounds(72, 11, 118, 29);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Empresa:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(16, 107, 68, 23);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Contraseña:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(16, 193, 97, 14);
        panel.add(lblNewLabel_2);

        JTextArea textempresa = new JTextArea();
        textempresa.setBounds(16, 134, 202, 29);
        panel.add(textempresa);

        JTextArea textcont = new JTextArea();
        textcont.setBounds(16, 217, 202, 29);
        panel.add(textcont);

        JButton btnNewButton = new JButton("Ingresar");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton.setBounds(72, 270, 108, 38);
        panel.add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empresa = textempresa.getText().trim();
                String contrasena = textcont.getText().trim();

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
                        Transacciones ventana = new Transacciones();
                        ventana.setVisible(true);
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