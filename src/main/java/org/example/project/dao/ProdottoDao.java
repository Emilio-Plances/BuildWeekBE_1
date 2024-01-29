package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.ProdottoAcquistato;


public class ProdottoDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    // Costruttore: inizializza l'EntityManagerFactory e l'EntityManager
    public ProdottoDao() {
        emf = Persistence.createEntityManagerFactory("biglietteria");
        em = emf.createEntityManager();
    }

    // Metodo per salvare un elemento nel database
    public void save(ProdottoAcquistato pa) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(pa);

        et.commit();
    }

    // Metodo per ottenere un elemento dato il suo ISBN
    public ProdottoAcquistato getById(int id) {
        return em.find(ProdottoAcquistato.class, id);
    }

    // Metodo per eliminare un elemento dal database
    public void delete(ProdottoAcquistato pa) {
        ProdottoAcquistato pacq = getById(pa.getId());

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(pacq);

        et.commit();
    }

    // Metodo per chiudere l'EntityManager e l'EntityManagerFactory
    public void close() {
        em.close();
        emf.close();
    }


}
