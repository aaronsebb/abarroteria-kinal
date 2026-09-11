package main.java.com.javatesting.kinalproyect;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.javatesting.kinalproyect.util.SceneManager;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        SceneManager sceneManager = new SceneManager(stage);
        try {
            sceneManager.showLoginView();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo iniciar la aplicación");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}