/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.model;

/**
 *
 * @author informatica
 *  
 */
public class Direcciones {
    private String idDireccion;
    private String ciudad;
    private String zona;
    private String noCasa;
    private String colonia;
    private String calle;

    public Direcciones(String idDireccion, String ciudad, String zona, String noCasa, String colonia, String calle) {
        this.idDireccion = idDireccion;
        this.ciudad = ciudad;
        this.zona = zona;
        this.noCasa = noCasa;
        this.colonia = colonia;
        this.calle = calle;
    }

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNoCasa() {
        return noCasa;
    }

    public void setNoCasa(String noCasa) {
        this.noCasa = noCasa;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    
}
