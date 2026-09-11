package main.java.com.javatesting.kinalproyect.repository.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.cliente.Cliente;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class ClienteRepository implements CRUDRepository<Cliente> {

    @Override
    public ObservableList<Cliente> findAll() {
        String sql = "select * from clientes;";
        ObservableList<Cliente> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Cliente(
                        rs.getString("id_cliente"),
                        rs.getString("id_direccion"),
                        rs.getString("nombre"),
                        rs.getString("apellido")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de clientes", e);
        }

        return list;
    }

    @Override
    public boolean save(Cliente cliente) {
        String sql = "insert into clientes (id_cliente, id_direccion, nombre, apellido) values (?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, cliente.getIdCliente());
            pstm.setString(2, cliente.getIdDireccion());
            pstm.setString(3, cliente.getNombre());
            pstm.setString(4, cliente.getApellido());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar el cliente", e);
        }
    }

    @Override
    public boolean deleteById(String idCliente) {
        String sql = "delete from clientes where id_cliente = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idCliente);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar el cliente", e);
        }
    }

    @Override
    public boolean updateById(Cliente cliente) {
        String sql = "update clientes set id_direccion = ?, nombre = ?, apellido = ? where id_cliente = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, cliente.getIdDireccion());
            pstm.setString(2, cliente.getNombre());
            pstm.setString(3, cliente.getApellido());
            pstm.setString(4, cliente.getIdCliente());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar el cliente", e);
        }
    }
}