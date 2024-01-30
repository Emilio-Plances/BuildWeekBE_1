package org.example.project.entities;
import jakarta.persistence.*;
import org.example.project.enums.TipoAbbonamento;
import java.time.LocalDate;

@Entity
@Table(name="abbonamenti")
@NamedQuery(name="checkValidita",query = "SELECT a FROM Abbonamento a WHERE a.id=:id ")
@NamedQuery(name="abbonamentiScaduti",query = "SELECT a FROM Abbonamento a WHERE a.fineValidita<CURRENT_DATE")
public class Abbonamento extends ProdottoAcquistato{
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_abbonamento",nullable = false)
    private TipoAbbonamento tipoAbbonamento;

    @Column(name = "fine_validita",nullable = false)
    private LocalDate fineValidita;

    @ManyToOne
    @JoinColumn(name = "tessera_cliente_fk",nullable = false)
    private TesseraCliente tesseraCliente;

    @Column(name="validita_abbonamento",nullable = false)
    private boolean validitaAbbonamento=true;

    @ManyToOne
    @JoinColumn(name="tratta_fk",nullable = false)
    private Tratta tratta;

    public Abbonamento() {}
    public Abbonamento(Venditore venditore, Tratta tratta, TipoAbbonamento tipoAbbonamento, TesseraCliente tesseraCliente) {
        super(venditore);
        this.tratta=tratta;
        this.tipoAbbonamento = tipoAbbonamento;
        this.tesseraCliente = tesseraCliente;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public TesseraCliente getTesseraCliente() {
        return tesseraCliente;
    }

    public LocalDate getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(LocalDate fineValidita) {
        this.fineValidita = fineValidita;
    }

    public boolean isValiditaAbbonamento() {
        return validitaAbbonamento;
    }

    public void setValiditaAbbonamento(boolean validitaAbbonamento) {
        this.validitaAbbonamento = validitaAbbonamento;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", tipoAbbonamento=" + tipoAbbonamento +
                ", tratta="+ tratta+
                ", fineValidita=" + fineValidita +
                ", tesseraCliente=" + tesseraCliente +
                ", validitaAbbonamento=" + validitaAbbonamento;
    }
}
