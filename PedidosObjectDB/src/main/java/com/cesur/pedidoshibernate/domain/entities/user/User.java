package com.cesur.pedidoshibernate.domain.entities.user;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase User representa a un usuario en el sistema.
 * Esta clase está anotada con las anotaciones de Java Persistence API (JPA) para mapearla a una tabla de base de datos.
 */
@Entity
public class User implements Serializable {
    // Identificador único del usuario, generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del usuario
    private String name;

    // Correo electrónico del usuario
    private String email;

    // Contraseña del usuario
    private String password;

    // Lista de pedidos asociados con el usuario
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El identificador único a establecer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name El nombre a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la lista de pedidos asociados con el usuario.
     *
     * @return La lista de pedidos asociados con el usuario.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Establece la lista de pedidos asociados con el usuario.
     *
     * @param orders La lista de pedidos a establecer.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Retorna una representación en cadena del objeto User.
     *
     * @return Una cadena que representa el objeto User.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                '}';
    }
}
