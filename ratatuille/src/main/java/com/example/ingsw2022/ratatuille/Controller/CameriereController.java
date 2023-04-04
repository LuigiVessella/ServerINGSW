package com.example.ingsw2022.ratatuille.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

@RestController
@RequestMapping("/camerieri")
public class CameriereController {
    
    private final CameriereRepository cameriereRepository;
    private final RistoranteRepository ristoranteRepository;


    public CameriereController(CameriereRepository cameriereRepository, RistoranteRepository ristoranteRepository) {
        this.cameriereRepository = cameriereRepository;
        this.ristoranteRepository = ristoranteRepository;
    }

    @GetMapping("/getAll")
    public @ResponseBody List<Cameriere> getAllCamerieri() {
        return cameriereRepository.findAll();
    }


    @PostMapping("/addNew")
    public @ResponseBody Ristorante addNewCameriere(@RequestParam Long codiceRistorante, @RequestParam String codiceFiscale, @RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String hashedPassword){
       
        Ristorante ristorante = null;
        Optional<Ristorante> ristOp = ristoranteRepository.findById(codiceRistorante);
        if(ristOp != null) ristorante = ristOp.get();

        if(ristorante != null) {
            Cameriere cameriere = new Cameriere();
            cameriere.setCodiceFiscale(codiceFiscale);
            cameriere.setNome(nome);
            cameriere.setHashedPassword(hashedPassword);
            cameriere.setCognome(cognome);
            cameriere.setEmail(email);
            cameriere.setRistorante(ristorante);
            cameriere.setOrdini(null);
            cameriereRepository.save(cameriere);
            
            return ristorante;
        }

        else return null;
    }

    @GetMapping("/getRistorante/{id}")
    public @ResponseBody Ristorante getRistoranteCameriere(@PathVariable String id) {
        return cameriereRepository.findById(id).get().getRistorante();
    }
}
