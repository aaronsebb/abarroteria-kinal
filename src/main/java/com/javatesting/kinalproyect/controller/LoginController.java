/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.controller;

import main.java.com.javatesting.kinalproyect.service.AuthService;
import main.java.com.javatesting.kinalproyect.util.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * XDML Controller class
 *
 * @author informatica
 */
public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;
    
    //clase alert temporal
    Alert showAlert = new Alert(AlertType.INFORMATION);
    
   
    
    private String commitChange;
    
    @FXML
    private Button btnRegistrar;
    
    @FXML 
    private Button btnIniciarSesion;
    
    @FXML 
    private TextField txtFieldEmail;
    
    @FXML 
    private TextField txtFieldContrasena;
    
    
    
    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
        
        
    }
    

       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
        public void handleLogin()throws Exception{
        if(txtFieldEmail.getText().isBlank()||txtFieldEmail.getText().isEmpty()){
        showAlert.setTitle("Error");
        showAlert.setHeaderText("Error en al loguearse");
        showAlert.setContentText("Las cajas de texto no pueden estar vacias");
        }
        
    }     
    }
    
    

