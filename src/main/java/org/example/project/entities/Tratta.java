package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.TipoTratta;

@Entity
@Table(name="tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_biglietteria")
    @SequenceGenerator(name = "sequenza_biglietteria",initialValue = 1,allocationSize = 1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;
    @Column(name =  "media_durata")
    private int mediaDurata;
    @Column(name = "prezzo_al_minuto")
    private double prezzoAlMinuto;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tratta")
    private TipoTratta  tipoTratta;
    private String partenza;
    private String destinazione;
    @Column(name = "prezzo_tratta")
    private Double prezzoTratta;

    public Tratta(){}

    public Tratta(Veicolo veicolo, int mediaDurata, int prezzoAlMinuto, TipoTratta tipoTratta, String partenza, String destinazione) {
        this.veicolo = veicolo;
        this.mediaDurata = mediaDurata;
        this.prezzoAlMinuto = setPrezzoAlMinuto();
        this.tipoTratta = tipoTratta;
        this.partenza = partenza;
        this.destinazione = destinazione;
        prezzoTratta = mediaDurata * setPrezzoAlMinuto();
    }
    public double setPrezzoAlMinuto(){
        if (tipoTratta.equals(TipoTratta.URBANA)){
            return prezzoAlMinuto = 0.4;
        }else {
            return prezzoAlMinuto= 0.7;
        }
    }

    public double getPrezzoAlMinuto() {
        return prezzoAlMinuto;
    }

    public void setPrezzoAlMinuto(double prezzoAlMinuto) {
        this.prezzoAlMinuto = prezzoAlMinuto;
    }

    public void setPrezzoTratta(Double prezzoTratta) {
        this.prezzoTratta = prezzoTratta;
    }

    public Double getPrezzoTratta() {
        return prezzoTratta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public int getMediaDurata() {
        return mediaDurata;
    }

    public void setMediaDurata(int mediaDurata) {
        this.mediaDurata = mediaDurata;
    }

    public TipoTratta getTipoTratta() {
        return tipoTratta;
    }

    public void setTipoTratta(TipoTratta tipoTratta) {
        this.tipoTratta = tipoTratta;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        destinazione = destinazione;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", veicolo=" + veicolo +
                ", mediaDurata=" + mediaDurata +
                ", prezzoAlMinuto=" + prezzoAlMinuto +
                ", tipoTratta=" + tipoTratta +
                ", partenza='" + partenza + '\'' +
                ", destinazione='" + destinazione + '\'' +
                '}';
    }
}
