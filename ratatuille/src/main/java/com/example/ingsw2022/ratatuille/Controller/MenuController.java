package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Menu;
import com.example.ingsw2022.ratatuille.Repository.MenuRepository;



@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepository;


    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }



    @PostMapping("/addMenu")
    public @ResponseBody String addNewMenu(@RequestParam String nome_piatto, @RequestParam String descrizione, @RequestParam String prezzo, @RequestParam String allergeni, @RequestParam String contiene){
        Menu menu = new Menu();
        if(menu != null){
            menu.setNome_piatto(nome_piatto);
            menu.setDescrizione(descrizione);
            menu.setAllergeni(allergeni);
            menu.setPrezzo(prezzo);
            menu.setContiene(contiene);
        }
        menuRepository.save(menu);
        return "piatto salvato";
    }


    


    
}
