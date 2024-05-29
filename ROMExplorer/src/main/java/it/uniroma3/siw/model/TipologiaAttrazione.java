package it.uniroma3.siw.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipologiaAttrazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@OneToMany(mappedBy = "tipo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Attrazione> attrazioni;
 

	public TipologiaAttrazione() {}

	public TipologiaAttrazione(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Attrazione> getAttrazioni() {
		return attrazioni;
	}

	public void setAttrazioni(Set<Attrazione> attrazioni) {
		this.attrazioni = attrazioni;
	}
}
