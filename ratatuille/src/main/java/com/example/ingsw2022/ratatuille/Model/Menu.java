package com.example.ingsw2022.ratatuille.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_menu;

    private String nome_piatto;

    @Column(columnDefinition="TEXT", length = 2048)
    private String descrizione;

    private String allergeni;

    @Column(columnDefinition="TEXT", length = 2048)
    private String contiene;

    private String prezzo;



    public Menu() {

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

    public Long getId_menu() {
        return this.id_menu;
    }

    public void setId_menu(Long id_menu) {
        this.id_menu = id_menu;
    }

    public String getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }


}
