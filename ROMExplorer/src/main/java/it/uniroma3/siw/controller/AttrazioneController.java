package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.repository.AttrazioneRepository;
import it.uniroma3.siw.service.AttrazioneService;

@Controller
public class AttrazioneController {
	@Autowired 
	private AttrazioneRepository attrazioneRepository;
	
	@Autowired 
	private AttrazioneService attrazioneService;
	
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
	
	
	

}
