/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main.java.com.javatesting.kinalproyect;

import main.java.com.javatesting.kinalproyect.util.SceneManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author informatica
 */
public class MainApp extends Application {

    private Stage stage;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
      launch();  
        
    }
    @Override
    public void start(Stage stage){
    
    this.stage = stage;
    SceneManager sceneManager = new SceneManager(stage);
    try{
    sceneManager.showLoginView();
    
    }catch(IOException e){
    
    e.printStackTrace();
    
    }
    stage.show();
    }
}
