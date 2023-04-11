package com.example.ingsw2022.ratatuille.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.SupervisoreRepository;

@RestController
@RequestMapping("/supervisore")
public class SupervisoreController {


    private final SupervisoreRepository supervisoreRepository;
    private final CameriereRepository cameriereRepository;


    public SupervisoreController(SupervisoreRepository supervisoreRepository, CameriereRepository cameriereRepository) {
        this.supervisoreRepository = supervisoreRepository;
        this.cameriereRepository = cameriereRepository;
    }



    @GetMapping("/getRistorante/{id}")
    public @ResponseBody Ristorante getRistoranteCameriere(@PathVariable String id) {
        return supervisoreRepository.findById(id).get().getRistorante();
    }
    
}
