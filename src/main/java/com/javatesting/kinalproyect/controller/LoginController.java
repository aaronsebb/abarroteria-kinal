package main.java.com.javatesting.kinalproyect.controller;

import main.java.com.javatesting.kinalproyect.util.SceneManager;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.exception.usuario.AuthException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.com.javatesting.kinalproyect.service.usuario.AuthService;
public class LoginController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private PasswordField txtFieldPass;

    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = txtFieldEmail.getText() != null ? txtFieldEmail.getText().trim() : "";
        String contrasena = txtFieldPass.getText() != null ? txtFieldPass.getText() : "";

        if (email.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Error", "Las cajas de texto no pueden estar vacías");
            return;
        }

        try {
            Usuario usuario = authService.login(email, contrasena);
            mostrarAlerta(AlertType.INFORMATION, "Éxito", "Bienvenido " + usuario.getNombre());
            sceneManager.showDashboardView(usuario);
        } catch (AuthException e) {
            mostrarAlerta(AlertType.ERROR, "Error de autenticación", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleIrRegistro(ActionEvent event) {
        try {
            sceneManager.showRegistroView();
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo abrir el registro");
            e.printStackTrace();
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