/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.model.cliente;

/**
 *
 * @author informatica
 */
public class Telefono {
    private String idTelefono;
    private String idCliente;
    private String telefono;

    public Telefono(String idTelefono, String idCliente, String telefono) {
        this.idTelefono = idTelefono;
        this.idCliente = idCliente;
        this.telefono = telefono;
    }

    public String getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(String idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
