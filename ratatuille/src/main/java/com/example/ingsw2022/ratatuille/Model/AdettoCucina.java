package com.example.ingsw2022.ratatuille.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;

@Entity
public class AdettoCucina extends Lavoratore {


    @OneToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante; 

    private int numeroOrdiniEvasi; 
    

    public AdettoCucina() {
    }


    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    public int getNumeroOrdiniEvasi() {
        return this.numeroOrdiniEvasi;
    }

    public void setNumeroOrdiniEvasi(int numeroOrdiniEvasi) {
        this.numeroOrdiniEvasi = numeroOrdiniEvasi;
    }
    
}
