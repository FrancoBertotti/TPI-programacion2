/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;


import config.DatabaseConnection;
import dao.SeguroVehicularDao;
import dao.VehiculoDao;
import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Franco
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AppMenu app = new AppMenu();
        app.mostrarMenu();

        //testingSeguroVehicularDao();

    }








    // PARA TESTEAR SeguroVehicularDao
    public static void testingSeguroVehicularDao(){
        //        SeguroVehicular seguroVehicular = new SeguroVehicular("La Perseverancia", "444000", Cobertura.TODO_RIESGO, LocalDate.of(2026, 03, 15));
//
        SeguroVehicularDao seguroVehicularDao = new SeguroVehicularDao();
//        System.out.println("El ID del nuevo seguro es: " + seguroVehicular.getId());


//        try(Connection conn = DatabaseConnection.getConnection()) {
//            seguroVehicularDao.agregar(conn, seguroVehicular);
//            System.out.println("El ID del nuevo seguro es: " + seguroVehicular.getId());
//        } catch (SQLException e){
//            System.out.println("Error en la conexión a la base de datos " + e);
//        }



        try(Connection conn = DatabaseConnection.getConnection()){
            SeguroVehicular sv1 = seguroVehicularDao.leer(conn, 1);
            SeguroVehicular sv2 = seguroVehicularDao.leer(conn, 3);

            System.out.println(sv1.toString());
            System.out.println(sv2.toString());

            sv1.setCobertura(Cobertura.RC);
            sv2.setVencimiento(LocalDate.of(2027, 1, 31));

            seguroVehicularDao.actualizar(conn, sv1);
            seguroVehicularDao.actualizar(conn, sv2);

            int res = seguroVehicularDao.eliminar(conn, 2);
            if(res > 0){
                System.out.println("Eliminación correcta");
            } else {
                System.out.println("No se encontró registro para eliminar con el ID " + 2);
            }

            List<SeguroVehicular> list = seguroVehicularDao.leerTodos(conn);
            System.out.println("Lista de seguros vehiculares");
            for (SeguroVehicular sv: list){
                System.out.println(sv.toString());
            }

        } catch (SQLException e){
            System.out.println("Error en la conexión a la base de datos " + e);
        }

    }
    


    // PARA TESTEAR VehiculoDao
    public static void testingVehiculoDao() {

        VehiculoDao vehiculoDao = new VehiculoDao();

            try (Connection conn = DatabaseConnection.getConnection()) {

                System.out.println("---- TEST VehiculoDao ----");

                // 1) Crear vehículo
                Vehiculo v = new Vehiculo("AB123CD", "Ford", "Fiesta", 2018, "CHASIS999");
                vehiculoDao.agregar(conn, v);
                System.out.println("Vehículo creado con ID: " + v.getId());

                // 2) Leer vehículo
                Vehiculo obtenido = vehiculoDao.leer(conn, v.getId());
                System.out.println("Leído: " + obtenido);

                // 3) Actualizar
                vehiculoDao.actualizar(conn, obtenido);
                System.out.println("Actualizado");

                // 4) Leer todos
                System.out.println("Listado:");
                vehiculoDao.leerTodos(conn).forEach(System.out::println);

                // 5) Eliminar (baja lógica)
                vehiculoDao.eliminar(conn, v.getId());
                System.out.println("Eliminado lógicamente");

                // 6) Leer después de eliminar
                Vehiculo eliminado = vehiculoDao.leer(conn, v.getId());
                System.out.println("Post-eliminado: " + eliminado);

    } catch (SQLException e) {
       System.out.println("Error en la conexión a la base de datos " + e);
    }
}
}

