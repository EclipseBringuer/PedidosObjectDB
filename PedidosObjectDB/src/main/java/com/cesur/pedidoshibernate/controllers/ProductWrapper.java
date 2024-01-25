package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.domain.entities.product.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * La clase ProductWrapper es una clase personalizada que extiende VBox y se utiliza para representar gráficamente un producto.
 * Contiene una imagen, el nombre y el precio del producto.
 */
public class ProductWrapper extends VBox {
    // Producto asociado al contenedor
    private Product product;

    /**
     * Constructor de la clase ProductWrapper.
     *
     * @param product El producto que se va a representar en el contenedor.
     */
    public ProductWrapper(Product product) {
        this.product = product;

        // Configuración de la imagen del producto
        ImageView image = new ImageView(new Image(this.product.getImage()));
        image.setFitHeight(120);
        image.setFitWidth(120);

        // Etiqueta para el nombre del producto
        Label title = new Label(this.product.getName());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20");

        // Etiqueta para el precio del producto
        Label price = new Label(this.product.getPrice() + " €");
        price.setStyle("-fx-font-size: 14; -fx-text-fill: #00A86B; -fx-font-weight: bold");

        // Añadir nodos al contenedor VBox
        this.getChildren().addAll(image, title, price);

        // Configuración del tamaño preferido del contenedor
        this.setPrefSize(200, 120);

        // Añadir una clase de estilo al contenedor (para darle estilo y personalizarlo)
        this.getStyleClass().add("contenedor");
    }

    /**
     * Obtiene el producto asociado al contenedor.
     *
     * @return El producto asociado al contenedor.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto asociado al contenedor.
     *
     * @param product El nuevo producto a asociar al contenedor.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
