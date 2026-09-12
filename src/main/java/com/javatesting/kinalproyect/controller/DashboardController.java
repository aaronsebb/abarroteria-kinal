package main.java.com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.service.producto.ProductoService;
import main.java.com.javatesting.kinalproyect.util.ManejoErrores;
import main.java.com.javatesting.kinalproyect.util.SceneManager;

public class DashboardController implements Initializable {

    private final SceneManager sceneManager;
    private final ProductoService productoService = new ProductoService();
    private Usuario usuario;

    @FXML private Label lblUsuario;
    @FXML private Label lblTotalProductos;
    @FXML private Label lblVentasHoy;
    @FXML private Label lblTotalClientes;
    @FXML private Label lblStockBajo;
    @FXML private VBox opcionesEmpleado;

    public DashboardController(SceneManager sceneManager, Usuario usuario) {
        this.sceneManager = sceneManager;
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Datos de ejemplo del dashboard actual.
        lblTotalProductos.setText("128");
        lblVentasHoy.setText("Q 1,450.00");
        lblTotalClientes.setText("47");
        lblStockBajo.setText("5");
    }

    public void configurarVista(boolean empleado) {
        String rol = empleado ? "Empleado" : "Cliente";

        lblUsuario.setText(
                usuario.getNombre() + " " + usuario.getApellido()
                + " (" + rol + ")"
        );

        opcionesEmpleado.setVisible(empleado);
        opcionesEmpleado.setManaged(empleado);
        opcionesEmpleado.setDisable(!empleado);
    }

    @FXML
    private void onInicio() {
        ManejoErrores.ejecutar(() -> {
            sceneManager.showDashboardView(usuario);
            usuario = null;
        });
    }

    @FXML
    private void onProductos() {
        ManejoErrores.ejecutar(() -> {
            productoService.validarPermisoCreacion(usuario);
            sceneManager.showProductoFormView(usuario);
            usuario = null;
        });
    }

    @FXML
    private void onCerrarSesion() {
        ManejoErrores.ejecutar(() -> {
            usuario = null;
            sceneManager.showLoginView();
        });
    }
}