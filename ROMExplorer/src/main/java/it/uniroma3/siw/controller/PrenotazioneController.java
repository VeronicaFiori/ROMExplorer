package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.GuidaTuristica;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.service.AttrazioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.GuidaTuristicaService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class PrenotazioneController {
	@Autowired
	private AttrazioneService attrazioneService;

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private GuidaTuristicaService guidaTuristicaService;
	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserService userService;


	@GetMapping("/prenotaLingua/{id}")
	public String mostraFormPrenotazione(@PathVariable("id") Long id, Model model) {
		Attrazione attrazione = attrazioneService.findById(id);
		model.addAttribute("attrazione", attrazione);
		return "prenotazioneLingua";
	}


	@PostMapping("/prenota/{id}")
	public String processaFormPrenotazioneLingua(@PathVariable("id") Long id,
			@RequestParam String linguaDesiderata,
			Model model) {
		Attrazione attrazione = attrazioneService.findById(id);
		model.addAttribute("attrazione", attrazione);
		model.addAttribute("linguaDesiderata", linguaDesiderata);
		List<GuidaTuristica> guide = guidaTuristicaService.getGuidaByLingua(linguaDesiderata);
		model.addAttribute("guide", guide);
		Prenotazione prenotazione= new Prenotazione();
		model.addAttribute("prenotazione", prenotazione);


		return "prenotazione";

	}


	@PostMapping("/prenotazione/{id}")
	public String newPrenotazione(@PathVariable("id") Long id, @Valid @ModelAttribute("prenotazione") Prenotazione prenotazione, Model model) {

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Attrazione attrazione = attrazioneService.findById(id);
		model.addAttribute("attrazione", attrazione);
		User currentUser = userService.getUserByCredentials(userDetails).orElseThrow(() -> new RuntimeException("User not found"));
		prenotazione.setUser(currentUser);
		prenotazione.setAttrazione(attrazione);
		this.prenotazioneService.savePrenotazione(prenotazione); 
		
		currentUser.getPrenotazioni().add(prenotazione);
		model.addAttribute("prenotazione", prenotazione);
		return "riepilogo";

	}


	@GetMapping("/carrello")
	public String carrello( Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.getUserByCredentials(userDetails).orElseThrow(() -> new RuntimeException("User not found"));
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("userDetails", userDetails);

		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			Iterable<Prenotazione> prenotazioni=  prenotazioneService.findAll();
			model.addAttribute("prenotazioni", prenotazioni);
		}
		else {
			List<Prenotazione> prenotazioni=  prenotazioneService.findPrenotazioniByUser(currentUser);		
			model.addAttribute("prenotazioni", prenotazioni);
		}

		return "carrello";
	} 

}

