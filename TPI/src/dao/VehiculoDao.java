package dao;

import entities.Cobertura;
import entities.SeguroVehicular;
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

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, 0);
            ps.setString(2, entity.getDominio());
            ps.setString(3, entity.getMarca());
            ps.setString(4, entity.getModelo());
            ps.setInt(5, entity.getAnio());
            ps.setString(6, entity.getNroChasis());
            
            int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas == 0) {
            throw new SQLException("La creación del vehículo falló, no se insertaron filas.");
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
    public Vehiculo leer(Connection conn, int id) throws SQLException {
       
        String sql = "SELECT * FROM vehiculo WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    SeguroVehicular s1 = new SeguroVehicular(1, false, "la segnuda", "fer748627", Cobertura.RC );

                    Vehiculo v = new Vehiculo(
                            rs.getInt("id"),
                            rs.getBoolean("eliminado"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("anio"),
                            rs.getInt("anio"),
                            rs.getString("nroChasis"),
                            s1
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

    @Override
    public void actualizar( Connection con, int id) throws SQLException {

    }

    @Override
    public void eliminar(Connection conn, int id) throws SQLException {

    }
}
