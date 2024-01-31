package org.example.project;

import jakarta.persistence.Query;
import org.example.project.dao.*;
import org.example.project.entities.*;
import org.example.project.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UseApp {
    private static final CorsaDao corsaDao = new CorsaDao();
    private static final ManutenzioneDao manutenzioneDao = new ManutenzioneDao();
    private static final ProdottoDao prodottoDao = new ProdottoDao();
    private static final TesseraDao tesseraDao = new TesseraDao();
    private static final TrattaDao trattaDao = new TrattaDao();
    private static final VeicoloDao veicoloDao = new VeicoloDao();
    private static final VenditoreDao venditoreDao = new VenditoreDao();

    public static void main(String[] args) throws Exception {
        //ricerca di prodottiAcquistati per un determinato venditore
        Venditore v1 = venditoreDao.getById(1);
        Object vendutiDaV1 = prodottoDao.vendutiDaVenditore(v1);
        System.out.println("conteggio di prodotti venduti:" + vendutiDaV1);

        //ricerca di prodottiAcquistati per range di date
        Object nrProdotti = prodottoDao.venditeEffettuateInData(LocalDate.of(2024, 1, 25),
                LocalDate.of(2024, 3, 31), v1.getId());
        System.out.println("conteggio di prodotti venduti in una data:" + nrProdotti);

        //Verifica rapida di validità abbonamento per singola tessera
        TesseraCliente t1 = tesseraDao.getById(283);
        List<Object> abbonamentiScaduti = prodottoDao.abbonamentiScadutiPerTessera(t1.getTessera_cliente());
        for (int i = 0; i < abbonamentiScaduti.size(); i++){
            System.out.println();
        }


        //metodo per avere la lista di manutenzioni:
//        stampaListaManutenziioni(veicolo1);


//        corsaDao.closeEM();
//        manutenzioneDao.closeEM();
//        prodottoDao.closeEM();
//        tesseraDao.closeEM();
//        trattaDao.closeEM();
//        veicoloDao.closeEM();
//        venditoreDao.closeEM();
    }

    public static TesseraCliente creaTessera(String nome, String cognome, LocalDate dataNascita, Genere genere, CategoriaCliente categoriaCliente) {
        try {
            TesseraCliente t = new TesseraCliente(nome, cognome, dataNascita, genere, categoriaCliente);
            tesseraDao.save(t);
            return t;
        } catch (Exception ex) {
            System.out.println("Errore nella creazione tessera");
        }
        return null;
    }

    public static Venditore creaVenditore(String nome) {
        try {
            Venditore v = new Venditore(nome);
            venditoreDao.save(v);
            return v;
        } catch (Exception ex) {
            System.out.println("Errore nella creazione venditore");
        }
        return null;
    }

    public static DistributoreAutomatico creaDistributore(String nome, StatoDistributore statoDistributore) {
        try {
            DistributoreAutomatico d = new DistributoreAutomatico(nome, statoDistributore);
            venditoreDao.save(d);
            return d;
        } catch (Exception ex) {
            System.out.println("Errore nella creazione");
        }
        return null;
    }

    public static Tratta creaTratta(TipoTratta tipoTratta, String partenza, String destinazione) {
        try {
            Tratta t = new Tratta(tipoTratta, partenza, destinazione);
            trattaDao.save(t);
            return t;
        } catch (Exception ex) {
            System.out.println("Errore nella creazione tratta");
        }
        return null;
    }

    public static Corsa creaCorsa(Veicolo veicolo, Tratta tratta, LocalDateTime dataPartenza) {
        try {
            Corsa c = new Corsa(veicolo, tratta, dataPartenza);
            corsaDao.aggiungiCorsa(c);
            return c;
        } catch (Exception e) {
            System.out.println("Errore nella creazione corsa");
        }
        return null;
    }

    public static Abbonamento creaAbbonamento(Venditore venditore, Tratta tratta, TipoAbbonamento tipoAbbonamento, TesseraCliente tesseraCliente) {
        try {
            Abbonamento a = new Abbonamento(venditore, tratta, tipoAbbonamento, tesseraCliente);
            prodottoDao.save(a);
            return a;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Errore nella creazione abbonamento");
        }
        return null;
    }

    public static Biglietto creaBiglietto(Venditore venditore, Corsa corsa) {
        try {
            Biglietto biglietto = new Biglietto(venditore, corsa);
            prodottoDao.save(biglietto);
            return biglietto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore nella creazione del biglietto");
        }
        return null;
    }

    public static Veicolo creaVeicolo(TipoVeicolo tipoveicolo) {
        Veicolo v = new Veicolo(tipoveicolo);
        veicoloDao.saveVeicolo(v);
        return v;
    }

    public static Manutenzione creaManutenzione(Veicolo veicolo, LocalDate dataInizio, LocalDate dataFine) {
        try {
            Manutenzione m = new Manutenzione(veicolo, dataInizio, dataFine);
            manutenzioneDao.saveManutenzione(m);
            return m;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore nella creazione manutenzione");
        }
        return null;
    }

    public static void stampaListaManutenziioni(Veicolo veicolo) {
        List<Object[]> manutenzioni = veicoloDao.dataManutenzioniVeicolo(veicolo.getId());
        for (Object[] manutenzione : manutenzioni) {
            LocalDate dataInizio = (LocalDate) manutenzione[0];
            LocalDate dataFine = (LocalDate) manutenzione[1];
            System.out.println(" data inizio:" + dataInizio + " data fine:" + dataInizio);
        }
    }
}


