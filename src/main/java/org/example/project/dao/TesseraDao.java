package org.example.project.dao;

import jakarta.persistence.*;
import org.example.project.entities.TesseraCliente;
import org.example.project.entities.Tratta;

import java.util.List;

public class TesseraDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TesseraDao() {
        this.emf = Persistence.createEntityManagerFactory("biglietteria");
        this.em = emf.createEntityManager();
    }
    public void save (Tratta a){
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(a);
            et.commit();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public Tratta getById(int id) {
        return em.find(Tratta.class,id);
    }
    public void delete (Tratta a){
        try{
            EntityTransaction et = em.getTransaction();
            et.begin();
            Tratta art = getById(a.getId());
            em.remove(art);
            et.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        emf.close();
        em.close();
    }


}
