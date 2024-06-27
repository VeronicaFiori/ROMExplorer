package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Ristorante;
import it.uniroma3.siw.repository.RistoranteRepository;
import it.uniroma3.siw.service.RistoranteService;

@Controller
public class RistoranteController {
	@Autowired 
	private RistoranteRepository ristoranteRepository;
	
	@Autowired 
	private RistoranteService ristoranteService;
	
	@GetMapping("/ristoranti")
	public String getRistoranti(Model model) {		
		model.addAttribute("ristoranti", this.ristoranteRepository.findAll());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		return "ristoranti.html";
	}
	
	@GetMapping("/ristorante/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ristorante", this.ristoranteRepository.findById(id).get());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userDetails", userDetails);
		return "ristorante.html";
	} 
	
	
	

}
