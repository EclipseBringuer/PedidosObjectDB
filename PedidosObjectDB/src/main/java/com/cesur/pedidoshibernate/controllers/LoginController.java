package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import com.cesur.pedidoshibernate.domain.entities.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de inicio de sesión.
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label txtRegistrate;

    /**
     * Intenta iniciar sesión con las credenciales proporcionadas. Muestra un mensaje de error si las credenciales son incorrectas.
     *
     * @param actionEvent El evento que desencadenó el intento de inicio de sesión.
     */
    @FXML
    public void logear(ActionEvent actionEvent) {
        var userDAO = new UserDAO();
        User user = userDAO.validateUser(txtCorreo.getText(), txtPass.getText());
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error al iniciar sesión");
            alert.setHeaderText("Usuario o contraseña incorrecto");
            alert.setContentText("Pulsa aceptar para volver");
            alert.showAndWait();
        } else {
            Session.setCurrentUser(user);
            Session.setCurrentOrderList(user.getOrders());
            App.changeScene("main-view.fxml");
        }
    }

    /**
     * Cambia la escena a la vista de registro.
     *
     * @param event El evento que desencadenó el cambio de escena.
     */
    @FXML
    public void cargarRegistro(Event event) {
        App.changeScene("regist-view.fxml");
    }

    /**
     * Inicializa la vista de inicio de sesión con valores predeterminados.
     *
     * @param url            La ubicación relativa del archivo FXML.
     * @param resourceBundle El paquete de recursos para la internacionalización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}