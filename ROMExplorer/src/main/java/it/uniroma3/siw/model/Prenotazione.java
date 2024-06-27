package it.uniroma3.siw.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@ManyToOne 
	@JoinColumn(name="attrazione")
    private Attrazione attrazione;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	private User user;
	
	@ManyToOne
	@JoinColumn(name="guida")
	private GuidaTuristica guida;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String codiceFiscale;
	private LocalDate dataPrenotazione;
	private String linguaDesiderata;
	private String richiesteParticolari;


	// Costruttore
	public Prenotazione() {}

	public Prenotazione(String nome, String cognome, LocalDate dataNascita, String codiceFiscale,
			LocalDate dataPrenotazione, String linguaDesiderata, String richiesteParticolari) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.dataPrenotazione = dataPrenotazione;
		this.linguaDesiderata = linguaDesiderata;
		this.richiesteParticolari = richiesteParticolari;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public String getLinguaDesiderata() {
		return linguaDesiderata;
	}

	public void setLinguaDesiderata(String linguaDesiderata) {
		this.linguaDesiderata = linguaDesiderata;
	}

	public String getRichiesteParticolari() {
		return richiesteParticolari;
	}

	public void setRichiesteParticolari(String richiesteParticolari) {
		this.richiesteParticolari = richiesteParticolari;
	}

	public Attrazione getAttrazione() {
		return attrazione;
	}

	public void setAttrazione(Attrazione attrazione) {
		this.attrazione = attrazione;
	}

	public GuidaTuristica getGuida() {
		return guida;
	}

	public void setGuida(GuidaTuristica guide) {
		this.guida = guide;
	}


    
}

