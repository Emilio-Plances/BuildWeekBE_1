package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Tratta;

public class TrattaDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    public TrattaDao(){
        emf= Persistence.createEntityManagerFactory("Biglietteria");
        em=emf.createEntityManager();
    }

    public void aggiungiTratta(Tratta t) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.persist(t);
        et.commit();
        em.refresh(t);
    }

    public Tratta cercaTrattaById(int id) throws Exception{
        return em.find(Tratta.class,id);
    }

    public void cancellaTrattaById(int id) throws Exception{
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.remove(cercaTrattaById(id));
        et.commit();
    }

    public void closeEM(){
        emf.close();
        em.close();
    }
}
