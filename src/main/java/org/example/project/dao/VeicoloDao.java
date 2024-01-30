package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Veicolo;

import java.util.List;


public class VeicoloDao {
    EntityManager entityManager;
    private EntityManagerFactory emf;

    public VeicoloDao() {
        this.emf = Persistence.createEntityManagerFactory("biglietteria");
        entityManager =emf.createEntityManager();
    }

    public void saveVeicolo(Veicolo veicolo) {
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
        }
    }

    public Veicolo getVeicoloById(int id){
        return entityManager.find(Veicolo.class,id);
    }
    public void delete(int id){
        EntityTransaction et=entityManager.getTransaction();
        et.begin();
        entityManager.remove(getVeicoloById(id));
        et.commit();
    }

    public void closeEM(){
        entityManager.close();
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

