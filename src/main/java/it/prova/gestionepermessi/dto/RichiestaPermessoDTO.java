package it.prova.gestionepermessi.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.validation.ValidationForInsertUpdateRichiestaPermesso;

public class RichiestaPermessoDTO {

	private Long id;

	private String codiceCertificato;

	@NotNull(message = "{dataInizio.notnull}", groups = (ValidationForInsertUpdateRichiestaPermesso.class))
	private Date dataInizio;

	private Date dataFine;
	@NotNull(message = "{tipoPermesso.notblank}", groups = (ValidationForInsertUpdateRichiestaPermesso.class))
	private TipoPermesso tipoPermesso;

	private Boolean approvato;

	private String note;

	@NotNull(message = "{dipendente.notnull}")
	private DipendenteDTO dipendente;

	public Boolean getApprovato() {
		return approvato;
	}

	public void setApprovato(Boolean approvato) {
		this.approvato = approvato;
	}

	public DipendenteDTO getDipendente() {
		return dipendente;
	}

	public void setDipendente(DipendenteDTO dipendente) {
		this.dipendente = dipendente;
	}

	public RichiestaPermessoDTO() {
		super();
	}

	public RichiestaPermessoDTO(Long id) {
		super();
		this.id = id;
	}

	public RichiestaPermessoDTO(Long id, String codiceCertificato, Date dataInizio, Date dataFine,
			TipoPermesso tipoPermesso, String note) {
		super();
		this.id = id;
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;
	}

	public RichiestaPermessoDTO(Long id, String codiceCertificato, Date dataInizio, Date dataFine,
			TipoPermesso tipoPermesso, String note, Boolean approvato) {
		super();
		this.id = id;
		this.codiceCertificato = codiceCertificato;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoPermesso = tipoPermesso;
		this.note = note;
		this.approvato = approvato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public TipoPermesso getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(TipoPermesso tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Boolean isApprovato() {
		return approvato;
	}

	public void setApprovato(boolean approvato) {
		this.approvato = approvato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public RichiestaPermesso buildRichiestaModel() {
		return new RichiestaPermesso(this.id, this.codiceCertificato, this.dataInizio, this.dataFine, this.tipoPermesso,
				this.note);
	}

	public RichiestaPermesso buildRichiestaModelForSearch() {
		return new RichiestaPermesso(this.id, this.codiceCertificato, this.dataInizio, this.dataFine, this.tipoPermesso,
				this.note, this.approvato, this.dipendente.buildDipendenteModelForSearch());
	}

	public RichiestaPermesso buildRichiestaModelForInsert() {
		return new RichiestaPermesso(this.id, this.codiceCertificato, this.dataInizio, this.dataFine, this.tipoPermesso,
				this.note, this.dipendente.buildDipendenteModelForSearch());
	}

	public RichiestaPermesso buildRichiestaModelForUpdate() {
		return new RichiestaPermesso(this.id, this.codiceCertificato, this.dataInizio, this.dataFine, this.tipoPermesso,
				this.note);
	}

	public static RichiestaPermessoDTO buildRichiestaDTOForMessageSearch(Long idDipendente) {
		RichiestaPermessoDTO result = new RichiestaPermessoDTO();
		result.setDipendente(new DipendenteDTO(idDipendente));

		return result;
	}

	public static RichiestaPermessoDTO buildRichiestaPermessoDTOFromModel(RichiestaPermesso richiestaModel) {
		return new RichiestaPermessoDTO(richiestaModel.getId(), richiestaModel.getCodiceCertificato(),
				richiestaModel.getDataInizio(), richiestaModel.getDataFine(), richiestaModel.getTipoPermesso(),
				richiestaModel.getNote(), richiestaModel.isApprovato());
	}

	public static List<RichiestaPermessoDTO> createRichiestaDTOListFromModelList(
			List<RichiestaPermesso> modelListInput) {
		return modelListInput.stream().map(richiestaEntity -> {
			return RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiestaEntity);
		}).collect(Collectors.toList());
	}

	public static List<RichiestaPermessoDTO> createRichiesteDTOListFromModelList(
			List<RichiestaPermesso> modelListInput) {
		return modelListInput.stream().map(richiesteEntity -> {
			return RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(richiesteEntity);
		}).collect(Collectors.toList());
	}

	public boolean isNotStarted() {
		return (new Date()).before(dataInizio);
	}

}
