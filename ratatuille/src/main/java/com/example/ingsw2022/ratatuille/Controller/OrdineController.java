package com.example.ingsw2022.ratatuille.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Ordine;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.OrdineRepository;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    private final OrdineRepository ordineRepository;
    private final CameriereRepository cameriereRepository;

    public OrdineController(OrdineRepository ordineRepository, CameriereRepository cameriereRepository) {
        this.ordineRepository = ordineRepository;
        this.cameriereRepository = cameriereRepository;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Ordine> getAll(){
        return ordineRepository.findAll();
    }

    @PostMapping("/addNew")
    public @ResponseBody String addNewOrder(@RequestParam String codiceFiscale, @RequestParam int numeroTavolo, @RequestParam int numeroPortate){
        Optional<Cameriere> cameriereOpt = cameriereRepository.findById(codiceFiscale);
        Cameriere cameriere = cameriereOpt.get();
        Ordine ordine = new Ordine();
        ordine.setCameriere(cameriere);
        ordine.setNumeroTavolo(numeroTavolo);
        ordine.setNumeroPortate(numeroPortate);
        return "ok";
    }
    
}
