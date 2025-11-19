package dao;

import Services.SeguroVehicularService;
import entities.Vehiculo;
import entities.SeguroVehicular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;

public class VehiculoDao implements GenericDao<Vehiculo> {

    private SeguroVehicularService seguroService;

    public VehiculoDao() {
        this.seguroService = new SeguroVehicularService();
    }

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
                    long idSeguro = rs.getLong("seguro");
                    SeguroVehicular seguro = seguroService.buscarPorId(idSeguro);

                    Vehiculo v = new Vehiculo(
                            rs.getLong("id"),
                            rs.getBoolean("eliminado"),
                            rs.getString("dominio"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("anio"),
                            rs.getString("nroChasis"),
                            seguro
                    );
                    return v;
                }
            }
        }
        return null;
    }

    @Override
    public List<Vehiculo> leerTodos(Connection conn) throws SQLException {

        String sql = "SELECT id FROM vehiculo";
        List<Vehiculo> lista = new ArrayList<>();

        try (PreparedStatement ps
                = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                long v = rs.getLong("id");
                Vehiculo vehiculo = this.leer(conn, v);
                lista.add(vehiculo);
            }
        }
        return lista;
    }

    @Override
    public int actualizar(Connection conn, Vehiculo vehiculo) throws SQLException {

        String sql = "UPDATE vehiculo SET eliminado = ?, dominio = ?, marca = ?, modelo = ?, anio = ?, nroChasis = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, vehiculo.isEliminado());
            ps.setString(2, vehiculo.getDominio());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setInt(5, vehiculo.getAnio());
            ps.setString(6, vehiculo.getNroChasis());
            ps.setLong(7, vehiculo.getId());

            return ps.executeUpdate();
        }
    }

    public int actualizarSeguro(Connection conn, long idV, long idS) throws SQLException {
        String sql = "UPDATE vehiculo SET seguro = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idS);
            ps.setLong(2, idV);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas;
        }
    }

    @Override
    public int eliminar(Connection conn, long id) throws SQLException {

        String sql = "UPDATE vehiculo SET eliminado = 1 WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        }

    }

}
