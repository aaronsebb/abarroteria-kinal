
package main.java.com.javatesting.kinalproyect.service.usuario;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import main.java.com.javatesting.kinalproyect.exception.usuario.AuthException;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.repository.usuario.AuthRepository;
import main.java.dev.alpha.alphalogin.security.jbcrypt.BCrypt;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Usuario login(String email, String contrasena) {
        if (email == null || email.isBlank()) {
            throw new AuthException("El email es obligatorio");
        }
        if (contrasena == null || contrasena.isBlank()) {
            throw new AuthException("La contrasena es obligatoria");
        }

        Usuario usuario;
        try {
            usuario = AuthRepository.findUserByEmail(email);
        } catch (SQLException e) {
            throw new AuthException("Error al validar las credenciales");
        }

        if (usuario == null) {
            throw new AuthException("El usuario con email " + email + " no existe");
        }
        if (!BCrypt.checkpw(contrasena, usuario.getContrasena())) {
            throw new AuthException("La contrasena ingresada es incorrecta");
        }

        return usuario;
    }

    public ObservableList<Usuario> findAll() {
        return authRepository.findAll();
    }

    public boolean save(Usuario usuario) {
        validarUsuario(usuario);

        String hashedPassword = BCrypt.hashpw(usuario.getContrasena(),BCrypt.gensalt());
        usuario.setContrasena(hashedPassword);

        boolean guardado = authRepository.save(usuario);
        if (!guardado) {
            throw new AuthException("No se pudo guardar el usuario");
        }
        return true;
    }

    public boolean deleteById(String idUsuario) {
        if (idUsuario == null || idUsuario.isBlank()) {
            throw new AuthException("El id del usuario es obligatorio");
        }

        boolean eliminado = authRepository.deleteById(idUsuario);
        if (!eliminado) {
            throw new AuthException("No se encontro el usuario con id " + idUsuario);
        }
        return true;
    }

    public boolean updateById(Usuario usuario) {
        validarUsuario(usuario);

        boolean actualizado = authRepository.updateById(usuario);
        if (!actualizado) {
            throw new AuthException("No se pudo actualizar el usuario con id " + usuario.getIdUsuario());
        }
        return true;
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new AuthException("El usuario no puede ser nulo");
        }
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new AuthException("El nombre del usuario es obligatorio");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new AuthException("El email del usuario no es valido");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().length() < 6) {
            throw new AuthException("La contrasena debe tener al menos 6 caracteres");
        }
        if (usuario.getIdRol() <= 0) {
            throw new AuthException("El usuario debe tener un rol valido asignado");
        }
    }
}
