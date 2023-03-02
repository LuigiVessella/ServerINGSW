package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Admin;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.AdminRepository;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;

import jakarta.websocket.OnError;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final CameriereRepository cameriereRepository;

    public AdminController(AdminRepository adminRepository, CameriereRepository cameriereRepository) {
        this.adminRepository = adminRepository;
        this.cameriereRepository = cameriereRepository;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Admin> getAllProducts() {
        return adminRepository.findAll();
     }

     
    @PostMapping("/addNew")
    public @ResponseBody String addNewAdmin(@RequestParam String codiceFiscale, @RequestParam String partitaIva, @RequestParam String nome, @RequestParam String cognome, @RequestParam String email, @RequestParam String hashedPassword) {
        Admin admin = new Admin();
        admin.setCodiceFiscale(codiceFiscale);
        admin.setPartita_iva(partitaIva);
        admin.setNome(nome);
        admin.setCognome(cognome);
        admin.setEmail(email);
        admin.setHashedPassword(hashedPassword);
        try {
            adminRepository.save(admin);
        } catch(IllegalArgumentException e) {
            return "errorr";
        }
        return "Succefully saved"; 
    }

    @PostMapping("/login") 
    public @ResponseBody String adminLogin(@RequestParam String email, @RequestParam String hashedPassword){
        Admin admin = new Admin();
        Cameriere cameriere = new Cameriere();
        admin = adminRepository.findByEmailAddress(email);
        if(admin == null) {
            cameriere = cameriereRepository.findByEmailAddress(email);
            if(cameriere != null) {
                if(cameriere.getHashedPassword().equals(hashedPassword))
                    return "cameriere";
            }
        }
        else {
            if(admin.getHashedPassword().equals(hashedPassword)){
                return "admin";
            }
            else return "password errata";
        }
        
        return "utente non esiste";
    }

    @PostMapping("/getRistoranti")
    public @ResponseBody Iterable<Ristorante> getRistorantiProprietario(@RequestParam String email) {

        Admin admin = new Admin();
        admin = adminRepository.findByEmailAddress(email);
        return admin.getRistoranti();

    }

    @PostMapping("/setNewNome")
    public @ResponseStatus int setNewNome(@RequestParam String email, @RequestParam String nome){
        Admin admin = new Admin();
        admin = adminRepository.findByEmailAddress(email);
        admin.setNome(nome);
        adminRepository.save(admin);
        return 200;
    }

    
    @OnError 
    public @ResponseBody String onError(){
        return "errore";
    }

    
}
