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

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.TipologiaAttrazione;
import it.uniroma3.siw.repository.AttrazioneRepository;
import it.uniroma3.siw.service.AttrazioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.TipologiaAttrazioneService;

@Controller
public class AttrazioneController {
	@Autowired 
	private AttrazioneRepository attrazioneRepository;
	
	@Autowired 
	private AttrazioneService attrazioneService;
	
	@Autowired
	private  TipologiaAttrazioneService tipologiaAttrazioneService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping("/attrazioni")
	public String getAttrazioni(Model model) {		
		model.addAttribute("attrazioni", this.attrazioneRepository.findAll());
		List<Attrazione> monumenti = attrazioneService.findAttrazioneByTipo("monumento");
		List<Attrazione> chiese = attrazioneService.findAttrazioneByTipo("chiesa");
		List<Attrazione> musei = attrazioneService.findAttrazioneByTipo("museo");
	    model.addAttribute("monumenti", monumenti);
	    model.addAttribute("chiese", chiese);
	    model.addAttribute("musei", musei);
		return "attrazioni.html";
	}
	
	@GetMapping("/attrazione/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attrazione", this.attrazioneRepository.findById(id).get());

		return "attrazione.html";
	} 
	
	
	/*PER ADMIN*/
	
	@GetMapping("/attrazioniAdmin")
	public String getAttrazioniAdmin(Model model) {		
		model.addAttribute("attrazioni", this.attrazioneRepository.findAll());
		List<Attrazione> monumenti = attrazioneService.findAttrazioneByTipo("monumento");
		List<Attrazione> chiese = attrazioneService.findAttrazioneByTipo("chiesa");
		List<Attrazione> musei = attrazioneService.findAttrazioneByTipo("museo");
	    model.addAttribute("monumenti", monumenti);
	    model.addAttribute("chiese", chiese);
	    model.addAttribute("musei", musei);
		return "/admin/attrazioni.html";
	}	
    
 
	
	@GetMapping("/addAttrazione")
	public String mostraFormAggiunta(Model model) {

		Attrazione attrazione = new Attrazione();

		 Iterable<TipologiaAttrazione> tipologie = this.tipologiaAttrazioneService.findAll();
	        model.addAttribute("attrazione", attrazione);
	        model.addAttribute("tipologie", tipologie);

		return "/admin/addAttrazione";
	}

	@PostMapping("/adminAddAttrazione")
    public String addAttrazione(@ModelAttribute("attrazione") Attrazione attrazione, Model model) {
        
        
        this.attrazioneService.save(attrazione);
		//model.addAttribute("attrazione", attrazione);

        // Logica per salvare l'attrazione
        return "redirect:/attrazioni"; // Redirect o nome della vista dopo il salvataggio
    }
	

}
