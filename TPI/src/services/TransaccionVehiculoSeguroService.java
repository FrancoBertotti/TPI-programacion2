/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import dao.VehiculoDao;
import entities.SeguroVehicular;
import entities.Vehiculo;
import java.sql.Connection;

public class TransaccionVehiculoSeguroService {

    private VehiculoDao vehiculoDao;
    private SeguroVehicularDao seguroDao;

    public TransaccionVehiculoSeguroService() {
        this.vehiculoDao = new VehiculoDao();
        this.seguroDao = new SeguroVehicularDao();
    }

    /**
     Operación compuesta:
     -Todo dentro de UNA sola transacción
     */
    public void crearVehiculoConSeguro(Vehiculo vehiculo, SeguroVehicular seguro) throws Exception {

      
        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false); 

            try {
                
                if (vehiculo.getDominio() == null || vehiculo.getDominio().isEmpty()) {
                    throw new Exception("El dominio del vehículo es obligatorio.");
                }
                if (seguro.getAseguradora() == null || seguro.getAseguradora().isEmpty()) {
                    throw new Exception("La aseguradora es obligatoria.");
                }

                //Crear seguro
                seguroDao.agregar(conn, seguro);

                //Crear vehículo
                vehiculoDao.agregar(conn, vehiculo);

                //Asignar seguro al vehículo
                vehiculoDao.actualizarSeguro(conn, vehiculo.getId(), seguro.getId());

                conn.commit(); 

                System.out.println("Vehículo + Seguro creados correctamente en una misma transacción.");

            } catch (Exception e) {

                conn.rollback(); 
                throw new Exception("ERROR en la transacción. Se realizó rollback. Motivo: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true); 
            }
        }
    }
}

