package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public Prenotazione savePrenotazione(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> findPrenotazioniByUser(User user) {
        return prenotazioneRepository.findByUser(user);
    }
    public Iterable<Prenotazione> findAll(){
    	return this.prenotazioneRepository.findAll();
    }
    
}
