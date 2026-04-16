package vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import datab.Conexion;
import datab.SesionActual;

public class Transacciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextArea textDescripcion, textFecha, textEstado, textCantidad;
    private JRadioButton rdbtnVenta, rdbtnGasto, rdbtnAdquisicion;

    private void cargarTabla() {
        modelo.setRowCount(0);
        try {
            java.sql.Connection cn = Conexion.getConexion();
            java.sql.PreparedStatement ps = cn.prepareStatement(
                "SELECT idtransacciones, descripcion, tipo, fecha, estado, cantidad " +
                "FROM transacciones WHERE id_usuario = ? ORDER BY fecha DESC"
            );
            ps.setInt(1, SesionActual.getIdUsuario());
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getInt(6)
                });
            }
            rs.close(); ps.close(); cn.close();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error cargando tabla: " + e.getMessage());
        }
    }

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

    public Transacciones() {
        setBackground(new Color(0, 0, 0));
        setTitle("Transacciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 875, 499);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // MENU LATERAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(0, 0, 147, 467);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon ic = new ImageIcon(getClass().getResource("/inicio/inventario.png"));
        JLabel lblInventario = new JLabel("   Inventario ");
        lblInventario.setForeground(new Color(0, 0, 0));
        lblInventario.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInventario.setOpaque(true);
        lblInventario.setBackground(new Color(0, 128, 255));
        lblInventario.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblInventario.setBounds(0, 193, 147, 49);
        lblInventario.setIcon(new ImageIcon(ic.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        panel.add(lblInventario);
        lblInventario.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                new Inventario().setVisible(true); dispose();
            }
        });

        ImageIcon ic1 = new ImageIcon(getClass().getResource("/inicio/flujo.png"));
        JLabel lblFlujoDeCaja = new JLabel("Flujo de caja");
        lblFlujoDeCaja.setForeground(new Color(0, 0, 0));
        lblFlujoDeCaja.setBackground(new Color(0, 128, 255));
        lblFlujoDeCaja.setOpaque(true);
        lblFlujoDeCaja.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblFlujoDeCaja.setBounds(4, 253, 143, 49);
        lblFlujoDeCaja.setIcon(new ImageIcon(ic1.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        panel.add(lblFlujoDeCaja);
        lblFlujoDeCaja.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                new Flujocaja().setVisible(true); dispose();
            }
        });

        ImageIcon kl = new ImageIcon(getClass().getResource("/inicio/reportes.png"));
        JLabel lblReportes = new JLabel("     Reportes ");
        lblReportes.setForeground(new Color(0, 0, 0));
        lblReportes.setHorizontalAlignment(SwingConstants.TRAILING);
        lblReportes.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblReportes.setBounds(0, 321, 147, 49);
        lblReportes.setIcon(new ImageIcon(kl.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        panel.add(lblReportes);
        lblReportes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                new Reportes().setVisible(true); dispose();
            }
        });

        ImageIcon icon = new ImageIcon(getClass().getResource("/inicio/transaccion.png"));
        JLabel lbltransacciones = new JLabel("Transacciones");
        lbltransacciones.setOpaque(true);
        lbltransacciones.setBackground(new Color(192, 192, 192));
        lbltransacciones.setForeground(new Color(0, 0, 0));
        lbltransacciones.setHorizontalAlignment(SwingConstants.TRAILING);
        lbltransacciones.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lbltransacciones.setIcon(new ImageIcon(icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
        lbltransacciones.setBounds(0, 140, 147, 42);
        panel.add(lbltransacciones);

        ImageIcon icon1 = new ImageIcon(getClass().getResource("/inicio/finanza.png"));
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(10, 11, 127, 97);
        lblNewLabel_2.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        panel.add(lblNewLabel_2);

        // PANEL PRINCIPAL
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

        // CAMPOS
        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblDescripcion.setBounds(10, 52, 90, 14);
        panel_1.add(lblDescripcion);

        textDescripcion = new JTextArea();
        textDescripcion.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textDescripcion.setBackground(new Color(190, 184, 187));
        textDescripcion.setBounds(10, 77, 152, 30);
        panel_1.add(textDescripcion);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblTipo.setBounds(10, 118, 83, 21);
        panel_1.add(lblTipo);

        // RADIO BUTTONS
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(10, 138, 170, 79);
        panel_1.add(panel_2);
        panel_2.setLayout(null);

        rdbtnVenta = new JRadioButton("Venta minorista");
        rdbtnVenta.setBounds(6, 7, 119, 27);
        rdbtnVenta.setBackground(new Color(255, 255, 255));
        rdbtnVenta.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        panel_2.add(rdbtnVenta);

        rdbtnGasto = new JRadioButton("Gasto operativo");
        rdbtnGasto.setBounds(6, 33, 139, 23);
        rdbtnGasto.setBackground(Color.WHITE);
        rdbtnGasto.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        panel_2.add(rdbtnGasto);

        rdbtnAdquisicion = new JRadioButton("Adquisición de activos");
        rdbtnAdquisicion.setBounds(6, 59, 158, 23);
        rdbtnAdquisicion.setBackground(Color.WHITE);
        rdbtnAdquisicion.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        panel_2.add(rdbtnAdquisicion);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rdbtnVenta);
        grupo.add(rdbtnGasto);
        grupo.add(rdbtnAdquisicion);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblFecha.setBounds(10, 225, 83, 14);
        panel_1.add(lblFecha);

        textFecha = new JTextArea();
        textFecha.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textFecha.setBackground(new Color(190, 184, 187));
        textFecha.setBounds(10, 250, 152, 30);
        panel_1.add(textFecha);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblEstado.setBounds(10, 287, 83, 14);
        panel_1.add(lblEstado);

        textEstado = new JTextArea();
        textEstado.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textEstado.setBackground(new Color(190, 184, 187));
        textEstado.setBounds(10, 312, 152, 30);
        panel_1.add(textEstado);

        JLabel lblCantidad = new JLabel("Cantidad $:");
        lblCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        lblCantidad.setBounds(10, 353, 83, 14);
        panel_1.add(lblCantidad);

        textCantidad = new JTextArea();
        textCantidad.setFont(new Font("Monospaced", Font.PLAIN, 17));
        textCantidad.setBackground(new Color(190, 184, 187));
        textCantidad.setBounds(10, 378, 152, 30);
        panel_1.add(textCantidad);

        // BOTONES
        ImageIcon ag = new ImageIcon(getClass().getResource("/guardar.png"));
        JButton lblAgregar = new JButton("Guardar") {
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
        lblAgregar.setIcon(new ImageIcon(ag.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        lblAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAgregar.setForeground(new Color(0, 0, 0));
        lblAgregar.setBounds(206, 421, 136, 35);
        lblAgregar.setContentAreaFilled(false);
        lblAgregar.setFocusPainted(false);
        lblAgregar.setBorderPainted(false);
        lblAgregar.setOpaque(false);
        lblAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	lblAgregar.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	        if (textDescripcion.getText().isEmpty() || textFecha.getText().isEmpty() || 
            	            textEstado.getText().isEmpty() || textCantidad.getText().isEmpty() ||
            	            (!rdbtnVenta.isSelected() && !rdbtnGasto.isSelected() && !rdbtnAdquisicion.isSelected())) {
            	            
            	            javax.swing.JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            	            return;
            	        }

            	        try {
            	            java.sql.Connection cn = Conexion.getConexion();
            	            java.sql.PreparedStatement ps = cn.prepareStatement(
            	                "INSERT INTO transacciones (descripcion, tipo, fecha, estado, cantidad, id_usuario) VALUES (?, ?, ?, ?, ?, ?)"
            	            );
            	            ps.setString(1, textDescripcion.getText());
            	            ps.setString(2, rdbtnVenta.isSelected() ? "Venta minorista" : 
            	                            rdbtnGasto.isSelected() ? "Gasto operativo" : "Adquisición de activos");
            	            ps.setString(3, textFecha.getText());
            	            ps.setString(4, textEstado.getText());
            	            ps.setInt(5, Integer.parseInt(textCantidad.getText()));
            	            ps.setInt(6, SesionActual.getIdUsuario());
            	            
            	            int resultado = ps.executeUpdate();
            	            if (resultado > 0) {
            	                javax.swing.JOptionPane.showMessageDialog(null, "Transacción guardada exitosamente");
            	                limpiarCampos();
            	                cargarTabla();
            	            }
            	            ps.close();
            	            cn.close();
            	        } catch (NumberFormatException ex) {
            	            javax.swing.JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            	        } catch (Exception ex) {
            	            javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	        }
            	    }

					private void limpiarCampos() {
						// TODO Auto-generated method stub
						
					}
            	});

            }
        });
        panel_1.add(lblAgregar);

        ImageIcon ed = new ImageIcon(getClass().getResource("/editar.png"));
        JButton lblEditar = new JButton("Editar") {
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
        lblEditar.setIcon(new ImageIcon(ed.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        lblEditar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEditar.setForeground(new Color(0, 0, 0));
        lblEditar.setBounds(373, 421, 136, 35);
        lblEditar.setContentAreaFilled(false);
        lblEditar.setFocusPainted(false);
        lblEditar.setBorderPainted(false);
        lblEditar.setOpaque(false);
        lblEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	lblEditar.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	        int f = tabla.getSelectedRow();
            	        if (f < 0) {
            	            javax.swing.JOptionPane.showMessageDialog(null, "Seleccione una transacción para editar", "Error", JOptionPane.ERROR_MESSAGE);
            	            return;
            	        }

            	        if (textDescripcion.getText().isEmpty() || textFecha.getText().isEmpty() || 
            	            textEstado.getText().isEmpty() || textCantidad.getText().isEmpty()) {
            	            javax.swing.JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            	            return;
            	        }

            	        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
            	            "¿Está seguro de editar esta transacción?", "Confirmar", JOptionPane.YES_NO_OPTION);
            	        
            	        if (confirmacion == JOptionPane.YES_OPTION) {
            	            try {
            	                java.sql.Connection cn = Conexion.getConexion();
            	                java.sql.PreparedStatement ps = cn.prepareStatement(
            	                    "UPDATE transacciones SET descripcion=?, tipo=?, fecha=?, estado=?, cantidad=? WHERE idtransacciones=? AND id_usuario=?"
            	                );
            	                ps.setString(1, textDescripcion.getText());
            	                ps.setString(2, rdbtnVenta.isSelected() ? "Venta minorista" : 
            	                                rdbtnGasto.isSelected() ? "Gasto operativo" : "Adquisición de activos");
            	                ps.setString(3, textFecha.getText());
            	                ps.setString(4, textEstado.getText());
            	                ps.setInt(5, Integer.parseInt(textCantidad.getText()));
            	                ps.setInt(6, (Integer)modelo.getValueAt(f, 0));
            	                ps.setInt(7, SesionActual.getIdUsuario());
            	                
            	                int resultado = ps.executeUpdate();
            	                if (resultado > 0) {
            	                    javax.swing.JOptionPane.showMessageDialog(null, "Transacción actualizada exitosamente");
            	                    limpiarCampos();
            	                    cargarTabla();
            	                }
            	                ps.close();
            	                cn.close();
            	            } catch (NumberFormatException ex) {
            	                javax.swing.JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            	            } catch (Exception ex) {
            	                javax.swing.JOptionPane.showMessageDialog(null, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	            }
            	        }
            	    }

					private void limpiarCampos() {
						// TODO Auto-generated method stub
						
					}
            	});
            }
        });
        panel_1.add(lblEditar);

        ImageIcon el = new ImageIcon(getClass().getResource("/eliminar (1).png"));
        JButton lblEliminar = new JButton("Eliminar") {
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
        lblEliminar.setIcon(new ImageIcon(el.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        lblEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEliminar.setForeground(new Color(0, 0, 0));
        lblEliminar.setBounds(536, 421, 150, 35);
        lblEliminar.setContentAreaFilled(false);
        lblEliminar.setFocusPainted(false);
        lblEliminar.setBorderPainted(false);
        lblEliminar.setOpaque(false);
        lblEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	lblEliminar.addActionListener(new ActionListener() {
            	    public void actionPerformed(ActionEvent e) {
            	        int f = tabla.getSelectedRow();
            	        if (f < 0) {
            	            javax.swing.JOptionPane.showMessageDialog(null, "Seleccione una transacción para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            	            return;
            	        }

            	        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(null, 
            	            "¿Está seguro de eliminar esta transacción?\nNo se puede deshacer esta acción.", 
            	            "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            	        
            	        if (confirmacion == JOptionPane.YES_OPTION) {
            	            try {
            	                java.sql.Connection cn = Conexion.getConexion();
            	                java.sql.PreparedStatement ps = cn.prepareStatement(
            	                    "DELETE FROM transacciones WHERE idtransacciones=? AND id_usuario=?"
            	                );
            	                ps.setInt(1, (Integer)modelo.getValueAt(f, 0));
            	                ps.setInt(2, SesionActual.getIdUsuario());
            	                
            	                int resultado = ps.executeUpdate();
            	                if (resultado > 0) {
            	                    javax.swing.JOptionPane.showMessageDialog(null, "Transacción eliminada exitosamente");
            	                    limpiarCampos();
            	                    cargarTabla();
            	                } else {
            	                    javax.swing.JOptionPane.showMessageDialog(null, "No se pudo eliminar la transacción", "Error", JOptionPane.ERROR_MESSAGE);
            	                }
            	                ps.close();
            	                cn.close();
            	            } catch (Exception ex) {
            	                javax.swing.JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            	            }
            	        }
            	    }

					private void limpiarCampos() {
						// TODO Auto-generated method stub
						
					}
            	});
            
            }
        });
        panel_1.add(lblEliminar);

        // TABLA
        modelo = new DefaultTableModel(
            new String[]{"ID","Descripción","Tipo","Fecha","Estado","Cantidad"}, 0
        ) { public boolean isCellEditable(int r, int c) { return false; } };
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 13));
        tabla.setRowHeight(22);
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(203, 64, 483, 343);
        panel_1.add(scroll);

        cargarTabla();

        tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int f = tabla.getSelectedRow();
                if (f >= 0) {
                    textDescripcion.setText(modelo.getValueAt(f, 1).toString());
                    String tipo = modelo.getValueAt(f, 2).toString();
                    rdbtnVenta.setSelected(tipo.equals("Venta minorista"));
                    rdbtnGasto.setSelected(tipo.equals("Gasto operativo"));
                    rdbtnAdquisicion.setSelected(tipo.equals("Adquisición de activos"));
                    textFecha.setText(modelo.getValueAt(f, 3).toString());
                    textEstado.setText(modelo.getValueAt(f, 4).toString());
                    textCantidad.setText(modelo.getValueAt(f, 5).toString());
                }
            }
        });
    }
}