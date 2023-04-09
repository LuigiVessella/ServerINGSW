package com.example.ingsw2022.ratatuille.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.AddettoCucina;
import com.example.ingsw2022.ratatuille.Model.Admin;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Lavoratore;
import com.example.ingsw2022.ratatuille.Model.Supervisore;
import com.example.ingsw2022.ratatuille.Repository.AddettoCucinaRepository;
import com.example.ingsw2022.ratatuille.Repository.AdminRepository;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.SupervisoreRepository;

import jakarta.websocket.OnError;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final CameriereRepository cameriereRepository;
    private final SupervisoreRepository supervisoreRepository;
    private final AddettoCucinaRepository addettoCucinaRepository;


    public AdminController(AdminRepository adminRepository, CameriereRepository cameriereRepository, SupervisoreRepository supervisoreRepository, AddettoCucinaRepository addettoCucinaRepository) {
        this.adminRepository = adminRepository;
        this.cameriereRepository = cameriereRepository;
        this.supervisoreRepository = supervisoreRepository;
        this.addettoCucinaRepository = addettoCucinaRepository;
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
        Supervisore supervisore = new Supervisore();
        AddettoCucina addettoCucina = new AddettoCucina();

        admin = adminRepository.findByEmailAddress(email);
        if(admin == null) {
            cameriere = cameriereRepository.findByEmailAddress(email);
            if(cameriere != null) {
                if(cameriere.getHashedPassword().equals(hashedPassword)) return cameriere;
            }
            else {
                supervisore = supervisoreRepository.findByEmailAddress(email);
                if(supervisore != null) {
                    if(supervisore.getHashedPassword().equals(hashedPassword)) return supervisore;
                }

                else {
                    addettoCucina = addettoCucinaRepository.findByEmailAddress(email);
                    if(addettoCucina != null) {
                        if(addettoCucina.getHashedPassword().equals(hashedPassword)) return addettoCucina;
                    }
                }
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

    @PostMapping("/changeEmail/{id}")
    public @ResponseBody Admin changeEmail(@PathVariable String id, @RequestParam String emailNew){
        Optional<Admin> adminOpt = adminRepository.findById(id);
        Admin admin = adminOpt.get();
        if(admin != null) {
            admin.setEmail(emailNew);
            return adminRepository.save(admin);
        }
       else return null;
    }

    @PostMapping("/changePassword/{id}")
    public @ResponseBody Admin changePassword(@PathVariable String id, @RequestParam String passNew){
        Optional<Admin> adminOpt = adminRepository.findById(id);
        Admin admin = adminOpt.get();
        if(admin != null) {
            admin.setHashedPassword(passNew);
            return adminRepository.save(admin);
        }
       else return null;
    }
    
    @OnError 
    public @ResponseBody String onError(){
        return "errore";
    }

    
}
