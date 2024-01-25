package com.cesur.pedidoshibernate.domain.entities.product;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ProductDAO implementa la interfaz DAO para la entidad Product.
 * Proporciona métodos para realizar operaciones de lectura en la base de datos para productos.
 */
public class ProductDAO implements DAO<Product> {
    /**
     * Obtiene una lista de todos los productos.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Product> getAll() {
        List<Product> salida = new ArrayList<>();

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p", Product.class);
            salida = q.getResultList();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return salida;
    }


    /**
     * Obtiene un producto basado en su identificador único. (No implementado en este caso)
     *
     * @param id El identificador único del producto.
     * @return El producto correspondiente al identificador proporcionado (en este caso, se devuelve null).
     */
    @Override
    public Product get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo producto en la base de datos. (No implementado en este caso)
     *
     * @param data El producto a ser guardado.
     * @return El producto guardado con posiblemente valores actualizados (en este caso, se devuelve null).
     */
    @Override
    public Product save(Product data) {
        return null;
    }

    /**
     * Actualiza los datos de un producto en la base de datos. (No implementado en este caso)
     *
     * @param data El producto a ser actualizado.
     */
    @Override
    public void update(Product data) {

    }

    /**
     * Elimina un producto de la base de datos. (No implementado en este caso)
     *
     * @param data El producto a ser eliminado.
     */
    @Override
    public void delete(Product data) {

    }
}
