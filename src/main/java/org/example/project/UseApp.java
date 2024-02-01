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

        TesseraCliente t1 = tesseraDao.getById(929);//  Tessera Emilio
        TesseraCliente t2 = tesseraDao.getById(887);//  Tessera Tommaso
        TesseraCliente t3 = tesseraDao.getById(437);//  Tessera Calogero

        Venditore v1 = venditoreDao.getById(1);
        DistributoreAutomatico d1 = (DistributoreAutomatico) venditoreDao.getById(2);
        Venditore v2 = venditoreDao.getById(3);
        DistributoreAutomatico d2 = (DistributoreAutomatico) venditoreDao.getById(4);

        Tratta tratta1 = trattaDao.getTrattaById(1);
        Tratta tratta2 = trattaDao.getTrattaById(2);
        Tratta tratta3 = trattaDao.getTrattaById(3);
        Tratta tratta4 = trattaDao.getTrattaById(4);

        Abbonamento abbonamento1 = (Abbonamento) prodottoDao.getById(1);
        Abbonamento abbonamento2 = (Abbonamento) prodottoDao.getById(2);
        Abbonamento abbonamento3 = (Abbonamento) prodottoDao.getById(3);
        Abbonamento abbonamento4 = (Abbonamento) prodottoDao.getById(4);

        Veicolo veicolo1 = veicoloDao.getVeicoloById(1);
        Veicolo veicolo2 = veicoloDao.getVeicoloById(2);
        Veicolo veicolo3 = veicoloDao.getVeicoloById(3);
        Veicolo veicolo4 = veicoloDao.getVeicoloById(4);

        Corsa corsa3 = corsaDao.cercaCorsaById(5);
        Corsa corsa4 = corsaDao.cercaCorsaById(6);
        Corsa corsa1 = corsaDao.cercaCorsaById(7);
        Corsa corsa2 = corsaDao.cercaCorsaById(8);


        Biglietto biglietto1 = (Biglietto) prodottoDao.getById(5);
        Biglietto biglietto2 = (Biglietto) prodottoDao.getById(6);
        Biglietto biglietto3 = (Biglietto) prodottoDao.getById(7);
        Biglietto biglietto4 = (Biglietto) prodottoDao.getById(8);


        Manutenzione m1 = manutenzioneDao.getManutenzioneById(1);
        Manutenzione m2 = manutenzioneDao.getManutenzioneById(2);
        Manutenzione m3 = manutenzioneDao.getManutenzioneById(3);
        Manutenzione m4 = manutenzioneDao.getManutenzioneById(4);


                                        //RICHIESTE DELLA TRACCIA

        // ricerca di prodottiAcquistati per un determinato venditore

        Object vendutiDaV1 = prodottoDao.vendutiDaVenditore(v1);
        System.out.println("conteggio di prodotti venduti:" + vendutiDaV1);
        System.out.println("----------------------------------------------------------------");

        //ricerca di prodottiAcquistati per range di date e venditore

        Object nrProdotti = prodottoDao.venditeEffettuateInData(LocalDate.of(2024, 1, 25),
                LocalDate.of(2024, 3, 31), v1.getId());
        System.out.println("conteggio di prodotti venduti in una data:" + nrProdotti);
        System.out.println("----------------------------------------------------------------");

