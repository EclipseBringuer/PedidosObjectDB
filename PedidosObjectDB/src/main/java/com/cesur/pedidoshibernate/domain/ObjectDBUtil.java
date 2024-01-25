package com.cesur.pedidoshibernate.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
    private static EntityManagerFactory emf = null;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("bd.odb");
            System.out.println("ENTITY MANAGER FACTORY CREADO CON ÉXITO");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Obtiene la factoría de entidades de ObjectDB.
     *
     * @return La factoría de entidades de ObjectDB.
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
