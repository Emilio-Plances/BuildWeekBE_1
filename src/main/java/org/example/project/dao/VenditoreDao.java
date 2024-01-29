package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Venditore;

public class VenditoreDao {
    private EntityManagerFactory emf;
    private EntityManager em;
    public VenditoreDao(){
        emf= Persistence.createEntityManagerFactory("Biglietteria");
        em=emf.createEntityManager();
    }

    public void aggiungiVenditore(Venditore v){
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.persist(v);
        et.commit();
        em.refresh(v);
    }

    public Venditore cercaVenditoreById(int id){
        return em.find(Venditore.class,id);
    }

    public void cancellaVenditorebyId(int id){
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
