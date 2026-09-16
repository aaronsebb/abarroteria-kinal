package main.java.com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.com.javatesting.kinalproyect.model.producto.Producto;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.service.producto.ProductoService;
import main.java.com.javatesting.kinalproyect.util.ManejoErrores;
import main.java.com.javatesting.kinalproyect.util.SceneManager;

public class ProductoFormController implements Initializable {

    private final SceneManager sceneManager;
    private final ProductoService productoService = new ProductoService();
    private Usuario usuario;
    private boolean guardado;

    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtIdCategoria;
    @FXML private TextField txtStock;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtUrlImagen;
    @FXML private Button btnGuardar;

    public ProductoFormController(SceneManager sceneManager, Usuario usuario) {
        this.sceneManager = sceneManager;
        this.usuario = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onGuardar() {
        ManejoErrores.ejecutar(() -> {
            if (guardado) {
                return;
            }

            productoService.validarPermisoCreacion(usuario);

            Producto producto = new Producto(
                    UUID.randomUUID().toString(),
                    txtNombreProducto.getText().trim(),
                    txtIdCategoria.getText().trim(),
                    Integer.parseInt(txtStock.getText().trim()),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    txtUrlImagen.getText().trim()
            );

            productoService.save(producto, usuario);

            guardado = true;
            btnGuardar.setDisable(true);

            ManejoErrores.mostrarInfo(
                    "Producto guardado",
                    "El producto se guardó correctamente."
            );

            sceneManager.showDashboardView(usuario);
            usuario = null;
        });
    }

    @FXML
    private void onCancelar() {
        ManejoErrores.ejecutar(() -> {
            sceneManager.showDashboardView(usuario);
            usuario = null;
        });
    }
}