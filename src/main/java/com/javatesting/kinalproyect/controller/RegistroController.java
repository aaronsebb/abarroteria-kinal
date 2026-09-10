package com.javatesting.kinalproyect.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RegistroController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }

    @FXML
    private void onRegistrar(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        // Validaciones
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return;
        }

        if (!password.equals(confirmPassword)) {
            mostrarAlerta("Error", "Las contraseñas no coinciden");
            return;
        }

        if (password.length() < 6) {
            mostrarAlerta("Error", "La contraseña debe tener al menos 6 caracteres");
            return;
        }

        // Aquí llamas a tu servicio para guardar en la base de datos
        // Ejemplo:
        // authService.registrar(nombre, apellido, email, password, 1); // id_rol = 1

        System.out.println("Registrando:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Rol: 1");

        mostrarAlerta("Éxito", "Usuario registrado correctamente");
    }

    @FXML
    private void onIrLogin(ActionEvent event) {
        // Aquí llamas al SceneManager para volver al login
        // sceneManager.showLoginView();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}