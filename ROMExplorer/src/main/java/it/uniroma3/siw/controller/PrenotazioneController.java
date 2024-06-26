package it.uniroma3.siw.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.model.GuidaTuristica;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AttrazioneRepository;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.GuidaTuristicaService;
import it.uniroma3.siw.service.PrenotazioneService;

@Controller
public class PrenotazioneController {
	@Autowired
    private AttrazioneRepository attrazioneRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private GuidaTuristicaService guidaTuristicaService;

    
    @Autowired
    private UserRepository userRepository;
    
    
    @GetMapping("/prenotaLingua/{id}")
    public String mostraFormPrenotazione(@PathVariable("id") Long id, Model model) {
        Attrazione attrazione = attrazioneRepository.findById(id).get();
        model.addAttribute("attrazione", attrazione);
        return "prenotazioneLingua";
    }

    
    @PostMapping("/prenota/{id}/selezionaLingua")
    public String processaFormPrenotazioneLingua(@PathVariable("id") Long id,
                                           @RequestParam String linguaDesiderata,
                                           Model model) {
    	
        model.addAttribute("linguaDesiderata", linguaDesiderata);

        return "prenotazione";
    	
    }
    
    
    
    
    
    
    @PostMapping("/prenota/{id}/selezionaGuida")
    public String processaFormPrenotazione(@PathVariable("id") Long id,
                                           @RequestParam String linguaDesiderata,
                                           Model model) {
        Attrazione attrazione = attrazioneRepository.findById(id).get();
        model.addAttribute("attrazione", attrazione);
        model.addAttribute("linguaDesiderata", linguaDesiderata);

        List<GuidaTuristica> guide = guidaTuristicaService.getGuidaByLingua(linguaDesiderata);
        
        model.addAttribute("guide", guide);

        
        return "guideTuristiche";
    }

    @PostMapping("/confermaPrenotazione")
    public String confermaPrenotazione(@RequestParam Long attrazioneId,
                                       @RequestParam Long guidaId,
                                       @RequestParam String nome,
                                       @RequestParam String cognome,
                                       @RequestParam String linguaDesiderata,
                                       @RequestParam String richiesteParticolari,
                                       @RequestParam LocalDate dataPrenotazione,
                                   
                                       Model model) {
        Attrazione attrazione = attrazioneRepository.findById(attrazioneId).get();
        GuidaTuristica guida = guidaTuristicaService.findGuidaById(guidaId);

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAttrazione(attrazione);
        prenotazione.setGuida(guida);
        prenotazione.setNome(nome);
        prenotazione.setCognome(cognome);
        prenotazione.setLinguaDesiderata(linguaDesiderata);
        prenotazione.setRichiesteParticolari(richiesteParticolari);
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazioneService.savePrenotazione(prenotazione);

        model.addAttribute("prenotazione", prenotazione);
        return "riepilogo";
    }
    
  //  @GetMapping("/carrello")
  //  public String carrello(@AuthenticationPrincipal UserDetails userDetails, Model model) {
       // User user = 
        //= prenotazioneService.findPrenotazioniByUser(user);
        //model.addAttribute("prenotazioni", prenotazioni);
       // return "carrello";
   // }
    
}

