package main.java.com.javatesting.kinalproyect.repository.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.factura.DetalleFactura;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class DetalleFacturaRepository implements CRUDRepository<DetalleFactura> {

    @Override
    public ObservableList<DetalleFactura> findAll() {
        String sql = "select * from detalle_factura;";
        ObservableList<DetalleFactura> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new DetalleFactura(
                        rs.getString("id_detalle_factura"),
                        rs.getString("id_producto"),
                        rs.getString("id_factura"),
                        rs.getInt("cantidad_comprada")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de detalle_factura", e);
        }

        return list;
    }

    @Override
    public boolean save(DetalleFactura detalleFactura) {
        String sql = "insert into detalle_factura (id_detalle_factura, id_producto, id_factura, cantidad_comprada) values (?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, detalleFactura.getIdDetalleFactura());
            pstm.setString(2, detalleFactura.getIdProducto());
            pstm.setString(3, detalleFactura.getIdFactura());
            pstm.setInt(4, detalleFactura.getCantidadComprada());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar el detalle de factura", e);
        }
    }

    @Override
    public boolean deleteById(String idDetalleFactura) {
        String sql = "delete from detalle_factura where id_detalle_factura = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idDetalleFactura);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar el detalle de factura", e);
        }
    }

    @Override
    public boolean updateById(DetalleFactura detalleFactura) {
        String sql = "update detalle_factura set id_producto = ?, id_factura = ?, cantidad_comprada = ? where id_detalle_factura = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, detalleFactura.getIdProducto());
            pstm.setString(2, detalleFactura.getIdFactura());
            pstm.setInt(3, detalleFactura.getCantidadComprada());
            pstm.setString(4, detalleFactura.getIdDetalleFactura());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar el detalle de factura", e);
        }
    }
}