package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Veicolo;

import java.util.List;


public class VeicoloDao {
    private EntityManagerFactory emf;

    public VeicoloDao() {

        this.emf = Persistence.createEntityManagerFactory("biglietteria");
    }

    public void saveVeicolo(Veicolo veicolo) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(veicolo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void closeEM(){
        emf.close();
    }

    public List<Object[]> dataManutenzioniVeicolo(int idVeicolo) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Manutenzione.findDataByVeicoloId", Object[].class)
                    .setParameter("idVeicolo", idVeicolo)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}

