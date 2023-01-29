package com.example.ingsw2022.ratatuille.Repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.Admin;

public interface AdminRepository extends JpaRepository <Admin, String> {
    @Query(value = "SELECT * FROM USERS WHERE email = ?1", nativeQuery = true)
    Admin findByEmailAddress(String emailAddress); 
}
