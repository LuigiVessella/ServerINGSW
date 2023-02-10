package com.example.ingsw2022.ratatuille.Model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cameriere extends Lavoratore {

    @ManyToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante; 

    //private int numeroOrdini;

    @OneToMany(mappedBy = "cameriere",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordine> ordini;

    public Cameriere() {
    }

    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

}
