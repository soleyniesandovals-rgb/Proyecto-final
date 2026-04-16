
package vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import datab.Conexion;
import inicio.MovimientoFlujocaja;
import datab.SesionActual;

public class Flujocaja extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // Declaración de variables faltantes
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextArea textCodigo;
    private JTextArea textProducto;
    private JTextArea textCantidad;
    private JTextArea textPrecio;
    private JTextArea textTotal;
    private JLabel lblTotalGeneral;

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
            String precioStr = textPrecio.getText().trim();
            String cantidadStr = textCantidad.getText().trim();
            
            if (precioStr.isEmpty() || cantidadStr.isEmpty()) {
                textTotal.setText("");
                return;
            }
            
            double precio = Double.parseDouble(precioStr);
            double cantidad = Double.parseDouble(cantidadStr);
            double total = precio * cantidad;
            textTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            textTotal.setText("");
        }
    }
    
    private void limpiarCampos() {
        textCodigo.setText("");
        textProducto.setText("");
        textCantidad.setText("");
        textPrecio.setText("");
        textTotal.setText("");
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(
                "SELECT idflujocaja, codigo, producto, cantidad, precio, total, fecha " +
                "FROM flujocaja WHERE id_usuario = ? ORDER BY fecha DESC"
            );
            ps.setInt(1, SesionActual.getIdUsuario());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7)
                });
            }
            rs.close(); ps.close(); cn.close();
            calcularTotalGeneral();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando tabla: " + e.getMessage());
        }
    }
    
    private void calcularTotalGeneral() {
        double total = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            total += (Double) modelo.getValueAt(i, 5);
        }
        lblTotalGeneral.setText(String.format("%.2f", total));
    }

    public void guardar() {
        Connection cn = null;
        try {
            cn = Conexion.getConexion();
            cn.setAutoCommit(false); // IMPORTANTE (transacción)

            String codigo = textCodigo.getText().trim();
            String producto = textProducto.getText().trim();
            int cantidad = Integer.parseInt(textCantidad.getText().trim());
            double precio = Double.parseDouble(textPrecio.getText().trim());

            // 1. INSERTAR EN FLUJO CAJA
            PreparedStatement ps1 = cn.prepareStatement(
                "INSERT INTO flujocaja (id_usuario, codigo, producto, cantidad, precio, total) VALUES (?,?,?,?,?,?)"
            );
            ps1.setInt(1, SesionActual.getIdUsuario());
            ps1.setString(2, codigo);
            ps1.setString(3, producto);
            ps1.setInt(4, cantidad);
            ps1.setDouble(5, precio);
            ps1.setDouble(6, precio * cantidad);
            ps1.executeUpdate();

            // 2. RESTAR EN INVENTARIO
            PreparedStatement ps2 = cn.prepareStatement(
                "UPDATE inventario SET cantidad = cantidad - ? WHERE codigo = ? AND id_usuario = ?"
            );
            ps2.setInt(1, cantidad);
            ps2.setString(2, codigo);
            ps2.setInt(3, SesionActual.getIdUsuario());
            ps2.executeUpdate();

            cn.commit();

            JOptionPane.showMessageDialog(this, "Venta registrada correctamente.");
            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void editar() {
        Connection cn = null;
        try {
            int fila = tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.");
                return;
            }

            cn = Conexion.getConexion();
            cn.setAutoCommit(false);

            int id = (Integer) modelo.getValueAt(fila, 0);
            int cantidadAnterior = (Integer) modelo.getValueAt(fila, 3);

            int nuevaCantidad = Integer.parseInt(textCantidad.getText().trim());
            double precio = Double.parseDouble(textPrecio.getText().trim());
            String codigo = textCodigo.getText().trim();

            int diferencia = nuevaCantidad - cantidadAnterior;

            // 1. ACTUALIZAR FLUJO CAJA
            PreparedStatement ps1 = cn.prepareStatement(
                "UPDATE flujocaja SET cantidad=?, precio=?, total=? WHERE idflujocaja=?"
            );
            ps1.setInt(1, nuevaCantidad);
            ps1.setDouble(2, precio);
            ps1.setDouble(3, precio * nuevaCantidad);
            ps1.setInt(4, id);
            ps1.executeUpdate();

            // 2. AJUSTAR INVENTARIO
            PreparedStatement ps2 = cn.prepareStatement(
                "UPDATE inventario SET cantidad = cantidad - ? WHERE codigo = ? AND id_usuario = ?"
            );
            ps2.setInt(1, diferencia);
            ps2.setString(2, codigo);
            ps2.setInt(3, SesionActual.getIdUsuario());
            ps2.executeUpdate();

            cn.commit();

            JOptionPane.showMessageDialog(this, "Editado correctamente.");
            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        Connection cn = null;
        try {
            int fila = tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.");
                return;
            }

            cn = Conexion.getConexion();
            cn.setAutoCommit(false);

            int id = (Integer) modelo.getValueAt(fila, 0);
            String codigo = modelo.getValueAt(fila, 1).toString();
            int cantidad = (Integer) modelo.getValueAt(fila, 3);

            // 1. ELIMINAR DE FLUJO
            PreparedStatement ps1 = cn.prepareStatement(
                "DELETE FROM flujocaja WHERE idflujocaja=?"
            );
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 2. DEVOLVER AL INVENTARIO
            PreparedStatement ps2 = cn.prepareStatement(
                "UPDATE inventario SET cantidad = cantidad + ? WHERE codigo=? AND id_usuario=?"
            );
            ps2.setInt(1, cantidad);
            ps2.setString(2, codigo);
            ps2.setInt(3, SesionActual.getIdUsuario());
            ps2.executeUpdate();

            cn.commit();

            JOptionPane.showMessageDialog(this, "Eliminado correctamente.");
            limpiarCampos();
            cargarTabla();

        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    public Flujocaja() {
        setTitle("Flujo de Caja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 875, 521);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Botones del menú (mantengo la funcionalidad existente)
        ImageIcon ic = new ImageIcon(getClass().getResource("/inicio/inventario.png"));
        Image img = ic.getImage();
        Image imgScaled = img.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon iconScaled = new ImageIcon(imgScaled);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(0, 0, 147, 482);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon ic1 = new ImageIcon(getClass().getResource("/inicio/inventario.png"));
        JLabel lblInventario = new JLabel("   Inventario ");
        lblInventario.setForeground(Color.BLACK);
        lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInventario.setOpaque(true);
        lblInventario.setBackground(new Color(0, 128, 255));
        lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblInventario.setBounds(0, 192, 147, 49);
        lblInventario.setIcon(new ImageIcon(ic1.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        panel.add(lblInventario);
        lblInventario.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { new Inventario().setVisible(true); dispose(); }
        });

        ImageIcon ic11 = new ImageIcon(getClass().getResource("/inicio/flujo.png"));
        JLabel lblFlujo = new JLabel("Flujo de caja");
        lblFlujo.setForeground(Color.BLACK);
        lblFlujo.setBackground(new Color(192, 192, 192));
        lblFlujo.setOpaque(true);
        lblFlujo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblFlujo.setBounds(0, 252, 147, 49);
        lblFlujo.setIcon(new ImageIcon(ic11.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
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

        JLabel lblDolar = new JLabel("$:");
        lblDolar.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblDolar.setBounds(586, 11, 19, 30);
        panel_1.add(lblDolar);
        
        // Panel principal
        JPanel panel1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(148, 0, 711, 482);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Flujo de Caja");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblNewLabel.setBounds(10, 11, 250, 30);
        panel_1.add(lblNewLabel);

        // Campos de entrada
        JLabel lblNewLabel_1 = new JLabel("Codigo:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(10, 64, 83, 18);
        panel_1.add(lblNewLabel_1);

        textCodigo = new JTextArea();
        textCodigo.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textCodigo.setBackground(new Color(190, 184, 187));
        textCodigo.setBounds(10, 89, 152, 30);
        panel_1.add(textCodigo);

        JLabel lblNewLabel_1_2 = new JLabel("Producto:");
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1_2.setBounds(10, 130, 83, 14);
        panel_1.add(lblNewLabel_1_2);

        textProducto = new JTextArea();
        textProducto.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textProducto.setBackground(new Color(190, 184, 187));
        textProducto.setBounds(10, 150, 152, 30);
        panel_1.add(textProducto);

        JLabel lblNewLabel_1_1 = new JLabel("Cantidad:");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1_1.setBounds(10, 194, 83, 14);
        panel_1.add(lblNewLabel_1_1);

        textCantidad = new JTextArea();
        textCantidad.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textCantidad.setBackground(new Color(190, 184, 187));
        textCantidad.setBounds(10, 219, 152, 30);
        panel_1.add(textCantidad);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Precio:");
        lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1_1_1_1.setBounds(10, 269, 83, 14);
        panel_1.add(lblNewLabel_1_1_1_1);

        textPrecio = new JTextArea();
        textPrecio.setForeground(new Color(0, 0, 0));
        textPrecio.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textPrecio.setBackground(new Color(190, 184, 187));
        textPrecio.setBounds(10, 294, 152, 30);
        panel_1.add(textPrecio);

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Total:");
        lblNewLabel_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblNewLabel_1_1_1_1_1.setBounds(10, 345, 83, 14);
        panel_1.add(lblNewLabel_1_1_1_1_1);

        textTotal = new JTextArea();
        textTotal.setForeground(new Color(0, 64, 0));
        textTotal.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textTotal.setEditable(false);
        textTotal.setBackground(new Color(190, 184, 187));
        textTotal.setBounds(10, 370, 152, 30);
        panel_1.add(textTotal);

        // Listeners para cálculo automático
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

        // Botones funcionales
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
        };
        lblAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        lblAgregar.setIcon(agrScaled);
        lblAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAgregar.setForeground(new Color(255, 255, 255));
        lblAgregar.setBounds(200, 419, 136, 35);
        lblAgregar.setContentAreaFilled(false);
        lblAgregar.setFocusPainted(false);
        lblAgregar.setBorderPainted(false);
        lblAgregar.setOpaque(false);
        panel_1.add(lblAgregar);

        // Botón Editar
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
        };
        lblEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        lblEditar.setIcon(ediScaled);
        lblEditar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEditar.setForeground(new Color(255, 255, 255));
        lblEditar.setBounds(367, 419, 136, 35);
        lblEditar.setContentAreaFilled(false);
        lblEditar.setFocusPainted(false);
        lblEditar.setBorderPainted(false);
        lblEditar.setOpaque(false);
        panel_1.add(lblEditar);

        // Botón Eliminar
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
        };
        lblEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        lblEliminar.setIcon(eliScaled);
        lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEliminar.setForeground(new Color(255, 255, 255));
        lblEliminar.setBounds(530, 419, 150, 35);
        lblEliminar.setContentAreaFilled(false);
        lblEliminar.setFocusPainted(false);
        lblEliminar.setBorderPainted(false);
        lblEliminar.setOpaque(false);
        panel_1.add(lblEliminar);

        // Tabla
        modelo = new DefaultTableModel(
            new String[]{"ID","Código","Producto","Cantidad","Precio","Total","Fecha"}, 0
        ) { 
            public boolean isCellEditable(int r, int c) { return false; } 
        };
        
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 13));
        tabla.setRowHeight(22);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(190, 64, 499, 344);
        panel_1.add(scroll);

        // Selección en tabla
        tabla.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                int f = tabla.getSelectedRow();
                if (f >= 0) {
                    textCodigo.setText(modelo.getValueAt(f, 1).toString());
                    textProducto.setText(modelo.getValueAt(f, 2).toString());
                    textCantidad.setText(modelo.getValueAt(f, 3).toString());
                    textPrecio.setText(String.format("%.2f", modelo.getValueAt(f, 4)));
                    textTotal.setText(String.format("%.2f", modelo.getValueAt(f, 5)));
                }
            }
        });

        // Total general
        JLabel lblNewLabel_3 = new JLabel("$:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 19));
        lblNewLabel_3.setBounds(574, 11, 19, 30);
        panel_1.add(lblNewLabel_3);
        
        lblTotalGeneral = new JLabel("0.00");
        lblTotalGeneral.setForeground(new Color(0, 128, 0));
        lblTotalGeneral.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
        lblTotalGeneral.setBounds(606, 11, 95, 28);
        panel_1.add(lblTotalGeneral);

        // Cargar datos al iniciar
        cargarTabla();
    }
}