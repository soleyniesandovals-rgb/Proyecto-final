package inicio;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Reportes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reportes frame = new Reportes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reportes() {
		setTitle("Reportes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//menu
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 147, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon ic = new ImageIcon(getClass().getResource("/inicio/inventario.png"));

		Image img = ic.getImage();
		Image imgScaled = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

		ImageIcon iconScaled = new ImageIcon(imgScaled);

JLabel lblInventario = new JLabel("   Inventario ");
lblInventario.setForeground(new Color(0, 0, 0));
lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
lblInventario.setOpaque(true); // Hace que el fondo sea visible
lblInventario.setBackground(new Color(0, 128, 255)); // Establece el color de fondo
lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
lblInventario.setBounds(0, 186, 147, 49);
lblInventario.setIcon(iconScaled);

panel.add(lblInventario);

lblInventario.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Inventario inventario = new Inventario(); // Instanciar nueva ventana
        inventario.setVisible(true);         // Mostrar nueva ventana
        dispose();                   // Cerrar Ventana1 (opcional)
    }
});

ImageIcon ic1 = new ImageIcon(getClass().getResource("/inicio/flujo.png"));

Image img1 = ic1.getImage();
Image imgScaled1 = img1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

ImageIcon iconScaled1 = new ImageIcon(imgScaled1); 

JLabel lblFlujoDeCaja = new JLabel("Flujo de caja");
lblFlujoDeCaja.setForeground(new Color(0, 0, 0));
lblFlujoDeCaja.setBackground(new Color(0, 128, 255));
lblFlujoDeCaja.setOpaque(true); 
lblFlujoDeCaja.setFont(new Font("Times New Roman", Font.PLAIN, 18));
lblFlujoDeCaja.setBounds(4, 246, 143, 49);
lblFlujoDeCaja.setIcon(iconScaled1);
panel.add(lblFlujoDeCaja);

lblFlujoDeCaja.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Flujocaja flujocaja = new Flujocaja(); 
        flujocaja.setVisible(true);        
        dispose();                 
    }
});

ImageIcon kl = new ImageIcon(getClass().getResource("/inicio/reportes.png"));

Image kl1 = kl.getImage();
Image imgScaled11 = kl1.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

ImageIcon imgScaled111 = new ImageIcon(imgScaled11); 

JLabel lblReportes = new JLabel("     Reportes ");
lblReportes.setOpaque(true); // Hace que el fondo sea visible
lblReportes.setBackground(new Color(192, 192, 192));
lblReportes.setForeground(new Color(0, 0, 0));
lblReportes.setHorizontalAlignment(SwingConstants.TRAILING);
lblReportes.setFont(new Font("Times New Roman", Font.PLAIN, 18));
lblReportes.setBounds(0, 314, 147, 49);
lblReportes.setIcon(imgScaled111);
panel.add(lblReportes);


lblReportes.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Reportes reportes = new Reportes(); 
        reportes.setVisible(true);        
        dispose();                 
    }
});

ImageIcon icon = new ImageIcon(getClass().getResource("/inicio/transaccion.png"));

Image img2 = icon.getImage();
Image imgScaled2 = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

ImageIcon iconScaled2 = new ImageIcon(imgScaled2);

JLabel lbltransacciones = new JLabel("Transacciones");
lbltransacciones.setBackground(new Color(0, 128, 255));
lbltransacciones.setOpaque(true); // Hace que el fondo sea visible
lbltransacciones.setBackground(new Color(0, 128, 255)); // Establece el color de fondo
lbltransacciones.setForeground(new Color(0, 0, 0));
lbltransacciones.setHorizontalAlignment(SwingConstants.TRAILING);
lbltransacciones.setFont(new Font("Times New Roman", Font.PLAIN, 17));
lbltransacciones.setIcon(iconScaled2);
lbltransacciones.setBounds(0, 133, 147, 42);
panel.add(lbltransacciones);

lbltransacciones.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Transacciones transacciones = new Transacciones(); 
        transacciones.setVisible(true);        
        dispose();                 
    }
});

ImageIcon icon1 = new ImageIcon(getClass().getResource("/inicio/finanza.png"));

Image img21 = icon1.getImage();
Image imgScaled21 = img21.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

ImageIcon iconScaled21 = new ImageIcon(imgScaled21);


JLabel lblNewLabel_2 = new JLabel("");
lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel_2.setBounds(10, 11, 127, 97);
panel.add(lblNewLabel_2);
lblNewLabel_2.setIcon(iconScaled21);


//menu
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(148, 0, 549, 458);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reportes");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 250, 30);
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 100, 518, 298);
		panel_1.add(panel_3);
		
		ImageIcon ag = new ImageIcon(getClass().getResource("/ver.png"));
		Image agr = ag.getImage();
		Image agrScaledImg = agr.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon agrScaled = new ImageIcon(agrScaledImg);

		JButton lblVer = new JButton("Mostrar") {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(new Color(192, 192, 192));
		        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		        g2.dispose();
		        super.paintComponent(g);
		    }

		    @Override
		    protected void paintBorder(java.awt.Graphics g) {
		        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(new Color(150, 150, 150));
		        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
		        g2.dispose();
		    }
		};
		lblVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lblVer.setIcon(agrScaled);
		lblVer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblVer.setForeground(new Color(0, 0, 0));
		lblVer.setBounds(67, 409, 136, 35);
		lblVer.setContentAreaFilled(false);
		lblVer.setFocusPainted(false);
		lblVer.setBorderPainted(false);
		lblVer.setOpaque(false);
		panel_1.add(lblVer);
		
				
		ImageIcon im = new ImageIcon(getClass().getResource("/imprimir.png"));
		Image imp = im.getImage();
		Image impScaledImg = imp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon impScaled = new ImageIcon(impScaledImg);

		JButton lblImprimir = new JButton("Imprimir") {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(new Color(192, 192, 192));
		        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		        g2.dispose();
		        super.paintComponent(g);
		    }

		    @Override
		    protected void paintBorder(java.awt.Graphics g) {
		        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(new Color(150, 150, 150));
		        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
		        g2.dispose();
		    }
		};
		lblImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lblImprimir.setIcon(impScaled);
		lblImprimir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblImprimir.setForeground(new Color(0, 0, 0));
		lblImprimir.setBounds(314, 409, 136, 35);
		lblImprimir.setContentAreaFilled(false);
		lblImprimir.setFocusPainted(false);
		lblImprimir.setBorderPainted(false);
		lblImprimir.setOpaque(false);
		panel_1.add(lblImprimir);
		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFecha.setBounds(20, 59, 54, 30);
		panel_1.add(lblFecha);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDesde.setBounds(94, 59, 62, 30);
		panel_1.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHasta.setBounds(268, 59, 62, 30);
		panel_1.add(lblHasta);
		
		JRadioButton rdbtntodas = new JRadioButton("Todas");
		rdbtntodas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtntodas.setBackground(new Color(255, 255, 255));
		rdbtntodas.setBounds(440, 64, 109, 23);
		panel_1.add(rdbtntodas);
		
		JTextArea textdesde = new JTextArea();
		textdesde.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textdesde.setBackground(new Color(192, 192, 192));
		textdesde.setBounds(154, 64, 104, 22);
		panel_1.add(textdesde);
		
		JTextArea texthasta = new JTextArea();
		texthasta.setFont(new Font("Monospaced", Font.PLAIN, 14));
		texthasta.setBackground(Color.LIGHT_GRAY);
		texthasta.setBounds(327, 64, 104, 22);
		panel_1.add(texthasta);

	}

}
