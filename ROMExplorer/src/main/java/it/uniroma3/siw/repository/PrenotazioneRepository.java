package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{
	
	 public List<Prenotazione> findByUser(User user);
	 
	 @Query("SELECT p FROM Prenotazione p")
	 public Iterable<Prenotazione> findAll();
	

}
