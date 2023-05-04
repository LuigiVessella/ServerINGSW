package com.example.ingsw2022.ratatuille.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Menu;
import com.example.ingsw2022.ratatuille.Model.Piatto;
import com.example.ingsw2022.ratatuille.Repository.MenuRepository;
import com.example.ingsw2022.ratatuille.Repository.PiattoRepository;

@RestController
@RequestMapping("/piatto")
public class PiattoController {

    private final PiattoRepository piattoRepository;
    private final MenuRepository menuRepository;


    public PiattoController(PiattoRepository piattoRepository, MenuRepository menuRepository) {
        this.piattoRepository = piattoRepository;
        this.menuRepository = menuRepository;
    }


    @PostMapping("/addPiatto")
    public @ResponseBody Menu addNewPiatto(@RequestParam String nome_piatto, @RequestParam String descrizione, @RequestParam String descr_sec, @RequestParam String prezzo, @RequestParam String allergeni, @RequestParam String contiene, @RequestParam Long codice_menu, @RequestParam String tipo, @RequestParam String tipoPietanza){
        
        Optional<Menu> menuOpt = menuRepository.findById(codice_menu);
        Menu menu = menuOpt.get();

        if(menu != null) {
            Piatto newPiatto = new Piatto();
            newPiatto.setNome_piatto(nome_piatto);
            newPiatto.setDescrizione(descrizione);
            newPiatto.setAllergeni(allergeni);
            newPiatto.setContiene(contiene);
            newPiatto.setPrezzo(prezzo);
            newPiatto.setTipo(tipo);
            newPiatto.setTipoPietanza(tipoPietanza);
            newPiatto.setSecond_descr(descr_sec);
            newPiatto.setMenu(menu);
            menu.getPortate().add(newPiatto);
            
            piattoRepository.save(newPiatto);
            return menu;
        }

        else return null;
    }

    @PostMapping("/getPiattiPerMenu")
    public @ResponseBody List<Piatto> getPiatto(@RequestParam Long codice_menu){
        Optional<Menu> menuOpt = menuRepository.findById(codice_menu);
        Menu menu = menuOpt.get();

        if(menu!=null) return menu.getPortate();
         else  return null;
    }
}

