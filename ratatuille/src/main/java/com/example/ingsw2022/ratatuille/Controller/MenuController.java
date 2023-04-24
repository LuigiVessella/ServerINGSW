package com.example.ingsw2022.ratatuille.Controller;

import java.util.Optional;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.Menu;
import com.example.ingsw2022.ratatuille.Model.Piatto;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.MenuRepository;
import com.example.ingsw2022.ratatuille.Repository.PiattoRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    private final MenuRepository menuRepository; 
    private final RistoranteRepository ristoranteRepository;
    private final PiattoRepository piattoRepository;
     

    public MenuController(MenuRepository menuRepository, RistoranteRepository ristoranteRepository, PiattoRepository piattoRepository) {
        this.menuRepository = menuRepository;
        this.ristoranteRepository = ristoranteRepository;
        this.piattoRepository = piattoRepository;
    }

    @PostMapping("/addMenu")
    public @ResponseBody Ristorante addNewMenu(@RequestParam Long codice_ristorante, @RequestParam String tipo, @RequestParam String lingua, @RequestParam String nome) {
                
        Optional<Ristorante> ristoranteOpt = ristoranteRepository.findById(codice_ristorante);
        Ristorante ristorante = ristoranteOpt.get();

        Menu menu = new Menu();
        menu.setLingua(lingua);
        menu.setRistorante(ristorante);
        menu.setPortate(null);
        menu.setTipo(tipo);
        menu.setNome(nome);
        ristorante.setMenu(menu);
        if(menuRepository.save(menu) != null)
            return ristorante;

        else return null;
    }

    @PostMapping("/deleteMenu")
    @Transactional
    public @ResponseBody String deleteMenu(@RequestParam String menu_id) {
        Menu menu =  menuRepository.findById(Long.parseLong(menu_id)).get();
        menu.getRistorante().setMenu(null);

        for(Piatto p : menu.getPortate()) {    
            p.setMenu(null);
            piattoRepository.delete(p);
        }
        
        try{
            menuRepository.delete(menu);
        }
        catch(IllegalArgumentException e) {
            return "400";
        }
        
        return "200";
    }
}
