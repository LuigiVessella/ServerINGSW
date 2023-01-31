package com.example.ingsw2022.ratatuille.Model;

import java.util.Set;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codice_ristorante;

    private String nome;

    private int coperti;

    private String locazione;

    @ManyToOne
    @JoinColumn(name = "partita_iva")
    private Admin proprietario; 
    

    @OneToMany(mappedBy = "ristorante")
    private Set<Cameriere> camerieri;


    public Ristorante() {
    }


    public Ristorante(Long codice_ristorante, String nome, int coperti, String locazione, Admin proprietario, Set<Cameriere> camerieri) {
        this.codice_ristorante = codice_ristorante;
        this.nome = nome;
        this.coperti = coperti;
        this.locazione = locazione;
        this.proprietario = proprietario;
        this.camerieri = camerieri;
    }



    public Long getCodice_ristorante() {
        return this.codice_ristorante;
    }

    public void setCodice_ristorante(Long codice_ristorante) {
        this.codice_ristorante = codice_ristorante;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCoperti() {
        return this.coperti;
    }

    public void setCoperti(int coperti) {
        this.coperti = coperti;
    }

    public String getLocazione() {
        return this.locazione;
    }

    public void setLocazione(String locazione) {
        this.locazione = locazione;
    }

    public Admin getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(Admin proprietario) {
        this.proprietario = proprietario;
    }

    public Set<Cameriere> getCamerieri() {
        return this.camerieri;
    }

    public void setCamerieri(Set<Cameriere> camerieri) {
        this.camerieri = camerieri;
    }
}
