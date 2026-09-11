package main.java.com.javatesting.kinalproyect.exception.usuario;

import main.java.com.javatesting.kinalproyect.exception.ServiceException;

/**
 * Excepción para errores de autenticación: credenciales inválidas,
 * usuario no encontrado, email/contraseña vacíos, etc.
 */
public class AuthException extends ServiceException {

    public AuthException(String message) {
        super(message);
    }
}
