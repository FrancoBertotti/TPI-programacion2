package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/seguros";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // 🔹 Carga del driver JDBC de MySQL una sola vez
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // 🔹 Se lanza una excepción en caso de que el driver no esté disponible
            throw new RuntimeException("Error: No se encontró el driver JDBC.", e);
        }
    }

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            // Intenta establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("✅ Conexión exitosa a la base de datos.");

        } catch (SQLException e) {
            // Captura errores de conexión (ej. DB no existe, XAMPP no está corriendo, clave incorrecta)
            System.err.println("❌ Error al conectar: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        // Prueba la conexión
        Connection c = getConnection();
        // Aquí podrías cerrar la conexión o usarla para consultas
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
