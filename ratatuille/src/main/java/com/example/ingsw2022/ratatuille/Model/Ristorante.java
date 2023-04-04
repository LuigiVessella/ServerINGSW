package com.example.ingsw2022.ratatuille.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


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
    @JsonIgnore
    private Admin proprietario; 

    //1 a molti
    @OneToMany(mappedBy = "ristorante",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cameriere> camerieri;

    //1 a 1 
    @OneToMany(mappedBy = "ristorante",  cascade = CascadeType.ALL, orphanRemoval = true)
    private AdettoCucina adettoCucina;



    //ristorate ha lista di menu 1 a molti
    @OneToOne(mappedBy = "ristorante",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Menu menu;

    //1 a 1
    @OneToOne(mappedBy = "ristorante", cascade=CascadeType.ALL, orphanRemoval=true)
    private Supervisore supervisore;


    public Ristorante() {
    }


    public Ristorante(Long codice_ristorante, String nome, int coperti, String locazione, Admin proprietario, List<Cameriere> camerieri) {
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

    public List<Cameriere> getDipendenti() {
        return this.camerieri;
    }

    public void setCamerieri(List<Cameriere> camerieri) {
        this.camerieri = camerieri;
    }

    public List<Cameriere> getCamerieri() {
        return this.camerieri;
    }

    public Supervisore getSupervisore() {
        return this.supervisore;
    }

    public void setSupervisore(Supervisore supervisore) {
        this.supervisore = supervisore;
    }

    
    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    public AdettoCucina getAdettoCucina() {
        return this.adettoCucina;
    }

    public void setAdettoCucina(AdettoCucina adettoCucina) {
        this.adettoCucina = adettoCucina;
    }



}
