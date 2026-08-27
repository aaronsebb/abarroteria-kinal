/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.javatesting.kinalproyect.util;

import main.java.com.javatesting.kinalproyect.controller.LoginController;
import main.java.com.javatesting.kinalproyect.repository.AuthRepository;
import main.java.com.javatesting.kinalproyect.service.AuthService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author informatica
 */
public class SceneManager {
 
    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }
    
    public void showLoginView() throws IOException{
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login-view.fxml"));
    
    loader.setControllerFactory(
    clazz ->{
    if(clazz == LoginController.class){
    AuthRepository authRepository = new AuthRepository();
    AuthService authService = new AuthService(authRepository);
    return new LoginController(authService,this);
    }
    try{
    
    return clazz.getDeclaredConstructor().newInstance();
    
        
    }catch(Exception e){
    
        throw new RuntimeException ("error al crear el constructor " + e.getMessage());
        
    }  
    }         
    );
    Parent root = loader.load();
    Scene scene = new Scene(root,600,600);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
    
    }
    
    
}
