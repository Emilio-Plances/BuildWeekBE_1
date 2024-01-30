package org.example.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.example.project.enums.StatoDistributore;

@Entity
@Table(name="distributori_automatici")
public class DistributoreAutomatico extends Venditore{
    @Enumerated(EnumType.STRING)
    private StatoDistributore stato;

    public DistributoreAutomatico(){}

    public DistributoreAutomatico(String name, StatoDistributore stato) {
        super(name);
        this.stato = stato;
    }

    public StatoDistributore getStato() {
        return stato;
    }

    public void setStato(StatoDistributore stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", stato=" + stato;
    }
}
