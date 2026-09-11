package main.java.com.javatesting.kinalproyect.repository.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.cliente.Telefono;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class TelefonoRepository implements CRUDRepository<Telefono> {

    @Override
    public ObservableList<Telefono> findAll() {
        String sql = "select * from telefonos;";
        ObservableList<Telefono> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Telefono(
                        rs.getString("id_telefono"),
                        rs.getString("id_cliente"),
                        rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de telefonos", e);
        }

        return list;
    }

    @Override
    public boolean save(Telefono telefono) {
        String sql = "insert into telefonos (id_telefono, id_cliente, telefono) values (?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, telefono.getIdTelefono());
            pstm.setString(2, telefono.getIdCliente());
            pstm.setString(3, telefono.getTelefono());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar el telefono", e);
        }
    }

    @Override
    public boolean deleteById(String idTelefono) {
        String sql = "delete from telefonos where id_telefono = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idTelefono);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar el telefono", e);
        }
    }

    @Override
    public boolean updateById(Telefono telefono) {
        String sql = "update telefonos set id_cliente = ?, telefono = ? where id_telefono = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, telefono.getIdCliente());
            pstm.setString(2, telefono.getTelefono());
            pstm.setString(3, telefono.getIdTelefono());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar el telefono", e);
        }
    }
}