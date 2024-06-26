package it.uniroma3.siw.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class GuidaTuristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    private String lingua;
    
    @OneToMany(mappedBy = "guida")
    private List<Prenotazione> prenotazioni;

  
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public GuidaTuristica() {}

    public GuidaTuristica(String nome, String cognome, String lingua) {
        this.nome = nome;
        this.cognome = cognome;
        this.lingua = lingua;
    }

    // Altri metodi necessari
}

