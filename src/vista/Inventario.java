package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import datab.editable;
import datab.eliminable;
import datab.guardable;
import datab.Proveedor;
import datab.Conexion;
import inicio.MovimentoInventario;
import datab.SesionActual;
import javax.swing.UIManager;

public class Inventario extends JFrame implements guardable, editable, eliminable {

    private static final long serialVersionUID = 1L;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextArea textCodigo, textProducto, textCantidad, textCosto, textPrecio;
    private JComboBox<Proveedor> comboProv;

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
            } catch (Exception ex) { }
        } // fin Nimbus 

        EventQueue.invokeLater(() -> {
            try { new Inventario().setVisible(true); }
            catch (Exception e) { e.printStackTrace(); }
        });
    }

    private void calcularPrecio() {
        try {
            double costo = Double.parseDouble(textCosto.getText().trim());
            double cantidad = Double.parseDouble(textCantidad.getText().trim());
            if (cantidad == 0) { textPrecio.setText("División por 0"); return; }
            textPrecio.setText(String.format("%.2f", (costo / cantidad) * 1.40));
        } catch (NumberFormatException e) {
            textPrecio.setText("");
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT i.idinventario, i.codigo, i.producto, p.nombre, " +
                "i.cantidad, i.costo, i.precio FROM inventario i " +
                "JOIN proveedores p ON i.id_proveedor = p.idproveedor " +
                "WHERE i.id_usuario = ?"
            );
            
            ps.setInt(1, SesionActual.getIdUsuario());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)
                });
            }
            rs.close(); ps.close(); cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando tabla: " + e.getMessage());
        }
    }

    private void cargarProveedores() {
        comboProv.removeAllItems();
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement("SELECT idproveedor, nombre FROM proveedores");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboProv.addItem(new Proveedor(rs.getInt(1), rs.getString(2)));
            }
            rs.close(); ps.close(); cn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando proveedores: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        textCodigo.setText(""); textProducto.setText("");
        textCantidad.setText(""); textCosto.setText(""); textPrecio.setText("");
        if (comboProv.getItemCount() > 0) comboProv.setSelectedIndex(0);
    }

    @Override
    public void guardar() {
        try {
            String codigo = textCodigo.getText().trim();
            String producto = textProducto.getText().trim();
            int cantidad = Integer.parseInt(textCantidad.getText().trim());
            double costo = Double.parseDouble(textCosto.getText().trim().replace(",", "."));
            double precio = Double.parseDouble(textPrecio.getText().trim().replace(",", "."));
            Proveedor prov = (Proveedor) comboProv.getSelectedItem();

            try (Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(
                    "INSERT INTO inventario (id_usuario, codigo, producto, id_proveedor, cantidad, costo, precio) VALUES (?, ?, ?, ?, ?, ?, ?)"
            )) {
                ps.setInt(1, SesionActual.getIdUsuario());
                ps.setString(2, codigo);
                ps.setString(3, producto);
                ps.setInt(4, prov.getId());
                ps.setInt(5, cantidad);
                ps.setDouble(6, costo);
                ps.setDouble(7, precio);

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(this, "Producto guardado correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "No se guardó nada");
                }
            }

            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void editar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un producto."); return; }
        try {
            int id = (int) modelo.getValueAt(fila, 0);
            String codigo = textCodigo.getText().trim();
            String producto = textProducto.getText().trim();
            int cantidad = Integer.parseInt(textCantidad.getText().trim());
            double costo = Double.parseDouble(textCosto.getText().trim());
            double precio = Double.parseDouble(textPrecio.getText().trim());
            Proveedor prov = (Proveedor) comboProv.getSelectedItem();

            try (Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(
                    "UPDATE inventario SET codigo=?, producto=?, id_proveedor=?, cantidad=?, costo=?, precio=? WHERE idinventario=?"
            )) {
                ps.setString(1, codigo);
                ps.setString(2, producto);
                ps.setInt(3, prov.getId());
                ps.setInt(4, cantidad);
                ps.setDouble(5, costo);
                ps.setDouble(6, precio);
                ps.setInt(7, id);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Producto actualizado.");
            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { JOptionPane.showMessageDialog(this, "Seleccione un producto."); return; }
        if (JOptionPane.showConfirmDialog(this, "¿Eliminar este producto?", "Confirmar",
                JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
        try {
            int id = (int) modelo.getValueAt(fila, 0);

            try (Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(
                    "DELETE FROM inventario WHERE idinventario=?"
            )) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Producto eliminado.");
            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public Inventario() {
        setTitle("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 875, 521);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // MENU LATERAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(0, 0, 147, 482);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon ic = new ImageIcon(getClass().getResource("/inicio/inventario.png"));
        JLabel lblInventario = new JLabel("   Inventario ");
        lblInventario.setForeground(Color.BLACK);
        lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInventario.setOpaque(true);
        lblInventario.setBackground(new Color(195, 195, 195));
        lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblInventario.setBounds(0, 192, 147, 49);
        lblInventario.setIcon(new ImageIcon(ic.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        panel.add(lblInventario);
        lblInventario.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new Inventario().setVisible(true); dispose(); }
        });

        ImageIcon ic1 = new ImageIcon(getClass().getResource("/inicio/flujo.png"));
        JLabel lblFlujo = new JLabel("Flujo de caja");
        lblFlujo.setForeground(Color.BLACK);
        lblFlujo.setBackground(new Color(0, 128, 255));
        lblFlujo.setOpaque(true);
        lblFlujo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblFlujo.setBounds(4, 252, 143, 49);
        lblFlujo.setIcon(new ImageIcon(ic1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        panel.add(lblFlujo);
        lblFlujo.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new Flujocaja().setVisible(true); dispose(); }
        });

        ImageIcon kl = new ImageIcon(getClass().getResource("/inicio/reportes.png"));
        JLabel lblReportes = new JLabel("     Reportes ");
        lblReportes.setForeground(Color.BLACK);
        lblReportes.setHorizontalAlignment(SwingConstants.TRAILING);
        lblReportes.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblReportes.setBounds(0, 320, 147, 49);
        lblReportes.setIcon(new ImageIcon(kl.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        panel.add(lblReportes);
        lblReportes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new Reportes().setVisible(true); dispose(); }
        });

        ImageIcon icon = new ImageIcon(getClass().getResource("/inicio/transaccion.png"));
        JLabel lblTrans = new JLabel("Transacciones");
        lblTrans.setOpaque(true);
        lblTrans.setBackground(new Color(0, 128, 255));
        lblTrans.setForeground(Color.BLACK);
        lblTrans.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTrans.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblTrans.setIcon(new ImageIcon(icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        lblTrans.setBounds(0, 139, 147, 42);
        panel.add(lblTrans);
        lblTrans.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new Transacciones().setVisible(true); dispose(); }
        });

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/inicio/finanza.png"));
        JLabel lblLogo = new JLabel("");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(10, 11, 127, 97);
        lblLogo.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        panel.add(lblLogo);

        // PANEL PRINCIPAL
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(148, 0, 711, 482);
        panel_1.setLayout(null);
        contentPane.add(panel_1);

        JLabel lblTitulo = new JLabel("Inventario");
        lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblTitulo.setBounds(10, 11, 250, 30);
        panel_1.add(lblTitulo);

        JLabel lblDolar = new JLabel("$:");
        lblDolar.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblDolar.setBounds(586, 11, 19, 30);
        panel_1.add(lblDolar);

        JLabel lblTotal = new JLabel("000");
        lblTotal.setForeground(new Color(255, 255, 0));
        lblTotal.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
        lblTotal.setBounds(606, 15, 95, 28);
        panel_1.add(lblTotal);

        // CAMPOS
        panel_1.add(crearLabel("Codigo:", 10, 52));
        textCodigo = crearTextArea(10, 77);
        panel_1.add(textCodigo);

        panel_1.add(crearLabel("Producto:", 10, 118));
        textProducto = crearTextArea(10, 138);
        panel_1.add(textProducto);

        panel_1.add(crearLabel("Proveedor:", 10, 179));
        comboProv = new JComboBox<>();
        comboProv.setBounds(10, 201, 152, 30);
        panel_1.add(comboProv);

        panel_1.add(crearLabel("Cantidad:", 10, 242));
        textCantidad = crearTextArea(10, 267);
        panel_1.add(textCantidad);

        panel_1.add(crearLabel("Costo:", 10, 304));
        textCosto = crearTextArea(10, 329);
        panel_1.add(textCosto);

        panel_1.add(crearLabel("Precio:", 10, 370));
        textPrecio = crearTextArea(10, 395);
        textPrecio.setForeground(new Color(0, 100, 0));
        textPrecio.setEditable(false);
        panel_1.add(textPrecio);

        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { calcularPrecio(); }
            public void removeUpdate(DocumentEvent e) { calcularPrecio(); }
            public void changedUpdate(DocumentEvent e) { calcularPrecio(); }
        };
        textCosto.getDocument().addDocumentListener(dl);
        textCantidad.getDocument().addDocumentListener(dl);

        // TABLA
        modelo = new DefaultTableModel(
            new String[]{"ID","Código","Producto","Proveedor","Cantidad","Costo","Precio"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 13));
        tabla.setRowHeight(22);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(190, 64, 494, 344);
        panel_1.add(scroll);

        tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int f = tabla.getSelectedRow();
                if (f >= 0) {
                    textCodigo.setText(modelo.getValueAt(f, 1).toString());
                    textProducto.setText(modelo.getValueAt(f, 2).toString());
                    textCantidad.setText(modelo.getValueAt(f, 4).toString());
                    textCosto.setText(modelo.getValueAt(f, 5).toString());
                    String prov = modelo.getValueAt(f, 3).toString();
                    for (int i = 0; i < comboProv.getItemCount(); i++) {
                        if (comboProv.getItemAt(i).toString().equals(prov)) {
                            comboProv.setSelectedIndex(i); break;
                        }
                    }
                }
            }
        });

        // BOTONES
        JButton btnAgregar = crearBoton("Agregar", "/agregar.png", 203, 436);
        panel_1.add(btnAgregar);
        btnAgregar.addActionListener(e -> guardar());

        JButton btnEditar = crearBoton("Editar", "/editar.png", 370, 436);
        panel_1.add(btnEditar);
        btnEditar.addActionListener(e -> editar());

        JButton btnEliminar = crearBoton("Eliminar", "/eliminar (1).png", 533, 436);
        panel_1.add(btnEliminar);
        btnEliminar.addActionListener(e -> eliminar());

        cargarProveedores();
        cargarTabla();
    }

    private JLabel crearLabel(String texto, int x, int y) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lbl.setBounds(x, y, 100, 18);
        return lbl;
    }

    private JTextArea crearTextArea(int x, int y) {
        JTextArea ta = new JTextArea();
        ta.setFont(new Font("Monospaced", Font.PLAIN, 17));
        ta.setBackground(new Color(190, 184, 187));
        ta.setBounds(x, y, 152, 30);
        return ta;
    }

    private JButton crearBoton(String texto, String rutaIcono, int x, int y) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(192, 192, 192));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose(); super.paintComponent(g);
            }
            @Override protected void paintBorder(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(150, 150, 150));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
                g2.dispose();
            }
        };
        ImageIcon ic = new ImageIcon(getClass().getResource(rutaIcono));
        btn.setIcon(new ImageIcon(ic.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        btn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btn.setForeground(Color.BLACK);
        btn.setBounds(x, y, texto.equals("Eliminar") ? 150 : 136, 35);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        return btn;
    }
}