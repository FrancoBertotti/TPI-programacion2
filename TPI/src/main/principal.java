/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import config.DatabaseConnection;
import dao.VehiculoDao;
import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;

import java.sql.Connection;
import java.sql.SQLException;

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
        
        
        
        
        
        
        
//        SeguroVehicular s1 = new SeguroVehicular(1, false, "la segnuda", "fer748627", Cobertura.RC );
//        
//        Vehiculo v1 = new Vehiculo(276874 ,false , "XYZ123", "Toyota", "Corolla", 2021, "",s1);
//
//        VehiculoDao vehiculoDao = new VehiculoDao();

//        try {
//            vehiculoDao.agregar(v1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            Vehiculo vehiculoQuery = vehiculoDao.leer(400315);
//            vehiculoQuery.toString();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(v1);
        
    }
    
}
