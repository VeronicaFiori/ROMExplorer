package it.uniroma3.siw.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.TipologiaAttrazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AttrazioneRepository;
import it.uniroma3.siw.repository.ImageRepository;
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
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping("/attrazioni")
	public String getAttrazioni(Model model) {		
		model.addAttribute("attrazioni", this.attrazioneRepository.findAll());
		List<Attrazione> monumenti = attrazioneService.findAttrazioneByTipo("monumento");
		List<Attrazione> chiese = attrazioneService.findAttrazioneByTipo("chiesa");
		List<Attrazione> musei = attrazioneService.findAttrazioneByTipo("museo");
	    model.addAttribute("monumenti", monumenti);
	    model.addAttribute("chiese", chiese);
	    model.addAttribute("musei", musei);
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "/admin/attrazioni.html";

		}
		
		return "attrazioni.html";
	}
	 

	
	
	@GetMapping("/attrazione/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attrazione", this.attrazioneRepository.findById(id).get());
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		model.addAttribute("credentials", credentials);

		return "attrazione.html";
	} 
	

    
 
	
	@GetMapping("/addAttrazione")
	public String mostraFormAggiunta(Model model) {
		
		Attrazione attrazione = new Attrazione();

		Iterable<TipologiaAttrazione> tipologie = this.tipologiaAttrazioneService.findAll();
		model.addAttribute("attrazione", attrazione);
		model.addAttribute("tipologie", tipologie);
//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		model.addAttribute("userDetails", userDetails);

		return "/admin/addAttrazione";
	}

	@PostMapping("/adminAddAttrazione")
    public String addAttrazione(@ModelAttribute("attrazione") Attrazione attrazione, 
    		                    Model model,
    		                    @RequestParam("file") MultipartFile image) throws IOException {
        
		Image img = new Image(image.getBytes());
        this.imageRepository.save(img);
        attrazione.setImage(img);
        this.attrazioneService.save(attrazione);
		
        return "redirect:/attrazioni"; // Redirect o nome della vista dopo il salvataggio
    }
	

}
