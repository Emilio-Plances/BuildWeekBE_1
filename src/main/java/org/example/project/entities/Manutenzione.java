package org.example.project.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="manutenzioni")
@NamedQueries({
        @NamedQuery(name = "Manutenzione.cercaPerVeicoloId",
        query = "SELECT m.dataInizio, m.dataFine FROM Manutenzione m WHERE m.veicoloM.id = :idVeicolo")
})
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "veicolo_fk")
    private Veicolo veicoloM;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    public Manutenzione(int id, Veicolo veicoloM, LocalDate dataInizio, LocalDate dataFine) {
        this.id = id;
        this.veicoloM = veicoloM;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }
    public void setDataFine(LocalDate dataFine) {
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
