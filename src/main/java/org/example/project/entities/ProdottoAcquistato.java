package org.example.project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ProdottoAcquistato {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_libreria")
    @SequenceGenerator(name="sequenza_libreria",initialValue = 1,allocationSize = 1)
    private int id;

    @Column(name="data_acquisto")
    private LocalDate dataAcquisto;

    @ManyToOne
    @JoinColumn(name="venditore_fk")
    private Venditore venditore;

    @ManyToOne
    @JoinColumn(name="tratta_fk")
    private Tratta tratta;

    public ProdottoAcquistato( LocalDate dataAcquisto, Venditore venditore, Tratta tratta) {
        this.dataAcquisto = dataAcquisto;
        this.venditore = venditore;
        this.tratta = tratta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "ProdottoAcquistato{" +
                "id=" + id +
                ", dataAcquisto=" + dataAcquisto +
                ", venditore=" + venditore +
                ", tratta=" + tratta +
                '}';
    }
}
