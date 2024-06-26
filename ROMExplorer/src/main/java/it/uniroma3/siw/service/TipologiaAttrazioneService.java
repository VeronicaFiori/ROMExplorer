package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.TipologiaAttrazione;
import it.uniroma3.siw.repository.TipologiaAttrazioneRepository;

@Service
public class TipologiaAttrazioneService {

    @Autowired
    private TipologiaAttrazioneRepository tipologiaAttrazioneRepository;

	
	public Iterable<TipologiaAttrazione> findAll() {
		return tipologiaAttrazioneRepository.findAll();
	}
}
