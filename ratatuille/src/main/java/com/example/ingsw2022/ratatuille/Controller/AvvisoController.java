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
    private int counterAvvisi;


    public AvvisoController(RistoranteRepository ristoranteRepository, AvvisoRepository avvisoRepository) {
        this.ristoranteRepository = ristoranteRepository;
        this.avvisoRepository = avvisoRepository;
        counterAvvisi = -1;
    }


    @PostMapping("/addNew/{ristorante_id}")
    public @ResponseBody Ristorante addNewAvviso(@PathVariable String ristorante_id, @RequestParam String descrizione, @RequestParam String emessoDa){
       
        Ristorante getRistorante = ristoranteRepository.findById(Long.parseLong(ristorante_id)).get();
        Avviso avviso = new Avviso();
        avviso.setDescrizione(descrizione);
        avviso.setRistorante(getRistorante);
        avviso.setEmessoDa(emessoDa);
        avviso.setLettoDa("");
        avviso.setDataEmissione(LocalDate.now());
        avviso.setOraEmissione(LocalTime.now());
        avvisoRepository.save(avviso);
        return getRistorante;
    } 


    @GetMapping("/getAvvisi/{ristorante_id}")
    public @ResponseBody String getAvvisi(@PathVariable Long ristorante_id) {
        Ristorante ristorante = ristoranteRepository.findById(ristorante_id).get();
        if(ristorante.getAvvisi().size() > counterAvvisi) {
            System.out.println("ci sono nuovi avvisi\n");
            counterAvvisi = ristorante.getAvvisi().size();
            return "new_alerts";
        }
        else {
            
            return "no_new_alerts";
        }
        
    }

    @GetMapping("/getAvvisiList/{ristorante_id}")
    public @ResponseBody List<Avviso> getAvvisiList(@PathVariable Long ristorante_id) {
        Ristorante ristorante = ristoranteRepository.findById(ristorante_id).get();
        return ristorante.getAvvisi();
    }

    @PostMapping("/setLettoDa/{avviso_id}")
    public @ResponseBody String setLettoDa(@PathVariable Long avviso_id, @RequestParam String cod_fisc) {
        Avviso avviso_to_edit = avvisoRepository.findById(avviso_id).get();
        avviso_to_edit.setLettoDa(avviso_to_edit.getLettoDa().concat(cod_fisc));
        avvisoRepository.save(avviso_to_edit);
        return "ok_letto_saved";
    }

}
