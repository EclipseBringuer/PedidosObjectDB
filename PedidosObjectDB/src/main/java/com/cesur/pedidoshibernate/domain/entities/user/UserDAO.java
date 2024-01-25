package com.cesur.pedidoshibernate.domain.entities.user;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.ObjectDBUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * La clase UserDAO implementa la interfaz DAO para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos para usuarios.
 */
public class UserDAO implements DAO<User> {
    /**
     * Obtiene una lista de todos los usuarios. (No implementado en este caso)
     *
     * @return Lista de usuarios (en este caso, se devuelve null).
     */
    @Override
    public List<User> getAll() {
        return null;
    }

    /**
     * Obtiene un usuario basado en su identificador único. (No implementado en este caso)
     *
     * @param id El identificador único del usuario.
     * @return El usuario correspondiente al identificador proporcionado (en este caso, se devuelve null).
     */
    @Override
    public User get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param data El usuario a ser guardado.
     * @return El usuario guardado con posiblemente valores actualizados.
     */
    @Override
    public User save(User data) {
        User salida = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            salida = data;
        } catch (Exception e) {
            System.out.println("Fallo al guardar: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return salida;
    }


    /**
     * Actualiza los datos de un usuario en la base de datos. (No implementado en este caso)
     *
     * @param data El usuario a ser actualizado.
     */
    @Override
    public void update(User data) {

    }

    /**
     * Elimina un usuario de la base de datos. (No implementado en este caso)
     *
     * @param data El usuario a ser eliminado.
     */
    @Override
    public void delete(User data) {

    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario validado, o null si las credenciales no son válidas.
     */
    public User validateUser(String email, String password) {
        User result = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :em AND u.password = :pass", User.class);
            q.setParameter("em", email);
            q.setParameter("pass", password);

            List<User> resultList = q.getResultList();
            if (!resultList.isEmpty()) {
                result = resultList.get(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return result;
    }
}
