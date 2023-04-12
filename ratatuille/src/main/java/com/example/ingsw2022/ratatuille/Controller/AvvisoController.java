package com.example.ingsw2022.ratatuille.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ingsw2022.ratatuille.Model.Avviso;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.AvvisoRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;

@RestController
@RequestMapping("/avviso")
public class AvvisoController {

    private final RistoranteRepository ristoranteRepository;
    private final AvvisoRepository avvisoRepository;


    public AvvisoController(RistoranteRepository ristoranteRepository, AvvisoRepository avvisoRepository) {
        this.ristoranteRepository = ristoranteRepository;
        this.avvisoRepository = avvisoRepository;
    }


    @PostMapping("/addNew/{ristorante_id}")
    public @ResponseBody Ristorante addNewAvviso(@PathVariable String ristorante_id, @RequestParam String descrizione){
        Ristorante getRistorante = ristoranteRepository.findById(Long.parseLong(ristorante_id)).get();
        Avviso avviso = new Avviso();
        avviso.setDescrizione(descrizione);
        avviso.setRistorante(getRistorante);
        avviso.setDataEmissione(LocalDate.now());
        avviso.setOraEmissione(LocalTime.now());
        avvisoRepository.save(avviso);
        return getRistorante;
    } 


    @GetMapping("/getAvvisi/{ristorante_id}")
    public @ResponseBody List<Avviso> getAvvisi(@PathVariable Long ristorante_id) {
        Ristorante ristorante = ristoranteRepository.findById(ristorante_id).get();
        return ristorante.getAvvisi();
    }

}
