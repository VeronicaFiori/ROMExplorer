package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.GuidaTuristica;
import it.uniroma3.siw.repository.GuidaTuristicaRepository;

@Service
public class GuidaTuristicaService {
    @Autowired
    private GuidaTuristicaRepository guidaTuristicaRepository;

    public List<GuidaTuristica> getGuidaByLingua(String lingua) {
        return guidaTuristicaRepository.findByLingua(lingua);
    }

	public GuidaTuristica findGuidaById(Long id) {
		return  guidaTuristicaRepository.findById(id).get();
	}

}
