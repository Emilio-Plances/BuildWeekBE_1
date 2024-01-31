package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.StatoVeicolo;
import org.example.project.enums.TipoVeicolo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public Veicolo(TipoVeicolo tipoVeicolo,LocalDate dataEntrataInServizio) {
        this.dataInizioServizio = dataEntrataInServizio;
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
    }
    public TipoVeicolo getTipoVeicolo() {
        return tipoVeicolo;
    }
    public void setTipoVeicolo(TipoVeicolo tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
        this.numeroPosti= setNumeroPosti();
    }


    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }

    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
    }

    public boolean isInManutenzione() {
        return this.statoVeicolo == StatoVeicolo.IN_MANUTENZIONE;
    }



    public boolean isDisponibile() {
        return statoVeicolo == StatoVeicolo.IN_SERVIZIO && isVeicoloNonImpegnato();
    }


    private boolean isVeicoloNonImpegnato() {
        if (listaCorse != null && !listaCorse.isEmpty()) {
            for (Corsa corsa : listaCorse) {
                if (corsa.getDataArrivo() == null || corsa.getDataArrivo().isAfter(LocalDateTime.now())) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return  "id=" + id +
                ", numeroPosti=" + numeroPosti +
                ", statoVeicolo=" + statoVeicolo +
                ", tipoVeicolo=" + tipoVeicolo;
    }
}
