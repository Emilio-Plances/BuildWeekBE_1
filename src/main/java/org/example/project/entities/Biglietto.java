package org.example.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="biglietti")
public class Biglietto extends ProdottoAcquistato{

    @Column (name = "ora_partenza")
    private LocalDate oraPartenza;

    public Biglietto(LocalDate oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Biglietto() {
    }

    public LocalDate getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(LocalDate oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "tratta=" + getTratta() +
                "dataAcquisto=" + getDataAcquisto() +
                "oraPartenza=" + oraPartenza +
                '}';
    }
}
