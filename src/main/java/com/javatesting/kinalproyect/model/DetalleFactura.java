/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.model;

/**
 *
 * @author informatica
 */
public class DetalleFactura {
    
    private String idDetalleFactura;
    private String idProducto;
    private String idFactura;
    private int cantidadComprada;

    public DetalleFactura(String idDetalleFactura, String idProducto, String idFactura, int cantidadComprada) {
        this.idDetalleFactura = idDetalleFactura;
        this.idProducto = idProducto;
        this.idFactura = idFactura;
        this.cantidadComprada = cantidadComprada;
    }

    public String getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(String idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }
    
}
