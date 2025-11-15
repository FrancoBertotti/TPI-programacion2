/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author gabriel
 */

import entities.Vehiculo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gabriel
 */

/* --- VERSION SIMULADA --- */



// Llamar a vehiculoDAO.insert(vehiculo)
public class VehiculoService {

    public void crear(Vehiculo vehiculo) {
        System.out.println("Creando vehiculo...");
        System.out.println("Vehiculo " + vehiculo.getDominio() + " guardado.");
    }
    
    
    
//  return vehiculoDAO.buscarPorId(id);
// Construir el objeto Vehiculo desde ResultSet.   
    public Vehiculo buscarPorId(long id) {
        System.out.println("Buscando ID: " + id);
        if (id == 1) { 
            Vehiculo v = new Vehiculo("AA123BB", "Toyota", "Corolla", 2020, "CHASIS123");
            v.setId(1L); 
            return v;
        }
        return null; 
    }
    
    
    
    
    // - return vehiculoDAO.listarTodos();
    public List<Vehiculo> listarTodos() {
        System.out.println("Listando todos los vehiculos...");
        return new ArrayList<>();
    }
}
