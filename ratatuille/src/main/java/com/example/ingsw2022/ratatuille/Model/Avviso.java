package com.example.ingsw2022.ratatuille.Model;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Avviso {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_avviso;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataEmissione;

    private Time oraEmissione; 

    private String descrizione;

    private boolean letto;

    private Integer lettoCounter;

    @ManyToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante; 
    
   
    public Avviso(){
        letto = false;
    }


    public Date getDataEmissione() {
        return this.dataEmissione;
    }

    public void setDataEmissione(Date dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Time getOraEmissione() {
        return this.oraEmissione;
    }

    public void setOraEmissione(Time oraEmissione) {
        this.oraEmissione = oraEmissione;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isLetto() {
        return this.letto;
    }

    public boolean getLetto() {
        return this.letto;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public Integer getLettoCounter() {
        return this.lettoCounter;
    }

    public void setLettoCounter(Integer lettoCounter) {
        this.lettoCounter = lettoCounter;
    }

}
