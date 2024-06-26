package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.GuidaTuristica;

public interface GuidaTuristicaRepository extends CrudRepository<GuidaTuristica, Long> {
	public List<GuidaTuristica> findByLingua(String lingua);
}