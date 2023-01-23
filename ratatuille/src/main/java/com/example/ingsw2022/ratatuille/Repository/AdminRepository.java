package com.example.ingsw2022.ratatuille.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ingsw2022.ratatuille.Model.Admin;

public interface AdminRepository extends JpaRepository <Admin, String> {
    
}
