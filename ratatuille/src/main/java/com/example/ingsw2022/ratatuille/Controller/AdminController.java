package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Admin;
import com.example.ingsw2022.ratatuille.Repository.AdminRepository;

import jakarta.websocket.OnError;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Admin> getAllProducts() {
        return adminRepository.findAll();
     }

     
    @PostMapping("/addNew")
    public @ResponseBody String addNewAdmin(@RequestParam String partitaIva, @RequestParam String nome, @RequestParam String cognome) {
        Admin admin = new Admin();
        admin.setPartita_iva(partitaIva);
        admin.setNome(nome);
        admin.setCognome(cognome);
        adminRepository.save(admin);
        return "Succefully saved";
        
    }

    @OnError 
    public @ResponseBody String onError(){
        return "errore";
    }

    
}
