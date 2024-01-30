package org.example.project.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsa")
@NamedQueries({
        @NamedQuery(
                name = "Corsa.trovaBigliettiTimbrati",
                query = "SELECT b FROM Corsa c JOIN c.prodottiAcquistati b WHERE TYPE(b) = Biglietto AND b.timbrato = true"
        )
})
public class Corsa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_venditore")
    @SequenceGenerator(name="sequenza_venditore",initialValue = 1,allocationSize = 1)
    private int id;
    @ManyToOne
    @JoinColumn(name = "veicolo_fk")
    private Veicolo veicolo;
    @ManyToOne
    @JoinColumn(name = "corsa_fk")
    private Tratta tratta;
    @OneToMany(mappedBy = "corsa")
    private List<ProdottoAcquistato> prodottiAcquistati=new ArrayList<>();

    @Column(name = "data_partenza")
    private LocalDateTime dataPartenza;
    @Column(name = "data_arrivo")
    private LocalDateTime dataArrivo;
    private int durata;

    public Corsa() {}

    public Corsa(Veicolo veicolo, Tratta tratta, LocalDateTime dataPartenza) {
        this.veicolo = veicolo;
        this.tratta = tratta;
        this.dataPartenza = dataPartenza;
    }
    public static List<Biglietto> getBigliettiTimbrati (Corsa corsa){
        List<Biglietto> biglietti = new ArrayList<>();
        for(ProdottoAcquistato p : corsa.getProdottiAcquistati()){
            if (p instanceof Biglietto){
                biglietti.add(((Biglietto)p));
            }
        }
        return biglietti;
    }

    public void timbraBiglietto(Biglietto biglietto){
       for(ProdottoAcquistato prodottoAcquistato : prodottiAcquistati){
           if(prodottoAcquistato instanceof Biglietto b){
               if (b.getId() == biglietto.getId()){
                   b.setTimbrato(true);
                   break;
               }
           }
       }
    }

    public int getId() {
        return id;
    }

    public List<ProdottoAcquistato> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public void addProdotto(ProdottoAcquistato prodotto){
        prodottiAcquistati.add(prodotto);
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", veicolo=" + veicolo +
                ", tratta=" + tratta +
                ", prodottiAcquistati=" + prodottiAcquistati +
                ", durata=" + durata;
    }
}
