package inicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Transacciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transacciones frame = new Transacciones();
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
	public Transacciones() {
		setBackground(new Color(0, 0, 0));
		setTitle("Tansacciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 147, 467);
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
		lblInventario.setBounds(0, 193, 147, 49);
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
		lblFlujoDeCaja.setBounds(4, 253, 143, 49);
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
		lblReportes.setForeground(new Color(0, 0, 0));
		lblReportes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblReportes.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblReportes.setBounds(0, 321, 147, 49);
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
		lbltransacciones.setBackground(new Color(192, 192, 192));
		lbltransacciones.setOpaque(true); // Hace que el fondo sea visible
		lbltransacciones.setBackground(new Color(192, 192, 192)); // Establece el color de fondo
		lbltransacciones.setForeground(new Color(0, 0, 0));
		lbltransacciones.setHorizontalAlignment(SwingConstants.TRAILING);
		lbltransacciones.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbltransacciones.setIcon(iconScaled2);
		lbltransacciones.setBounds(0, 140, 147, 42);
		panel.add(lbltransacciones);
		
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/inicio/finanza.png"));

		Image img21 = icon1.getImage();
		Image imgScaled21 = img21.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

		ImageIcon iconScaled21 = new ImageIcon(imgScaled21);

		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 127, 97);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(iconScaled21);


		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(148, 0, 711, 467);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Transacciones");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 250, 30);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descipcion:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 52, 83, 14);
		panel_1.add(lblNewLabel_1);
		
		JTextArea textDescripcion = new JTextArea();
		textDescripcion.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textDescripcion.setBackground(new Color(190, 184, 187));
		textDescripcion.setBounds(10, 77, 152, 30);
		panel_1.add(textDescripcion);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 225, 83, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JTextArea textFecha = new JTextArea();
		textFecha.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textFecha.setBackground(new Color(190, 184, 187));
		textFecha.setBounds(10, 250, 152, 30);
		panel_1.add(textFecha);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tipo:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(10, 118, 83, 21);
		panel_1.add(lblNewLabel_1_2);
		
		JTextArea textEstado = new JTextArea();
		textEstado.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textEstado.setBackground(new Color(190, 184, 187));
		textEstado.setBounds(10, 312, 152, 30);
		panel_1.add(textEstado);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Estado:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(10, 287, 83, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cantidad $:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(10, 353, 83, 14);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JTextArea textCantidad = new JTextArea();
		textCantidad.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textCantidad.setBackground(new Color(190, 184, 187));
		textCantidad.setBounds(10, 378, 152, 30);
		panel_1.add(textCantidad);
		
		//inicio botones

		
		ImageIcon ag = new ImageIcon(getClass().getResource("/guardar.png"));
		Image agr = ag.getImage();
		Image agrScaledImg = agr.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon agrScaled = new ImageIcon(agrScaledImg);

		JButton lblAgregar = new JButton("Guardar") {
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
		lblAgregar.setIcon(agrScaled);
		lblAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgregar.setForeground(new Color(0, 0, 0));
		lblAgregar.setBounds(206, 421, 136, 35);
		lblAgregar.setContentAreaFilled(false);
		lblAgregar.setFocusPainted(false);
		lblAgregar.setBorderPainted(false);
		lblAgregar.setOpaque(false);
		panel_1.add(lblAgregar);
		
		ImageIcon ed = new ImageIcon(getClass().getResource("/editar.png"));
		Image edi = ed.getImage();
		Image ediScaledImg = edi.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon ediScaled = new ImageIcon(ediScaledImg);

		JButton lblEditar = new JButton("Editar") {
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
		lblEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lblEditar.setIcon(ediScaled);
		lblEditar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEditar.setForeground(new Color(0, 0, 0));
		lblEditar.setBounds(373, 421, 136, 35);
		lblEditar.setContentAreaFilled(false);
		lblEditar.setFocusPainted(false);
		lblEditar.setBorderPainted(false);
		lblEditar.setOpaque(false);
		panel_1.add(lblEditar);
		
		ImageIcon el = new ImageIcon(getClass().getResource("/eliminar (1).png"));
		Image eli = el.getImage();
		Image eliScaledImg = eli.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon eliScaled = new ImageIcon(eliScaledImg);
		
		JButton lblEliminar = new JButton("Eliminar") {
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
		lblEliminar.setIcon(eliScaled);
		lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEliminar.setForeground(new Color(0, 0, 0));
		lblEliminar.setBounds(536, 421, 150, 35);
		lblEliminar.setContentAreaFilled(false);
		lblEliminar.setFocusPainted(false);
		lblEliminar.setBorderPainted(false);
		lblEliminar.setOpaque(false);
		panel_1.add(lblEliminar);
		
		//fin botones
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 138, 170, 79);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnVenta = new JRadioButton("Venta minorista");
		rdbtnVenta.setBounds(6, 7, 119, 27);
		panel_2.add(rdbtnVenta);
		rdbtnVenta.setBackground(new Color(255, 255, 255));
		rdbtnVenta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JRadioButton rdbtnGastoOperativo = new JRadioButton("Gasto operativo");
		rdbtnGastoOperativo.setBounds(6, 33, 139, 23);
		panel_2.add(rdbtnGastoOperativo);
		rdbtnGastoOperativo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnGastoOperativo.setBackground(Color.WHITE);
		
		JRadioButton rdbtnVenta_1_1 = new JRadioButton("Adquisición de activos");
		rdbtnVenta_1_1.setBounds(6, 59, 158, 23);
		panel_2.add(rdbtnVenta_1_1);
		rdbtnVenta_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtnVenta_1_1.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(203, 64, 483, 343);
		panel_1.add(panel_3);
		
		
	}
}
