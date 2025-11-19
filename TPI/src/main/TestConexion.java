package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DatabaseConnection;

public class TestConexion {

    public static void main(String[] args) {
        /**
         * 🔹 Se usa un bloque try-with-resources para asegurar que la conexión
         * se cierre automáticamente al salir del bloque. 🔹 No es necesario
         * llamar explícitamente a conn.close().
         */
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexión establecida con éxito.");

                // 🔹 Crear y ejecutar consulta SQL con PreparedStatement
                String sql = "SELECT * FROM vehiculo LIMIT 1";
                try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("📋 Listado de vehiculos:");
                    while (rs.next()) {
                        System.out.println(rs.getString("dominio"));
                    }
                }
            } else {
                System.out.println("❌ No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            // 🔹 Manejo de errores en la conexión a la base de datos
            System.err.println("⚠️ Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace(); // Imprime el stack trace completo para depuración
        }
    }
}
