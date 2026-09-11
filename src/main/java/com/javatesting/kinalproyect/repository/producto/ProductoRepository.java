package main.java.com.javatesting.kinalproyect.repository.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.producto.Producto;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class ProductoRepository implements CRUDRepository<Producto> {

    @Override
    public ObservableList<Producto> findAll() {
        String sql = "select * from productos;";
        ObservableList<Producto> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Producto(
                        rs.getString("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getInt("stock"),
                        rs.getDouble("precio"),
                        rs.getString("url_imagen")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de productos", e);
        }

        return list;
    }

    public ObservableList<Producto> findProductsByCategory(String idCategoria) {
        String sql = "select * from productos where id_categoria = ?;";
        ObservableList<Producto> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idCategoria);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    list.add(new Producto(
                            rs.getString("id_producto"),
                            rs.getString("nombre_producto"),
                            rs.getInt("stock"),
                            rs.getDouble("precio"),
                            rs.getString("url_imagen")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("error al buscar productos por categoria", e);
        }

        return list;
    }

    @Override
    public boolean save(Producto producto) {
        String sql = "insert into productos (id_producto, nombre_producto, stock, precio, url_imagen) values (?, ?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getIdProducto());
            pstm.setString(2, producto.getNombreProducto());
            pstm.setInt(3, producto.getStock());
            pstm.setDouble(4, producto.getPrecio());
            pstm.setString(5, producto.getUrlImagen());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar el producto", e);
        }
    }

    @Override
    public boolean deleteById(String idProducto) {
        String sql = "delete from productos where id_producto = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idProducto);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar el producto", e);
        }
    }

    @Override
    public boolean updateById(Producto producto) {
        String sql = "update productos set nombre_producto = ?, stock = ?, precio = ?, url_imagen = ? where id_producto = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombreProducto());
            pstm.setInt(2, producto.getStock());
            pstm.setDouble(3, producto.getPrecio());
            pstm.setString(4, producto.getUrlImagen());
            pstm.setString(5, producto.getIdProducto());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar el producto", e);
        }
    }
}