/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entities.Cobertura;
import entities.SeguroVehicular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */




public class SeguroVehicularDao implements GenericDao<SeguroVehicular>{

    @Override
    public void agregar(Connection conn, SeguroVehicular entity) throws SQLException {

        String sql = "INSERT INTO segurovehicular (eliminado, aseguradora, nroPoliza, cobertura, vencimiento) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setLong(1, 0);
            ps.setString(2, entity.getAseguradora());
            ps.setString(3, entity.getNroPoliza());
            ps.setString(4, entity.getCobertura().toString());
            ps.setDate(5, java.sql.Date.valueOf(entity.getVencimiento()));

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La creación del seguro falló, no se insertaron filas.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                } else {
                    throw new SQLException("La creación del seguro falló, no se pudo obtener el ID.");
                }
            }
        }


    }

   @Override
    public SeguroVehicular leer(Connection conn, long id) throws SQLException {

        String sql = "SELECT * FROM segurovehicular WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    SeguroVehicular seguroVehicular = new SeguroVehicular(
                            rs.getLong("id"),
                            rs.getInt("eliminado") != 0,
                            rs.getString("aseguradora"),
                            rs.getString("nroPoliza"),
                            Cobertura.valueOf(rs.getString("cobertura")),
                            rs.getDate("vencimiento").toLocalDate()
                    );

                    return seguroVehicular;
                }
            }
        }

        return null;

    }

    @Override
    public List<SeguroVehicular> leerTodos(Connection conn) throws SQLException {

        String sql = "SELECT * FROM segurovehicular";
        List<SeguroVehicular> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    SeguroVehicular seguroVehicular = new SeguroVehicular(
                            rs.getLong("id"),
                            rs.getInt("eliminado") != 0,
                            rs.getString("aseguradora"),
                            rs.getString("nroPoliza"),
                            Cobertura.valueOf(rs.getString("cobertura")),
                            rs.getDate("vencimiento").toLocalDate()
                    );

                    list.add(seguroVehicular);
                }


            }
        }

        return list;

    }

    @Override
    public int actualizar(Connection conn, SeguroVehicular entity) throws SQLException {
        String sql = "UPDATE segurovehicular SET eliminado = ?, aseguradora = ?, nroPoliza = ?, cobertura = ?, vencimiento = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, entity.isEliminado());
            ps.setString(2, entity.getAseguradora());
            ps.setString(3, entity.getNroPoliza());
            ps.setString(4, entity.getCobertura().name());
            ps.setDate(5, java.sql.Date.valueOf(entity.getVencimiento()));
            ps.setLong(6, entity.getId());

            return ps.executeUpdate();

        }
    }


    @Override
    public int eliminar(Connection conn, long id) throws SQLException {

        String sql = "DELETE FROM segurovehicular WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            return ps.executeUpdate();
        }

    }
    
}
