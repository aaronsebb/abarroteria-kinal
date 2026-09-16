package main.java.com.javatesting.kinalproyect.util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.javatesting.kinalproyect.controller.DashboardController;
import main.java.com.javatesting.kinalproyect.controller.LoginController;
import main.java.com.javatesting.kinalproyect.controller.ProductoFormController;
import main.java.com.javatesting.kinalproyect.controller.RegistroController;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.repository.usuario.AuthRepository;
import main.java.com.javatesting.kinalproyect.service.usuario.AuthService;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showLoginView() throws IOException {
        LoginController controller = new LoginController(
                new AuthService(new AuthRepository()), this);

        mostrar(crearLoader("login-view.fxml", controller).load(), 600, 600);
    }

    public void showRegistroView() throws IOException {
        RegistroController controller = new RegistroController(
                new AuthService(new AuthRepository()), this);

        mostrar(crearLoader("registro-view.fxml", controller).load(), 600, 600);
    }

    public void showDashboardView() throws IOException{
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/dashboard-view.fxml"));
    
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

    public void showRegistroView() throws IOException{
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/registro-view.fxml"));
    
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

