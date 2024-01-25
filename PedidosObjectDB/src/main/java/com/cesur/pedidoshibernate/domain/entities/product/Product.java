package com.cesur.pedidoshibernate.domain.entities.product;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase Product representa un producto en el sistema.
 * Esta clase está anotada con las anotaciones de Java Persistence API (JPA) para mapearla a una tabla de base de datos.
 */
@Entity
public class Product implements Serializable {
    // Identificador único del producto, generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del producto
    private String name;

    // Precio del producto
    private Integer price;

    // Cantidad disponible del producto
    private Integer amount;

    // Descripción del producto
    private String description;

    // Ruta de la imagen asociada con el producto
    private String image;

    /**
     * Obtiene la descripción del producto.
     *
     * @return La descripción del producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param description La descripción a establecer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la ruta de la imagen asociada con el producto.
     *
     * @return La ruta de la imagen del producto.
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece la ruta de la imagen asociada con el producto.
     *
     * @param image La ruta de la imagen a establecer.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return El identificador único del producto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id El identificador único a establecer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name El nombre a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price El precio a establecer.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     *
     * @return La cantidad disponible del producto.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Establece la cantidad disponible del producto.
     *
     * @param amount La cantidad a establecer.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Retorna una representación en cadena del objeto Product.
     *
     * @return Una cadena que representa el objeto Product.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    /**
     * Compara este objeto Product con otro objeto para determinar su igualdad.
     *
     * @param o El objeto con el que se va a comparar.
     * @return `true` si los objetos son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(amount, product.amount) && Objects.equals(description, product.description) && Objects.equals(image, product.image);
    }
}
