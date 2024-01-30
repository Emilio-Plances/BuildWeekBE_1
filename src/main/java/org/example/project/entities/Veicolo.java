package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.StatoVeicolo;
import org.example.project.enums.TipoVeicolo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="veicoli")
public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_posti")
    private int numeroPosti;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_veicolo")
    private StatoVeicolo statoVeicolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veicolo")
    private TipoVeicolo tipoVeicolo;

    @OneToMany(mappedBy = "veicoloM", cascade = CascadeType.ALL)
    private List<Manutenzione> manutenzioni;

    @OneToMany(mappedBy = "veicolo", cascade = CascadeType.ALL)
    private List<Corsa> listaCorse;

    public Veicolo(int id, int numeroPosti, StatoVeicolo stato, TipoVeicolo tipoVeicolo) {
        this.id = id;
        this.numeroPosti = numeroPosti;
        this.statoVeicolo = StatoVeicolo.IN_SERVIZIO;
        this.tipoVeicolo = tipoVeicolo;
        this.manutenzioni = new ArrayList<>();
        this.listaCorse = new ArrayList<>();
    }
        public void setStateVeicolo(StatoVeicolo state){
            this.statoVeicolo = state;
        }

        public StatoVeicolo getStateVeicolo(){
            return statoVeicolo;
        }

        public void addManutenzione(Manutenzione manutenzione){
            manutenzioni.add(manutenzione);
        }

        public void removeManutenzione(Manutenzione manutenzione){
            manutenzioni.remove(manutenzione);
        }

        public void addCorsa(Corsa corsa){
            listaCorse.add(corsa);
        }

        public void removeCorsa(Corsa corsa){
            listaCorse.remove(corsa);
        }

        public int getNumeroPosti() {
            return numeroPosti;
        }

        public TipoVeicolo getTipoVeicolo() {
            return tipoVeicolo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public StatoVeicolo getStatoVeicolo() {
            return statoVeicolo;
        }

        public void setStatoVeicolo(StatoVeicolo statoVeicolo) {
            this.statoVeicolo = statoVeicolo;
        }

    @Override
    public String toString() {
        return "Veicolo{" +
                "id=" + id +
                ", numeroPosti=" + numeroPosti +
                ", statoVeicolo=" + statoVeicolo +
                ", tipoVeicolo=" + tipoVeicolo +
                '}';
    }
}
