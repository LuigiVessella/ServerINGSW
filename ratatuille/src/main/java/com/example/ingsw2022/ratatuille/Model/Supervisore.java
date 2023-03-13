package com.example.ingsw2022.ratatuille.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Supervisore extends Lavoratore{


    private int numeroAvvisi;


    @OneToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante;


    public Supervisore() {
    }


    public int getNumeroAvvisi() {
        return this.numeroAvvisi;
    }

    public void setNumeroAvvisi(int numeroAvvisi) {
        this.numeroAvvisi = numeroAvvisi;
    }

    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    
}
