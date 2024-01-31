package org.example.project.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.project.entities.Manutenzione;
import org.example.project.entities.Veicolo;
import org.example.project.enums.StatoVeicolo;

import java.time.LocalDate;
import java.util.List;

public class ManutenzioneDao {
    private final EntityManagerFactory emf;
    private final EntityManager em;
    private VeicoloDao veicoloDao;

    public ManutenzioneDao() {
        emf= Persistence.createEntityManagerFactory("biglietteria");
        em=emf.createEntityManager();
        veicoloDao = new VeicoloDao();
    }

    public void saveManutenzione(Manutenzione manutenzione) throws Exception{
        EntityTransaction transaction = em.getTransaction();
        Veicolo veicolo = manutenzione.getVeicoloM();
        List<Object[]> manutenzioni = veicoloDao.dataManutenzioniVeicolo(veicolo.getId());

        veicolo.setStatoVeicolo(StatoVeicolo.IN_MANUTENZIONE);
        //SERVE AGGIORNARE IL DB
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

    public boolean isPeriodoSovrapposto(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !end1.isBefore(start2) && !start1.isAfter(end2);
    }


}

