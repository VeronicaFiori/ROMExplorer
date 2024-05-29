package it.uniroma3.siw.repository;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Attrazione;

public interface AttrazioneRepository extends CrudRepository<Attrazione, Long> {
	
	@Query("SELECT a FROM Attrazione a JOIN a.tipo t WHERE t.nome = :nome")
    List<Attrazione> findByTipoNome(@Param("nome") String nome);

}
