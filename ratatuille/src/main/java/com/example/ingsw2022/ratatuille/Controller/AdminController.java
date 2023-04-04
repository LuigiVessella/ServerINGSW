package com.example.ingsw2022.ratatuille.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Admin;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Lavoratore;
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

        if(adminRepository.findByEmailAddress(email) != null) return "email_used";
        if(adminRepository.findByPartitaIva(partitaIva)!= null) return "piva_used";
        if(!adminRepository.findById(codiceFiscale).isEmpty()) return "codfisc_used";
        
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
            return "error";
        }
        return "succefully_saved"; 
    }

    @PostMapping("/login") 
    public @ResponseBody Lavoratore adminLogin(@RequestParam String email, @RequestParam String hashedPassword){
        Admin admin = new Admin();
        Cameriere cameriere = new Cameriere();
        admin = adminRepository.findByEmailAddress(email);
        if(admin == null) {
            cameriere = cameriereRepository.findByEmailAddress(email);
            if(cameriere != null) {
                Ristorante ristorante = cameriere.getRistorante();
                cameriere.setRistorante(ristorante);
                System.out.println("ristorante del cameriere:" + cameriere.getRistorante().getNome());
                if(cameriere.getHashedPassword().equals(hashedPassword))
                    return cameriere;
            }
        }
        else {
            if(admin.getHashedPassword().equals(hashedPassword)){
                return admin;
            }
            else return null;
        }
        
        return null;
    }


    @PostMapping("/setNewNome")
    public @ResponseStatus int setNewNome(@RequestParam String email, @RequestParam String nome){
        Admin admin = new Admin();
        admin = adminRepository.findByEmailAddress(email);
        admin.setNome(nome);
        adminRepository.save(admin);
        return 200;
    }

    @PatchMapping("/changeEmail/{id}")
    public @ResponseBody Admin changeCredentials(@PathVariable String id, @RequestParam String emailNew){
        Optional<Admin> adminOpt = adminRepository.findById(id);
        Admin admin = adminOpt.get();
        if(admin != null) {
            admin.setEmail(emailNew);
            return adminRepository.save(admin);
        }
       else return null;
    }
    
    @OnError 
    public @ResponseBody String onError(){
        return "errore";
    }

    
}
