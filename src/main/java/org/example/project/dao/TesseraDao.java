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
    public void save (TesseraCliente t){
        try {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(t);
            et.commit();
            em.refresh(t);
        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    public TesseraCliente getById(int id) {
        return em.find(TesseraCliente.class,id);
    }
    public void delete (int id){
        try{
            EntityTransaction et = em.getTransaction();
            et.begin();
            TesseraCliente tes = getById(id);
            em.remove(tes);
            et.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeEM() {
        emf.close();
        em.close();
    }
}
