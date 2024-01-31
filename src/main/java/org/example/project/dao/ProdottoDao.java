package org.example.project.dao;

import jakarta.persistence.*;
import org.example.project.entities.Abbonamento;
import org.example.project.entities.Biglietto;
import org.example.project.entities.DistributoreAutomatico;
import org.example.project.entities.ProdottoAcquistato;
import org.example.project.enums.StatoDistributore;

import java.time.LocalDate;
import java.util.List;


public class ProdottoDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    // Costruttore: inizializza l'EntityManagerFactory e l'EntityManager
    public ProdottoDao() {
        emf = Persistence.createEntityManagerFactory("biglietteria");
        em = emf.createEntityManager();
    }

    // Metodo per salvare un elemento nel database
    public void save(ProdottoAcquistato pa) throws Exception {
        EntityTransaction et = em.getTransaction();
        et.begin();
        //CONTROLLO SE ABBONAMENTO GIA PRESENTE
        if (pa.getVenditore() instanceof DistributoreAutomatico distributore && distributore.getStato()==StatoDistributore.FUORI_SERVIZIO) {
            throw new Exception("Il distributore non è attivo.");
        }
        em.persist(pa);
        et.commit();
    }


    // Metodo per ottenere un elemento dato il suo ISBN
    public ProdottoAcquistato getById(int id){
        return em.find(ProdottoAcquistato.class, id);
    }

    // Metodo per eliminare un elemento dal database
    public void delete(ProdottoAcquistato pa) throws Exception{
        ProdottoAcquistato pacq = getById(pa.getId());
        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(pacq);

        et.commit();
    }

    // Metodo per chiudere l'EntityManager e l'EntityManagerFactory
    public void closeEM(){
        em.close();
        emf.close();
    }

    public boolean checkValidita(int id){
        Query q=em.createNamedQuery("checkValidita");
        q.setParameter("id",id);
        ProdottoAcquistato prod=(ProdottoAcquistato) q.getSingleResult();
        if(prod instanceof Abbonamento a) return a.isValiditaAbbonamento();
        return false;
    }
    public List<Abbonamento> abbonamentiScaduti(){
        Query q = em.createNamedQuery("abbonamentiScaduti");
        return q.getResultList();
    }

    public List<ProdottoAcquistato> venditeEffettuateInData(LocalDate dataInizio,LocalDate dataFine,int idVenditore){
        Query q=em.createNamedQuery("bigliettiVenduti");
        q.setParameter("dataInizio",dataInizio);
        q.setParameter("dataFine",dataFine);
        q.setParameter("idVenditore",idVenditore);
        return q.getResultList();
    }

    public List<ProdottoAcquistato> vendutiDaVenditore(int idVenditore){
        Query q= em.createNamedQuery("vendutiDaVenditore");
        q.setParameter("idVenditore",idVenditore);
        return q.getResultList();
    }
}
