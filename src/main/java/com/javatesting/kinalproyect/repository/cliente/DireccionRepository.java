package main.java.com.javatesting.kinalproyect.repository.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.cliente.Direccion;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class DireccionRepository implements CRUDRepository<Direccion> {

    @Override
    public ObservableList<Direccion> findAll() {
        String sql = "select * from direcciones;";
        ObservableList<Direccion> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Direccion(
                        rs.getString("id_direccion"),
                        rs.getString("ciudad"),
                        rs.getString("zona"),
                        rs.getString("no_casa"),
                        rs.getString("colonia"),
                        rs.getString("calle")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de direcciones", e);
        }

        return list;
    }

    @Override
    public boolean save(Direccion direccion) {
        String sql = "insert into direcciones (id_direccion, ciudad, zona, no_casa, colonia, calle) values (?, ?, ?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, direccion.getIdDireccion());
            pstm.setString(2, direccion.getCiudad());
            pstm.setString(3, direccion.getZona());
            pstm.setString(4, direccion.getNoCasa());
            pstm.setString(5, direccion.getColonia());
            pstm.setString(6, direccion.getCalle());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar la direccion", e);
        }
    }

    @Override
    public boolean deleteById(String idDireccion) {
        String sql = "delete from direcciones where id_direccion = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idDireccion);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar la direccion", e);
        }
    }

    @Override
    public boolean updateById(Direccion direccion) {
        String sql = "update direcciones set ciudad = ?, zona = ?, no_casa = ?, colonia = ?, calle = ? where id_direccion = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, direccion.getCiudad());
            pstm.setString(2, direccion.getZona());
            pstm.setString(3, direccion.getNoCasa());
            pstm.setString(4, direccion.getColonia());
            pstm.setString(5, direccion.getCalle());
            pstm.setString(6, direccion.getIdDireccion());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar la direccion", e);
        }
    }
}