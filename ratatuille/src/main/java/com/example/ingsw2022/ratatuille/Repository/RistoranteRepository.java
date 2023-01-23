package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public interface RistoranteRepository extends JpaRepository <Ristorante, Long>{
    
}
