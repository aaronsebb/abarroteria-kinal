package main.java.com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.java.com.javatesting.kinalproyect.exception.ServiceException;
import main.java.com.javatesting.kinalproyect.util.SceneManager;

public class DashboardController implements Initializable {

    private final SceneManager sceneManager;

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblTotalProductos;

    @FXML
    private Label lblVentasHoy;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblStockBajo;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button btnCerrarSesion;

    public DashboardController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aquí puedes cargar datos reales después
        lblUsuario.setText("Administrador");
        lblTotalProductos.setText("128");
        lblVentasHoy.setText("Q 1,450.00");
        lblTotalClientes.setText("47");
        lblStockBajo.setText("5");
    }

    @FXML
    private void onCerrarSesion() {
        ejecutarConManejoDeErrores(() -> sceneManager.showLoginView());
    }

    /*
     *  esto permite lanzar excepciones checked 
     * por ejemplo IOException al cargar un FXML) dentro de ejecutarConManejoDeErrores.
     * Runnable no sirve para esto porque su run() no declara "throws".
     */
    @FunctionalInterface
    protected interface AccionDashboard {
        void ejecutar() throws Exception;
    }

    /*
     * Envuelve una acción del dashboard (crear producto, cambiar de vista,
     * cerrar sesión, etc.) con manejo de excepciones consistente. Úsar asi
     *
     * @FXML
     * private void onGuardarProducto(ActionEvent event) {
     *     ejecutarConManejoDeErrores(() -> {
     *         productoService.save(producto);
     *         // refrescar tabla / cerrar formulario aquí
     *     });
     * }
     *
     * No limpia ni resetea campos del formulario: si la acción falla, el
     * estado que el usuario ya llenó se conserva tal cual para que pueda
     * corregir y reintentar sin volver a escribir todo.
     */
    protected void ejecutarConManejoDeErrores(AccionDashboard accion) {
        try {
            accion.ejecutar();

        } catch (ServiceException e) {
            // Errores de negocio/validación (ProductoException, AuthException,
            // etc.) ya traen un mensaje pensado para mostrarse tal cual.
            mostrarError("No se pudo completar la acción", e.getMessage());

        } catch (RuntimeException e) {
            // El repository envuelve fallos de conexión/restricciones SQL en
            // RuntimeException, conservando el SQLException como causa.
            if (e.getCause() instanceof SQLException) {
                mostrarError(
                        "Error de base de datos",
                        "No se pudo completar la operación. Verifica tu conexión e intenta de nuevo."
                );
            } else {
                mostrarError(
                        "Error inesperado",
                        e.getMessage() != null ? e.getMessage() : "Ocurrió un error inesperado."
                );
            }

        } catch (Exception e) {
            // Cubre checked exceptions como IOException (ej. al cargar un FXML).
            mostrarError("Error inesperado", e.getMessage());
        }
    }

    protected void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}