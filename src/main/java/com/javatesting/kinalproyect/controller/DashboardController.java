package main.java.com.javatesting.kinalproyect.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DashboardController implements Initializable {

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

    private Node homeContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUsuario.setText("Administrador");
        lblTotalProductos.setText("128");
        lblVentasHoy.setText("Q 1,450.00");
        lblTotalClientes.setText("47");
        lblStockBajo.setText("5");

        // Guardamos el contenido inicial del dashboard para poder volver a él con "Inicio"
        if (!contentArea.getChildren().isEmpty()) {
            homeContent = contentArea.getChildren().get(0);
        }
    }

    @FXML
    private void handleInicio() {
        if (homeContent != null) {
            contentArea.getChildren().setAll(homeContent);
        }
    }

    @FXML
    private void handleProductos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/productos-view.fxml"));
            Node vistaProductos = loader.load();
            contentArea.getChildren().setAll(vistaProductos);
        } catch (IOException ex) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo cargar la vista de productos: " + ex.getMessage());
        }
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}