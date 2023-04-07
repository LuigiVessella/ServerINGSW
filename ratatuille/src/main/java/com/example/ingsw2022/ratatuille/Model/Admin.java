package com.example.ingsw2022.ratatuille.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Admin extends Lavoratore{

    @Column(nullable = false, unique = true) 
    private String partita_iva;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ristorante> ristoranti;

    public Admin() {
        this.setRuolo("amministratore");
    }

    public List<Ristorante> getRistoranti() {
        return this.ristoranti;
    }

    public void setRistoranti(List<Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }
    

    public String getPartita_iva() {
        return this.partita_iva;
    }

    public void setPartita_iva(String partita_iva) {
        this.partita_iva = partita_iva;
    }
    
}
