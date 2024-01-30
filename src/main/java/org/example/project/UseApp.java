package org.example.project;

import org.example.project.dao.*;
import org.example.project.entities.*;
import org.example.project.enums.*;

import java.time.LocalDate;

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
        System.out.println(t1);
        System.out.println(t2);

//        Venditore v1=creaVenditore("Da Mario");
//        DistributoreAutomatico d1=creaDistributore("Shish",StatoDistributore.ATTIVO);
        Venditore v1=venditoreDao.getById(1);

//        Tratta tratta1=creaTratta(TipoTratta.EXTRA_URBANA,"Palermo","Catania");
        Tratta tratta1=trattaDao.getTrattaById(1);

        //abbonamento a1=creaAbbonamento(v1,tratta1,TipoAbbonamento.MENSILE,t1);

        //Veicolo veicolo1=creaVeicolo(TipoVeicolo.AUTOBUS);
        Veicolo veicolo1=veicoloDao.getVeicoloById(1);





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
            System.out.println("Errore nella creazione");
        }
        return null;
    }
    public static Venditore creaVenditore(String nome){
        try{
            Venditore v=new Venditore(nome);
            venditoreDao.save(v);
            return v;
        }catch(Exception ex){
            System.out.println("Errore nella creazione");
        }
        return null;
    }
    public static DistributoreAutomatico creaDistributore(String nome, StatoDistributore statoDistributore){
        try{
            DistributoreAutomatico d=new DistributoreAutomatico(nome,statoDistributore);
            venditoreDao.save(d);
            return d;
        }catch(Exception ex){
            System.out.println("Errore nella creazione");
        }
        return null;
    }
    public static Tratta creaTratta(TipoTratta tipoTratta, String partenza, String destinazione){
        try{
            Tratta t=new Tratta(tipoTratta,partenza,destinazione);
            trattaDao.save(t);
            return t;
        }catch(Exception ex){
            System.out.println("Errore nella creazione");
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
            System.out.println("Errore nella creazione");
        }
        return null;
    }
    public static Veicolo creaVeicolo(TipoVeicolo tipoveicolo){
        Veicolo v=new Veicolo(tipoveicolo);
        veicoloDao.saveVeicolo(v);
        return v;
    }
}
