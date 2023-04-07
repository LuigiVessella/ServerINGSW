package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.AddettoCucina;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Model.Supervisore;
import com.example.ingsw2022.ratatuille.Repository.AddettoCucinaRepository;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;
import com.example.ingsw2022.ratatuille.Repository.SupervisoreRepository;

@RestController
@RequestMapping("/lavoratore")
public class LavoratoreController {

    private final CameriereRepository cameriereRepository;
    private final AddettoCucinaRepository addettoCucinaRepository;
    private final SupervisoreRepository supervisoreRepository;
    private final RistoranteRepository ristoranteRepository;


    public LavoratoreController(CameriereRepository cameriereRepository, AddettoCucinaRepository addettoCucinaRepository, SupervisoreRepository supervisoreRepository, RistoranteRepository ristoranteRepository) {
        this.cameriereRepository = cameriereRepository;
        this.addettoCucinaRepository = addettoCucinaRepository;
        this.supervisoreRepository = supervisoreRepository;
        this.ristoranteRepository = ristoranteRepository;
    }


    @PostMapping("/addNew")
    public @ResponseBody Ristorante addNuovoLavoratore(@RequestParam String codice_ristorante, @RequestParam String codiceFiscale, @RequestParam String ruolo, @RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String hashedPassword){
        Ristorante ristorante = ristoranteRepository.findById(Long.parseLong(codice_ristorante)).get();
        if(ruolo.equals("cameriere")) {
            Cameriere cameriere = new Cameriere();
            cameriere.setNome(nome);
            cameriere.setHashedPassword(hashedPassword);
            cameriere.setCodiceFiscale(codiceFiscale);
            cameriere.setCognome(cognome);
            cameriere.setEmail(email);
            cameriere.setRistorante(ristorante);
            cameriere.setRuolo(ruolo);
            cameriere.setOrdini(null);
            cameriereRepository.save(cameriere);
            return ristorante;
        }
        else if(ruolo.equals("supervisore")) {
            Supervisore supervisore = new Supervisore();
            supervisore.setNome(nome);
            supervisore.setCodiceFiscale(codiceFiscale);
            supervisore.setHashedPassword(hashedPassword);
            supervisore.setCognome(cognome);
            supervisore.setEmail(email);
            supervisore.setRistorante(ristorante);
            supervisore.setRuolo(ruolo);
            supervisore.setNumeroAvvisi(0);
            supervisoreRepository.save(supervisore);
            return ristorante;
        
        }

        else if(ruolo.equals("addetto_cucina")) {
            AddettoCucina addettoCucina = new AddettoCucina();
            addettoCucina.setNome(nome);
            addettoCucina.setHashedPassword(hashedPassword);
            addettoCucina.setCodiceFiscale(codiceFiscale);
            addettoCucina.setCognome(cognome);
            addettoCucina.setEmail(email);
            addettoCucina.setRistorante(ristorante);
            addettoCucina.setNumeroOrdiniEvasi(0);
            addettoCucina.setRuolo(ruolo);
            addettoCucinaRepository.save(addettoCucina);
            return ristorante;
        
        }
        
        
        return null;
    } 
}
