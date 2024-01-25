package com.cesur.pedidoshibernate.domain.entities.order;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.ObjectDBUtil;  // Importa la clase ObjectDBUtil
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * La clase OrderDAO implementa la interfaz DAO para la entidad Order.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos para pedidos.
 */
public class OrderDAO implements DAO<Order> {

    /**
     * Obtiene una lista de todos los pedidos.
     *
     * @return Lista de pedidos.
     */
    @Override
    public List<Order> getAll() {
        List<Order> salida = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<Order> q = em.createQuery("SELECT o FROM Order o", Order.class);
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
     * Obtiene un pedido basado en su identificador único.
     *
     * @param id El identificador único del pedido.
     * @return El pedido correspondiente al identificador proporcionado.
     */
    @Override
    public Order get(Long id) {
        Order order = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            order = em.find(Order.class, id);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return order;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param data El pedido a ser guardado.
     * @return El pedido guardado con posiblemente valores actualizados.
     */
    @Override
    public Order save(Order data) {
        Order exit = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            data.setCode(generateNewCode(em));
            em.persist(data);
            em.getTransaction().commit();
            exit = data;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return exit;
    }

    /**
     * Actualiza los datos de un pedido en la base de datos.
     *
     * @param data El pedido a ser actualizado.
     */
    @Override
    public void update(Order data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data El pedido a ser eliminado.
     */
    @Override
    public void delete(Order data) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, data.getId());
            if (order != null) {
                em.remove(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Genera un nuevo código para un pedido basado en el último código existente en la base de datos.
     *
     * @param em El EntityManager a utilizar.
     * @return El nuevo código generado para el pedido.
     */
    private String generateNewCode(EntityManager em) {
        String newCode = null;

        try {
            Query q = em.createQuery("SELECT max(o.code) from Order o", String.class);
            String lastCode = (String) q.getSingleResult();
            int newNumber = 1;

            if (lastCode != null) {
                String numberStr = lastCode.substring(4);
                newNumber = Integer.parseInt(numberStr) + 1;
            }

            newCode = String.format("PED-%03d", newNumber);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return newCode;
    }
}

