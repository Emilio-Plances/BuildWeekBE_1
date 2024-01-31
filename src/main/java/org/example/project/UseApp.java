package org.example.project;

import org.example.project.dao.*;
import org.example.project.entities.*;
import org.example.project.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UseApp {
    private static final CorsaDao corsaDao=new CorsaDao();
    private static final ManutenzioneDao manutenzioneDao=new ManutenzioneDao();
    private static final ProdottoDao prodottoDao=new ProdottoDao();
    private static final TesseraDao tesseraDao=new TesseraDao();
    private static final TrattaDao trattaDao=new TrattaDao();
    private static final VeicoloDao veicoloDao=new VeicoloDao();
    private static final VenditoreDao venditoreDao=new VenditoreDao();

    public static void main(String[] args) {

//        TesseraCliente t1=creaTessera("Emilio","Plances",LocalDate.of(1997,3,7),Genere.M,CategoriaCliente.STANDARD);
//        TesseraCliente t2= creaTessera("Tommaso","Cantarini",LocalDate.of(1991,6,20),Genere.M,CategoriaCliente.STANDARD);
        TesseraCliente t1=tesseraDao.getById(526);//  Tessera Emilio
        TesseraCliente t2=tesseraDao.getById(397);//  Tessera Tommaso


//        Venditore v1=creaVenditore("Da Mario");
//        DistributoreAutomatico d1=creaDistributore("Shish",StatoDistributore.ATTIVO);
        Venditore v1=venditoreDao.getById(1);

//        Tratta tratta1=creaTratta(TipoTratta.EXTRA_URBANA,"Palermo","Catania");
        Tratta tratta1=trattaDao.getTrattaById(1);

        //Abbonamento a1=creaAbbonamento(v1,tratta1,TipoAbbonamento.MENSILE,t1);
        ProdottoAcquistato pa=prodottoDao.getById(1);

        //Veicolo veicolo1=creaVeicolo(TipoVeicolo.AUTOBUS);
        Veicolo veicolo1=veicoloDao.getVeicoloById(1);

        LocalDateTime ldt1=LocalDateTime.of(LocalDate.of(2024,1,15), LocalTime.of(15,0,0));
        LocalDateTime ldt2=LocalDateTime.of(LocalDate.of(2024,1,15), LocalTime.of(18,0,0));

        //Corsa c=creaCorsa(veicolo1,tratta1,ldt1);
        Corsa c=corsaDao.cercaCorsaById(3);

        Biglietto b1=creaBiglietto(v1,c);

        //Manutenzione m=creaManutenzione(veicolo1,LocalDate.of(2024,2,3));
        Manutenzione m=manutenzioneDao.getManutenzioneById(1);

        System.out.println(c);

        c.setDataArrivo(ldt2);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(veicolo1);

        corsaDao.closeEM();
        manutenzioneDao.closeEM();
        prodottoDao.closeEM();
        tesseraDao.closeEM();
        trattaDao.closeEM();
        veicoloDao.closeEM();
        venditoreDao.closeEM();
    }
    public static TesseraCliente creaTessera(String nome, String cognome, LocalDate dataNascita, Genere genere, CategoriaCliente categoriaCliente){
        try{
            TesseraCliente t=new TesseraCliente(nome,cognome,dataNascita,genere,categoriaCliente);
            tesseraDao.save(t);
            return t;
        }catch(Exception ex){
            System.out.println("Errore nella creazione tessera");
        }
        return null;
    }
    public static Venditore creaVenditore(String nome){
        try{
            Venditore v=new Venditore(nome);
            venditoreDao.save(v);
            return v;
        }catch(Exception ex){
            System.out.println("Errore nella creazione venditore");
        }
        return null;
    }
    public static DistributoreAutomatico creaDistributore(String nome, StatoDistributore statoDistributore){
        try{
            DistributoreAutomatico d=new DistributoreAutomatico(nome,statoDistributore);
            venditoreDao.save(d);
            return d;
        }catch(Exception ex){
            System.out.println("Errore nella creazione distributore");
        }
        return null;
    }
    public static Tratta creaTratta(TipoTratta tipoTratta, String partenza, String destinazione){
        try{
            Tratta t=new Tratta(tipoTratta,partenza,destinazione);
            trattaDao.save(t);
            return t;
        }catch(Exception ex){
            System.out.println("Errore nella creazione tratta");
        }
        return null;
    }
    public static Abbonamento creaAbbonamento(Venditore venditore, Tratta tratta, TipoAbbonamento tipoAbbonamento, TesseraCliente tesseraCliente){
        try{
            Abbonamento a=new Abbonamento(venditore,tratta,tipoAbbonamento,tesseraCliente);
            prodottoDao.save(a);
            return a;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Errore nella creazione abbonamneto");
        }
        return null;
    }
    public static Veicolo creaVeicolo(TipoVeicolo tipoveicolo){
        Veicolo v=new Veicolo(tipoveicolo);
        veicoloDao.saveVeicolo(v);
        return v;
    }
    public static Corsa creaCorsa(Veicolo veicolo, Tratta tratta, LocalDateTime dataPartenza) {
        try {
            Corsa c = new Corsa(veicolo, tratta, dataPartenza);
            corsaDao.aggiungiCorsa(c);
            return c;
        }catch (Exception e){
            System.out.println("Errore nella creazione corsa");
        }
        return null;
    }
    public static Manutenzione creaManutenzione(Veicolo veicoloM, LocalDate dataInizio){
        try {
            Manutenzione s=new Manutenzione(veicoloM,dataInizio);
            manutenzioneDao.saveManutenzione(s);
            return s;
        }catch (Exception e){
            System.out.println("Errore nella creazione manutenzione");
        }
        return null;
    }
    public static Biglietto creaBiglietto(Venditore venditore, Corsa corsa){
        try{
            Biglietto b=new Biglietto(venditore,corsa);
            prodottoDao.save(b);
            return b;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Errore nella creazione biglietto");
        }
        return null;
    }

}
