package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.Supervisore;

public interface SupervisoreRepository extends JpaRepository<Supervisore, String>{
    @Query(value = "SELECT * FROM supervisore WHERE email = ?1", nativeQuery = true)
    Supervisore findByEmailAddress(String emailAddress); 
    
}