//        //Verifica rapida di validità abbonamento per singola tessera
        Abbonamento a1=creaAbbonamento(v2,tratta3,TipoAbbonamento.MENSILE,t3);


        Abbonamento ab =(Abbonamento) prodottoDao.getById(1);
        ab.setValiditaAbbonamento(false);
        prodottoDao.save(ab);
        List<Object[]> abbonamentiScaduti = prodottoDao.abbonamentiScadutiPerTessera(t1.getTessera_cliente());
        for (Object[] abb : abbonamentiScaduti) {
            System.out.println("abbId:" + abb[0]);
            System.out.println("nomeTessera:" + abb[1]);
            System.out.println("validita:" + abb[2]);
        }
        System.out.println("----------------------------------------------------------------");

        //metodo per avere la lista di manutenzioni:

        stampaListaManutenziioni(veicolo1);
        System.out.println("----------------------------------------------------------------");

        //metodo per avere la lista dei dati di servizio
        stampaPeriodiDiServizio(veicolo1);
        System.out.println("----------------------------------------------------------------");

        //timbratura di un biglietto




















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

    public static Biglietto creaBiglietto(Venditore venditore, Tratta tratta) {
        try {
            Biglietto biglietto = new Biglietto(venditore, tratta);
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

    public static void stampaPeriodiDiServizio(Veicolo veicolo){
        List<Object[]> periodiServizio = veicoloDao.periodiServizioVeicolo(veicolo.getId());

        for (Object[] periodo : periodiServizio) {
            LocalDate dataInizio = (LocalDate) periodo[0];
            LocalDate dataFine = (LocalDate) periodo[1];

            System.out.println("Periodo di servizio: " + dataInizio + " - " + dataFine);
        }
        System.out.println("Il veicolo con ID: " + veicolo.getId() + " ha effettuato " + veicoloDao.sommaGiorniServizio(veicolo.getId()) + " giorni di servizio");
        System.out.println("Il veicolo con ID: " + veicolo.getId() + " ha effettuato " + veicoloDao.sommaGiorniManutenzione(veicolo.getId()) + " giorni di manutenzione");
    }

    public static void stampaListaManutenziioni(Veicolo veicolo) {
        List<Object[]> manutenzioni = veicoloDao.dataManutenzioniVeicolo(veicolo.getId());
        for (Object[] manutenzione : manutenzioni) {
            LocalDate dataInizio = (LocalDate) manutenzione[0];
            LocalDate dataFine = (LocalDate) manutenzione[1];
            System.out.println(" data inizio manutenzione:" + dataInizio + " data fine:" + dataInizio);
        }
    }
}

//        TesseraCliente t1=creaTessera("Emilio","Plances",LocalDate.of(1997,3,7),Genere.M,CategoriaCliente.STUDENTE);
//        TesseraCliente t2= creaTessera("Tommaso","Cantarini",LocalDate.of(1991,6,20),Genere.M,CategoriaCliente.STUDENTE);
//        TesseraCliente t3= creaTessera("Calogero","Teresi",LocalDate.of(1999,3,12),Genere.M,CategoriaCliente.STUDENTE);

//        Venditore v1=creaVenditore("Da Mario");
//        DistributoreAutomatico d1=creaDistributore("Shish",StatoDistributore.ATTIVO);
//        Venditore v2=creaVenditore("Da Carlo");
//        DistributoreAutomatico d2=creaDistributore("BellOchhio",StatoDistributore.ATTIVO);

//        Tratta tratta1=creaTratta(TipoTratta.EXTRA_URBANA,"Palermo","Catania");
//        Tratta tratta2=creaTratta(TipoTratta.URBANA,"Via piave","Via Isonzo");
//        Tratta tratta3=creaTratta(TipoTratta.EXTRA_URBANA,"Cagliari","Oristano");
//        Tratta tratta4=creaTratta(TipoTratta.EXTRA_URBANA,"Ancona","Osimo");

//        Abbonamento a1=creaAbbonamento(v1,tratta1,TipoAbbonamento.MENSILE,t1);
//        Abbonamento a2=creaAbbonamento(v2,tratta2,TipoAbbonamento.MENSILE,t1);
//        Abbonamento a3=creaAbbonamento(d1,tratta1,TipoAbbonamento.MENSILE,t2);
//        Abbonamento a4=creaAbbonamento(v1,tratta1,TipoAbbonamento.MENSILE,t3);

//Veicolo veicolo1=creaVeicolo(TipoVeicolo.AUTOBUS);
//        Veicolo veicolo2=creaVeicolo(TipoVeicolo.AUTOBUS);
//        Veicolo veicolo3=creaVeicolo(TipoVeicolo.TRAM);
//        Veicolo veicolo4=creaVeicolo(TipoVeicolo.TRAM);

//
//        Corsa c1 = creaCorsa(veicolo1,tratta1,LocalDateTime.of(2024,1,28,8,20));
//        Corsa c2= creaCorsa(veicolo2,tratta1,LocalDateTime.of(2024,1,28,10,20));
//        Corsa c3= creaCorsa(veicolo3,tratta2,LocalDateTime.of(2024,1,28,12,20));
//        Corsa c4 = creaCorsa(veicolo4,tratta2,LocalDateTime.of(2024,1,28,14,20));

//        Biglietto biglietto = creaBiglietto(v1,tratta1);
//        Biglietto biglietto1 = creaBiglietto(v1,tratta1);
//        Biglietto biglietto2 = creaBiglietto(v1,tratta2);
//        Biglietto biglietto3 = creaBiglietto(v1,tratta2);

//        Manutenzione manutenzione1 = creaManutenzione(veicolo1, LocalDate.of(2024,3,10),LocalDate.of(2024,3,20));
//        Manutenzione manutenzione2 = creaManutenzione(veicolo1, LocalDate.of(2024,6,10),LocalDate.of(2024,6,20));
//        Manutenzione manutenzione3 = creaManutenzione(veicolo2, LocalDate.of(2024,3,10),LocalDate.of(2024,3,20));
//        Manutenzione manutenzione4 = creaManutenzione(veicolo2, LocalDate.of(2024,6,10),LocalDate.of(2024,6,20));





