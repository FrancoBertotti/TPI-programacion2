package dao;

import entities.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;

public class VehiculoDao implements GenericDao<Vehiculo> {

    
    @Override
    public void agregar(Connection conn, Vehiculo entity) throws SQLException {

        String sql = "INSERT INTO vehiculo (eliminado, dominio, marca, modelo, anio, nroChasis) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, 0);
            ps.setString(2, entity.getDominio());
            ps.setString(3, entity.getMarca());
            ps.setString(4, entity.getModelo());
            ps.setInt(5, entity.getAnio());
            ps.setString(6, entity.getNroChasis());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La creación del vehículo falló, no se insertaron filas. ");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                } else {
                    throw new SQLException("La creación del vehículo falló, no se pudo obtener el ID.");
                }
            }
        }
    }

    @Override
    public Vehiculo leer(Connection conn, long id) throws SQLException {

        String sql = "SELECT * FROM vehiculo WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vehiculo v = new Vehiculo(
                            rs.getLong("id"),
                            rs.getBoolean("eliminado"),
                            rs.getString("dominio"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("anio"),
                            rs.getString("nroChasis")
                    );
                    return v;
                }
            }
        }
        return null;
    }

    @Override
    public List<Vehiculo> leerTodos(Connection conn) throws SQLException {
        return List.of();
    }

    public int actualizarSeguro(Connection conn, long idV, long idS) throws SQLException {
        String sql = "UPDATE vehiculo SET seguro = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idS);
            ps.setLong(2, idV);
            return ps.executeUpdate();
        }
    }

    @Override
    public int eliminar(Connection conn, long id) throws SQLException {
        return 0;
    }

    @Override
    public int actualizar(Connection conn, Vehiculo entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
