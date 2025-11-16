package Services;

import config.DatabaseConnection;
import dao.VehiculoDao;
import entities.Vehiculo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoService {
        private VehiculoDao vehiculoDao;
    
        public VehiculoService() {
            this.vehiculoDao = new VehiculoDao();
        }

    public void crear(Vehiculo vehiculo) {
        System.out.println("Creando vehiculo...");
        System.out.println("Vehiculo " + vehiculo.getDominio() + " guardado.");
    }
    
        public Vehiculo crearVehiculo(Vehiculo vehiculo) {
       
         try (Connection conn = DatabaseConnection.getConnection()) {
                 vehiculoDao.agregar(conn, vehiculo);
                  System.out.println("Vehiculo creado con exito.");

         } catch(SQLException e) {
                System.err.println("Error en inserción " + e);
        }
       
         return vehiculo; 
    }
    
//  return vehiculoDAO.buscarPorId(id);
// Construir el objeto Vehiculo desde ResultSet.   
    public Vehiculo buscarPorId(long id) {
        Vehiculo vehiculo = null;
        System.out.println("Buscando ID: " + id);
        try(Connection conn = DatabaseConnection.getConnection()){
            vehiculo = vehiculoDao.leer(conn, id);
        }
         catch(SQLException e) {
                System.err.println("Error " + e);
         return vehiculo;
        }
        return vehiculo; 
    }
    
    
    // - return vehiculoDAO.listarTodos();
    public List<Vehiculo> listarTodos() {
        System.out.println("Listando todos los vehiculos...");
        return new ArrayList<>(); //esto esta muy mal
    }
}
