package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.repository.AttrazioneRepository;

@Controller
public class AttrazioneController {
	@Autowired 
	private AttrazioneRepository attrazioneRepository;
	
	@GetMapping("/attrazioni")
	public String getMovies(Model model) {		
		model.addAttribute("attrazioni", this.attrazioneRepository.findAll());
		return "attrazioni.html";
	}
}
