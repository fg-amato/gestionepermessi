package it.prova.gestionepermessi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.validation.ValidationNoPassword;
import it.prova.gestionepermessi.validation.ValidationWithPassword;

public class UtenteToShowDTO {

	private Long id;

	private String username;

	@NotBlank(message = "{nome.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	private String nome;

	@NotBlank(message = "{cognome.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	private String cognome;

	private Date dateCreated;

	private StatoUtente stato;

	private List<RuoloDTO> ruoli = new ArrayList<>();

	public UtenteToShowDTO() {
	}

	public UtenteToShowDTO(Long id, String username, String nome, String cognome, StatoUtente stato) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
	}

	public UtenteToShowDTO(Long id, String username, String nome, String cognome, StatoUtente stato, Date dateCreated) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.stato = stato;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public List<RuoloDTO> getRuoli() {
		return ruoli;
	}

	public void setRuoliIds(List<RuoloDTO> ruoli) {
		this.ruoli = ruoli;
	}

	public Utente buildUtenteModel(boolean includeRoles) {
		Utente result = new Utente(this.id, null, this.nome, this.cognome, this.dateCreated, this.stato);
		if (includeRoles && ruoli != null) {
			for (RuoloDTO ruoloDtoItem : this.ruoli) {
				result.getRuoli().add(ruoloDtoItem.buildRuoloModel());

			}
		}
		return result;
	}

	// niente password...
	public static UtenteToShowDTO buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteToShowDTO result = new UtenteToShowDTO(utenteModel.getId(), utenteModel.getUsername(),
				utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getStato(), utenteModel.getDateCreated());

		if (!utenteModel.getRuoli().isEmpty()) {
			for (Ruolo roleItem : utenteModel.getRuoli()) {
				System.out.println(roleItem.getId() + roleItem.getCodice());
				RuoloDTO temp = RuoloDTO.buildRuoloDTOFromModel(roleItem);
				System.out.println(temp.getId() + temp.getCodice());
				result.addToRuoli(temp);
			}
		}
		System.out.println(result.ruoli.size());
		return result;
	}

	private void addToRuoli(RuoloDTO ruoloDTO) {
		this.ruoli.add(ruoloDTO);
	}

}
