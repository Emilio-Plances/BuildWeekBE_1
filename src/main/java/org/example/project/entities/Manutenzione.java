package org.example.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name="manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "data_fine")
    private String dataFine;

    public Manutenzione(int id, Veicolo veicolo, String dataInizio, String dataFine) {
        this.id = id;
        this.veicolo = veicolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }
    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", veicolo=" + veicolo +
                ", dataInizio='" + dataInizio + '\'' +
                ", dataFine='" + dataFine + '\'' +
                '}';
    }
}
