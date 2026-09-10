/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.java.com.javatesting.kinalproyect.service.AuthService;
import main.java.com.javatesting.kinalproyect.util.SceneManager;

public class DashboardController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;
    
        public DashboardController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aquí puedes cargar datos reales después
        lblUsuario.setText("Administrador");
        lblTotalProductos.setText("128");
        lblVentasHoy.setText("Q 1,450.00");
        lblTotalClientes.setText("47");
        lblStockBajo.setText("5");
    }
}