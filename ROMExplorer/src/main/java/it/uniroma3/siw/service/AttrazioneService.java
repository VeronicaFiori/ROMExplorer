package it.uniroma3.siw.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Attrazione;
import it.uniroma3.siw.repository.AttrazioneRepository;

@Service
public class AttrazioneService {
	
	@Autowired
	private AttrazioneRepository AttrazioneRepository;

	public Attrazione findById(Long id) {
		return AttrazioneRepository.findById(id).get();
	}

	public Iterable<Attrazione> findAll() {
		return AttrazioneRepository.findAll();
	}

	public void save(Attrazione attrazione) {
		// TODO Auto-generated method stub
		AttrazioneRepository.save(attrazione);
		
	}


}
