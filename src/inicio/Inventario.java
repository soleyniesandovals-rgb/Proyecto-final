package inicio;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Inventario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventario frame = new Inventario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void calcularPrecio(JTextArea textCosto, JTextArea textCantidad, JTextArea textPrecio) {
		try {
			double costo = Double.parseDouble(textCosto.getText().trim());
			double cantidad = Double.parseDouble(textCantidad.getText().trim());

			if (cantidad == 0) {
				textPrecio.setText("División por 0");
				return;
			}

			double costoPorUnidad = costo / cantidad;
			double precioVenta = costoPorUnidad * 1.40;

			textPrecio.setText(String.format("%.2f", precioVenta));

		} catch (NumberFormatException e) {
			textPrecio.setText("");
		}
	}

	public Inventario() {
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblInventario.setOpaque(true);
		lblInventario.setBackground(new Color(195, 195, 195));
		lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblInventario.setBounds(0, 192, 147, 49);
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
		lblFlujoDeCaja.setBounds(4, 252, 143, 49);
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
		lblReportes.setBounds(0, 320, 147, 49);
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
		lbltransacciones.setBounds(0, 139, 147, 42);
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(148, 0, 549, 458);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Inventario");
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
		lblNewLabel_1_1.setBounds(10, 191, 83, 14);
		panel_1.add(lblNewLabel_1_1);

		JTextArea textCantidad = new JTextArea();
		textCantidad.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textCantidad.setBackground(new Color(190, 184, 187));
		textCantidad.setBounds(10, 216, 152, 30);
		panel_1.add(textCantidad);

		JLabel lblNewLabel_1_2 = new JLabel("Producto:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(10, 130, 83, 14);
		panel_1.add(lblNewLabel_1_2);

		JTextArea textCosto = new JTextArea();
		textCosto.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textCosto.setBackground(new Color(190, 184, 187));
		textCosto.setBounds(10, 278, 152, 30);
		panel_1.add(textCosto);

		JLabel lblNewLabel_1_1_1 = new JLabel("Costo:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(10, 253, 83, 14);
		panel_1.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Precio:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(10, 319, 83, 14);
		panel_1.add(lblNewLabel_1_1_1_1);

		JTextArea textPrecio = new JTextArea();
		textPrecio.setForeground(new Color(0, 100, 0));
		textPrecio.setEditable(false);
		textPrecio.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textPrecio.setBackground(new Color(190, 184, 187));
		textPrecio.setBounds(10, 344, 152, 30);
		panel_1.add(textPrecio);

		textCosto.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
			public void removeUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
			public void changedUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
		});

		textCantidad.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
			public void removeUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
			public void changedUpdate(DocumentEvent e) { calcularPrecio(textCosto, textCantidad, textPrecio); }
		});

		JLabel lblAgregar = new JLabel("Agregar");
		lblAgregar.setBackground(new Color(0, 128, 64));
		lblAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAgregar.setBounds(59, 419, 71, 28);
		panel_1.add(lblAgregar);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(190, 64, 338, 344);
		panel_1.add(panel_3);

		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEditar.setBackground(new Color(0, 128, 64));
		lblEditar.setBounds(253, 419, 54, 28);
		panel_1.add(lblEditar);

		JLabel lblEliminar = new JLabel("Eliminar");
		lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEliminar.setBackground(new Color(0, 128, 64));
		lblEliminar.setBounds(419, 419, 76, 28);
		panel_1.add(lblEliminar);

		JTextArea textProducto = new JTextArea();
		textProducto.setFont(new Font("Monospaced", Font.PLAIN, 17));
		textProducto.setBackground(new Color(190, 184, 187));
		textProducto.setBounds(10, 150, 152, 30);
		panel_1.add(textProducto);
	}
}