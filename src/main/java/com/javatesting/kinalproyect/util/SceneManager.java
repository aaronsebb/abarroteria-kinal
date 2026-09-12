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

    public void showDashboardView(Usuario usuario) throws IOException {
        if (usuario == null) {
            showLoginView();
            return;
        }

        boolean vistaEmpleado;

        if (usuario.getIdRol() == 1) {
            vistaEmpleado = false;
        } else if (usuario.getIdRol() == 2) {
            vistaEmpleado = true;
        } else {
            throw new IOException(
                    "No hay una vista configurada para este rol.");
        }

        DashboardController controller =
                new DashboardController(this, usuario);

        FXMLLoader loader = crearLoader("dashboard-view.fxml", controller);
        Parent root = loader.load();

        controller.configurarVista(vistaEmpleado);
        mostrar(root, 1200, 700);
    }

    public void showProductoFormView(Usuario usuario) throws IOException {
        ProductoFormController controller =
                new ProductoFormController(this, usuario);

        mostrar(crearLoader("producto-form-view.fxml", controller).load(),
                900, 700);
    }

    private FXMLLoader crearLoader(String archivo, Object controller)
            throws IOException {

        URL ruta = getClass().getResource(
                "/main/resources/view/" + archivo);

        if (ruta == null) {
            throw new IOException("No se encontró la vista: " + archivo);
        }

        FXMLLoader loader = new FXMLLoader(ruta);

        loader.setControllerFactory(tipo -> {
            if (tipo == controller.getClass()) {
                return controller;
            }

            try {
                return tipo.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(
                        "No se pudo crear: " + tipo.getName(), e);
            }
        });

        return loader;
    }

    private void mostrar(Parent root, int ancho, int alto) {
        stage.setScene(new Scene(root, ancho, alto));
        stage.centerOnScreen();
        stage.show();
    }
}