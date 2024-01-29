package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.TipoAbbonamento;
import org.example.project.enums.TipoTratta;

import java.time.LocalDate;

@Entity
@Table(name="abbonamenti")
public class Abbonamento extends ProdottoAcquistato{


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_abbonamento")
    private TipoAbbonamento tipoAbbonamento;

    @Column(name = "fine_validita")
    private LocalDate fineValidita;

    @ManyToOne
    @JoinColumn (name="tessera_cliente_fk")
    private TesseraCliente tesseraCliente;


    @Column(name = "prezzo_abbonamento")
    private Double prezzoAbbonamento;
    public Abbonamento() {
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
