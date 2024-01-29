package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Venditore;

public class VenditoreDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    public VenditoreDao(){
        emf= Persistence.createEntityManagerFactory("biglietteria");
        em=emf.createEntityManager();
    }

    public void aggiungiVenditore(Venditore v) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.persist(v);
        et.commit();
        em.refresh(v);
    }

    public Venditore cercaVenditoreById(int id) throws Exception{
        return em.find(Venditore.class,id);
    }

    public void cancellaVenditoreById(int id) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.remove(cercaVenditoreById(id));
        et.commit();
    }

    public void closeEM(){
        emf.close();
        em.close();
    }
}
