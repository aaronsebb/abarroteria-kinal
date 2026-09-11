package main.java.com.javatesting.kinalproyect.model.producto;

public class Producto {

    private String idProducto;
    private String nombreProducto;
    private String idCategoria;
    private int stock;
    private double precio;
    private String urlImagen;

    public Producto() {
    }

    public Producto(String idProducto, String nombreProducto, String idCategoria, int stock, double precio, String urlImagen) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.idCategoria = idCategoria;
        this.stock = stock;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}