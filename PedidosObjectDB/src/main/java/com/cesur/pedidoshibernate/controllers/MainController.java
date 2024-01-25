package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.item.Item;
import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.order.OrderDAO;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @javafx.fxml.FXML
    private HBox userMenu;
    @javafx.fxml.FXML
    private Label txtUsuario;
    @javafx.fxml.FXML
    private ImageView imgSalir;
    @javafx.fxml.FXML
    private TableView<Order> table;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnCodigo;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnFecha;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnPrecio;
    @javafx.fxml.FXML
    private Label infoPedido;
    @javafx.fxml.FXML
    private TableView<Item> tableItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnProducto;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnPrecioProducto;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnCantidad;
    @javafx.fxml.FXML
    private TableColumn<Product, Void> columnAction;
    @javafx.fxml.FXML
    private Button btnMake;

    @javafx.fxml.FXML
    public void logout(Event event) {
        App.logout();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración inicial de la vista
        txtUsuario.setText(txtUsuario.getText() + Session.getCurrentUser().getName());
        ObservableList<Item> items = tableItem.getItems();
        ObservableList<Order> orders = table.getItems();

        orders.addAll(Session.getCurrentOrderList());

        // Configuración de las columnas de la tabla de pedidos
        columnFecha.setCellValueFactory((fila) -> {
            String fecha = fila.getValue().getDate();
            return new SimpleStringProperty(fecha);
        });

        columnCodigo.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getCode();
            return new SimpleStringProperty(codigo);
        });

        columnPrecio.setCellValueFactory((fila) -> {
            String total = fila.getValue().getPrice() + " €";
            return new SimpleStringProperty(total);
        });
        // Manejo de selección de un pedido en la tabla
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, vOld, vNew) -> {
                    Session.setCurrentOrder(table.getSelectionModel().getSelectedItem());
                    items.clear();
                    infoPedido.setText("Información del pedido: " + Session.getCurrentOrder().getCode());
                    items.addAll(Session.getCurrentOrder().getItems());
                }
        );

        // Configuración de las columnas de la tabla de ítems en el pedido
        columnCantidad.setCellValueFactory((fila) -> {
            String fecha = fila.getValue().getAmount() + "";
            return new SimpleStringProperty(fecha);
        });

        columnProducto.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getProduct().getName();
            return new SimpleStringProperty(codigo);
        });

        columnPrecioProducto.setCellValueFactory((fila) -> {
            String total = fila.getValue().getProduct().getPrice() + " €";
            return new SimpleStringProperty(total);
        });

        columnAction.setCellFactory(param -> new TableCell<Product, Void>() {
            private final Button btnEliminar = new Button();
            private final Button btnEditar = new Button();

            {
                // Configurar imágenes para botones
                ImageView eliminarImage = new ImageView(new Image("C:\\Users\\gabri\\IdeaProjects\\PedidosHibernate\\src\\main\\resources\\img\\borrar.png"));
                eliminarImage.setFitWidth(25);
                eliminarImage.setFitHeight(25);
                btnEliminar.setGraphic(eliminarImage);
                //Funcion del boton eliminar
                btnEliminar.setOnAction(event -> {
                    Order orderSelected = table.getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("¿Estas seguro de que quieres borrar el pedido: " + orderSelected.getCode() + "?");
                    alert.setContentText("Presiona aceptar para eliminar el pedido");
                    alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                    alert.showAndWait().ifPresent((response) -> {
                        if (response == ButtonType.OK) {
                            var miDao = new OrderDAO();
                            miDao.delete(orderSelected);
                            Session.getCurrentOrderList().remove(orderSelected);
                            orders.remove(orderSelected);
                        }
                    });
                });

                ImageView editarImage = new ImageView(new Image("C:\\Users\\gabri\\IdeaProjects\\PedidosHibernate\\src\\main\\resources\\img\\lapiz.png"));
                editarImage.setFitHeight(25);
                editarImage.setFitWidth(25);
                btnEditar.setGraphic(editarImage);
                //Funcion del boton editar
                btnEditar.setOnAction(event -> {
                    Order orderSelected = table.getItems().get(getIndex());
                    Session.setCurrentOrder(orderSelected);
                    App.changeScene("order-view.fxml");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Añadir botones a la celda
                    HBox hbox = new HBox(btnEliminar, btnEditar);
                    hbox.setSpacing(20);
                    setGraphic(hbox);
                }
            }
        });

    }

    @javafx.fxml.FXML
    public void makeOrder(ActionEvent actionEvent) {
        var orderDao = new OrderDAO();
        Order order = new Order();
        order.setUser(Session.getCurrentUser());
        LocalDate currentDate = LocalDate.now();
        order.setDate(String.valueOf(currentDate));
        order.setPrice(0);
        order = orderDao.save(order);
        Session.getCurrentOrderList().add(order);
        Session.setCurrentOrder(order);
        App.changeScene("order-view.fxml");
    }

}
