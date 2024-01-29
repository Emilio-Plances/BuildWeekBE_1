package org.example.project.entities;

import jakarta.persistence.*;
import org.example.project.enums.CategoriaCliente;
import org.example.project.enums.Genere;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tessere_clienti")
public class TesseraCliente {

    @Id
    @Column(name = "tessera_cliente")
    private int tessera_cliente;

    private String nome;
    private String cognome;
    @Column(name = "data_di_nascita")
    private LocalDate dataNascita;
    @Enumerated(EnumType.STRING)
    private Genere genere;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_cliente")
    private CategoriaCliente categoriaCliente;
    @Transient
    private List<Integer> listaTessere = new ArrayList<>();

    public TesseraCliente() {  }

    public TesseraCliente(String nome, String cognome, LocalDate dataNascita, Genere genere, CategoriaCliente categoriaCliente) throws Exception {
        this.tessera_cliente = setNumTessera();
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.genere = genere;
        this.categoriaCliente = categoriaCliente;
    }
    public int setNumTessera() throws Exception {
        Random random = new Random();
        int possibleTessera;
        final long maxSpace = 1000L;

        if (listaTessere.size() < maxSpace) {
            do {
                possibleTessera = random.nextInt(1000) + 1;
            } while (listaTessere.contains(possibleTessera));

            listaTessere.add(possibleTessera);
            return possibleTessera;
        } else {
            throw new Exception("No more space in archive");
        }
    }

    @Override
    public String toString() {
        return "TesseraCliente{" +
                "tessera_cliente=" + tessera_cliente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", genere=" + genere +
                ", categoriaCliente=" + categoriaCliente +
                '}';
    }

    public int getTessera_cliente() {
        return tessera_cliente;
    }

    public void setTessera_cliente(int tessera_cliente) {
        this.tessera_cliente = tessera_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public CategoriaCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(CategoriaCliente categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }
}
