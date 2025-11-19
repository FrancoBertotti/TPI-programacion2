package Services;

import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import entities.SeguroVehicular;
import java.sql.Connection;
import java.sql.SQLException;

public class SeguroVehicularService {

    private SeguroVehicularDao seguroDao;

    public SeguroVehicularService() {
        this.seguroDao = new SeguroVehicularDao();
    }

    public void crear(SeguroVehicular seguro) {
        try (Connection conn = DatabaseConnection.getConnection()) {

            seguroDao.agregar(conn, seguro); 
            System.out.println("Seguro creado con exito.");

        } catch (SQLException e) {
            System.err.println("Error al crear seguro: " + e.getMessage());
            throw new RuntimeException("No se pudo crear el seguro.", e);
        }
    }

    public SeguroVehicular buscarPorId(long id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return seguroDao.leer(conn, id); 
        } catch (SQLException e) {
            System.err.println("Error al buscar seguro: " + e.getMessage());
            return null;
        }
    }
}
