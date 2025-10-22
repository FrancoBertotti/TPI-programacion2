package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Datos de conexión - Se configuran directamente en el código
    private static final String URL = "jdbc:mysql://localhost:3306/tmp5";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static {
        try {
            // 🔹 Carga del driver JDBC de MySQL una sola vez
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // 🔹 Se lanza una excepción en caso de que el driver no esté disponible
            throw new RuntimeException("Error: No se encontró el driver JDBC.", e);
        }
    }

    /**
     * 🔹 Método para obtener una conexión a la base de datos.
     * @return Connection si la conexión es exitosa.
     * @throws SQLException Si hay un problema al conectarse.
     */
   /** public static Connection getConnection() throws SQLException {
        // Validación adicional para asegurarse de que las credenciales no estén vacías
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty() || PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException("Configuración de la base de datos incompleta o inválida.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }*/
    
    public static Connection getConnection() {
        Connection conexion = null;
        try {
            // Intenta establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");

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