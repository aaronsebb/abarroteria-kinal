package main.java.com.javatesting.kinalproyect.repository.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.factura.Factura;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class FacturaRepository implements CRUDRepository<Factura> {

    @Override
    public ObservableList<Factura> findAll() {
        String sql = "select * from facturas;";
        ObservableList<Factura> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Factura(
                        rs.getString("id_factura"),
                        rs.getString("id_cliente"),
                        rs.getDouble("monto"),
                        rs.getDate("fecha")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de facturas", e);
        }

        return list;
    }

    @Override
    public boolean save(Factura factura) {
        String sql = "insert into facturas (id_factura, id_cliente, monto, fecha) values (?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, factura.getIdFactura());
            pstm.setString(2, factura.getIdCliente());
            pstm.setDouble(3, factura.getMonto());
            pstm.setDate(4, new java.sql.Date(factura.getFecha().getTime()));

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar la factura", e);
        }
    }

    @Override
    public boolean deleteById(String idFactura) {
        String sql = "delete from facturas where id_factura = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idFactura);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar la factura", e);
        }
    }

    @Override
    public boolean updateById(Factura factura) {
        String sql = "update facturas set id_cliente = ?, monto = ?, fecha = ? where id_factura = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, factura.getIdCliente());
            pstm.setDouble(2, factura.getMonto());
            pstm.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            pstm.setString(4, factura.getIdFactura());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar la factura", e);
        }
    }
}