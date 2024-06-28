package it.uniroma3.siw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ristorante;
import it.uniroma3.siw.repository.RistoranteRepository;

@Service
public class RistoranteService {
	
	@Autowired
	private RistoranteRepository RistoranteRepository;

	public Ristorante findById(Long id) {
		return RistoranteRepository.findById(id).get();
	}

	public Iterable<Ristorante> findAll() {
		return RistoranteRepository.findAll();
	}

	public void save(Ristorante ristorante) {
		RistoranteRepository.save(ristorante);
		
	}

	
	
	
	

}
