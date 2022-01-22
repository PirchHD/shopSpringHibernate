package com.shop.demo.database.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Database {

    public EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;
    public EntityTransaction transaction;

    private static Database databaseInstace;

    public Database(){
         entityManagerFactory = Persistence.createEntityManagerFactory("default");
         entityManager = entityManagerFactory.createEntityManager();
         transaction = entityManager.getTransaction();
    }

    public static Database getInstace(){
        databaseInstace = new Database();
        return databaseInstace;
    }

}
