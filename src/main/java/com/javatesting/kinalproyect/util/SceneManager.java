package main.java.com.javatesting.kinalproyect.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.javatesting.kinalproyect.controller.LoginController;
import main.java.com.javatesting.kinalproyect.controller.RegistroController;
import main.java.com.javatesting.kinalproyect.repository.usuario.AuthRepository;
import main.java.com.javatesting.kinalproyect.service.usuario.AuthService;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showLoginView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class) {
                AuthRepository authRepository = new AuthRepository();
                AuthService authService = new AuthService(authRepository);
                return new LoginController(authService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Error al crear el controlador: " + ex.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Abarrotería Kinal - Login");
        stage.centerOnScreen();
        stage.show();
    }

    public void showRegistroView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/registro-view.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == RegistroController.class) {
                AuthRepository authRepository = new AuthRepository();
                AuthService authService = new AuthService(authRepository);
                return new RegistroController(authService, this);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Error al crear el controlador: " + ex.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Abarrotería Kinal - Registro");
        stage.centerOnScreen();
        stage.show();
    }

    public void showDashboardView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/dashboard-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.setTitle("Abarrotería Kinal - Dashboard");
        stage.centerOnScreen();
        stage.show();
    }
}