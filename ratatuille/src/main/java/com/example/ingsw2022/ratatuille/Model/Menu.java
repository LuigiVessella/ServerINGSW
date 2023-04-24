package com.example.ingsw2022.ratatuille.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_menu;

    @OneToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante; 

    //Menu ha una lista di piatti
    @OneToMany(mappedBy = "menu",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piatto> portate;

    private String nome;

    private String tipo; 
    private String lingua;  


    public Long getId_menu() {
        return this.id_menu;
    }

    public void setId_menu(Long id_menu) {
        this.id_menu = id_menu;
    }

    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    public List<Piatto> getPortate() {
        return this.portate;
    }

    public void setPortate(List<Piatto> portate) {
        this.portate = portate;
    }
    

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLingua() {
        return this.lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

}
