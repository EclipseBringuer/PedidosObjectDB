package com.cesur.pedidoshibernate;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import com.cesur.pedidoshibernate.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Session representa la información de sesión actual para la aplicación.
 * Almacena información sobre el usuario actual, el pedido actual, la lista de productos y la lista de pedidos actuales.
 */
public class Session {
    // Usuario actual que ha iniciado sesión en la sesión
    private static User currentUser = null;
    // Pedido actual que se está procesando en la sesión
    private static Order currentOrder = null;
    // Lista de productos disponibles en la sesión
    private static List<Product> productsList = new ArrayList<>();
    // Lista de pedidos asociados con la sesión actual
    private static List<Order> currentOrderList = new ArrayList<>();

    /**
     * Obtiene el usuario actual que ha iniciado sesión en la sesión.
     *
     * @return El usuario actual.
     */
    public static User getCurrentUser() {

        return currentUser;
    }

    /**
     * Establece el usuario actual para la sesión.
     *
     * @param currentUser El usuario que se establecerá como el usuario actual.
     */
    public static void setCurrentUser(User currentUser) {

        Session.currentUser = currentUser;
    }

    /**
     * Obtiene el pedido actual en la sesión.
     *
     * @return El pedido actual.
     */
    public static Order getCurrentOrder() {

        return currentOrder;
    }

    /**
     * Establece el pedido actual para la sesión.
     *
     * @param currentOrder El pedido que se establecerá como el pedido actual.
     */
    public static void setCurrentOrder(Order currentOrder) {

        Session.currentOrder = currentOrder;
    }

    /**
     * Obtiene la lista de productos disponibles en la sesión.
     *
     * @return La lista de productos.
     */
    public static List<Product> getProductsList() {

        return productsList;
    }

    /**
     * Establece la lista de productos para la sesión.
     *
     * @param productsList La lista de productos que se establecerá.
     */
    public static void setProductsList(List<Product> productsList) {

        Session.productsList = productsList;
    }

    /**
     * Obtiene la lista de pedidos asociados con la sesión actual.
     *
     * @return La lista de pedidos.
     */
    public static List<Order> getCurrentOrderList() {
        return currentOrderList;
    }

    /**
     * Establece la lista de pedidos para la sesión actual.
     *
     * @param currentOrderList La lista de pedidos que se establecerá.
     */
    public static void setCurrentOrderList(List<Order> currentOrderList) {
        Session.currentOrderList = currentOrderList;
    }
}