//        TesseraCliente t1=creaTessera("Emilio","Plances",LocalDate.of(1997,3,7),Genere.M,CategoriaCliente.STANDARD);
//        TesseraCliente t2= creaTessera("Tommaso","Cantarini",LocalDate.of(1991,6,20),Genere.M,CategoriaCliente.STANDARD);
//        TesseraCliente t1 = tesseraDao.getById(283);//  Tessera Emilio
//        TesseraCliente t2 = tesseraDao.getById(621);//  Tessera Tommaso

//        Venditore v1=creaVenditore("Da Mario");
//        DistributoreAutomatico d1=creaDistributore("Shish",StatoDistributore.ATTIVO);
//        Venditore v1 = venditoreDao.getById(1);
//        DistributoreAutomatico d1 = (DistributoreAutomatico) venditoreDao.getById(2);

//        Tratta tratta1=creaTratta(TipoTratta.EXTRA_URBANA,"Palermo","Catania");
//        Tratta tratta1 = trattaDao.getTrattaById(1);
//
//        Abbonamento a1=creaAbbonamento(v1,tratta1,TipoAbbonamento.MENSILE,t1);
//        Abbonamento abbonamento1 = (Abbonamento) prodottoDao.getById(1);

//        Veicolo veicolo1=creaVeicolo(TipoVeicolo.AUTOBUS);
//        Veicolo veicolo1 = veicoloDao.getVeicoloById(1);

//        Corsa c = creaCorsa(veicolo1,tratta1,LocalDateTime.of(2024,1,28,8,20));
//        Corsa corsa1 = corsaDao.cercaCorsaById(3);

//        Biglietto biglietto = creaBiglietto(v1,corsa1);
//        Biglietto biglietto1 = creaBiglietto(v1,corsa1);
//        Biglietto biglietto1 = (Biglietto) prodottoDao.getById(2);
//        Biglietto biglietto2 = (Biglietto) prodottoDao.getById(3);
//        Biglietto biglietto3 = (Biglietto) prodottoDao.getById(4);

//        Manutenzione manutenzione1 = creaManutenzione(veicolo1, LocalDate.of(2024,3,10),LocalDate.of(2024,3,20));
//        Manutenzione m1 = manutenzioneDao.getManutenzioneById(1);
//        Manutenzione m2 = manutenzioneDao.getManutenzioneById(2);