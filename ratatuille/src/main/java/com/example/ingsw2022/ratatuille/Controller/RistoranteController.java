package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.Admin;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.AdminRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

@RestController
@RequestMapping("/ristorante")
public class RistoranteController {
    private final RistoranteRepository ristoranteRepository;
    private final AdminRepository adminRepository;

    public RistoranteController(RistoranteRepository ristoranteRepository, AdminRepository adminRepository) {
        this.ristoranteRepository = ristoranteRepository;
        this.adminRepository = adminRepository;
        
    }

    @GetMapping("getAll")
    public @ResponseBody Iterable<Ristorante> getAllRistoranti() {
        return ristoranteRepository.findAll();
    }

    
    //per il momento prende l'email, poi si passa alla p.iva
    @PostMapping("/addNew")
    public @ResponseBody String addNewRistorante(@RequestParam String email, @RequestParam String nome, @RequestParam String coperti, @RequestParam String locazione) {

        Admin admin = new Admin();
        admin = adminRepository.findByEmailAddress(email);
        if(admin != null) {
            Ristorante ristorante = new Ristorante();
            ristorante.setNome(nome);
            ristorante.setCoperti(Integer.parseInt(coperti));
            ristorante.setLocazione(locazione);
            ristorante.setProprietario(admin);
            admin.getRistoranti().add(ristorante);
            ristoranteRepository.save(ristorante);
            return "Ristorante salvato correttamente";
        }
        else return "Errore nel salvataggio dei dati ristorante";
        
    }

    
}

