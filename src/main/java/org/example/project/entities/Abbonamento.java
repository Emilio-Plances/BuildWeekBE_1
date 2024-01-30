package org.example.project.entities;
import jakarta.persistence.*;
import org.example.project.enums.TipoAbbonamento;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento extends ProdottoAcquistato {

@Table(name="abbonamenti")
public class Abbonamento extends ProdottoAcquistato{
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_abbonamento")
    private TipoAbbonamento tipoAbbonamento;

    @Column(name = "fine_validita")
    private LocalDate fineValidita;

    @ManyToOne
    @JoinColumn(name = "tessera_cliente_fk")
    private TesseraCliente tesseraCliente;

    @Column(name = "prezzo_abbonamento")
    private Double prezzoAbbonamento;

    public Abbonamento( LocalDate dataAcquisto, Venditore venditore, Tratta tratta) {
        super( dataAcquisto, venditore, tratta);
    }

    public Abbonamento( LocalDate dataAcquisto, Venditore venditore, Tratta tratta, TipoAbbonamento tipoAbbonamento, LocalDate fineValidita, TesseraCliente tesseraCliente, Double prezzoAbbonamento) {
        super(dataAcquisto, venditore, tratta);
        this.tipoAbbonamento = tipoAbbonamento;
        this.fineValidita = fineValidita;
        this.tesseraCliente = tesseraCliente;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public LocalDate getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(LocalDate fineValidita) {
        this.fineValidita = fineValidita;
    }

    public TesseraCliente getTesseraCliente() {
        return tesseraCliente;
    }

    public void setTesseraCliente(TesseraCliente tesseraCliente) {
        this.tesseraCliente = tesseraCliente;
    }

    public Double getPrezzoAbbonamento() {
        return prezzoAbbonamento;
    }

    public void setPrezzoAbbonamento(Double prezzoAbbonamento) {
        this.prezzoAbbonamento = prezzoAbbonamento;
    }

    // Aggiunto un metodo per calcolare il prezzo dell'abbonamento in base alla tratta
    public void calcolaPrezzoAbbonamento(Tratta tratta) {
        if (tratta != null) {
            double prezzoTratta = tratta.getPrezzoTratta();

            switch (tipoAbbonamento) {
                case ANNUALE:
                    setPrezzoAbbonamento(prezzoTratta * 365 * 0.7);
                    break;
                case MENSILE:
                    setPrezzoAbbonamento(prezzoTratta * 30* 0.8);
                    break;
                case SETTIMANALE:
                    setPrezzoAbbonamento(prezzoTratta * 7* 0.9);
                    break;

                default:
                    setPrezzoAbbonamento(prezzoTratta);
                    break;

            }
        }
    }



    @Override
    public String toString() {
        return "Abbonamento{" +
                "tratta=" + getTratta() +
                "dataAcquisto=" + getDataAcquisto() +
                "tipoAbbonamento=" + tipoAbbonamento +
                ", fineValidita=" + fineValidita +
                ", tesseraCliente=" + tesseraCliente +
                '}';
    }
}
