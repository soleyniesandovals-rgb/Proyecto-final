package inicio;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Representa una operación de flujo de caja (venta de producto).
 * Se conecta con la pantalla Flujocaja.java y guarda en tabla `flujocaja`.
 * El total se calcula automáticamente: precio × cantidad.
 *
 * Uso desde el botón Guardar de Flujocaja.java:
 *
 *   Movimiento mov = new MovFlujoCaja(
 *       textCodigo.getText(),
 *       textProducto.getText(),
 *       Integer.parseInt(textCantidad.getText()),
 *       Double.parseDouble(textPrecio.getText()),
 *       SesionActual.getIdUsuario()
 *   );
 *   mov.ejecutar();
 */
public class MovimientoFlujocaja extends Movimiento {

    private String codigo;
    private String producto;
    private int    cantidad;
    private double precio;
    private double total;   // calculado automaticamente

    public MovimientoFlujocaja(String codigo, String producto, int cantidad,
                        double precio, int idUsuario) {
        super("Flujo de caja: " + cantidad + "x " + producto, idUsuario);
        this.codigo   = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio   = precio;
        this.total    = precio * cantidad;  // misma regla que calcularTotal() en la UI
    }

    @Override
    public void ejecutar() {
        String sql = "INSERT INTO flujocaja (id_usuario, codigo, producto, cantidad, precio, total) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, codigo);
            ps.setString(3, producto);
            ps.setInt(4, cantidad);
            ps.setDouble(5, precio);
            ps.setDouble(6, total);
            ps.executeUpdate();

            var rs = ps.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);

        } catch (Exception e) {
            System.err.println("Error al guardar flujo de caja: " + e.getMessage());
        }
    }

    @Override
    public String getDetalles() {
        return super.getDetalles() + String.format(" | [%s] %s — %d uds × $%.2f = $%.2f",
                codigo, producto, cantidad, precio, total);
    }

    public String getCodigo()   { return codigo; }
    public String getProducto() { return producto; }
    public int    getCantidad() { return cantidad; }
    public double getPrecio()   { return precio; }
    public double getTotal()    { return total; }
}
