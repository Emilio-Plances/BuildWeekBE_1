package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.dao.VeicoloDao;
import org.example.project.enums.StatoVeicolo;
import org.example.project.enums.TipoVeicolo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "veicoli")
public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_posti",nullable = false)
    private int numeroPosti;

    @Column(name = "data_inizio_servizio")
    private LocalDate dataInizioServizio;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_veicolo",nullable = false)
    private StatoVeicolo statoVeicolo;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veicolo",nullable = false)
    private TipoVeicolo tipoVeicolo;

    @OneToMany(mappedBy = "veicoloM", cascade = CascadeType.ALL)
    private List<Manutenzione> manutenzioni;

    @OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
    private List<Corsa> listaCorse;

    public Veicolo() {}

    public Veicolo(TipoVeicolo tipoVeicolo) {
        this.dataInizioServizio = LocalDate.now().minusMonths(3);
        this.statoVeicolo = StatoVeicolo.IN_SERVIZIO;
        setTipoVeicolo(tipoVeicolo);
    }
    public int setNumeroPosti(){
        if (this.tipoVeicolo==TipoVeicolo.TRAM){
            return 50;
        }else {
            return 100;
        }
    }

    public void caricaDatabase() {
        VeicoloDao veicoloDao = new VeicoloDao();
        try{veicoloDao.upDate(this);}
        catch (Exception e){
            System.out.println("Errore nel salvataggio");
            System.out.println(e.getMessage());
        }finally {
            veicoloDao.closeEM();
        }
    }

    public int getId() {
        return id;
    }
    public int getNumeroPosti() {
        return numeroPosti;
    }
    public StatoVeicolo getStatoVeicolo() {
        return statoVeicolo;
    }
    public void setStatoVeicolo(StatoVeicolo statoVeicolo) {
        this.statoVeicolo = statoVeicolo;
        caricaDatabase();
    }
    public TipoVeicolo getTipoVeicolo() {
        return tipoVeicolo;
    }
    public void setTipoVeicolo(TipoVeicolo tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
        this.numeroPosti= setNumeroPosti();
        caricaDatabase();
    }

    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }

    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
        caricaDatabase();
    }

    public boolean isInManutenzione() {
        return this.statoVeicolo == StatoVeicolo.IN_MANUTENZIONE;
    }


    private boolean isVeicoloNonImpegnato(Corsa c) {
        if (listaCorse != null && !listaCorse.isEmpty()) {
            for (Corsa corsa : listaCorse) {
                LocalDateTime dataPartenzaCorsa = corsa.getDataPartenza();
                LocalDateTime dataArrivoCorsa = corsa.getDataArrivo();
                LocalDateTime nuovaCorsaDataPartenza = c.getDataPartenza();
                LocalDateTime nuovaCorsaDataArrivo = c.getDataArrivo();

                // Verifica di sovrapposizione considerando date null come non sovrapposte
                if ((dataPartenzaCorsa == null || nuovaCorsaDataArrivo == null || nuovaCorsaDataArrivo.isAfter(dataPartenzaCorsa))
                        && (dataArrivoCorsa == null || nuovaCorsaDataPartenza == null || nuovaCorsaDataPartenza.isBefore(dataArrivoCorsa))) {
                    return false;  // Sovrapposizione di date con la nuova corsa
                }
            }
        }
        return true;  // Il veicolo è disponibile se non ci sono sovrapposizioni
    }



    public boolean isDisponibile(Corsa c) {
        VeicoloDao veicoloDao = new VeicoloDao();
        LocalDate ultimaDataManutenzione = veicoloDao.getUltimaDataManutenzione(getId());

        // Controlla se il veicolo è non impegnato e se la nuova corsa si sovrappone con corse esistenti
        if (isVeicoloNonImpegnato(c) && (ultimaDataManutenzione == null || LocalDate.now().isAfter(ultimaDataManutenzione))) {
            setStatoVeicolo(StatoVeicolo.IN_SERVIZIO);
            return true;  // Il veicolo è disponibile per la nuova corsa
        }
        return false;  // Il veicolo non è disponibile per la nuova corsa
    }








    @Override
    public String toString() {
        return  "id=" + id +
                ", numeroPosti=" + numeroPosti +
                ", statoVeicolo=" + statoVeicolo +
                ", tipoVeicolo=" + tipoVeicolo;
    }
}
