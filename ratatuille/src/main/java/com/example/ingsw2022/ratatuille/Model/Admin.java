package com.example.ingsw2022.ratatuille.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Admin {


    @Id 
    private String partita_iva;
    
    private String nome;
    private String cognome;
    private String email;

    @OneToMany(mappedBy = "proprietario")
    private Set<Ristorante> ristoranti;
    

    public Admin(String partita_iva, String nome, String cognome, String email, Set<Ristorante> ristoranti) {
        this.partita_iva = partita_iva;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.ristoranti = ristoranti;
    }

    public Admin() {
        
    }


    public String getPartita_iva() {
        return this.partita_iva;
    }

    public void setPartita_iva(String partita_iva) {
        this.partita_iva = partita_iva;
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

    public Set<Ristorante> getRistoranti() {
        return this.ristoranti;
    }

    public void setRistoranti(Set<Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    
    
}
