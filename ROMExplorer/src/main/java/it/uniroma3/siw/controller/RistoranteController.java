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
import it.uniroma3.siw.model.Ristorante;
import it.uniroma3.siw.repository.AttrazioneRepository;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.RistoranteRepository;
import it.uniroma3.siw.service.AttrazioneService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.RistoranteService;

@Controller
public class RistoranteController {
	@Autowired 
	private RistoranteRepository ristoranteRepository;
	
	@Autowired 
	private RistoranteService ristoranteService;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private AttrazioneService attrazioneService;
	@Autowired
	private AttrazioneRepository attrazioneRepository;
	
	@GetMapping("/ristoranti")
	public String getRistoranti(Model model) {		
		model.addAttribute("ristoranti", this.ristoranteRepository.findAll());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);


		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "/admin/ristoranti.html";

		}
		return "ristoranti.html";
	}
	
	
	@GetMapping("/attrazioni/{id}/ristoranti")
    public String getRistorantiPerAttrazione(@PathVariable("id") Long id, Model model) {
        Attrazione attrazione = attrazioneService.findById(id);
		model.addAttribute("attrazione", attrazione);
        String quartiere = attrazione.getQuartiere();
        List<Ristorante> ristoranti = ristoranteRepository.findByQuartiere(quartiere);
        model.addAttribute("quartiere",quartiere);
        model.addAttribute("ristoranti", ristoranti);
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "/admin/ristoranti.html";
		}
        return "/ristoranti.html";
    }
	
	
	@GetMapping("/ristorante/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ristorante", this.ristoranteRepository.findById(id).get());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		return "ristorante.html";
	} 
	
	/*Admin puo agggiungere un ristorante*/
	@GetMapping("/addRistorante")
	public String mostraFormAggiunta(Model model) {

		Ristorante ristorante = new Ristorante();
		model.addAttribute("ristorante", ristorante);
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		
		List<String> quartieri = this.attrazioneRepository.findAllQuartieri();
        model.addAttribute("quartieri", quartieri);
        
        


		return "/admin/addRistorante";
	}

	@PostMapping("/adminAddRistorante")
    public String addRistorante(@ModelAttribute("ristorante") Ristorante ristorante,
    		                    @RequestParam("file") MultipartFile image) throws IOException {
        
		Image img = new Image(image.getBytes());
        this.imageRepository.save(img);
        ristorante.setImage(img);
        this.ristoranteService.save(ristorante);
		
        return "redirect:/ristoranti"; 
    }
	
	
	

}
