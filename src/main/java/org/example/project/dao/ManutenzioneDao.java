package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.project.entities.Manutenzione;

import java.util.List;


public class ManutenzioneDao {
    private EntityManager entityManager;

    public ManutenzioneDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveManutenzione(Manutenzione manutenzione) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(manutenzione);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public Manutenzione getManutenzioneById(int id) {
        return entityManager.find(Manutenzione.class, id);
    }

    public List<Manutenzione> getAllManutenzioni() {
        return entityManager.createQuery("seleziona oggetti da Manutenzione ", Manutenzione.class).getResultList();
    }

}
