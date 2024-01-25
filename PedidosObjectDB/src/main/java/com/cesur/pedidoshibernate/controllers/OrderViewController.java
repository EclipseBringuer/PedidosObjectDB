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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de pedidos.
 */
public class OrderViewController implements Initializable {

    @javafx.fxml.FXML
    private HBox userMenu;
    @javafx.fxml.FXML
    private Label txtUsuario;
    @javafx.fxml.FXML
    private ImageView imgSalir;
    @javafx.fxml.FXML
    private FlowPane container;
    @javafx.fxml.FXML
    private TableView<Item> table;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cName;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cPrice;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cAmount;
    @javafx.fxml.FXML
    private Button btnReturn;
    @javafx.fxml.FXML
    private TableColumn<Item, Void> cDelete;
    @javafx.fxml.FXML
    private Label labelPed;
    @javafx.fxml.FXML
    private Button btnAddItem;
    @javafx.fxml.FXML
    private Button btnSaveOrder;
    @javafx.fxml.FXML
    private Label labelTotal;
    @javafx.fxml.FXML
    private Spinner<Integer> units;
    @javafx.fxml.FXML
    private Label labelUnits;
    @javafx.fxml.FXML
    private Label labelName;
    private Product currentProduct = null;
    private ObservableList<Item> items;
    @javafx.fxml.FXML
    private ImageView image;
    private Order newOrder;

