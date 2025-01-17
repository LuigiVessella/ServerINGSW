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
import com.example.ingsw2022.ratatuille.Model.Ordine;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.OrdineRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/camerieri")
public class CameriereController {
    
    private final CameriereRepository cameriereRepository;
    private final RistoranteRepository ristoranteRepository;
    private final OrdineRepository ordineRepository;


    public CameriereController(CameriereRepository cameriereRepository, RistoranteRepository ristoranteRepository, OrdineRepository ordineRepository) {
        this.cameriereRepository = cameriereRepository;
        this.ristoranteRepository = ristoranteRepository;
        this.ordineRepository = ordineRepository;
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

    @PostMapping("/changePassword/{id}")
    public @ResponseBody String changePassword(@PathVariable String id, @RequestParam String passNew){
        Optional<Cameriere> adminOpt = cameriereRepository.findById(id);
        Cameriere admin = adminOpt.get();
        if(admin != null) {
            admin.setHashedPassword(passNew);
            cameriereRepository.save(admin);
            return "new_pass_saved";
        }
       else return "error";
    }


    @PostMapping("/deleteCameriere")
    @Transactional
    public @ResponseBody String deleteMenu(@RequestParam String cameriere_id) {
        Cameriere cameriere =  cameriereRepository.findById(cameriere_id).get();
        cameriere.setRistorante(null);
        String cod_fisc = cameriere.getCodiceFiscale();
        
        for(Ordine p : cameriere.getOrdini()) {    
            p.setCameriere(null);
            ordineRepository.delete(p);
        }
        
        try{
            cameriereRepository.delete(cameriere);
        }
        catch(IllegalArgumentException e) {
            return "cameriere_not_delete";
        }
        
        return "cameriere_deleted"+cod_fisc;
    }
    
}
