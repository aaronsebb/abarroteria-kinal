package main.java.com.javatesting.kinalproyect.service.producto;

import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.producto.ProductoException;
import main.java.com.javatesting.kinalproyect.model.producto.Producto;
import main.java.com.javatesting.kinalproyect.repository.producto.ProductoRepository;

public class ProductoService {

    private final ProductoRepository productoRepository = new ProductoRepository();

    public ObservableList<Producto> findAll() {
        return productoRepository.findAll();
    }

    public ObservableList<Producto> findProductsByCategory(String idCategoria) {
        if (idCategoria == null || idCategoria.isBlank()) {
            throw new ProductoException("La categoria es obligatoria para realizar la busqueda");
        }
        return productoRepository.findProductsByCategory(idCategoria);
    }

    public boolean save(Producto producto) {
        validarProducto(producto);

        boolean guardado = productoRepository.save(producto);
        if (!guardado) {
            throw new ProductoException("No se pudo guardar el producto");
        }
        return true;
    }

    public boolean deleteById(String idProducto) {
        if (idProducto == null || idProducto.isBlank()) {
            throw new ProductoException("El id del producto es obligatorio");
        }

        boolean eliminado = productoRepository.deleteById(idProducto);
        if (!eliminado) {
            throw new ProductoException("No se encontro el producto con id " + idProducto);
        }
        return true;
    }

    public boolean updateById(Producto producto) {
        validarProducto(producto);

        boolean actualizado = productoRepository.updateById(producto);
        if (!actualizado) {
            throw new ProductoException("No se pudo actualizar el producto con id " + producto.getIdProducto());
        }
        return true;
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new ProductoException("El producto no puede ser nulo");
        }
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank()) {
            throw new ProductoException("El nombre del producto es obligatorio");
        }
        if (producto.getStock() < 0) {
            throw new ProductoException("El stock no puede ser negativo");
        }
        if (producto.getPrecio() < 0) {
            throw new ProductoException("El precio no puede ser negativo");
        }
    }
}
