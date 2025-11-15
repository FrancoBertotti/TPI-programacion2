/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entities.Cobertura;
import entities.SeguroVehicular;
import entities.Vehiculo;

/**
 *
 * @author Franco
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SeguroVehicular s1 = new SeguroVehicular(1234124, false, "la segnuda", "fer748627", Cobertura.RC );
        Vehiculo v1 = new Vehiculo(276874,false, "aaa111", "ford", "focus", 2018, "f4rtfwtge",s1);
         
        System.out.println(v1);
        
    }
    
}
