package main.java.com.javatesting.kinalproyect.util;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.com.javatesting.kinalproyect.exception.ServiceException;

public class ManejoErrores {

    @FunctionalInterface
    public interface AccionRiesgosa {
        void ejecutar() throws Exception;
    }

    public static void ejecutar(AccionRiesgosa accion) {
        try {
            accion.ejecutar();

        } catch (Exception e) {
            // Conserva el detalle técnico en la consola.
            e.printStackTrace();

            if (tieneCausa(e, SQLException.class)) {
                mostrarError(
                        "Error de base de datos",
                        "No se pudo completar la operación en la base de datos. "
                        + "Si el problema continúa, informa al encargado."
                );

            } else if (e instanceof NumberFormatException) {
                mostrarError(
                        "Datos inválidos",
                        "El stock debe ser un número entero y el precio "
                        + "un número válido. Para decimales, utiliza punto."
                );

            } else if (e instanceof ServiceException) {
                String mensaje = e.getMessage();

                mostrarError(
                        "No se pudo completar la acción",
                        mensaje == null || mensaje.isBlank()
                                ? "Revisa los datos ingresados."
                                : mensaje
                );

            } else if (tieneCausa(e, IOException.class)) {
                mostrarError(
                        "Error al abrir la pantalla",
                        "No se pudo cargar la pantalla solicitada. "
                        + "Si el problema continúa, informa al encargado."
                );

            } else {
                mostrarError(
                        "Error inesperado",
                        "No se pudo completar la acción. "
                        + "Si el problema continúa, informa al encargado."
                );
            }
        }
    }

    private static boolean tieneCausa(
            Throwable error,
            Class<? extends Throwable> tipo) {

        Throwable causa = error;

        while (causa != null) {
            if (tipo.isInstance(causa)) {
                return true;
            }

            causa = causa.getCause();
        }

        return false;
    }

    public static void mostrarError(String titulo, String mensaje) {
        mostrarAlerta(AlertType.ERROR, titulo, mensaje);
    }

    public static void mostrarInfo(String titulo, String mensaje) {
        mostrarAlerta(AlertType.INFORMATION, titulo, mensaje);
    }

    private static void mostrarAlerta(
            AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}