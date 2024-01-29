package org.example.project.dao;

import jakarta.persistence.*;

import java.util.List;

public class TesseraDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TesseraDao() {
        this.emf = Persistence.createEntityManagerFactory("biglietteria");
        this.em = emf.createEntityManager();
    }
    public void save (TesseraDao a){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(a);
        et.commit();
    }

    public TesseraDao getById(int id) {
        return em.find(TesseraDao.class,id);
    }
    public void delete (TesseraDao a){
        EntityTransaction et = em.getTransaction();
        et.begin();
        TesseraDao art = getById(a.getId());
        em.remove(art);
        et.commit();
    }
}
