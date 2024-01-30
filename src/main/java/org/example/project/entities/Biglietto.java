package org.example.project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends ProdottoAcquistato {

    private boolean timbrato = false;

    @OneToMany()
    @JoinColumn(name = "corsa_fk")
    private Corsa corsa;

    public Biglietto(LocalDate dataAcquisto, Venditore venditore, boolean timbrato) {
        super(dataAcquisto, venditore);
        this.timbrato = timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }

    @Override
    public String toString() {
        return "corsa=" + getCorsa() +
                "dataAcquisto=" + getDataAcquisto();
    }
}
