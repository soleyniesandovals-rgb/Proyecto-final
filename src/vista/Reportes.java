package vista;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel; // AGREGADO
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*; // AGREGADO
import javax.swing.JLabel;
import javax.swing.JOptionPane; // AGREGADO
import javax.swing.JScrollPane; // AGREGADO
import javax.swing.JTable; // AGREGADO
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Reportes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabla; // AGREGADO
	private DefaultTableModel modelo; // AGREGADO

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
		setBounds(100, 100, 875, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//menu
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 147, 482);
		contentPane.add(panel);
		panel.setLayout(null);
		
		ImageIcon ic = new ImageIcon(getClass().getResource("/inicio/inventario.png"));

		Image img = ic.getImage();
		Image imgScaled = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);

		ImageIcon iconScaled = new ImageIcon(imgScaled);

		JLabel lblInventario = new JLabel("    Inventario ");
		lblInventario.setForeground(new Color(0, 0, 0));
		lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInventario.setOpaque(true); 
		lblInventario.setBackground(new Color(0, 128, 255)); 
		lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblInventario.setBounds(0, 186, 147, 49);
		lblInventario.setIcon(iconScaled);
		panel.add(lblInventario);

		lblInventario.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        Inventario inventario = new Inventario(); 
		        inventario.setVisible(true);         
		        dispose();                   
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

		JLabel lblReportes = new JLabel("      Reportes ");
		lblReportes.setOpaque(true); 
		lblReportes.setBackground(new Color(192, 192, 192));
		lblReportes.setForeground(new Color(0, 0, 0));
		lblReportes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblReportes.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblReportes.setBounds(0, 314, 147, 49);
		lblReportes.setIcon(imgScaled111);
		panel.add(lblReportes);

		ImageIcon icon = new ImageIcon(getClass().getResource("/inicio/transaccion.png"));
		Image img2 = icon.getImage();
		Image imgScaled2 = img2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		ImageIcon iconScaled2 = new ImageIcon(imgScaled2);

		JLabel lbltransacciones = new JLabel("Transacciones");
		lbltransacciones.setBackground(new Color(0, 128, 255));
		lbltransacciones.setOpaque(true); 
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

		// Contenedor principal
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(148, 0, 711, 482);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reportes");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 250, 30);
		panel_1.add(lblNewLabel);
		
		// --- CONFIGURACIÓN DE TABLA Y SCROLLPANE ---
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(30, 100, 655, 298);
		panel_1.add(panel_3);
		panel_3.setLayout(new java.awt.BorderLayout()); // Necesario para el scroll

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new String[]{"Código", "Producto", "Proveedor", "Stock", "Vendidos", "Ingresos"});
		tabla = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panel_3.add(scrollPane, java.awt.BorderLayout.CENTER);
		
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
		
		// --- LÓGICA DEL BOTÓN MOSTRAR ---
		lblVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setRowCount(0);
				String sql = "SELECT i.codigo, i.producto, p.nombre, i.cantidad, " +
		                     "IFNULL(SUM(f.cantidad), 0), IFNULL(SUM(f.total), 0) " +
		                     "FROM inventario i " +
		                     "JOIN proveedores p ON i.id_proveedor = p.idproveedor " +
		                     "LEFT JOIN flujocaja f ON i.codigo = f.codigo " +
		                     "GROUP BY i.codigo, i.producto, p.nombre, i.cantidad";
				
				try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_final", "root", "12345");
				     Statement st = con.createStatement();
				     ResultSet rs = st.executeQuery(sql)) {
					while (rs.next()) {
						modelo.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6)});
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		
		lblVer.setIcon(agrScaled);
		lblVer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblVer.setForeground(new Color(0, 0, 0));
		lblVer.setBounds(135, 421, 136, 35);
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
		// --- LÓGICA DEL BOTÓN IMPRIMIR ---
		lblImprimir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Verificar si la tabla tiene datos antes de imprimir
		            if (tabla.getRowCount() == 0) {
		                JOptionPane.showMessageDialog(null, "No hay datos en la tabla para imprimir.");
		                return;
		            }

		            // Configuración básica de la impresión
		            boolean completo = tabla.print(JTable.PrintMode.FIT_WIDTH, 
		                new java.text.MessageFormat("Reporte de Transacciones"), // Título arriba
		                new java.text.MessageFormat("Página {0}")); // Pie de página con número

		            if (completo) {
		                JOptionPane.showMessageDialog(null, "Impresión finalizada con éxito.");
		            }
		            
		        } catch (java.awt.print.PrinterException ex) {
		            JOptionPane.showMessageDialog(null, "Error al intentar imprimir: " + ex.getMessage());
		        }
		    }
		});
		lblImprimir.setIcon(impScaled);
		lblImprimir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblImprimir.setForeground(new Color(0, 0, 0));
		lblImprimir.setBounds(496, 421, 136, 35);
		lblImprimir.setContentAreaFilled(false);
		lblImprimir.setFocusPainted(false);
		lblImprimir.setBorderPainted(false);
		lblImprimir.setOpaque(false);
		panel_1.add(lblImprimir);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFecha.setBounds(103, 59, 54, 30);
		panel_1.add(lblFecha);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDesde.setBounds(177, 59, 62, 30);
		panel_1.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHasta.setBounds(351, 59, 62, 30);
		panel_1.add(lblHasta);
		
		JRadioButton rdbtntodas = new JRadioButton("Todas");
		rdbtntodas.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rdbtntodas.setBackground(new Color(255, 255, 255));
		rdbtntodas.setBounds(523, 64, 109, 23);
		panel_1.add(rdbtntodas);
		
		JTextArea textdesde = new JTextArea();
		textdesde.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textdesde.setBackground(new Color(192, 192, 192));
		textdesde.setBounds(237, 64, 104, 22);
		panel_1.add(textdesde);
		
		JTextArea texthasta = new JTextArea();
		texthasta.setFont(new Font("Monospaced", Font.PLAIN, 14));
		texthasta.setBackground(Color.LIGHT_GRAY);
		texthasta.setBounds(410, 64, 104, 22);
		panel_1.add(texthasta);
	}
}