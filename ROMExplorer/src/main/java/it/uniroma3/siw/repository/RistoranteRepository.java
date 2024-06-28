package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ristorante;

public interface RistoranteRepository extends CrudRepository<Ristorante, Long> {
    List<Ristorante> findByQuartiere(String quartiere);

	

}