    /**
     * Inicializa la vista del pedido.
     *
     * @param url            La URL relativa al archivo FXML.
     * @param resourceBundle El recurso de la interfaz de usuario.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Vinculacion de el observable y la tabla
        items = table.getItems();

        //Configuracion del spinner y su label
        labelUnits.setText(labelUnits.getText() + " 0 unidades: 0 €");
        units.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelUnits.setText("Precio con " + newValue + " unidades: " + currentProduct.getPrice() * newValue + " €");
        });
        //

        items.addAll(Session.getCurrentOrder().getItems());
        labelPed.setText(labelPed.getText() + " " + Session.getCurrentOrder().getCode());
        btnSaveOrder.setText("Guardar cambios");
        updateTotal(items);

        cName.setCellValueFactory((row) -> {
            String name = row.getValue().getProduct().getName();
            return new SimpleStringProperty(name);
        });

        cAmount.setCellValueFactory((row) -> {
            String amount = row.getValue().getAmount() + "";
            return new SimpleStringProperty(amount);
        });

        cPrice.setCellValueFactory((row) -> {
            String price = row.getValue().getProduct().getPrice() + " €";
            return new SimpleStringProperty(price);
        });

        cDelete.setCellFactory(param -> new TableCell<Item, Void>() {
            private final Button btnEliminar = new Button();

            {
                // Configurar imágenes para botones
                ImageView eliminarImage = new ImageView(new Image("C:\\Users\\gabri\\IdeaProjects\\PedidosHibernate\\src\\main\\resources\\img\\borrar.png"));
                eliminarImage.setFitWidth(25);
                eliminarImage.setFitHeight(25);
                btnEliminar.setGraphic(eliminarImage);
                //Acción de eliminar item
                btnEliminar.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("¿Estas seguro de que quieres borrar el producto " + table.getItems().
                            get(getIndex()).getProduct().getName() + "?");
                    alert.setContentText("Presiona aceptar para borrarlo");
                    alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                    alert.showAndWait().ifPresent((response) -> {
                        if (response == ButtonType.OK) {
                            items.remove(table.getItems().get(getIndex()));
                            updateTotal(items);
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Añadir botones a la celda
                    HBox hbox = new HBox(btnEliminar);
                    hbox.setSpacing(20);
                    setGraphic(hbox);
                }
            }
        });


        txtUsuario.setText(txtUsuario.getText() + Session.getCurrentUser().getName());

        for (Product p : Session.getProductsList()) {
            makeProductViewer(p);
        }
    }

    /**
     * Crea una vista para un producto en la interfaz de usuario y la agrega al contenedor de productos.
     *
     * @param p El producto para el cual se creará la vista.
     */
    private void makeProductViewer(Product p) {
        ProductWrapper pw = new ProductWrapper(p);
        pw.setOnMouseClicked(event -> {
            currentProduct = pw.getProduct();
            units.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, p.getAmount(), 1, 1));
            labelUnits.setText("Precio con 1 unidades: " + currentProduct.getPrice() + " €");
            labelName.setText(" " + p.getName());
            image.setImage(new Image(pw.getProduct().getImage()));
        });
        container.getChildren().add(pw);
    }

    /**
     * Realiza el cierre de sesión, redirigiendo a la aplicación al inicio de sesión.
     *
     * @param event El evento que desencadenó el cierre de sesión.
     */
    @javafx.fxml.FXML
    public void logout(Event event) {
        App.logout();
    }

    /**
     * Regresa a la pantalla principal, mostrando una confirmación si el pedido actual está vacío.
     *
     * @param actionEvent El evento que desencadenó el retorno a la pantalla principal.
     */
    @javafx.fxml.FXML
    public void returnToMain(ActionEvent actionEvent) {
        if (Session.getCurrentOrder().getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("El pedido " + Session.getCurrentOrder().getCode()
                    + " está vacio ¿Estas seguro de que quieres salir?");
            alert.setContentText("Si pulsas salir se borrará el pedido");
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait().ifPresent((response) -> {
                if (response == ButtonType.OK) {
                    var miDao = new OrderDAO();
                    miDao.delete(Session.getCurrentOrder());
                    Session.getCurrentOrderList().remove(Session.getCurrentOrder());
                    Session.setCurrentOrder(null);
                    App.changeScene("main-view.fxml");
                }
            });
        } else {
            Session.setCurrentOrder(null);
            App.changeScene("main-view.fxml");
        }
    }

    /**
     * Actualiza el contador del precio total basado en los elementos de la tabla.
     *
     * @param items La lista de elementos que contribuyen al precio total.
     * @return El nuevo precio total después de la actualización.
     */
    private Integer updateTotal(List<Item> items) {
        int precioTotal = 0;
        for (Item i : items) {
            precioTotal += i.getProduct().getPrice() * i.getAmount();
        }
        labelTotal.setText("Total: " + precioTotal + " €");
        return precioTotal;
    }

    /**
     * Añade un nuevo item a la tabla de pedidos, actualizando el precio total.
     *
     * @param actionEvent El evento que desencadenó la adición de un nuevo item.
     */
    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        if (currentProduct != null) {
            Item item = new Item();
            item.setOrder(Session.getCurrentOrder());
            item.setAmount(units.getValue());
            item.setProduct(currentProduct);
            boolean found = false;
            for (Item i : items) {
                if (i.getProduct().equals(item.getProduct())) {
                    found = true;
                    i.setAmount(i.getAmount() + units.getValue());
                    table.refresh();
                }
            }
            if (!found) {
                items.add(item);
                table.refresh();
            }
            updateTotal(items);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error al añadir producto");
            alert.setHeaderText("No hay un producto seleccionado");
            alert.setContentText("Pulsa aceptar para volver");
            alert.showAndWait();
        }

    }

    /**
     * Guarda los cambios realizados en el pedido actual, actualizando la lista de ítems y el precio total.
     * Utiliza el objeto OrderDAO para actualizar la información en la base de datos.
     *
     * @param actionEvent El evento de acción que desencadenó este método.
     */
    @javafx.fxml.FXML
    public void saveOrder(ActionEvent actionEvent) {
        var orderDao = new OrderDAO();
        Session.getCurrentOrder().setItems(items);
        Session.getCurrentOrder().setPrice(updateTotal(items));
        orderDao.update(Session.getCurrentOrder());
    }
}