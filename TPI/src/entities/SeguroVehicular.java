/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Franco
 */
public class SeguroVehicular {
    private long id;
    private boolean eliminado;
    private String aseguradora;
    private String nroPoliza;
    private Cobertura cobertura;
    private java.time.LocalDate vencimiento;

    public SeguroVehicular(long id, boolean eliminado, String aseguradora, String nroPoliza, Cobertura cobertura ) {
        this.id = id;
        this.eliminado = eliminado;
        this.aseguradora = aseguradora;
        this.nroPoliza = nroPoliza;
        this.cobertura = cobertura;
        this.vencimiento = vencimiento;
        
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public String getNroPoliza() {
        return nroPoliza;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    @Override
    public String toString() {
        return "Seguro Vehicular: " + "id= " + id + ", eliminado= " + eliminado + ", aseguradora= " + aseguradora + ", nroPoliza= " + nroPoliza + ", cobertura= " + cobertura + ", vencimiento= " + vencimiento;
    }
    
    
    
    
    
    
    
}
