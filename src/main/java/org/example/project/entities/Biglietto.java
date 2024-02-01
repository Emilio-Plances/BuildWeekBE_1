package org.example.project.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "biglietti")
public class Biglietto extends ProdottoAcquistato {
    @Column(nullable = false)
    private boolean timbrato = false;

    @ManyToOne
    @JoinColumn(name="corsa_fk")
    private Corsa corsa;

    public Biglietto() {}
    public Biglietto(Venditore venditore, Tratta tratta) {
        super(venditore, tratta);
    }
    public boolean isTimbrato() {
        return timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }

    public Corsa getCorsa() {
        return corsa;
    }

    public void setCorsa(Corsa corsa) {
        this.corsa = corsa;
    }

    public void timbraBiglietto(Corsa corsa){
        if (!isTimbrato()) {
            setTimbrato(true);
            setCorsa(corsa);
        }}

    @Override
    public String toString() {
        return  super.toString()+
                ", corsa="+corsa+
                ", timbrato=" + timbrato;
    }
}
