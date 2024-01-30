package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Corsa;
import org.example.project.entities.Venditore;

public class CorsaDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    public CorsaDao(){
        emf= Persistence.createEntityManagerFactory("biglietteria");
        em=emf.createEntityManager();
    }

    public void aggiungiCorsa(Corsa c) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();
        em.refresh(c);
    }

    public Corsa cercaCorsaById(int id) throws Exception{
        return em.find(Corsa.class,id);
    }

    public void cancellaCorsaById(int id) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.remove(cercaCorsaById(id));
        et.commit();
    }

    public void closeEM(){
        emf.close();
        em.close();
    }
}
