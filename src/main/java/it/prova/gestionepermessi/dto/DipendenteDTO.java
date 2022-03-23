package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Sesso;

public class DipendenteDTO {

	private Long id;

	@NotBlank(message = "{nomeDip.notblank}")
	private String nome;
	@NotBlank(message = "{cognomeDip.notblank}")
	private String cognome;
	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 16, max = 16, message = "Il valore inserito '${validatedValue}' deve essere lungo {min} caratteri")
	private String codFis;
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String email;
	@NotNull(message = "{dataNascita.notnull}")
	private Date dataNascita;
	@NotNull(message = "{dataAssunzione.notnull}")
	private Date dataAssunzione;

	private Date dataDimissioni;

	@NotNull(message = "{sesso.notblank}")
	private Sesso sesso;

	public DipendenteDTO(Long id, String nome, String cognome, String codFis, String email, Date dataNascita,
			Date dataAssunzione, Sesso sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public DipendenteDTO(String nome, String cognome, String codFis, String email, Date dataNascita,
			Date dataAssunzione, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.sesso = sesso;
	}

	public DipendenteDTO(Long id, String nome, String cognome, String codFis, String email, Date dataNascita,
			Date dataAssunzione, Date dataDimissioni, Sesso sesso) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codFis = codFis;
		this.email = email;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
		this.dataDimissioni = dataDimissioni;
		this.sesso = sesso;
	}

	public DipendenteDTO() {
		super();
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

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataDimissioni() {
		return dataDimissioni;
	}

	public void setDataDimissioni(Date dataDimissioni) {
		this.dataDimissioni = dataDimissioni;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public Dipendente buildDipendenteModel() {
		return new Dipendente(this.id, this.nome, this.cognome, this.codFis, this.email, this.dataNascita,
				this.dataAssunzione, this.dataDimissioni, this.sesso);
	}

	public static DipendenteDTO buildDipendenteDTOFromModel(Dipendente dipendenteModel) {
		return new DipendenteDTO(dipendenteModel.getId(), dipendenteModel.getNome(), dipendenteModel.getCognome(),
				dipendenteModel.getCodFis(), dipendenteModel.getEmail(), dipendenteModel.getDataNascita(),
				dipendenteModel.getDataAssunzione(), dipendenteModel.getDataDimissioni(), dipendenteModel.getSesso());
	}

	public static List<DipendenteDTO> createContribuenteDTOListFromModelList(List<Dipendente> modelListInput) {
		return modelListInput.stream().map(dipendenteEntity -> {
			return DipendenteDTO.buildDipendenteDTOFromModel(dipendenteEntity);
		}).collect(Collectors.toList());
	}
}