package com.example.ingsw2022.ratatuille.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrdine;

    private int numeroTavolo;
    private int numeroPortate;
    
    private boolean evaso;
    private String evasoDa;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private Cameriere cameriere; 

    
    public Ordine() {
    }
    

    public boolean isEvaso() {
        return this.evaso;
    }

    public boolean getEvaso() {
        return this.evaso;
    }

    public void setEvaso(boolean evaso) {
        this.evaso = evaso;
    }

    public String getEvasoDa() {
        return this.evasoDa;
    }

    public void setEvasoDa(String evasoDa) {
        this.evasoDa = evasoDa;
    }

    public Cameriere getCameriere() {
        return this.cameriere;
    }

    public void setCameriere(Cameriere cameriere) {
        this.cameriere = cameriere;
    }

    public Long getIdOrdine() {
        return this.idOrdine;
    }

    public void setIdOrdine(Long idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getNumeroTavolo() {
        return this.numeroTavolo;
    }

    public void setNumeroTavolo(int numeroTavolo) {
        this.numeroTavolo = numeroTavolo;
    }

    public int getNumeroPortate() {
        return this.numeroPortate;
    }

    public void setNumeroPortate(int numeroPortate) {
        this.numeroPortate = numeroPortate;
    }


}
