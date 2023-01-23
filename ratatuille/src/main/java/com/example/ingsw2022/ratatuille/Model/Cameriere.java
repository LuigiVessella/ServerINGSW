package com.example.ingsw2022.ratatuille.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cameriere {

    @Id
    private String codice_fiscale;

    private String nome;

    private String cognome;

    private String email;


    @ManyToOne
    @JoinColumn(name = "codice_ristorante")
    private Ristorante ristorante;

    public Cameriere(){}
    public Cameriere(String codice_fiscale, String nome, String cognome, String email, Ristorante ristorante) {
        this.codice_fiscale = codice_fiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.ristorante = ristorante;
    }


    public String getCodice_fiscale() {
        return this.codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }



}
