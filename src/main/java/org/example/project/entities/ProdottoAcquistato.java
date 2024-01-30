package org.example.project.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="bigliettiVenduti", query="SELECT COUNT(p) FROM ProdottoAcquistato p WHERE :dataInizio>=p.dataAcquisto AND :dataFine<=p.dataAcquisto AND p.venditore=:idVenditore ")
@NamedQuery(name="vendutiDaVenditore",query="SELECT COUNT(p) FROM ProdottoAcquistato p WHERE p.venditore=:idVenditore")
public abstract class ProdottoAcquistato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_prodotto")
    @SequenceGenerator(name="sequenza_prodotto",initialValue = 1,allocationSize = 1)
    private int id;

    @Column(name="data_acquisto",nullable = false)
    private LocalDate dataAcquisto;

    @ManyToOne
    @JoinColumn(name="venditore_fk",nullable = false)
    private Venditore venditore;

    @ManyToOne
    @JoinColumn(name="corsa_fk",nullable = false)
    private Corsa corsa;

    public ProdottoAcquistato(){}

    public ProdottoAcquistato(Venditore venditore, Corsa corsa) {
        this.venditore = venditore;
        this.corsa = corsa;
        dataAcquisto=LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Venditore getVenditore() {
        return venditore;
    }

    public void setVenditore(Venditore venditore) {
        this.venditore = venditore;
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", dataAcquisto=" + dataAcquisto +
                ", venditore=" + venditore +
                ", corsa=" + corsa;
    }
}
