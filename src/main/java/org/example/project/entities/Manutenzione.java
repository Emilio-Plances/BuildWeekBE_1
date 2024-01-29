package org.example.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name="manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "veicolo_fk")
    private Veicolo veicoloM;

    @Column(name = "data_inizio")
    private String dataInizio;

    @Column(name = "data_fine")
    private String dataFine;

    public Manutenzione(int id, Veicolo veicoloM, String dataInizio, String dataFine) {
        this.id = id;
        this.veicoloM = veicoloM;
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
        return veicoloM;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", veicolo=" + veicoloM +
                ", dataInizio='" + dataInizio + '\'' +
                ", dataFine='" + dataFine + '\'' +
                '}';
    }
}
