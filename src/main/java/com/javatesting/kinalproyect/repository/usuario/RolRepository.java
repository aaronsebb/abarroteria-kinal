package main.java.com.javatesting.kinalproyect.repository.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.usuario.Rol;

public class RolRepository {

    public ObservableList<Rol> findAll() {
        String sql = "select * from roles;";
        ObservableList<Rol> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Rol(
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de roles", e);
        }

        return list;
    }

    public Rol findById(int idRol) {
        String sql = "select * from roles where id_rol = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idRol);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("error al buscar el rol por id", e);
        }

        return null;
    }
}