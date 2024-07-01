package it.uniroma3.siw.model;


import java.time.Month;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank
    private Integer numeroCarta;

    @NotBlank
    private Integer codiceSicurezza;
    private Month scadenza;
  


    // Getter e setter

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

    public Integer getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(Integer numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public  Month getScadenza() {
        return scadenza;
    }

    public void setScadenza(Month scadenza) {
        this.scadenza = scadenza;
    }

    public Integer getCodiceSicurezza() {
        return codiceSicurezza;
    }

    public void setCodiceSicurezza(Integer codiceSicurezza) {
        this.codiceSicurezza = codiceSicurezza;
    }

    
}

