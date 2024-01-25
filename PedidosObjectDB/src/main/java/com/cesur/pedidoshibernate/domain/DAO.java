package com.cesur.pedidoshibernate.domain;

import java.util.List;

/**
 * La interfaz DAO proporciona métodos CRUD (Crear, Leer, Actualizar, Borrar) generales para todas las clases.
 *
 * @param <T> El parámetro que representa la entidad que se espera en la devolución, como Usuario, Juego, etc.
 */
public interface DAO<T> {

    /**
     * Obtiene una lista de todas las entidades de tipo T.
     *
     * @return La lista de entidades.
     */
    public List<T> getAll();

    /**
     * Obtiene una entidad de tipo T basada en su identificador único.
     *
     * @param id El identificador único de la entidad.
     * @return La entidad correspondiente al identificador proporcionado.
     */
    public T get(Long id);

    /**
     * Guarda una nueva entidad de tipo T en la base de datos.
     *
     * @param data La entidad a ser guardada.
     * @return La entidad guardada con posiblemente valores actualizados.
     */
    public T save(T data);

    /**
     * Actualiza los datos de una entidad de tipo T en la base de datos.
     *
     * @param data La entidad a ser actualizada.
     */
    public void update(T data);

    /**
     * Elimina una entidad de tipo T de la base de datos.
     *
     * @param data La entidad a ser eliminada.
     */
    public void delete(T data);

}
