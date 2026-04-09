package inicio;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Representa un movimiento de la tabla `transacciones`.
 * Se conecta con la pantalla Transacciones.java.
 *
 * Columnas de la tabla:
 *   idtransacciones (AUTO), id_usuario, descripcion,
 *   tipo varchar, fecha (AUTO), estado varchar(20), cantidad int
 *
 * Uso desde el botón Guardar de Transacciones.java:
 *
 *   String tipo = rdbtnVenta.isSelected()        ? "Venta minorista"      :
 *                 rdbtnGastoOperativo.isSelected()? "Gasto operativo"      :
 *                                                   "Adquisición de activos";
 *   Movimiento mov = new MovTransaccion(
 *       textDescripcion.getText(),
 *       textEstado.getText(),
 *       tipo,
 *       Integer.parseInt(textCantidad.getText()),
 *       SesionActual.getIdUsuario()
 *   );
 *   mov.ejecutar();
 */
public class MovimientoTransacion extends Movimiento {

    private String tipo;      // valor del JRadioButton seleccionado
    private String estado;    // textEstado
    private int    cantidad;  // textCantidad — coincide con campo "cantidad int" de la BD

    public MovimientoTransacion(String descripcion, String estado, String tipo,
                          int cantidad, int idUsuario) {
        super(descripcion, idUsuario);
        this.tipo     = tipo;
        this.estado   = estado;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {
        String sql = "INSERT INTO transacciones (id_usuario, descripcion, tipo, estado, cantidad) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, descripcion);
            ps.setString(3, tipo);
            ps.setString(4, estado);
            ps.setInt(5, cantidad);
            ps.executeUpdate();

            var rs = ps.getGeneratedKeys();
            if (rs.next()) this.id = rs.getInt(1);

        } catch (Exception e) {
            System.err.println("Error al guardar transacción: " + e.getMessage());
        }
    }

    @Override
    public String getDetalles() {
        return super.getDetalles() + String.format(" | Tipo: %s | Estado: %s | Cantidad: %d",
                tipo, estado, cantidad);
    }

    public String getTipo()    { return tipo; }
    public String getEstado()  { return estado; }
    public int    getCantidad(){ return cantidad; }
}
