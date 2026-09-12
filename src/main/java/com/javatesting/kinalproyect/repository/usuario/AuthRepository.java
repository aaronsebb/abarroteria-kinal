package main.java.com.javatesting.kinalproyect.repository.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.config.DBConnection;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.repository.CRUDRepository;

public class AuthRepository implements CRUDRepository<Usuario> {

    public static Usuario findUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement prst = conn.prepareStatement(sql)) {

            prst.setString(1, email);

            try (ResultSet rs = prst.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("contrasena_hash"),
                            rs.getInt("id_rol")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public ObservableList<Usuario> findAll() {
        String sql = "SELECT * FROM usuarios;";
        ObservableList<Usuario> list = FXCollections.observableArrayList();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                list.add(new Usuario(
                        rs.getString("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getInt("id_rol")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error en la consulta de usuarios", e);
        }

        return list;
    }

    @Override
    public boolean save(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id_usuario, nombre, apellido, email, contrasena, id_rol) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, usuario.getIdUsuario());
            pstm.setString(2, usuario.getNombre());
            pstm.setString(3, usuario.getApellido());
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getContrasena());
            pstm.setInt(6, usuario.getIdRol());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al guardar el usuario", e);
        }
    }

    @Override
    public boolean deleteById(String idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, idUsuario);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al eliminar el usuario", e);
        }
    }

    @Override
    public boolean updateById(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, contrasena = ?, id_rol = ? WHERE id_usuario = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getApellido());
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getContrasena());
            pstm.setInt(5, usuario.getIdRol());
            pstm.setString(6, usuario.getIdUsuario());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("error al actualizar el usuario", e);
        }
    }
}