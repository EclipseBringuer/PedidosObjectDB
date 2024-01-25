package com.cesur.pedidoshibernate.domain.entities.item;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import javax.persistence.*;

import java.io.Serializable;

/**
 * La clase Item representa un elemento en un pedido que está asociado a un producto específico.
 * Esta clase está anotada con las anotaciones de Java Persistence API (JPA) para mapearla a una tabla de base de datos.
 */
@Entity
public class Item implements Serializable {
    // Identificador único del ítem, generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Cantidad del producto en el ítem
    private Integer amount;

    // Pedido al que pertenece el ítem
    @ManyToOne
    private Order order;

    // Producto asociado al ítem
    @OneToOne
    private Product product;

    /**
     * Obtiene el identificador único del ítem.
     *
     * @return El identificador único del ítem.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del ítem.
     *
     * @param id El identificador único a establecer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene la cantidad del producto en el ítem.
     *
     * @return La cantidad del producto en el ítem.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Establece la cantidad del producto en el ítem.
     *
     * @param amount La cantidad a establecer.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Obtiene el pedido al que pertenece el ítem.
     *
     * @return El pedido al que pertenece el ítem.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Establece el pedido al que pertenece el ítem.
     *
     * @param order El pedido a establecer.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Obtiene el producto asociado al ítem.
     *
     * @return El producto asociado al ítem.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece el producto asociado al ítem.
     *
     * @param product El producto a establecer.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Retorna una representación en cadena del objeto Item.
     *
     * @return Una cadena que representa el objeto Item.
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", amount=" + amount +
                ", order=" + order.getCode() +
                ", product=" + product +
                '}';
    }
}
