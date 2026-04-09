package inicio;

public class Proveedor {
    private int id;
    private String nombre;

    public Proveedor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    @Override
    public String toString() { return nombre; } // Esto es lo que verá el usuario
}