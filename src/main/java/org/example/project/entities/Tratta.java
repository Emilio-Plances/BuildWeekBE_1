package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.TipoTratta;

import java.util.List;

@Entity
@Table(name="tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_tratta")
    @SequenceGenerator(name = "sequenza_tratta",initialValue = 1,allocationSize = 1)
    private int id;

    @Column(name =  "media_durata")
    private int mediaDurata;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tratta",nullable = false)
    private TipoTratta  tipoTratta;
    @Column(nullable = false)
    private String partenza;
    @Column(nullable = false)
    private String destinazione;
    @OneToMany(mappedBy = "tratta")
    private List<Corsa> listaCorse;

    public Tratta(){}

    public Tratta(TipoTratta tipoTratta, String partenza, String destinazione) {
        this.tipoTratta = tipoTratta;
        this.partenza = partenza;
        this.destinazione = destinazione;
    }

    public int getId() {
        return id;
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
        this.destinazione = destinazione;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", mediaDurata=" + mediaDurata +
                ", tipoTratta=" + tipoTratta +
                ", partenza='" + partenza + '\'' +
                ", destinazione='" + destinazione + '\'';
    }
}
