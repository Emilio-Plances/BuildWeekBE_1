package org.example.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.project.enums.StatoVeicolo;
import org.example.project.enums.TipoVeicolo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veicoli")
public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_posti")
    private int numeroPosti;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "stato_veicolo")
    private StatoVeicolo statoVeicolo;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "tipo_veicolo")
    private TipoVeicolo tipoVeicolo;

    @OneToMany(mappedBy = "veicoloM", cascade = CascadeType.ALL)
    private List<Manutenzione> manutenzioni;

    @OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
    private List<Corsa> listaCorse;

    public Veicolo() {}

    public Veicolo(TipoVeicolo tipoVeicolo) {
        this.numeroPosti = setNumeroPosti();
        this.statoVeicolo = StatoVeicolo.IN_SERVIZIO;
        this.tipoVeicolo = tipoVeicolo;
    }

    public int setNumeroPosti(){
        if (this.tipoVeicolo.equals(TipoVeicolo.TRAM)){
            return 50;
        }else {
            return 100;
        }
    }
    public int getId() {
        return id;
    }
    public int getNumeroPosti() {
        return numeroPosti;
    }
    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }
    public StatoVeicolo getStatoVeicolo() {
        return statoVeicolo;
    }
    public void setStatoVeicolo(StatoVeicolo statoVeicolo) {
        this.statoVeicolo = statoVeicolo;
    }
    public TipoVeicolo getTipoVeicolo() {
        return tipoVeicolo;
    }
    public void setTipoVeicolo(TipoVeicolo tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
        setNumeroPosti();
    }
    @Override
    public String toString() {
        return  "id=" + id +
                ", numeroPosti=" + numeroPosti +
                ", statoVeicolo=" + statoVeicolo +
                ", tipoVeicolo=" + tipoVeicolo;
    }
}
