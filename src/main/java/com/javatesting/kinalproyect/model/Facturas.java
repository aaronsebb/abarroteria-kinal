/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.model;
import java.util.Date;

/**
 *
 * @author informatica
 */
public class Facturas {
   
    private String idFactura;
    private String idCliente;
    private double monto;
    private Date fecha;

    public Facturas(String idFactura, String idCliente, double monto, Date fecha) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
