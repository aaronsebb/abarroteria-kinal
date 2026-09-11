package main.java.com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.javatesting.kinalproyect.exception.producto.ProductoException;
import main.java.com.javatesting.kinalproyect.model.producto.Producto;
import main.java.com.javatesting.kinalproyect.service.producto.ProductoService;

public class ProductoController implements Initializable {

    private final ProductoService productoService = new ProductoService();

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, String> colId;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TextField txtBuscarCategoria;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        cargarProductos();
    }

    private void cargarProductos() {
        try {
            ObservableList<Producto> productos = productoService.findAll();
            tblProductos.setItems(productos);
        } catch (Exception ex) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudieron cargar los productos: " + ex.getMessage());
        }
    }

    @FXML
    private void handleBuscarPorCategoria() {
        String idCategoria = txtBuscarCategoria.getText() != null ? txtBuscarCategoria.getText().trim() : "";

        try {
            if (idCategoria.isEmpty()) {
                cargarProductos();
                return;
            }
            ObservableList<Producto> productos = productoService.findProductsByCategory(idCategoria);
            tblProductos.setItems(productos);
        } catch (ProductoException ex) {
            mostrarAlerta(AlertType.ERROR, "Error", ex.getMessage());
        } catch (Exception ex) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo realizar la búsqueda: " + ex.getMessage());
        }
    }

    @FXML
    private void handleEliminarProducto() {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(AlertType.WARNING, "Atención", "Selecciona un producto de la tabla para eliminarlo");
            return;
        }

        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que deseas eliminar el producto \"" + seleccionado.getNombreProducto() + "\"? Esta acción no se puede deshacer.");

        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.isEmpty() || respuesta.get() != ButtonType.OK) {
            return;
        }

        try {
            productoService.deleteById(seleccionado.getIdProducto());
            mostrarAlerta(AlertType.INFORMATION, "Éxito", "Producto eliminado correctamente");
            cargarProductos();
        } catch (ProductoException ex) {
            mostrarAlerta(AlertType.ERROR, "Error", ex.getMessage());
        } catch (Exception ex) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo eliminar el producto: " + ex.getMessage());
        }
    }

    @FXML
    private void handleRefrescar() {
        txtBuscarCategoria.clear();
        cargarProductos();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}