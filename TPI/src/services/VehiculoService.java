package Services;

import config.DatabaseConnection;
import dao.VehiculoDao;
import entities.SeguroVehicular;
import entities.Vehiculo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService {

    private VehiculoDao vehiculoDao;
    private SeguroVehicularService seguroVehicularService;

    public VehiculoService() {
        this.vehiculoDao = new VehiculoDao();
        this.seguroVehicularService = new SeguroVehicularService();
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {

        try (Connection conn = DatabaseConnection.getConnection()) {
            vehiculoDao.agregar(conn, vehiculo);
            System.out.println("Vehiculo creado con exito.");

        } catch (SQLException e) {
            System.err.println("Error en inserción " + e);
        }
        return vehiculo;
    }

    public Vehiculo buscarPorId(long id) {
        Vehiculo vehiculo = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            vehiculo = vehiculoDao.leer(conn, id);
        } catch (SQLException e) {
            System.err.println("Error " + e);
            return vehiculo;
        }
        return vehiculo;
    }

    public void listarTodos() {
        System.out.println("Lista de vehiculos: ");
        try (Connection conn = DatabaseConnection.getConnection()) {
            List<Vehiculo> lista = vehiculoDao.leerTodos(conn);
            for (Vehiculo vehiculo : lista) {
                System.out.println(vehiculo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehiculos " + e);
        }
    }

    public void asignarSeguro(long idVehiculo, long idSeguro) throws Exception {
        Vehiculo vehiculo = buscarPorId(idVehiculo);
        if (vehiculo == null) {
            throw new Exception("No existe vehiculo con id " + idVehiculo);
        }
        SeguroVehicular seguro = seguroVehicularService.buscarPorId(idSeguro);
        if (seguro == null) {
            throw new Exception("No existe seguro con id " + idSeguro);
        }

        try (Connection conn = DatabaseConnection.getConnection()) {

            int filas = vehiculoDao.actualizarSeguro(conn, idVehiculo, idSeguro);

            if (filas > 0) {
                System.out.println("Seguro asignado correctamente.");
            } else {
                System.out.println("No se pudo asignar el seguro (no se actualizo ninguna fila).");
            }
        } catch (SQLException e) {
            throw new Exception("Error de base de datos al asignar seguro: " + e.getMessage(), e);
        }
    }
}
