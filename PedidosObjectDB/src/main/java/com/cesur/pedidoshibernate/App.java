package com.cesur.pedidoshibernate;

import com.cesur.pedidoshibernate.domain.entities.product.ProductDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase App es la clase principal de la aplicación que extiende Application de JavaFX.
 * Contiene métodos para iniciar la aplicación, cambiar la escena y gestionar el cierre de sesión.
 */
public class App extends Application {
    // Representa el escenario principal de la aplicación
    private static Stage myStage;

    /**
     * Método principal que inicia la aplicación.
     */
    @Override
    public void start(Stage stage) throws IOException {
        var productDao = new ProductDAO();
        Session.setProductsList(productDao.getAll());
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        myStage.setTitle("CholloGaming");
        myStage.setMaximized(true);
        myStage.setScene(scene);
        myStage.show();
    }

    /**
     * Inicia la aplicación y carga la escena de inicio.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Cambia la escena actual por la indicada por el archivo FXML proporcionado.
     *
     * @param fxml El nombre del archivo FXML de la nueva escena.
     */
    public static void changeScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml));
            Pane newPanel = fxmlLoader.load();
            myStage.getScene().setRoot(newPanel);
            myStage.setMaximized(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra la sesión actual del usuario, mostrando un cuadro de diálogo de confirmación.
     * Si el usuario confirma, se restablecen las variables de sesión y se cambia a la escena de inicio de sesión.
     */
    public static void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Estas seguro de que quieres salir?");
        alert.setContentText("Presiona aceptar para cerrar sesión");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent((response) -> {
            if (response == ButtonType.OK) {
                Session.setCurrentUser(null);
                Session.setCurrentOrder(null);
                App.changeScene("login-view.fxml");
            }
        });
    }
}