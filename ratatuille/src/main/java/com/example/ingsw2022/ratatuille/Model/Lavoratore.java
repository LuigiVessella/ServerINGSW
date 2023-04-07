package com.example.ingsw2022.ratatuille.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Lavoratore {
    @Id
    private String codiceFiscale; 
    
    private String nome;
    private String cognome;

    @Column(nullable = false, unique = true) 
    private String email;

    private String hashedPassword;
    private String firstPassword; 
    private String ruolo; //che può essere, admin, cameriere, addettocucina




    public Lavoratore() {
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
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


    public String getFirstPassword() {
        return this.firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getRuolo() {
        return this.ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }


}
