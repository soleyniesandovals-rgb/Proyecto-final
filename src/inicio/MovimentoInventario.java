package inicio;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Representa la entrada de un producto al inventario.
 * Se conecta con la pantalla Inventario.java y guarda en tabla `inventario`.
 *
 * Uso desde el botón Agregar de Inventario.java:
 *
 *   Proveedor prov = (Proveedor) comboProv.getSelectedItem();
 *   Movimiento mov = new MovInventario(
 *       textCodigo.getText(),
 *       textProducto.getText(),
 *       prov.getId(),
 *       Integer.parseInt(textCantidad.getText()),
 *       Double.parseDouble(textCosto.getText()),
 *       Double.parseDouble(textPrecio.getText()),
 *       SesionActual.getIdUsuario()
 *   );
 *   mov.ejecutar();
 */
public class MovimentoInventario extends Movimiento {

    private String codigo;
    private String producto;
    private int    idProveedor;
    private int    cantidad;
    private double costo;
    private double precio;

    public MovimentoInventario(String codigo, String producto, int idProveedor,
                         int cantidad, double costo, double precio, int idUsuario) {
        super("Entrada de inventario: " + producto, idUsuario);
        this.codigo      = codigo;
        this.producto    = producto;
        this.idProveedor = idProveedor;
        this.cantidad    = cantidad;
        this.costo       = costo;
        this.precio      = precio;
    }

    @Override
    public void ejecutar() {
        String sql = "INSERT INTO inventario (id_usuario, id_proveedor, codigo, producto, cantidad, costo, precio) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idProveedor);
            ps.setString(3, codigo);
            ps.setString(4, producto);
            ps.setInt(5, cantidad);
            ps.setDouble(6, costo);
            ps.setDouble(7, precio);
            ps.executeUpdate();

            var rs = ps.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);

        } catch (Exception e) {
            System.err.println("Error al guardar en inventario: " + e.getMessage());
        }
    }

    @Override
    public String getDetalles() {
        return super.getDetalles() + String.format(" | [%s] %s — %d uds | Costo: $%.2f | Precio: $%.2f",
                codigo, producto, cantidad, costo, precio);
    }

    public String getCodigo()      { return codigo; }
    public String getProducto()    { return producto; }
    public int    getIdProveedor() { return idProveedor; }
    public int    getCantidad()    { return cantidad; }
    public double getCosto()       { return costo; }
    public double getPrecio()      { return precio; }
}

