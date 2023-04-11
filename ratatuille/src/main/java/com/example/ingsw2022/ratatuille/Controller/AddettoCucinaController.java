package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.AddettoCucinaRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

@RestController
@RequestMapping("/addettocucina/")
public class AddettoCucinaController {


    private final AddettoCucinaRepository addettoCucinaRepository;
    private final RistoranteRepository ristoranteRepository;


    public AddettoCucinaController(AddettoCucinaRepository addettoCucinaRepository, RistoranteRepository ristoranteRepository) {
        this.addettoCucinaRepository = addettoCucinaRepository;
        this.ristoranteRepository = ristoranteRepository;
    }



    @GetMapping("/getRistorante/{id}")
    public @ResponseBody Ristorante getRistoranteCameriere(@PathVariable String id) {
        return addettoCucinaRepository.findById(id).get().getRistorante();
    }
    
}
