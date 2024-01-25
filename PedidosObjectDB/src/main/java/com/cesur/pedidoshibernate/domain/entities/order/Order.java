package com.cesur.pedidoshibernate.domain.entities.order;

import com.cesur.pedidoshibernate.domain.entities.item.Item;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Order representa un pedido en el sistema.
 * Esta clase está anotada con las anotaciones de Java Persistence API (JPA) para mapearla a una tabla de base de datos.
 */
@Entity
public class Order implements Serializable {
    // Identificador único del pedido, generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Código único del pedido, generado automáticamente
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;

    // Fecha del pedido
    private String date;

    // Total del pedido
    private Integer price;

    // Usuario asociado al pedido
    @ManyToOne
    private User user;

    // Lista de elementos (ítems) asociados al pedido
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    /**
     * Obtiene el identificador único del pedido.
     *
     * @return El identificador único del pedido.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del pedido.
     *
     * @param id El identificador único a establecer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el código único del pedido.
     *
     * @return El código único del pedido.
     */
    public String getCode() {
        return code;
    }

    /**
     * Establece el código único del pedido.
     *
     * @param code El código único a establecer.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene la fecha del pedido.
     *
     * @return La fecha del pedido.
     */
    public String getDate() {
        return date;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param date La fecha a establecer.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtiene el total del pedido.
     *
     * @return El total del pedido.
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Establece el total del pedido.
     *
     * @param price El total a establecer.
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Obtiene el usuario asociado al pedido.
     *
     * @return El usuario asociado al pedido.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado al pedido.
     *
     * @param user El usuario a establecer.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene la lista de ítems asociados al pedido.
     *
     * @return La lista de ítems asociados al pedido.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Establece la lista de ítems asociados al pedido.
     *
     * @param items La lista de ítems a establecer.
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Retorna una representación en cadena del objeto Order.
     *
     * @return Una cadena que representa el objeto Order.
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", user=" + user.getName() +
                ", items=" + items +
                '}';
    }

    /**
     * Combina los datos de dos pedidos. Útil para fusionar datos al actualizar.
     *
     * @param origen  El pedido de origen.
     * @param destino El pedido de destino.
     */
    public static void merge(Order origen, Order destino) {
        destino.setCode(origen.getCode());
        destino.setDate(origen.getDate());
        destino.setId(origen.getId());
        destino.setUser(origen.getUser());
        destino.setPrice(origen.getPrice());
        destino.setItems(origen.getItems());
    }
}
