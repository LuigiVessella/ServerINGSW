package com.example.ingsw2022.ratatuille.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ingsw2022.ratatuille.Model.Menu;

public interface MenuRepository extends JpaRepository <Menu, Long> {

    @Query(value = "SELECT * FROM menu WHERE codice_ristorante = ?1", nativeQuery = true)
    List<Menu> findByRestaurantCode(Long codice_ristorante); 
    
}
