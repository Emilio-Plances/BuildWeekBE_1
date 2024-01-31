package org.example.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Biglietto;
import org.example.project.entities.Corsa;
import org.example.project.entities.ProdottoAcquistato;
import org.example.project.entities.Veicolo;

import java.util.ArrayList;
import java.util.List;

public class CorsaDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public CorsaDao() {
        emf = Persistence.createEntityManagerFactory("biglietteria");
        em = emf.createEntityManager();
    }

    public List<Biglietto> getBigliettiTimbratiInCorsa(int idCorsa) {
        try {
            return em.createNamedQuery("Corsa.trovaBigliettiTimbrati", Biglietto.class)
                    .setParameter("idCorsa", idCorsa)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void aggiungiCorsa(Corsa c) throws Exception {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Veicolo veicolo = c.getVeicolo();
        if (!veicolo.isDisponibile()) {
            throw new Exception("Il veicolo non è disponibile per la corsa.");
        }
        em.persist(c);
        et.commit();
        em.refresh(c);
    }

    public Corsa cercaCorsaById(int id){
        return em.find(Corsa.class, id);
    }

    public void cancellaCorsaById(int id) throws Exception {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(cercaCorsaById(id));
        et.commit();
    }

    public void closeEM() {
        emf.close();
        em.close();
    }
}
