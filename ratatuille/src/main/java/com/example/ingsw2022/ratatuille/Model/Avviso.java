package com.example.ingsw2022.ratatuille.Model;

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

    @JsonFormat(pattern = "hh:mm")
    private LocalTime oraEmissione; 

    @Column(columnDefinition="TEXT", length = 2048)
    private String descrizione;


    @Column(columnDefinition="TEXT", length = 2048)
    private String lettoDa;

    private String emessoDa; //o admin o supervisore

    public String getEmessoDa() {
        return this.emessoDa;
    }

    public void setEmessoDa(String emessoDa) {
        this.emessoDa = emessoDa;
    }

    public String getLettoDa() {
        return this.lettoDa;
    }

    public void setLettoDa(String lettoDa) {
        this.lettoDa = lettoDa;
    }

    

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
        
    }



    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
