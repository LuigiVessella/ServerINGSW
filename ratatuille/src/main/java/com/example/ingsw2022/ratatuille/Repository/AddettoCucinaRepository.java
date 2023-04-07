package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.AddettoCucina;

public interface AddettoCucinaRepository extends JpaRepository<AddettoCucina, String> {

    @Query(value = "SELECT * FROM addetto_cucina WHERE email = ?1", nativeQuery = true)
    AddettoCucina findByEmailAddress(String emailAddress); 
    
}
