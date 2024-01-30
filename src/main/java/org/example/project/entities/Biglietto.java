package org.example.project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends ProdottoAcquistato {

    private boolean timbrato = false;
    public Biglietto() {}
    public Biglietto(Venditore venditore, Corsa corsa) {
        super(venditore, corsa);
    }

    public boolean isTimbrato() {
        return timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", timbrato=" + timbrato;
    }
}
