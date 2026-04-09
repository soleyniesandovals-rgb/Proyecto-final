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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Flujocaja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flujocaja frame = new Flujocaja();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void calcularTotal(JTextArea textPrecio, JTextArea textCantidad, JTextArea textTotal) {
		try {
			double precio = Double.parseDouble(textPrecio.getText().trim());
			double cantidad = Double.parseDouble(textCantidad.getText().trim());
			double total = precio * cantidad;
			textTotal.setText(String.format("%.2f", total));
		} catch (NumberFormatException e) {
			textTotal.setText("");
		}
	}

	public Flujocaja() {
		setTitle("Flujo de caja");
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

		JLabel lblInventario = new JLabel("   Inventario ");
		lblInventario.setForeground(new Color(0, 0, 0));
		lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInventario.setOpaque(true);
		lblInventario.setBackground(new Color(0, 128, 255));
		lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblInventario.setBounds(0, 194, 147, 49);
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
		lblFlujoDeCaja.setBackground(new Color(195, 195, 195));
		lblFlujoDeCaja.setOpaque(true);
		lblFlujoDeCaja.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFlujoDeCaja.setBounds(0, 254, 147, 49);
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
		lblReportes.setBounds(0, 322, 147, 49);
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
		lbltransacciones.setOpaque(true);
		lbltransacciones.setBackground(new Color(0, 128, 255));
		lbltransacciones.setForeground(new Color(0, 0, 0));
		lbltransacciones.setHorizontalAlignment(SwingConstants.TRAILING);
		lbltransacciones.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lbltransacciones.setIcon(iconScaled2);
		lbltransacciones.setBounds(0, 141, 147, 42);
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
		panel_1.setBounds(148, 0, 711, 482);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Flujo de Caja");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 250, 30);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 64, 83, 18);
		panel_1.add(lblNewLabel_1);

		JTextArea textCodigo = new JTextArea();
		textCodigo.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textCodigo.setBackground(new Color(190, 184, 187));
		textCodigo.setBounds(10, 89, 152, 30);
		panel_1.add(textCodigo);

		JLabel lblNewLabel_1_1 = new JLabel("Cantidad:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 194, 83, 14);
		panel_1.add(lblNewLabel_1_1);

		JTextArea textCantidad = new JTextArea();
		textCantidad.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textCantidad.setBackground(new Color(190, 184, 187));
		textCantidad.setBounds(10, 219, 152, 30);
		panel_1.add(textCantidad);

		JLabel lblNewLabel_1_2 = new JLabel("Producto:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(10, 130, 83, 14);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Precio:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(10, 269, 83, 14);
		panel_1.add(lblNewLabel_1_1_1_1);

		JTextArea textPrecio = new JTextArea();
		textPrecio.setForeground(new Color(0, 0, 0));
		textPrecio.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textPrecio.setBackground(new Color(190, 184, 187));
		textPrecio.setBounds(10, 294, 152, 30);
		panel_1.add(textPrecio);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Total:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1_1.setBounds(10, 345, 83, 14);
		panel_1.add(lblNewLabel_1_1_1_1_1);

		JTextArea textTotal = new JTextArea();
		textTotal.setForeground(new Color(0, 64, 0));
		textTotal.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textTotal.setEditable(false);
		textTotal.setBackground(new Color(190, 184, 187));
		textTotal.setBounds(10, 370, 152, 30);
		panel_1.add(textTotal);

		textPrecio.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
			public void removeUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
			public void changedUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
		});

		textCantidad.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
			public void removeUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
			public void changedUpdate(DocumentEvent e) { calcularTotal(textPrecio, textCantidad, textTotal); }
		});

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
		lblAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		lblAgregar.setIcon(agrScaled);
		lblAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgregar.setForeground(new Color(0, 0, 0));
		lblAgregar.setBounds(200, 419, 136, 35);
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
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
		lblEditar.setIcon(ediScaled);
		lblEditar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEditar.setForeground(new Color(0, 0, 0));
		lblEditar.setBounds(367, 419, 136, 35);
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
		lblEliminar.setBounds(530, 419, 150, 35);
		lblEliminar.setContentAreaFilled(false);
		lblEliminar.setFocusPainted(false);
		lblEliminar.setBorderPainted(false);
		lblEliminar.setOpaque(false);
		panel_1.add(lblEliminar);

		//fin botones

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(190, 64, 499, 344);
		panel_1.add(panel_3);

		JTextArea textProducto = new JTextArea();
		textProducto.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textProducto.setBackground(new Color(190, 184, 187));
		textProducto.setBounds(10, 150, 152, 30);
		panel_1.add(textProducto);

		JLabel lblNewLabel_3 = new JLabel("$:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel_3.setBounds(574, 11, 19, 30);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("000");
		lblNewLabel_4.setForeground(new Color(0, 128, 0));
		lblNewLabel_4.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(594, 15, 95, 28);
		panel_1.add(lblNewLabel_4);
	}
}