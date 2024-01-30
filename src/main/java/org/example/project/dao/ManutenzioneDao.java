package org.example.project.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Manutenzione;

public class ManutenzioneDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ManutenzioneDao() {
        emf= Persistence.createEntityManagerFactory("biglietteria");
        em=emf.createEntityManager();
    }

    public void saveManutenzione(Manutenzione manutenzione) throws Exception{
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(manutenzione);
        transaction.commit();
        em.refresh(manutenzione);
    }
    public Manutenzione getManutenzioneById(int id) {
        return em.find(Manutenzione.class, id);
    }

    public void deleteManutenzione(int id){
        EntityTransaction et=em.getTransaction();
        et.begin();
        em.remove(getManutenzioneById(id));
        et.commit();
    }

    public void closeEM(){
        emf.close();
        em.close();
    }
}
