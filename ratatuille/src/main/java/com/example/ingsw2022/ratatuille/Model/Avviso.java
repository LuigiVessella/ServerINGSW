package com.example.ingsw2022.ratatuille.Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
    private LocalDate dataEmissione;

    private LocalTime oraEmissione; 

    @Column(columnDefinition="TEXT", length = 2048)
    private String descrizione;

    private boolean letto;

    private Integer lettoCounter;

    @ManyToOne
    @JoinColumn(name = "codice_ristorante")
    @JsonIgnore
    private Ristorante ristorante; 

    public Long getId_avviso() {
        return this.id_avviso;
    }

    public void setId_avviso(Long id_avviso) {
        this.id_avviso = id_avviso;
    }

    public Ristorante getRistorante() {
        return this.ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }
    
   
    public Avviso(){
        letto = false;
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

    
    public LocalDate getDataEmissione() {
        return this.dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }


    public LocalTime getOraEmissione() {
        return this.oraEmissione;
    }

    public void setOraEmissione(LocalTime oraEmissione) {
        this.oraEmissione = oraEmissione;
    }

}
