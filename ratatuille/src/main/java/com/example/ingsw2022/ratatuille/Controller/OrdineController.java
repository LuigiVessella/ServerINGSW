package com.example.ingsw2022.ratatuille.Controller;


import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ingsw2022.ratatuille.Model.Cameriere;
import com.example.ingsw2022.ratatuille.Model.Ordine;
import com.example.ingsw2022.ratatuille.Model.Ristorante;
import com.example.ingsw2022.ratatuille.Repository.CameriereRepository;
import com.example.ingsw2022.ratatuille.Repository.OrdineRepository;
import com.example.ingsw2022.ratatuille.Repository.RistoranteRepository;


@RestController
@RequestMapping("/ordini")
public class OrdineController {

    private final OrdineRepository ordineRepository;
    private final CameriereRepository cameriereRepository;
    private final RistoranteRepository ristoranteRepository;

    public OrdineController(OrdineRepository ordineRepository, CameriereRepository cameriereRepository, RistoranteRepository ristoranteRepository) {
        this.ordineRepository = ordineRepository;
        this.cameriereRepository = cameriereRepository;
        this.ristoranteRepository = ristoranteRepository;
    }


    @GetMapping("/getAll")
    public @ResponseBody Iterable<Ordine> getAll(){
        return ordineRepository.findAll();
    }

    
    @PostMapping("/addNew/{id_cameriere}")
    public @ResponseBody Cameriere addNewOrder(@RequestBody Ordine ordine, @PathVariable String id_cameriere){
        Cameriere cameriere = cameriereRepository.findById(id_cameriere).get();
        ordine.setCameriere(cameriere);
        ordine.setDataOrdine(LocalDate.now());
        ordineRepository.save(ordine);
        System.out.println(ordine.getCameriere().getCognome());

        return cameriere;

    }

    @PostMapping("/setEvaso/{id_ordine}")
    public @ResponseBody String setOrdineEvaso(@PathVariable String id_ordine){
        Ordine ordine = ordineRepository.findById(Long.parseLong(id_ordine)).get();
        ordine.setEvaso(true);
        ordineRepository.save(ordine);
        return "done";
    }

    @PostMapping("/setSollecitato/{id_ordine}")
    public @ResponseBody String setOrdineSollecitato(@PathVariable String id_ordine){
        Ordine ordine = ordineRepository.findById(Long.parseLong(id_ordine)).get();
        ordine.setSollecitato(true);
        ordineRepository.save(ordine);
        return "done";
    }
    
    @GetMapping("/getOrdiniRistorante/{id_ristorante}")
    public @ResponseBody Ristorante getOrdiniPerRistorante(@PathVariable Long id_ristorante) {
        Ristorante ristorante = ristoranteRepository.findById(id_ristorante).get();
        return ristorante;


    }
}
