package inicio;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta raíz de todos los movimientos del sistema.
 * No puede instanciarse directamente.
 *
 * Subclases concretas:
 *   MovTransaccion  → guarda en tabla transacciones
 *   MovInventario   → guarda en tabla inventario
 *   MovFlujoCaja    → guarda en tabla flujocaja
 */
public abstract class Movimiento {

    protected int           id;
    protected int           idUsuario;
    protected LocalDateTime fecha;
    protected String        descripcion;

    public Movimiento(String descripcion, int idUsuario) {
        this.descripcion = descripcion;
        this.idUsuario   = idUsuario;
        this.fecha       = LocalDateTime.now();
    }

    // Cada subclase define como se guarda en su tabla correspondiente
    public abstract void ejecutar();

    // Metodo compartido por todas las subclases
    public String getDetalles() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s", fecha.format(fmt), descripcion);
    }

    public int           getId()          { return id; }
    public int           getIdUsuario()   { return idUsuario; }
    public LocalDateTime getFecha()       { return fecha; }
    public String        getDescripcion() { return descripcion; }

    public void setId(int id) { this.id = id; }
}
