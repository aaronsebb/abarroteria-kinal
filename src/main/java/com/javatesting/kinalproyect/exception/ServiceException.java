package main.java.com.javatesting.kinalproyect.exception;

/**
 * Excepción base para todas las excepciones personalizadas de la capa
 * de servicio del proyecto. Permite capturar cualquier error de negocio
 * de forma genérica si así se requiere (catch ServiceException).
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }
}
