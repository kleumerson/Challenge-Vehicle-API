package com.ags.backend.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("HibernatePU");
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
