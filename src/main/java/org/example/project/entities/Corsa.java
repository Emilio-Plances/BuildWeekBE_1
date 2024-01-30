package org.example.project.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Corsa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_venditore")
    @SequenceGenerator(name="sequenza_venditore",initialValue = 1,allocationSize = 1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "veicolo_fk")
    private Veicolo veicolo;
    @ManyToOne
    @JoinColumn(name = "corsa_fk")
    private Tratta tratta;
    @OneToMany(mappedBy = "corsa")
    private List<ProdottoAcquistato> prodottiAcquistati=new ArrayList<>();

    private int durata;

    public Corsa() {
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
