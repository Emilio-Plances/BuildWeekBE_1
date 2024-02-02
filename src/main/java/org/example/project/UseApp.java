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

        TesseraCliente t1 = tesseraDao.getById(163);//  Tessera Emilio
        TesseraCliente t2 = tesseraDao.getById(206);//  Tessera Tommaso
        TesseraCliente t3 = tesseraDao.getById(523);//  Tessera Calogero

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
//
//        //ricerca di prodottiAcquistati per range di date e venditore

        Object nrProdotti = prodottoDao.venditeEffettuateInData(LocalDate.of(2024, 1, 25),
                LocalDate.of(2024, 3, 31), v1.getId());
        System.out.println("conteggio di prodotti venduti in una data:" + nrProdotti);
        System.out.println("----------------------------------------------------------------");
//
//      //Verifica rapida di validità abbonamento per singola tessera

        Abbonamento ab =(Abbonamento) prodottoDao.getById(1);
        ab.setValiditaAbbonamento(false);
        prodottoDao.upDate(ab);
        List<Object[]> abbonamentiScaduti = prodottoDao.abbonamentiScadutiPerTessera(t1.getTessera_cliente());
        for (Object[] abb : abbonamentiScaduti) {
            System.out.println("abbId:" + abb[0]);
            System.out.println("nomeTessera:" + abb[1]);
            System.out.println("validita:" + abb[2]);
        }
        System.out.println("----------------------------------------------------------------");

//        //metodo per avere la lista di manutenzioni:

        stampaListaManutenziioni(veicolo2);
        System.out.println("----------------------------------------------------------------");
//
//        //metodo per avere la lista dei dati di servizio
        stampaPeriodiDiServizio(veicolo2);
        System.out.println("----------------------------------------------------------------");

        //timbratura di un biglietto
        biglietto3.timbraBiglietto(corsa2);
        biglietto1.timbraBiglietto(corsa2);


        //biglietti timbrati in una corsa
        corsa2.getBiglietti().forEach(System.out::println);


        //biglietti timbrati in una corsa in un certo periodo di tempo
        List<Biglietto> bigliettos = corsa2.cercaBigliettoPerData(LocalDate.of(2024,01,22),LocalDate.of(2024,02,4));
        bigliettos.forEach(System.out::println);






//        corsaDao.closeEM();
//        manutenzioneDao.closeEM();
//        prodottoDao.closeEM();
//        tesseraDao.closeEM();
//        trattaDao.closeEM();
//        veicoloDao.closeEM();
//        venditoreDao.closeEM();
    }














    public static void stampaPeriodiDiServizio(Veicolo veicolo){
        List<Object[]> periodiServizio = veicoloDao.periodiServizioVeicolo(veicolo.getId());

        for (Object[] periodo : periodiServizio) {
            LocalDate dataInizio = (LocalDate) periodo[0];
            LocalDate dataFine = (LocalDate) periodo[1];

            System.out.println("Periodo di servizio: " + dataInizio + " - " + dataFine);
        }
        System.out.println("Il veicolo con ID: " + veicolo.getId() + " ha effettuato " + veicoloDao.sommaGiorniServizio(veicolo.getId()) + " giorni di servizio");
    }

    public static void stampaListaManutenziioni(Veicolo veicolo) {
        List<Object[]> manutenzioni = veicoloDao.dataManutenzioniVeicolo(veicolo.getId());
        for (Object[] manutenzione : manutenzioni) {
            LocalDate dataInizio = (LocalDate) manutenzione[0];
            LocalDate dataFine = (LocalDate) manutenzione[1];
            System.out.println(" data inizio manutenzione:" + dataInizio + " data fine:" + dataInizio);
        }
        System.out.println("Il veicolo con ID: " + veicolo.getId() + " ha effettuato " + veicoloDao.sommaGiorniManutenzione(veicolo.getId()) + " giorni di manutenzione");
    }
}





