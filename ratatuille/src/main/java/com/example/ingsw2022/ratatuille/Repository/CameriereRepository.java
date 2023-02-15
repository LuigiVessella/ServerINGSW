package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.Cameriere;

public interface CameriereRepository extends JpaRepository <Cameriere, String>{
    
    @Query(value = "SELECT * FROM cameriere WHERE email = ?1", nativeQuery = true)
    Cameriere findByEmailAddress(String emailAddress); 
}
