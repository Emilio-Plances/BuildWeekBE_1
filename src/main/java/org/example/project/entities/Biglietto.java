package org.example.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "biglietti")
public class Biglietto extends ProdottoAcquistato {
    @Column(nullable = false)
    private boolean timbrato = false;
    public Biglietto() {}
    public Biglietto(Venditore venditore, Corsa corsa) {
        super(venditore, corsa);
    }

    @ManyToOne
    @JoinColumn(name="corsa_fk",nullable = false)
    private Corsa corsa;

    public boolean isTimbrato() {
        return timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", timbrato=" + timbrato;
    }
}
