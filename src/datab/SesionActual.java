package datab;

public class SesionActual {
    private static int idUsuario;
    private static String empresa;

    public static int getIdUsuario() { return idUsuario; }
    public static void setIdUsuario(int id) { idUsuario = id; }

    public static String getEmpresa() { return empresa; }
    public static void setEmpresa(String emp) { empresa = emp; }
}