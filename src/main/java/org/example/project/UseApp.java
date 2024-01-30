package org.example.project;

import org.example.project.dao.*;
import org.example.project.entities.*;
import org.example.project.enums.CategoriaCliente;
import org.example.project.enums.Genere;
import org.example.project.enums.TipoAbbonamento;

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

        //TesseraCliente t1= creaTessera("Emilio","Plances",LocalDate.of(1997,3,7),Genere.M,CategoriaCliente.STANDARD);
        TesseraCliente t1=tesseraDao.getById(159);
        System.out.println(t1);

        //Venditore v1=creaVenditore();
        venditoreDao.getById(1);

        //Abbonamento a1=creaAbbonamento();

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
    public static Venditore creaVenditore(){
        try{
            Venditore v=new Venditore();
            venditoreDao.save(v);
            return v;
        }catch(Exception ex){
            System.out.println("Errore nella creazione");
        }
        return null;
    }
    public static Abbonamento creaAbbonamento(Venditore venditore, TipoAbbonamento tipoAbbonamento, TesseraCliente tesseraCliente){
        try{
            Abbonamento a=new Abbonamento();
            prodottoDao.save(a);
            return a;
        }catch(Exception ex){
            System.out.println("Errore nella creazione");
        }
        return null;
    }
}
