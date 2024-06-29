package it.uniroma3.siw.model;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Attrazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 
    private String nome;
    
    @Column(length = 1000)
    private String descrizione;
    private LocalTime apertura;
    private LocalTime chiusura;
    
	private float prezzo;
   // private String urlimage;
    
    private String quartiere;
    
    @OneToOne
	private Image image;

    
    @ManyToOne
    @JoinColumn(name = "tipo")
    private TipologiaAttrazione tipo;
    
    @OneToMany (mappedBy = "attrazione")
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalTime getApertura() {
		return apertura;
	}
	public void setApertura(LocalTime apertura) {
		this.apertura = apertura;
	}
    public LocalTime getChiusura() {
		return chiusura;
	}
	public void setChiusura(LocalTime chiusura) {
		this.chiusura = chiusura;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
//	public String getUrlImage() {
//		return urlimage;
//	}
//	public void setUrlImage(String urlImage) {
//		this.urlimage = urlImage;
//	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	public String getQuartiere() {
		return quartiere;
	}
	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}
	
	
	public TipologiaAttrazione getTipo() {
		return tipo;
	}
	public void setTipo(TipologiaAttrazione tipo) {
		this.tipo = tipo;
	}
	
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attrazione other = (Attrazione) obj;
		return Objects.equals(id, other.id);
	}
    
    

}
