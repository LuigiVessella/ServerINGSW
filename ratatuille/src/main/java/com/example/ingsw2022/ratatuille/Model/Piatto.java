package com.example.ingsw2022.ratatuille.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;

@Entity
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_piatto;

    private String nome_piatto;

    private String tipo; //primo, secondo, dessert, ecc
    private String tipoPietanza; //pesce, carne, ecc
    //quindi primo di pesce, secondo di carne, ecc

    @Column(columnDefinition="TEXT", length = 2048)
    private String descrizione;

    private String allergeni;

    @Column(columnDefinition="TEXT", length = 2048)
    private String contiene;

    private String prezzo;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    @JsonIgnore
    private Menu menu; 

 
    public Piatto() {

    }





    public String getNome_piatto() {
        return this.nome_piatto;
    }

    public void setNome_piatto(String nome_piatto) {
        this.nome_piatto = nome_piatto;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getAllergeni() {
        return this.allergeni;
    }

    public void setAllergeni(String allergeni) {
        this.allergeni = allergeni;
    }

    public String getContiene() {
        return this.contiene;
    }

    public void setContiene(String contiene) {
        this.contiene = contiene;
    }

    public Long getId_piatto() {
        return this.id_piatto;
    }

    public void setId_piatto(Long id_menu) {
        this.id_piatto = id_menu;
    }

    public String getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    
    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoPietanza() {
        return this.tipoPietanza;
    }

    public void setTipoPietanza(String tipoPietanza) {
        this.tipoPietanza = tipoPietanza;
    }





}
