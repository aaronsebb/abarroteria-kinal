/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.controller;

import main.java.com.javatesting.kinalproyect.service.AuthService;
import main.java.com.javatesting.kinalproyect.util.SceneManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * XDML Controller class
 *
 * @author informatica
 */
public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }
    
    

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
