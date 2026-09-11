package main.java.com.javatesting.kinalproyect.controller;

import main.java.com.javatesting.kinalproyect.util.SceneManager;
import main.java.com.javatesting.kinalproyect.model.usuario.Usuario;
import main.java.com.javatesting.kinalproyect.exception.usuario.AuthException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.com.javatesting.kinalproyect.service.usuario.AuthService;

public class RegistroController implements Initializable {

    private final AuthService authService;
    private final SceneManager sceneManager;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnIrLogin;

    public RegistroController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String apellido = txtApellido.getText() != null ? txtApellido.getText().trim() : "";
        String email = txtEmail.getText() != null ? txtEmail.getText().trim() : "";
        String password = txtPassword.getText() != null ? txtPassword.getText() : "";
        String confirmPassword = txtConfirmPassword.getText() != null ? txtConfirmPassword.getText() : "";

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Error", "Todos los campos son obligatorios");
            return;
        }

        if (!password.equals(confirmPassword)) {
            mostrarAlerta(AlertType.ERROR, "Error", "Las contraseñas no coinciden");
            return;
        }

        if (password.length() < 6) {
            mostrarAlerta(AlertType.ERROR, "Error", "La contraseña debe tener al menos 6 caracteres");
            return;
        }

        try {
            String idUsuario = UUID.randomUUID().toString().substring(0, 8);
            Usuario nuevo = new Usuario(idUsuario, nombre, apellido, email, password, 1);

            authService.save(nuevo);

            mostrarAlerta(AlertType.INFORMATION, "Éxito", "Usuario registrado correctamente");
            sceneManager.showLoginView();
        } catch (AuthException e) {
            mostrarAlerta(AlertType.ERROR, "Error", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo registrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onIrLogin(ActionEvent event) {
        try {
            sceneManager.showLoginView();
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo volver al login");
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