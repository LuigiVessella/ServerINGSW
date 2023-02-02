package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.Admin;

public interface AdminRepository extends JpaRepository <Admin, String> {
    @Query(value = "SELECT * FROM admin WHERE email = ?1", nativeQuery = true)
    Admin findByEmailAddress(String emailAddress); 

    @Query(value = "SELECT * FROM admin WHERE partita_iva = ?1", nativeQuery = true)
    Admin findByPartitaIva(String partitaIva);


   

}
