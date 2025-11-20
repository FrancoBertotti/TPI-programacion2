/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Franco
 */
public class Vehiculo {

    private long id;
    private boolean eliminado;
    private String dominio;
    private String marca;
    private String modelo;
    private int anio;
    private String nroChasis;
    private SeguroVehicular seguro;

    public Vehiculo(String dominio, String marca, String modelo, int anio, String nroChasis) {
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.eliminado = false;
        this.seguro = null;
    }

    public Vehiculo(long id, boolean eliminado, String dominio, String marca, String modelo, int anio, String nroChasis) {
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.eliminado = eliminado;
        this.seguro = null;
        this.id = id;
    }

    public Vehiculo(long id, boolean eliminado, String dominio, String marca, String modelo, int anio, String nroChasis, SeguroVehicular seguro) {
        this.id = id;
        this.eliminado = eliminado;
        this.dominio = dominio;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.nroChasis = nroChasis;
        this.seguro = seguro;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setSeguro(SeguroVehicular seguro) {
        this.seguro = seguro;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
        public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }   

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public String getDominio() {
        return dominio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public SeguroVehicular getSeguro() {
        return seguro;
    }

    @Override
    public String toString() {
        return "id= " + id + ", eliminado= " + eliminado + ", dominio= " + dominio + ", marca= " + marca + ", modelo= " + modelo + ", anio= " + anio + ", nroChasis= " + nroChasis + ", {Datos del seguro: " + seguro+"}";
    }
 }
