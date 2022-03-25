package it.prova.gestionepermessi.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.gestionepermessi.model.Messaggio;

public class MessaggioDTO {

	private Long id;

	private String testo;

	private String oggetto;

	private Boolean letto;

	private RichiestaPermessoDTO rp;

	public MessaggioDTO() {
		super();
	}

	public MessaggioDTO(Long id, String testo, String oggetto, Boolean letto) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
	}

	public MessaggioDTO(Long id, String testo, String oggetto, Boolean letto, RichiestaPermessoDTO rp) {
		super();
		this.id = id;
		this.testo = testo;
		this.oggetto = oggetto;
		this.letto = letto;
		this.rp = rp;
	}

	public RichiestaPermessoDTO getRp() {
		return rp;
	}

	public void setRp(RichiestaPermessoDTO rp) {
		this.rp = rp;
	}

	public Boolean getLetto() {
		return letto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public Boolean isLetto() {
		return letto;
	}

	public void setLetto(Boolean letto) {
		this.letto = letto;
	}

	public Messaggio buildMessaggioModelForSearch() {
		return new Messaggio(this.id, this.testo, this.oggetto, this.letto);
	}

	public static MessaggioDTO buildMessaggioDTOFromModel(Messaggio messaggioModel) {
		return new MessaggioDTO(messaggioModel.getId(), messaggioModel.getTesto(), messaggioModel.getOggetto(),
				messaggioModel.isLetto(),
				RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(messaggioModel.getRichiesta()));
	}

	public static List<MessaggioDTO> createMessaggioDTOListFromModelList(List<Messaggio> modelListInput) {
		return modelListInput.stream().map(messaggioEntity -> {
			return MessaggioDTO.buildMessaggioDTOFromModel(messaggioEntity);
		}).collect(Collectors.toList());
	}

}
