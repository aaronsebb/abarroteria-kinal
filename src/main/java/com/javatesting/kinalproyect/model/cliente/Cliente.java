package main.java.com.javatesting.kinalproyect.model.cliente;

public class Cliente {
    
    private String idCliente;
    private String idDireccion;
    private String nombre;
    private String apellido;

    public Cliente(String idCliente, String idDireccion, String nombre, String apellido) {
        this.idCliente = idCliente;
        this.idDireccion = idDireccion;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(String idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
