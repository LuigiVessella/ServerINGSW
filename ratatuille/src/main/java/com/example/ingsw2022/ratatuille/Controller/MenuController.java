package com.example.ingsw2022.ratatuille.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.Menu;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.MenuRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuRepository menuRepository;
    private final RistoranteRepository ristoranteRepository;


    public MenuController(MenuRepository menuRepository, RistoranteRepository ristoranteRepository) {
        this.menuRepository = menuRepository;
        this.ristoranteRepository = ristoranteRepository;
    }


    @PostMapping("/addMenu")
    public @ResponseBody String addNewMenu(@RequestParam String nome_piatto, @RequestParam String descrizione, @RequestParam String prezzo, @RequestParam String allergeni, @RequestParam String contiene, @RequestParam Long codice_ristorante, @RequestParam String tipo, @RequestParam String tipoPietanza){
        Menu menu = new Menu();
        Ristorante ristorante = new Ristorante();
        Optional<Ristorante> ristOp = ristoranteRepository.findById(codice_ristorante);
        if(ristOp != null) ristorante = ristOp.get();
        if(menu != null){
            menu.setNome_piatto(nome_piatto);
            menu.setDescrizione(descrizione);
            menu.setAllergeni(allergeni);
            menu.setPrezzo(prezzo);
            menu.setContiene(contiene);
            menu.setRistorante(ristorante);
            menu.setTipo(tipo);
            menu.setTipoPietanza(tipoPietanza);
        }
        ristorante.getMenu().add(menu);
        menuRepository.save(menu);
        return "piatto salvato";
    }

    @PostMapping("/getMenu")
    public @ResponseBody List<Menu> getMenu(@RequestParam String codice_ristorante){
        List<Menu> menus = menuRepository.findByRestaurantCode(Long.parseLong(codice_ristorante));
        if(menus != null){
            return menus;
        }
        
       else return null;
    }
    
}
