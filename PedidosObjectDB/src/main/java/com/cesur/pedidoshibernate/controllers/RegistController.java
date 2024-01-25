package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import com.cesur.pedidoshibernate.domain.entities.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de registro de usuario.
 */
public class RegistController implements Initializable {
    // Etiqueta para mostrar información al usuario
    @javafx.fxml.FXML
    private Label info;

    // Campo de texto para el nombre del usuario
    @javafx.fxml.FXML
    private TextField txtNombre;

    // Campo de texto para el correo del usuario
    @javafx.fxml.FXML
    private TextField txtCorreo;

    // Campo de contraseña para la contraseña del usuario
    @javafx.fxml.FXML
    private PasswordField txtPass;

    // Botón para volver a la vista de inicio de sesión
    @javafx.fxml.FXML
    private Button btnVolver;

    // Botón para registrar un nuevo usuario
    @javafx.fxml.FXML
    private Button btnRegist;

    /**
     * Método llamado al hacer clic en el botón para volver a la vista de inicio de sesión.
     *
     * @param actionEvent Evento de acción generado por el clic en el botón.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        App.changeScene("login-view.fxml");
    }

    /**
     * Método llamado al hacer clic en el botón para registrar un nuevo usuario.
     *
     * @param actionEvent Evento de acción generado por el clic en el botón.
     */
    @javafx.fxml.FXML
    public void registrarUsuario(ActionEvent actionEvent) {
        if (txtCorreo.getText().contains("@gmail.com")) {
            if (!Objects.equals(txtNombre.getText(), "") && !Objects.equals(txtCorreo.getText(), "") && !Objects.equals(txtPass.getText(), "")) {
                User user = new User();
                user.setName(txtNombre.getText());
                user.setEmail(txtCorreo.getText());
                user.setPassword(txtPass.getText());
                var userDAO = new UserDAO();
                try {
                    User salida = userDAO.save(user);
                    if (salida != null) {
                        App.changeScene("login-view.fxml");
                    } else {
                        info.setText("Error, fallo al registrate");
                    }
                } catch (RuntimeException e) {
                    info.setText("Usuario ya existente");
                }
            } else {
                info.setText("Rellena todos los campos");
            }
        } else {
            info.setText("Correo invalido");
        }
    }

    /**
     * Método llamado durante la inicialización del controlador.
     *
     * @param url           La ubicación relativa a la clase del archivo FXML.
     * @param resourceBundle Los recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}